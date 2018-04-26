package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.cms.modules.services.CmsChannelService;
import com.aebiz.app.cms.modules.services.CmsContentService;
import com.aebiz.app.cms.modules.services.CmsTopicService;
import com.aebiz.app.cms.modules.services.StaticPageService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.app.web.commons.utils.FtpUtil;
import com.aebiz.app.web.commons.utils.LoginMemberUtil;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.page.Paginable;
import com.aebiz.baseframework.page.SimplePage;
import com.aebiz.baseframework.rabbit.RabbitProducer;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.MoneyUtil;
import com.aebiz.commons.utils.SpringUtil;
import com.aebiz.app.cms.modules.models.*;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.ResourceLoader;
import org.beetl.core.Template;
import org.beetl.core.resource.FileResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;


@Service
public class StaticPageServiceImpl implements StaticPageService {

    private static final Log log = Logs.get();
    private Dao dao;
    @Autowired
    private BeetlGroupUtilConfiguration beetlConfig;
    @Autowired
    private PropertiesProxy config;
    @Autowired
    private CmsContentService cmsContentService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsTopicService cmsTopicService;
    @Autowired
    private RabbitProducer rabbitProducer;

    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        this.dao = dao;
    }

    @Override
    public synchronized boolean geIndex(Cms_site site, Map<String, Object> data) throws IOException {
        long time = System.currentTimeMillis();
        // 模板文件目录
        String tplRoot = (Globals.APP_ROOT + "/WEB-INF/cmstemplate/"
                + site.getSite_path() + "/").replaceAll("\\\\", "/");
        log.debug("tplRoot:" + tplRoot);
        // 站点根目录
        String basePath = config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path();
        basePath = basePath.replaceAll("\\\\", "/");
        // 输出文件路径
        String filePath = basePath + "/index.html";
        log.debug("filePath:" + filePath);
        File f = new File(filePath);
        // 获取目录
        File parent = f.getParentFile();
        // 目录不存在则创建
        if (!parent.exists()) {
            parent.mkdirs();
        }
        Writer out = null;
        try {

            out = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
            FileResourceLoader resLoader = new FileResourceLoader(tplRoot);

            GroupTemplate gt = getGroupTemplate(beetlConfig.getGroupTemplate().getConf());
            beetlConfig.config(gt);
            gt.setResourceLoader(resLoader);
            Template template = gt.getTemplate(site.getSite_css() + "/index/首页.html");

            if (data == null) {
                data = new HashMap<String, Object>();
            }
            data.put("baseRes", "res/" + site.getSite_css());
            data.put("base", "");
            data.put("site", site);
            template.binding(data);
            template.renderTo(out);
            // 上传到ftp服务器去
            uploadToFtp(template.render(), site.getSite_path(), f.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        time = System.currentTimeMillis() - time;
        log.info("create index page in " + time + " ms");
        log.info("filePath : " + f.getAbsolutePath());

        return true;
    }

    @Override
    public boolean geIndex(Cms_site site) throws IOException {
        return geIndex(site, null);
    }

    @Override
    public int geIndexs(List<Cms_site> sites) throws IOException {
        int count = 0;
        for (Cms_site site : sites) {
            if (geIndex(site, null)) count++;
        }
        return count;
    }

    @Override
    public int geIndexs(List<Cms_site> sites, Map<String, Object> data) throws IOException {
        int count = 0;
        for (Cms_site site : sites) {
            if (geIndex(site, data)) count++;
        }
        return count;
    }

    @Override
    public synchronized boolean delIndex(Cms_site site) throws IOException {
        String filePath = config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path() + "/index.html";
        File file = new File(filePath);
        delFileFromFtp(site.getSite_path(), file.getName());
        return file.delete();
    }

    public int channelPages(String siteId, String channelId, boolean containChild, boolean isStatic) throws IOException {
        return channelPages(siteId, channelId, containChild, isStatic, null);
    }

    @Override
    public synchronized int channelPages(String siteId, String channelId, boolean containChild, boolean isStatic, Map data) throws IOException {
        long time = System.currentTimeMillis();
        int count = 0;
        SimpleCriteria cri = Cnd.cri();
        List<Cms_channel> list = new ArrayList<>();
        // 拼装条件
        if (siteId != null && !"".equals(siteId)) {
            cri.where().and("site_id", "=", siteId);
        }

        if (isStatic) {
            cri.where().and("is_static", "=", 0);
        }

        // 是否包含子栏目
        if (containChild) {
            if (channelId != null && !"".equals(channelId)) {
                // 是
                Cms_channel rootParent = dao.fetch(Cms_channel.class, channelId);
                list.add(rootParent);

                list.addAll(getChildren("SELECT * FROM cms_channel " + cri.toString(), rootParent.getId(), null));
            } else {
                // 全部
                list = dao.query(Cms_channel.class, cri);
            }
        } else {
            if (channelId != null && !"".equals(channelId)) {
                // 否
                cri.where().and("id", "=", channelId);
                cri.getOrderBy().asc("location");
                list = dao.query(Cms_channel.class, cri);
            }
        }

        // 生成页面
        for (int j = 0; j < list.size(); j++) {
            Cms_channel c = list.get(j);
            boolean flag = channelPage(c, isStatic, data);
            if (flag) {
                //静态化标识改为已生成
                dao.update(Cms_channel.class, Chain.make("is_static", 1), Cnd.where("id", "=", c.getId()).and("site_id", "=", c.getSite_id()));
                count++;
            }
        }
        time = System.currentTimeMillis() - time;
        log.info("create channels staticPage in " + time + " ms");
        return count;
    }

    @Override
    public synchronized boolean channelPage(Cms_channel c, boolean isStatic, Map<String, Object> data) throws IOException {
        long time = System.currentTimeMillis();
        String real, filename;
        File f, parent;
        Writer out = null;
        Template tpl;

        Cms_site site = dao.fetch(Cms_site.class, c.getSite_id());
        String tpl_content;
        int contents, totalPage, quantity;
        // 生成页面
        // 不生成的情况,外部链接、isStatic为true
        if (!StringUtils.isBlank(c.getLinkurl()) || (isStatic && c.getIs_static() == 1)) {
            return false;
        }
        if (data == null) {
            data = new HashMap();
        }
        tpl_content = c.getTpl_channel();
        // 没有对应的模板 获取父栏目模板
        if ("".equals(StringUtils.trimToEmpty(tpl_content))) {
            String pcid = c.getParentId();
            // 获取父级栏目
            while (true) {
                if (pcid != null && !"".equals(pcid)) {
                    Cms_channel parentChannel = dao.fetch(Cms_channel.class, Cnd.where("id", "=", pcid)
                            .and("site_id", "=", c.getSite_id()));
                    if (parentChannel != null) {
                        tpl_content = parentChannel.getTpl_channel();
                        pcid = parentChannel.getParentId();
                        if ("".equals(StringUtil.null2String(tpl_content))) {
                            continue;
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        // 还是没有模板，不生成
        if ("".equals(StringUtil.null2String(tpl_content))) {
            return false;
        }

        String path = "../";
        String[] ps = StringUtils.split(c.getPath(), "/");
        for (int i = 1; i < ps.length; i++) {
            path += "../";
        }
        // 把当前这个channel传到模板去
        data.put("current", c);
        // site
        data.put("site", site);
        // 资源路径
        data.put("baseRes", path + "res/" + site.getSite_css());
        // 站点目录
        data.put("base", path);
        //根据内容分页
        Sql sql = Sqls.create("SELECT COUNT(*) FROM CMS_CONTENT WHERE SITE_ID='" + c.getSite_id() + "' AND CHANNEL_ID = '" + c.getId() + "'");
        contents = cmsContentService.count(sql);

        if (contents < 1 || c.isHasChildren()) {
            totalPage = 1;
        } else {
            sql = Sqls.create("SELECT COUNT(*) FROM CMS_CONTENT WHERE SITE_ID='" + c.getSite_id() + "'AND status = 3 AND CHANNEL_ID = '" + c.getId() + "'");
            quantity = cmsContentService.count(sql);
            if (quantity <= 0) {
                totalPage = 1;
            } else {
                int pageSize = Integer.parseInt(c.getPagesize());
                totalPage = (quantity - 1) / pageSize + 1;
            }
        }

        real = (config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path() + "/" + c.getPath() + "/").replaceAll("\\\\", "/");
        // 模板文件目录
        String tplRoot = (Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path() + "/").replaceAll("\\\\", "/");
        FileResourceLoader resLoader = new FileResourceLoader(tplRoot);

        GroupTemplate gt = getGroupTemplate(beetlConfig.getGroupTemplate().getConf());
        beetlConfig.config(gt);
        gt.setResourceLoader(resLoader);
        tpl = gt.getTemplate(tpl_content);

        for (int i = 1; i <= totalPage; i++) {
            if (totalPage == 1) {
                filename = "index.html";
            } else {
                filename = "index_" + i + ".html";
                if (i == 1) filename = "index.html";
            }
            f = new File(real + "/" + filename);
            parent = f.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            // 页码
            data.put("pageNum", i);
            data.put("totalPage", totalPage);
            try {
                out = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
                tpl.binding(data);
                tpl.renderTo(out);
                // 上传到ftp服务器去
                uploadToFtp(tpl.render(), site.getSite_path() + "/" + c.getPath() + "/", f.getName());
                log.info("create static file:" + f.getAbsolutePath());
            } finally {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            }
        }
        //静态化标识改为已生成
        dao.update(Cms_channel.class, Chain.make("is_static", 1), Cnd.where("id", "=", c.getId()).and("site_id", "=", c.getSite_id()));
        time = System.currentTimeMillis() - time;
        log.info("create channels staticPage in " + time + " ms");
        return true;
    }

    @Override
    public synchronized void delChannelPage(Cms_channel c) throws IOException {
        long time = System.currentTimeMillis();
        String real, filename;
        Cms_site site = dao.fetch(Cms_site.class, c.getSite_id());
        int contents, totalPage, quantity;

        //根据内容分页
        Sql sql = Sqls.create("SELECT COUNT(*) FROM CMS_CONTENT WHERE SITE_ID='" + c.getSite_id() + "' AND CHANNEL_ID = '" + c.getId() + "'");
        contents = cmsContentService.count(sql);
        if (contents < 1 || c.isHasChildren()) {
            totalPage = 1;
        } else {
            sql = Sqls.create("SELECT COUNT(*) FROM CMS_CONTENT WHERE SITE_ID='" + c.getSite_id() + "'AND status = 3 AND CHANNEL_ID = '" + c.getId() + "'");
            quantity = cmsContentService.count(sql);
            if (quantity <= 0) {
                totalPage = 1;
            } else {
                int pageSize = Integer.parseInt(c.getPagesize());
                totalPage = (quantity - 1) / pageSize + 1;
            }
        }

        File f;
        real = (config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path() + "/" + c.getPath() + "/").replaceAll("\\\\", "/");
        for (int i = 1; i <= totalPage; i++) {
            if (totalPage == 1) {
                filename = "index.html";
            } else {
                filename = "index_" + i + ".html";
                if (i == 1) filename = "index.html";
            }
            f = new File(real + "/" + filename);
            if (f.exists()) {
                f.delete();
                // 删除ftp的文件
                delFileFromFtp(site.getSite_path() + "/" + c.getPath() + "/", f.getName());
            }
        }
        time = System.currentTimeMillis() - time;
        log.info("delete channels staticPage in " + time + " ms");
    }

    @Override
    public synchronized boolean contentPage(Cms_content content, boolean isStatic, Map data) {
        Cms_channel channel = dao.fetch(Cms_channel.class, Cnd.where("id", "=", content.getChannel_id()).and("site_id", "=", content.getSite_id()));
        Cms_content_txt contentTxt = dao.fetch(Cms_content_txt.class, Cnd.where("content_id", "=", content.getId()));
        // 如果是外部链接，则不生成
        if (!"".equals(StringUtil.null2String(channel.getLinkurl()))) {
            return false;
        }
        // 如果不需要生成静态页面，则不生成
        if (isStatic && content.getIs_static() == 1) {
            return false;
        }
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        Template tpl;
        Writer out = null;
        //获取内容模板，如内容未设置则取栏目设置，栏目未设置则取上级栏目
        String tpl_content = content.getTpl_content();
        if ("".equals(StringUtil.null2String(tpl_content))) {
            Cms_channel_model cmodel = dao.fetch(Cms_channel_model.class, Cnd.where("site_id", "=", content.getSite_id()).and("channel_id", "=", content.getChannel_id()).and("model_id", "=", content.getModel_id()));
            if (cmodel != null) tpl_content = cmodel.getTpl_content();
            if ("".equals(StringUtil.null2String(tpl_content))) {

                String pcid = StringUtils.trimToEmpty(channel.getParentId());
                while (true) {
                    if (pcid != null && !"".equals(pcid)) {
                        Cms_channel parentChannel = dao.fetch(Cms_channel.class, Cnd.where("id", "=", pcid)
                                .and("site_id", "=", content.getSite_id()));
                        if (parentChannel != null) {
                            pcid = parentChannel.getParentId();
                            cmodel = dao.fetch(Cms_channel_model.class, Cnd.where("site_id", "=", content.getSite_id())
                                    .and("channel_id", "=", parentChannel.getId())
                                    .and("model_id", "=", content.getModel_id()));
                            if (cmodel != null) tpl_content = cmodel.getTpl_content();
                            if ("".equals(StringUtil.null2String(tpl_content))) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }

            }
        }
        // 还是没有模板，直接返回错误
        if ("".equals(StringUtil.null2String(tpl_content))) {
            return false;
        }
        Cms_site site = dao.fetch(Cms_site.class, content.getSite_id());

        String path = "../";
        String[] ps = StringUtils.split(channel.getPath(), "/");
        for (int i = 1; i < ps.length; i++) {
            path += "../";
        }
        data.put("baseRes", path + "res/" + site.getSite_css());
        data.put("base", path);
        data.put("site", site);
        String tplRoot = (Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path() + "/").replaceAll("\\\\", "/");
        FileResourceLoader resLoader = new FileResourceLoader(tplRoot);

        GroupTemplate gt = getGroupTemplate(beetlConfig.getGroupTemplate().getConf());
        beetlConfig.config(gt);
        gt.setResourceLoader(resLoader);
        tpl_content = tpl_content.replaceAll(tplRoot.replaceAll("//","/"), "/");
        tpl = gt.getTemplate(tpl_content);
        Sql sql = Sqls.create("SELECT A.* FROM CMS_TAG A ,CMS_CONTENT_TAG B WHERE A.id=B.tag_id AND B.CONTENT_ID='" + content.getId() + "'");
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Cms_tag.class));
        dao.execute(sql);
        List<Cms_tag> taglist = sql.getList(Cms_tag.class);
        String tag = "";
        for (int i = 0; i < taglist.size(); i++) {
            if (i == taglist.size() - 1) {
                tag += taglist.get(i).getName();
            } else tag += taglist.get(i).getName() + ",";
        }

        data.put("picList", dao.query(Cms_content_picture.class, Cnd.where("content_id", "=", content.getId())));
        data.put("fileList", dao.query(Cms_content_file.class, Cnd.where("content_id", "=", content.getId())));
        data.put("content", content);
        data.put("content_txt", contentTxt);
        data.put("content_tag", tag);
        data.put("current", channel);
        int totalPage = getTxtCount(content);
        File file, parent;
        String real, temp;
        for (int pageNo = 1; pageNo <= totalPage; pageNo++) {
            String txt = getTxtByNo(content, pageNo);

            Paginable pagination = new SimplePage(pageNo, 1, totalPage + 1);
            data.put("pagination", pagination);
            data.put("txt", txt);
            tpl.binding(data);
            if (totalPage == 1) {
                temp = content.getId() + ".html";
            } else {
                temp = content.getId() + "_" + pageNo + ".html";
                if (pageNo == 1) temp = content.getId() + ".html";
            }
            real = (config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path() + "/" + channel.getPath() + "/" + temp).replaceAll("\\\\", "/");
            file = new File(real);
            if (pageNo == 1) {
                parent = file.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
            }
            try {
                out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
                tpl.renderTo(out);
                // 上传到ftp服务器去
                //uploadToFtp(tpl.render(), site.getSite_path() + "/" + channel.getPath() + "/" + temp, file.getName());
                log.info("create static file:" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (out != null) {
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        dao.update(Cms_content.class, Chain.make("is_static", 1), Cnd.where("id", "=", content.getId()).and("site_id", "=", content.getSite_id()));
        return true;
    }

    @Override
    public String contentPage(Cms_content content, Map data) {
        Cms_channel channel = dao.fetch(Cms_channel.class, Cnd.where("id", "=", content.getChannel_id()).and("site_id", "=", content.getSite_id()));
        Cms_content_txt contentTxt = dao.fetch(Cms_content_txt.class, Cnd.where("content_id", "=", content.getId()));
        // 如果是外部链接，则不生成
        if (!"".equals(StringUtil.null2String(channel.getLinkurl()))) {
            dao.update(Cms_content.class, Chain.make("is_static", 1), Cnd.where("id", "=", content.getId()).and("site_id", "=", content.getSite_id()));
            return "0";
        }

        if (data == null) {
            data = new HashMap<String, Object>();
        }
        Template tpl;
        Writer out = null;
        //获取内容模板，如内容未设置则取栏目设置，栏目未设置则取上级栏目
        String tpl_content = content.getTpl_content();
        if ("".equals(StringUtil.null2String(tpl_content))) {
            Cms_channel_model cmodel = dao.fetch(Cms_channel_model.class, Cnd.where("site_id", "=", content.getSite_id()).and("channel_id", "=", content.getChannel_id()).and("model_id", "=", content.getModel_id()));
            if (cmodel != null) tpl_content = cmodel.getTpl_content();
            if ("".equals(StringUtil.null2String(tpl_content))) {
                String pcid = StringUtils.trimToEmpty(channel.getParentId());
                while (true) {
                    if (pcid != null && !"".equals(pcid)) {
                        Cms_channel parentChannel = dao.fetch(Cms_channel.class, Cnd.where("id", "=", pcid)
                                .and("site_id", "=", content.getSite_id()));
                        if (parentChannel != null) {
                            pcid = parentChannel.getParentId();
                            cmodel = dao.fetch(Cms_channel_model.class, Cnd.where("site_id", "=", content.getSite_id())
                                    .and("channel_id", "=", parentChannel.getId())
                                    .and("model_id", "=", content.getModel_id()));
                            if (cmodel != null) tpl_content = cmodel.getTpl_content();
                            if ("".equals(StringUtil.null2String(tpl_content))) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }

            }
        }
        // 还是没有模板
        if ("".equals(StringUtil.null2String(tpl_content))) {
            return "cms.staticpage.error.notpl";
        }
        Cms_site site = dao.fetch(Cms_site.class, content.getSite_id());

        String path = "../";
        String[] ps = StringUtils.split(channel.getPath(), "/");
        for (int i = 1; i < ps.length; i++) {
            path += "../";
        }
        data.put("site", site);
        data.put("baseRes", path + "res/" + site.getSite_css());
        data.put("base", path);
        // 模板文件目录
        String tplRoot = (Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path() + "/").replaceAll("\\\\", "/");
        FileResourceLoader resLoader = new FileResourceLoader(tplRoot);

        GroupTemplate gt = getGroupTemplate(beetlConfig.getGroupTemplate().getConf());
        beetlConfig.config(gt);
        gt.setResourceLoader(resLoader);
        tpl = gt.getTemplate(tpl_content);
        Sql sql = Sqls.create("SELECT A.* FROM CMS_TAG A ,CMS_CONTENT_TAG B WHERE A.id=B.tag_id AND B.CONTENT_ID='" + content.getId() + "'");
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Cms_tag.class));
        dao.execute(sql);
        List<Cms_tag> taglist = sql.getList(Cms_tag.class);
        String tag = "";
        for (int i = 0; i < taglist.size(); i++) {
            if (i == taglist.size() - 1) {
                tag += taglist.get(i).getName();
            } else tag += taglist.get(i).getName() + ",";
        }


        data.put("picList", dao.query(Cms_content_picture.class, Cnd.where("content_id", "=", content.getId())));
        data.put("fileList", dao.query(Cms_content_file.class, Cnd.where("content_id", "=", content.getId())));
        data.put("content", content);
        data.put("content_txt", contentTxt);
        data.put("content_tag", tag);
        data.put("channel", channel);
        int totalPage = getTxtCount(content);
        File file, parent;
        String real, temp;
        for (int pageNo = 1; pageNo <= totalPage; pageNo++) {
            String txt = getTxtByNo(content, pageNo);
            Paginable pagination = new SimplePage(pageNo, 1, totalPage + 1);
            data.put("pagination", pagination);
            data.put("txt", txt);
            tpl.binding(data);
            if (totalPage == 1) {
                temp = content.getId() + ".html";
            } else {
                temp = content.getId() + "_" + pageNo + ".html";
                if (pageNo == 1) temp = content.getId() + ".html";
            }
            real = (config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path() + "/" + channel.getPath() + "/" + temp).replaceAll("\\\\", "/");
            file = new File(real);
            if (pageNo == 1) {
                parent = file.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
            }
            try {
                out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
                tpl.renderTo(out);
                // 上传到ftp服务器去
                uploadToFtp(tpl.render(), site.getSite_path() + "/" + channel.getPath() + "/" + temp, file.getName());
                log.info("create static file:" + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                return "cms.staticpage.error";
            } finally {
                if (out != null) {
                    try {
                        out.flush();
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
        dao.update(Cms_content.class, Chain.make("is_static", 1), Cnd.where("id", "=", content.getId()).and("site_id", "=", content.getSite_id()));

        //mq文章同步es jxx
        // if (SpringUtil.isRabbitEnabled()) {
        //     String exchange = "topicExchange";
        //     String routeKey = "topic.es.cmsContent";
        //     RabbitMessage msg = new RabbitMessage(exchange, routeKey, NutMap.NEW().addv("contentId",content.getId()).addv("action","update"));
        //     rabbitProducer.sendMessage(msg);
        // }
/*        //加入搜索引擎job
        Cms_content_estemp estemp = new Cms_content_estemp();
        estemp.setAction("update");
        estemp.setContentId(content.getId());
        cmsContentEstempService.insert(estemp);*/
        return "0";
    }

    @Override
    public String contentPageHmtl(Cms_content content, Map data) {
        Cms_channel channel = dao.fetch(Cms_channel.class, Cnd.where("id", "=", content.getChannel_id()).and("site_id", "=", content.getSite_id()));
        Cms_content_txt contentTxt = dao.fetch(Cms_content_txt.class, Cnd.where("content_id", "=", content.getId()));

        if (data == null) {
            data = new HashMap<String, Object>();
        }
        Template tpl;
        //获取内容模板，如内容未设置则取栏目设置，栏目未设置则取上级栏目
        String tpl_content = content.getTpl_content();
        if ("".equals(StringUtil.null2String(tpl_content))) {
            Cms_channel_model cmodel = dao.fetch(Cms_channel_model.class, Cnd.where("site_id", "=", content.getSite_id()).and("channel_id", "=", content.getChannel_id()).and("model_id", "=", content.getModel_id()));
            if (cmodel != null) tpl_content = cmodel.getTpl_content();
            if ("".equals(StringUtil.null2String(tpl_content))) {
                String pcid = StringUtils.trimToEmpty(channel.getParentId());
                while (true) {
                    if (pcid != null && !"".equals(pcid)) {
                        Cms_channel parentChannel = dao.fetch(Cms_channel.class, Cnd.where("id", "=", pcid)
                                .and("site_id", "=", content.getSite_id()));
                        if (parentChannel != null) {
                            pcid = parentChannel.getParentId();
                            cmodel = dao.fetch(Cms_channel_model.class, Cnd.where("site_id", "=", content.getSite_id())
                                    .and("channel_id", "=", parentChannel.getId())
                                    .and("model_id", "=", content.getModel_id()));
                            if (cmodel != null) tpl_content = cmodel.getTpl_content();
                            if ("".equals(StringUtil.null2String(tpl_content))) {
                                continue;
                            } else {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }

            }
        }
        // 还是没有模板，直接返回文章内容
        if ("".equals(StringUtil.null2String(tpl_content))) {
            return contentTxt.getTxt();
        }
        Cms_site site = dao.fetch(Cms_site.class, content.getSite_id());

        String path = "../";
        String[] ps = StringUtils.split(channel.getPath(), "/");
        for (int i = 1; i < ps.length; i++) {
            path += "../";
        }
        data.put("site", site);
        data.put("baseRes", path + "res/" + site.getSite_css());
        data.put("base", path);
        // 模板文件目录
        String tplRoot = (Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path() + "/").replaceAll("\\\\", "/");
        FileResourceLoader resLoader = new FileResourceLoader(tplRoot);

        GroupTemplate gt = getGroupTemplate(beetlConfig.getGroupTemplate().getConf());
        beetlConfig.config(gt);
        ResourceLoader old = gt.getResourceLoader();
        gt.setResourceLoader(resLoader);
        tpl = gt.getTemplate(tpl_content);
        Sql sql = Sqls.create("SELECT A.* FROM CMS_TAG A ,CMS_CONTENT_TAG B WHERE A.id=B.tag_id AND B.CONTENT_ID='" + content.getId() + "'");
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Cms_tag.class));
        dao.execute(sql);
        List<Cms_tag> taglist = sql.getList(Cms_tag.class);
        String tag = "";
        for (int i = 0; i < taglist.size(); i++) {
            if (i == taglist.size() - 1) {
                tag += taglist.get(i).getName();
            } else tag += taglist.get(i).getName() + ",";
        }

        data.put("picList", dao.query(Cms_content_picture.class, Cnd.where("content_id", "=", content.getId())));
        data.put("fileList", dao.query(Cms_content_file.class, Cnd.where("content_id", "=", content.getId())));
        data.put("content", content);
        data.put("content_txt", contentTxt);
        data.put("content_tag", tag);
        data.put("channel", channel);
        tpl.binding(data);
        String html = tpl.render();
        gt.setResourceLoader(old);
        beetlConfig.setResourceLoader(old);
        return html;
    }

    @Override
    public String contentPageHmtl(Cms_content content) {
        String html = contentPageHmtl(content, addUtils());
        return html;
    }

    @Override
    public int contentPages(String siteId, String channelId, Date start, Boolean isStatic, Map data) throws IOException {
        String s = "SELECT A.* FROM cms_content A WHERE A.STATUS =3 ";
        String[] ids = null;
        if (siteId != null && !"".equals(siteId)) {
            s += " AND A.SITE_ID='" + siteId + "'";
        }
        if (channelId != null && !"".equals(channelId.trim())) {
            ids = StringUtils.split(channelId, ",");
            s += " AND A.CHANNEL_ID in(@channelId)";
        }
        if (start != null) {
            s += " AND A.ADD_TIME >='" + start + "'";
        }
        if (isStatic != null && isStatic) {
            s += " AND A.IS_STATIC =0";
        }
        s += " ORDER BY A.id DESC";
        int count = 0;
        if (data == null) {
            data = new HashMap<String, Object>();
        }

        Sql sql = Sqls.create(s);
        if (ids != null && ids.length > 0) {
            sql.setParam("channelId", ids);
        }
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Cms_content.class));

        dao.execute(sql);
        List<Cms_content> contents = sql.getList(Cms_content.class);

        for (int j = 0; j < contents.size(); j++) {
            Cms_content c = contents.get(j);
            if (contentPage(c, isStatic, data)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String contentPages(List<Cms_content> contents) {
        String re = "0";

        for (Cms_content c : contents) {

            re = contentPage(c, addUtils());
            if ("0".equals(re)) {
                continue;
            } else {
                break;
            }
        }
        return re;
    }

    @Override
    public void deleteContent(Cms_content c) {
        String real, temp;
        File file;
        Cms_site site = dao.fetch(Cms_site.class, c.getSite_id());
        Cms_channel channel = dao.fetch(Cms_channel.class, Cnd.where("id", "=", c.getChannel_id()).and("site_id", "=", c.getSite_id()));
        int totalPage = getTxtCount(c);
        for (int pageNo = 1; pageNo <= totalPage + 1; pageNo++) {
            if (totalPage == 1) {
                temp = c.getId() + ".html";
            } else {
                temp = c.getId() + "_" + pageNo + ".html";
                if (pageNo == 1) temp = c.getId() + ".html";
            }
            real = (config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path() + "/" + channel.getPath() + "/" + temp).replaceAll("\\\\", "/");
            file = new File(real);
            if (file.exists()) {
                file.delete();
                // 上传到ftp服务器去
                delFileFromFtp(site.getSite_path() + "/" + channel.getPath() + "/" + temp, file.getName());
            }
        }
    }

    @Override
    public synchronized boolean topicPage(Cms_topic topic, Map<String, Object> data) throws IOException {
        if (topic == null) {
            return false;
        }
        long time = System.currentTimeMillis();
        String real;
        File f, parent;
        Writer out = null;
        Template tpl;
        Cms_site site = dao.fetch(Cms_site.class, topic.getSite_id());
        List<Cms_channel> channelList = new ArrayList<>();
        if (topic.getChannelIds() != null) {
            channelList = cmsChannelService.query(Cnd.where("id", "in", StringUtils.split(topic.getChannelIds(), ",")));
        }
        String tpl_content;
        if (data == null) {
            data = new HashMap();
        }
        // 生成页面
        tpl_content = topic.getTplContent();

        // 没有对应的模板,不生成
        if ("".equals(StringUtils.trimToEmpty(tpl_content))) {
            return false;
        }
        // 把这个topic传到模板去
        data.put("site", site);
        data.put("topic", topic);
        data.put("channels", channelList);
        data.put("baseRes", "../res/" + site.getSite_css());
        data.put("base", "../");

        real = (config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path() + "/topic/").replaceAll("\\\\", "/");
        // 模板文件目录
        String tplRoot = (Globals.APP_ROOT + "/WEB-INF/cmstemplate/" + site.getSite_path() + "/").replaceAll("\\\\", "/");
        FileResourceLoader resLoader = new FileResourceLoader(tplRoot.replaceAll("\\\\", "/"));
        //根据内容分页
        Sql sql = Sqls.create("SELECT COUNT(*) FROM CMS_CONTENT WHERE SITE_ID='" + topic.getSite_id() + "' AND TOPIC_ID = '" + topic.getId() + "'");
        int contents = cmsContentService.count(sql);
        int totalPage;
        if (contents < 1) {
            totalPage = 1;
        } else {
            sql = Sqls.create("SELECT COUNT(*) FROM CMS_CONTENT WHERE SITE_ID='" + topic.getSite_id() + "'AND status = 3 AND TOPIC_ID = '" + topic.getId() + "'");
            int quantity = cmsContentService.count(sql);
            if (quantity <= 0) {
                totalPage = 1;
            } else {
                int pageSize = StringUtil.str2int(Strings.sNull(data.getOrDefault("pageSize", "10")), 10);
                totalPage = (quantity - 1) / pageSize + 1;
            }
        }
        GroupTemplate gt = getGroupTemplate(beetlConfig.getGroupTemplate().getConf());
        beetlConfig.config(gt);
        gt.setResourceLoader(resLoader);
        tpl = gt.getTemplate(tpl_content);
        String filename;
        for (int i = 1; i <= totalPage; i++) {
            if (totalPage == 1) {
                filename = topic.getId() + ".html";
            } else {
                filename = topic.getId() + "_" + i + ".html";
                if (i == 1) filename = topic.getId() + ".html";
            }
            f = new File(real + "/" + filename);
            parent = f.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            // 页码
            data.put("pageNum", i);
            data.put("totalPage", totalPage);
            try {
                out = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
                tpl.binding(data);
                tpl.renderTo(out);
                // 上传到ftp服务器去
                uploadToFtp(tpl.render(), site.getSite_path() + "/topic/", f.getName());
                log.info("create static file:" + f.getAbsolutePath());
            } finally {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            }
        }

        time = System.currentTimeMillis() - time;
        log.info(String.format("生成专题[%s]静态页耗时（create topic staticPage in）：[%s]", topic.getName(), time + " ms"));
        return true;
    }

    @Override
    public int topicPages(String siteId, String topicId, Map<String, Object> data) throws IOException {
        long time = System.currentTimeMillis();
        int count = 0;
        SimpleCriteria cri = Cnd.cri();
        // 拼装条件
        if (siteId != null && !"".equals(siteId)) {
            cri.where().and("site_id", "=", siteId);
        }
        if (topicId != null && !"".equals(topicId)) {
            cri.where().and("id", "=", topicId);
        }
        List<Cms_topic> topicList = cmsTopicService.query(cri);
        for (Cms_topic topic : topicList) {
            if (!topicPage(topic, data)) {
                continue;
            }
            count++;
        }
        time = System.currentTimeMillis() - time;
        log.info("create topic staticPage in " + time + " ms");

        return count;
    }

    @Override
    public synchronized void delTopic(Cms_topic topic) throws IOException {
        long time = System.currentTimeMillis();
        String real;
        File f;
        Cms_site site = dao.fetch(Cms_site.class, topic.getSite_id());

        real = (config.get("staticpage.out.basepath", Globals.APP_ROOT + "/cms") + "/" + site.getSite_path() + "/topic/").replaceAll("\\\\", "/");
        // 文件命名规则 站点目录/topic/topicId.html
        f = new File(real + "/" + topic.getId() + ".html");
        if (f.exists()) {
            f.delete();
            // 从ftp删除
            delFileFromFtp(site.getSite_path() + "/topic/", f.getName());
        }
        time = System.currentTimeMillis() - time;
        log.info("delete topic staticPage in " + time + " ms");
    }

    @Override
    public void delTopics(List<Cms_topic> topics) throws IOException {
        for (Cms_topic topic : topics) {
            delTopic(topic);
        }
    }


    /**
     * 更新首页
     *
     * @param siteId
     * @param useThread
     */
    @Override
    public synchronized void updateIndex(String siteId, boolean useThread) {
        long start = System.currentTimeMillis();
        List<Cms_site> sites = new ArrayList<>();
        config.put("updateStaticIndex", "start");
        if (siteId == null || StringUtils.isBlank(siteId)) {
            sites = dao.query(Cms_site.class, Cnd.where("delFlag", "=", 0));
        } else {
            sites.add(dao.fetch(Cms_site.class, siteId));
        }
        if (!useThread) {
            try {
                int n = geIndexs(sites, addUtils());
                log.debugf("更新了[%s]个站点的静态首页", n);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            IndexThread indexThread = new IndexThread();
            indexThread.setSites(sites);
            new Thread(indexThread).start();
        }
        log.debugf("更新首页花费了;[%s] ms", System.currentTimeMillis() - start);
        config.put("updateStaticIndex", "end");
    }

    @Override
    public void updateAllWithMultiThread(String siteId) {
        IndexThread indexThread = new IndexThread();
        List<String> siteIds = new ArrayList<>();
        if (siteId == null || StringUtils.isBlank(siteId)) {

            List<Cms_site> sites = dao.query(Cms_site.class, Cnd.where("delFlag", "=", 0));
            for (Cms_site site : sites) {
                siteIds.add(site.getId());
            }
            indexThread.setSites(sites);
        } else {
            String[] ids = siteId.split(",");
            siteIds.addAll(Arrays.asList(ids));
            List sites = dao.query(Cms_site.class, Cnd.where("id", "in", ids));
            indexThread.setSites(sites);
        }
        List<Cms_channel> channels = cmsChannelService.query(Cnd.where("site_id", "in", siteIds).and("delFlag", "=", false));

        ChannelThread chThread = new ChannelThread();
        chThread.setChannels(channels);

        List<Cms_topic> topics = cmsTopicService.query(Cnd.where("site_id", "in", siteIds).and("delFlag", "=", false));
        TopicThread topicThread = new TopicThread();
        topicThread.setTopics(topics);

        Thread indexT = new Thread(indexThread);
        indexT.start();
        Thread channelT = new Thread(chThread);
        channelT.start();

        Thread topicT = new Thread(topicThread);
        topicT.start();
    }

    @Override
    public void updateAll(String siteId) {

    }

    /**
     * 根据栏目和站点更新栏目页和首页和专题页
     *
     * @param c
     * @param s
     */
    @Override
    public void updateChannelAndIndexAndTopic(Cms_channel c, Cms_site s, Cms_topic t) {
        ChannelThread channelThread = new ChannelThread();
        channelThread.setChannel(c);

        IndexThread indexThread = new IndexThread();
        indexThread.setSite(s);

        TopicThread topicThread = new TopicThread();
        topicThread.setTopic(t);

        Thread channelT = new Thread(channelThread);
        Thread indexT = new Thread(indexThread);
        Thread topicT = new Thread(topicThread);


        channelT.start();
        indexT.start();
        topicT.start();
    }

    /**
     * 根据栏目和站点更新栏目页和首页
     *
     * @param c
     * @param s
     */
    @Override
    public void updateChannelAndIndex(Cms_channel c, Cms_site s) {
        ChannelThread channelThread = new ChannelThread();
        channelThread.setChannel(c);

        IndexThread indexThread = new IndexThread();
        indexThread.setSite(s);

        Thread channelT = new Thread(channelThread);
        Thread indexT = new Thread(indexThread);
        channelT.start();
        indexT.start();
    }

    /**
     * 获取子栏目
     *
     * @param sqlstr
     * @param parentId
     * @param children
     * @return
     */
    private List<Cms_channel> getChildren(String sqlstr, String parentId, List<Cms_channel> children) {
        Sql sql = Sqls.create(sqlstr + " AND parentId =@parentId ORDER BY location asc");
        sql.setParam("parentId", parentId);
        if (children == null) {
            children = new ArrayList<>();
        }
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao.getEntity(Cms_channel.class));
        dao.execute(sql);
        List<Cms_channel> list = sql.getList(Cms_channel.class);

        for (Cms_channel c : list) {
            children.add(c);
            if (c.isHasChildren()) {
                getChildren(sqlstr, c.getId(), children);
            }
        }

        return children;
    }

    private Map<String, Cms_channel> list2map(List<Cms_channel> list) {
        HashMap<String, Cms_channel> map = new HashMap<String, Cms_channel>();
        for (Cms_channel c : list) {
            map.put(c.getId(), c);
        }
        return map;
    }

    private int getTxtCount(Cms_content c) {
        Cms_content_txt txt = dao.fetch(Cms_content_txt.class, Cnd.where("content_id", "=", c.getId()));
        if (txt != null) {
            int count = 1;
            int start = 0;
            while (txt.getTxt() != null && txt.getTxt().indexOf("_ueditor_page_break_tag_", start) >= 0 && start < txt.getTxt().length()) {
                count++;
                start = txt.getTxt().indexOf("_ueditor_page_break_tag_", start) + "_ueditor_page_break_tag_".length();
            }
            return count;
        } else {
            return 1;
        }
    }

    private String getTxtByNo(Cms_content c, int pageNo) {
        Cms_content_txt txt = dao.fetch(Cms_content_txt.class, Cnd.where("content_id", "=", c.getId()));
        if (txt != null) {
            return getTxtByNo(txt.getTxt(), pageNo);
        } else {
            return null;
        }
    }

    private String getTxtByNo(String txt, int pageNo) {
        if (StringUtils.isBlank(txt) || pageNo < 1) {
            return null;
        }
        if (txt.indexOf("_ueditor_page_break_tag_") > 0) {
            String[] str = txt.split("_ueditor_page_break_tag_");
            return str[pageNo - 1];
        } else {
            return txt;
        }
    }

    private GroupTemplate getGroupTemplate(Configuration config) {
        String root = System.getProperty("user.dir") + "\\\\" + "template";
        FileResourceLoader resourceLoader = new FileResourceLoader(root, "utf-8");
        if (config == null) {
            try {
                config = Configuration.defaultConfiguration();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        GroupTemplate gt = new GroupTemplate(resourceLoader, config);
        return gt;
    }

    /**
     * 单独线程静态化生成对应栏目
     */
    public class ChannelThread implements Runnable {
        private Cms_channel c;
        private List<Cms_channel> cs;

        public void setChannel(Cms_channel c) {
            this.c = c;
        }

        public void setChannels(List<Cms_channel> cs) {
            this.cs = cs;
        }

        @Override
        public void run() {
            try {
                log.info(this);
                if (c != null) {
                    boolean b = channelPage(c, false, addUtils());
                    log.debug(String.format("栏目页线程生成栏目[%s] [%s]", c.getName(), b));
                } else if (cs != null && !cs.isEmpty()) {
                    for (Cms_channel channel : cs) {
                        boolean b = channelPage(channel, false, addUtils());
                        log.debug(String.format("栏目页线程生成栏目[%s] [%s]", channel.getName(), b));
                    }
                }
            } catch (Exception e) {
                log.debug(e);
            }
        }
    }

    /**
     * 单独线程，静态化生成对应的首页
     */
    public class IndexThread implements Runnable {
        private Cms_site s;

        private List<Cms_site> ss;

        public void setSite(Cms_site s) {
            this.s = s;
        }

        public void setSites(List<Cms_site> ss) {
            this.ss = ss;
        }

        @Override
        public void run() {
            try {
                if (s != null) {
                    boolean b = geIndex(s, addUtils());
                    log.debug(String.format("首页线程生成站点[%s]静态首页[%s]", s.getSite_name(), b));
                } else if (ss != null && !ss.isEmpty()) {
                    int count = geIndexs(ss, addUtils());
                    log.debug(String.format("首页线程生成所有站点静态首页，计数[%d]", count));
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.debug(e);
            }
        }
    }

    /**
     * 单独线程，静态化生成对应的专题
     */
    public class TopicThread implements Runnable {
        private Cms_topic t;
        private List<Cms_topic> ts;
        public void setTopic(Cms_topic t) {
            this.t = t;
        }

        public void setTopics(List<Cms_topic> ts) {
            this.ts = ts;
        }

        @Override
        public void run() {
            try {
                if (t != null) {
                    boolean b = topicPage(t, addUtils());
                    log.debug(String.format("专题页线程生成站点[%s]静态首页[%s]", t.getSite_id(), b));
                } else if (ts != null && !ts.isEmpty()) {
                    for (Cms_topic topic : ts) {
                        boolean b = topicPage(topic, addUtils());
                        log.debug(String.format("专题页线程生成站点[%s]静态首页[%s]", topic.getSite_id(), b));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.debug(e);
            }
        }
    }

    /**
     * 单独线程，上传静态化文件
     */
    public class UploadThread implements Runnable {
        private byte[] bytes;
        private String filePth;
        private String fileName;

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }

        public void setFilePth(String filePth) {
            this.filePth = filePth;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            String host = config.get("staticpage.ftp.host", "");
            int port = StringUtil.str2int(config.get("staticpage.ftp.port"), 21);
            String username = config.get("staticpage.ftp.username", "");
            String password = config.get("staticpage.ftp.password", "");
            String basePath = config.get("staticpage.ftp.basepath", "");
            InputStream in = null;
            in = new ByteArrayInputStream(bytes);
            boolean re = FtpUtil.uploadFile(host, port, username, password, basePath, filePth, fileName, in);
            log.debug(String.format("上传文件[%s]到ftp[%s]", fileName, re ? "成功" : "失败"));
        }
    }

    public class DeleteFileThread implements Runnable {
        private String filePth;
        private String fileName;

        public void setFilePth(String filePth) {
            this.filePth = filePth;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            String host = config.get("staticpage.ftp.host", "");
            int port = StringUtil.str2int(config.get("staticpage.ftp.port"), 21);
            String username = config.get("staticpage.ftp.username", "");
            String password = config.get("staticpage.ftp.password", "");
            String basePath = config.get("staticpage.ftp.basepath", "");
            boolean re = FtpUtil.deleteFile(host, port, username, password, basePath + filePth, fileName);
            log.debug(String.format("从ftp删除文件[%s] [%s]", fileName, re ? "成功" : "失败"));
        }
    }

    private void uploadToFtp(String data, String filePth, String fileName) {
        if ("on".equalsIgnoreCase(config.get("staticpage.ftp"))) {
            byte[] bytes = null;
            try {
                bytes = data.getBytes("utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            UploadThread uThread = new UploadThread();
            uThread.setBytes(bytes);
            uThread.setFileName(fileName);
            uThread.setFilePth(filePth);
            new Thread(uThread).start();
        }
    }

    private void delFileFromFtp(String filePth, String fileName) {
        if ("on".equalsIgnoreCase(config.get("staticpage.ftp", "off"))) {
            DeleteFileThread dThread = new DeleteFileThread();
            dThread.setFileName(fileName);
            dThread.setFilePth(filePth);
            new Thread(dThread).start();
        }
    }

    /**
     * 获取文件输出目录
     *
     * @return
     */
    private String getBasePath(String key, String defalutValue) {
        String path = config.get("staticpage.out.basepath", defalutValue);
        // if(path.startsWith("/")||path.startsWith("\\"))
        return "";
    }

    /**
     * 添加工具类
     * @return
     */
    private Map<String, Object> addUtils() {
        Map map = new HashMap<String, Object>();
        map.put("app_name", Globals.APP_NAME);
        map.put("app_short_name", Globals.APP_SHORT_NAME);
        map.put("date", SpringUtil.getBean(DateUtil.class));
        map.put("string", SpringUtil.getBean(com.aebiz.commons.utils.StringUtil.class));
        map.put("money", SpringUtil.getBean(MoneyUtil.class));
        map.put("area", SpringUtil.getBean(MoneyUtil.class));
        map.put("member", SpringUtil.getBean(LoginMemberUtil.class));
        map.put("site_index_qq", Globals.SITE_INDEX_QQ);
        map.put("staticBase", Globals.STATIC_URL);
        map.put("siteDomain", Globals.SITE_URL);
        map.put("site_name", Globals.SITE_NAME);
        map.put("advisory_tel", Globals.ADVISORY_TEL);
        return map;
    }
}
