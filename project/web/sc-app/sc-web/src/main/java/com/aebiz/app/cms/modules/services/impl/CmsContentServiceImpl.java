package com.aebiz.app.cms.modules.services.impl;

import com.aebiz.app.cms.modules.models.*;
import com.aebiz.app.cms.modules.models.em.ChannelCheckedEnum;
import com.aebiz.app.cms.modules.models.em.CmsIsStaticEnum;
import com.aebiz.app.cms.modules.models.em.ContentStatusEnum;
import com.aebiz.app.sys.modules.models.Sys_user;
import com.aebiz.app.web.commons.utils.StringUtil;
import com.aebiz.baseframework.base.service.BaseServiceImpl;
import com.aebiz.baseframework.rabbit.RabbitMessage;
import com.aebiz.baseframework.rabbit.RabbitProducer;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.commons.utils.SpringUtil;
import com.aebiz.app.cms.modules.services.*;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.dao.util.Daos;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CmsContentServiceImpl extends BaseServiceImpl<Cms_content> implements CmsContentService {
    private static final Log log = Logs.get();
    @Resource(name = "nutDao", type = Dao.class)
    public void init(Dao dao) {
        super.setDao(dao);
    }

    @Autowired
    private CmsContentTxtService cmsContentTxtService;
    @Autowired
    private CmsContentTagService cmsContentTagService;
    @Autowired
    private CmsTagService cmsTagService;
    @Autowired
    private CmsContentPictureService cmsContentPictureService;
    @Autowired
    private CmsContentFileService cmsContentFileService;
    @Autowired
    private CmsLogService cmsLogService;
    @Autowired
    private CmsChannelService cmsChannelService;
    @Autowired
    private CmsSiteService cmsSiteService;
    @Autowired
    private CmsContentCheckService cmsContentCheckService;
    @Autowired
    private CmsWorkflowInfoService cmsWorkflowInfoService;
    @Autowired
    private StaticPageService staticPageService;
    @Autowired
    private RabbitProducer rabbitProducer;

    @Override
    public Map getStatisticMap(String site_id, String channel_id) {

        StringBuilder sb = new StringBuilder("select CONCAT('status',a.status), count(*) from cms_content a where a.delFlag = '0' and a.status<> -1 ");
        this.getSql(site_id, channel_id, sb);
        sb.append(" group by a.status ");
        sb.append(" UNION ");
        sb.append(" SELECT CONCAT('status',5), COUNT(*) FROM cms_content a WHERE a.delFlag = '0' and a.status<> -1 ");
        this.getSql(site_id, channel_id, sb);
        return this.getMap(Sqls.create(sb.toString()));
    }

    @Override
    @Transactional
    public void addDo(Cms_content cmsContent, String tagNmae, String type, String txt, String picPaths, String attachmentInfo, Sys_user user, String ip, String resUrl) {
        String tag1 = StringUtil.null2String(tagNmae) + ",";
        Cms_content cms = cmsContent;
        Cms_content_txt cms_content_txt = new Cms_content_txt();
        cms_content_txt.setTxt(txt);
        if ("".equals(StringUtil.null2String(type)) || "ADD".equals(StringUtil.null2String(type))) {
            cms.setStatus(ContentStatusEnum.DRAFTS.getKey());
        }
        if ("SUBMIT".equals(StringUtil.null2String(type))) {
            cms.setStatus(ContentStatusEnum.PENDING.getKey());
        }
        cms.setUnit_id(user.getUnitid());
        cms.setUser_id(user.getId());
        cms.setAdd_time(DateUtil.getDateTime());
        cms.setIs_static(CmsIsStaticEnum.RELEASED.getKey());
        Cms_content content = this.insert(cms);
        cms_content_txt.setContent_id(content.getId());
        if (cms_content_txt != null && StringUtil.null2String(cms_content_txt.getTxt()).length() > 0) {
            cmsContentTxtService.insert(cms_content_txt);
        }
        //标签
        this.saveTag(tagNmae, content.getId());
        //图片集合
        this.savePic(picPaths, content.getId());
        //附件
        this.saveAttachment(attachmentInfo, content.getId());
        cmsLogService.info(content.getId(), 3, content.getSite_id(), "添加文章", "ID:" + content.getId() + ";TITLE:" + content.getTitle(), user, ip, resUrl);


    }

    @Override
    @Transactional
    public void editDo(Cms_content cmsContent, String tagNmae, String type, String txtContent, String picPaths, String attachmentPaths, Sys_user user, String ip, String resUrl) {
        Cms_content_txt txt = new Cms_content_txt();
        txt.setContent_id(cmsContent.getId());
        txt.setTxt(txtContent);
        cmsContent.setIs_static(CmsIsStaticEnum.RELEASED.getKey());
        List<Cms_channel> channels = cmsChannelService.query(Cnd.where("site_id", "=", cmsContent.getSite_id()).and("id", "=", cmsContent.getChannel_id()));
        Cms_channel channel = new Cms_channel();
        if (channels != null && channels.size() > 0) {
            channel = channels.get(0);
        }
        boolean isStatic = false;
        if (ChannelCheckedEnum.UNCHANGED.getKey().equals(channel.getChecked()) && cmsContent.getStatus() == ContentStatusEnum.FINALIZED.getKey()) { //栏目设置为审核后“修改后不变”，则不进行状态变更
            isStatic = true;
        } else if (ChannelCheckedEnum.RETURN.getKey().equals(channel.getChecked()) && cmsContent.getStatus() == ContentStatusEnum.FINALIZED.getKey()) {// //栏目设置为审核后“修改后退回”，则将审核记录表状态变更
            isStatic = true;
            cmsContent.setStatus(ContentStatusEnum.EXAMINED.getKey());
            cmsContentCheckService.update(org.nutz.dao.Chain.make("status", "1"), Cnd.where("content_id", "=", cmsContent.getId()));
        } else if (ChannelCheckedEnum.MODIFYDELETE.getKey().equals(channel.getChecked()) && cmsContent.getStatus() == ContentStatusEnum.FINALIZED.getKey()) {//栏目设置为审核后“不能修改删除”
            return;
        } else {
            //默认，根据操作变更状态
            if ("".equals(StringUtil.null2String(type)) || "ADD".equals(StringUtil.null2String(type))) {
                cmsContent.setStatus(ContentStatusEnum.DRAFTS.getKey());
            }
            if ("UPDATE".equals(StringUtil.null2String(type))) {
                cmsContent.setStatus(ContentStatusEnum.DRAFTS.getKey());
                //需要根据栏目设置进行判断
            }
            if ("SUBMIT".equals(StringUtil.null2String(type))) {
                cmsContent.setStatus(ContentStatusEnum.PENDING.getKey());
            }
        }
        //文章有变更则将静态化设置为需生成，同时将站点和栏目设置为需生成
        cmsSiteService.update(org.nutz.dao.Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()), Cnd.where("id", "=", cmsContent.getSite_id()));
        cmsChannelService.update(org.nutz.dao.Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()), Cnd.where("site_id", "=", cmsContent.getSite_id()).and("id", "=", cmsContent.getChannel_id()));
        cmsContent.setOpBy(StringUtil.getUid());
        cmsContent.setOpAt((int) (System.currentTimeMillis() / 1000));
        this.updateIgnoreNull(cmsContent);
        //文章内容
        cmsContentTxtService.clear(Cnd.where("content_id", "=", cmsContent.getId()));
        cmsContentTxtService.insert(txt);
        //标签
        this.saveTag(tagNmae, cmsContent.getId());
        //图片集合
        this.savePic(picPaths, cmsContent.getId());
        //附件
        this.saveAttachment(attachmentPaths, cmsContent.getId());
        if (isStatic) {
            //当文章从终审变为其他状态，删除已生成的静态页，并更改静态化标识
            this.update(org.nutz.dao.Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()).add("is_index", "0"), Cnd.where("id", "=", cmsContent.getId()));
            staticPageService.deleteContent(cmsContent);
            //设置栏目为需要静态化
            cmsChannelService.update(org.nutz.dao.Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()), Cnd.where("id", "=", cmsContent.getChannel_id()).or("parentId", "=", cmsContent.getChannel_id()).and("site_id", "=", cmsContent.getSite_id()));
            cmsLogService.info(cmsContent.getId(), 3, cmsContent.getSite_id(), "修改文章", "ID:" + cmsContent.getId() + ";TITLE:" + cmsContent.getTitle(), user, ip, resUrl);
        }
    }

    @Transactional
    public String savecheck(String is_checked, String check_opinion, String flowid, Cms_content content, Integer step, Sys_user user, String ip, String resUrl) {
        String content_id = content.getId();
        if (!Strings.sBlank(flowid).equals("0")) {//若栏目定义了工作流
            int wksize = cmsWorkflowInfoService.count(Cnd.where("flowid", "=", flowid));
            Cms_content_check check = cmsContentCheckService.fetch(Cnd.where("CONTENT_ID", "=", content_id).and("FLOWID", "=", flowid).and("STATUS", "=", 0));
            Cms_workflow_info info = cmsWorkflowInfoService.fetch(Cnd.where("FLOWID", "=", flowid).and("step", "=", step));
            if (check == null) {//获取当前节点审核数据，若为起始节点则创建
                Cms_content_check c = new Cms_content_check();
                c.setContent_id(content_id);
                c.setStatus(0);
                c.setFlowid(flowid);
                c.setCheck_step(step);
                check = cmsContentCheckService.insert(c);
            }
            if (info.getType() == 0) {//节点类型为“普通流转”
                if (step == wksize - 1) {//若是最后一节点，则终审
                    check.setCheck_date(DateUtil.getDateTime());
                    check.setReviewer(user.getId());
                    check.setIs_checked("Y");
                    check.setCheck_opinion("终审通过");
                    check.setStatus(1);
                    cmsContentCheckService.updateIgnoreNull(check);
                    this.update(Chain.make("status", ContentStatusEnum.FINALIZED.getKey()).add("is_static", CmsIsStaticEnum.RELEASED.getKey()).add("is_index", "0"), Cnd.where("id", "=", content_id));
                    cmsChannelService.update(Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()), Cnd.where("site_id", "=", content.getSite_id()).and("id", "=", content.getChannel_id()).or("parentId", "=", content.getChannel_id()));

                    //mq文章同步es jxx
                    if (SpringUtil.isRabbitEnabled()) {
                        String exchange = "topicExchange";
                        String routeKey = "topic.es.cmsContent";
                        RabbitMessage msg = new RabbitMessage(exchange, routeKey, NutMap.NEW().addv("contentId", content.getId()).addv("action", "create"));
                        rabbitProducer.sendMessage(msg);
                    }
                    return "cms.content.check.result.final.success";
                } else {//若不是最后节点，流转到下一节点
                    check.setCheck_date(DateUtil.getDateTime());
                    check.setReviewer(user.getId());
                    check.setIs_checked("Y");
                    check.setCheck_opinion("流转到下一节点");
                    check.setStatus(1);
                    cmsContentCheckService.updateIgnoreNull(check);
                    Cms_content_check c = new Cms_content_check();
                    c.setContent_id(content_id);
                    c.setStatus(0);
                    c.setFlowid(flowid);
                    c.setCheck_step(step + 1);
                    c.setUser_id(user.getId());
                    c.setCheck_opinion("");
                    c.setIs_checked("");
                    c.setCheck_date("");
                    cmsContentCheckService.insert(c);
                    this.update(Chain.make("status", ContentStatusEnum.EXAMINED.getKey()), Cnd.where("id", "=", content_id));
                    return "cms.content.check.result.check.success";
                }

            } else {//节点类型为“会签”
                if ("Y".equals(StringUtil.null2String(is_checked))) {//审核通过
                    if (step == wksize - 1) {//若是最后一节点，则终审
                        check.setCheck_date(DateUtil.getDateTime());
                        check.setReviewer(user.getId());
                        check.setIs_checked("Y");
                        check.setCheck_opinion(StringUtil.null2String(check_opinion));
                        check.setStatus(1);
                        cmsContentCheckService.updateIgnoreNull(check);
                        this.update(Chain.make("status", ContentStatusEnum.FINALIZED.getKey()).add("is_static", CmsIsStaticEnum.RELEASED.getKey()).add("is_index", "0"), Cnd.where("id", "=", content_id));
                        cmsChannelService.update(Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()), Cnd.where("site_id", "=", content.getSite_id()).and("id", "=", content.getChannel_id()).or("parentId", "=", content.getChannel_id()));

                        //mq文章同步es jxx
                        if (SpringUtil.isRabbitEnabled()) {
                            String exchange = "topicExchange";
                            String routeKey = "topic.es.cmsContent";
                            RabbitMessage msg = new RabbitMessage(exchange, routeKey, NutMap.NEW().addv("contentId", content.getId()).addv("action", "create"));
                            rabbitProducer.sendMessage(msg);
                        }
                        return "cms.content.check.result.final.success";
                    } else {//若不是最后节点，流转到下一节点
                        check.setCheck_date(DateUtil.getDateTime());
                        check.setReviewer(user.getId());
                        check.setIs_checked("Y");
                        check.setCheck_opinion(StringUtil.null2String(check_opinion));
                        check.setStatus(1);
                        cmsContentCheckService.updateIgnoreNull(check);
                        Cms_content_check c = new Cms_content_check();
                        c.setContent_id(content_id);
                        c.setStatus(0);
                        c.setFlowid(flowid);
                        c.setCheck_step(step + 1);
                        c.setUser_id(user.getId());
                        c.setCheck_opinion("");
                        c.setIs_checked("");
                        c.setCheck_date("");
                        cmsContentCheckService.insert(c);
                        //审核中
                        this.update(Chain.make("status", ContentStatusEnum.EXAMINED.getKey()), Cnd.where("id", "=", content_id));
                        return "cms.content.check.result.check.success";
                    }

                } else {
                    if (step > 0) {//若是未通过，则不能是起始节点，前台页面做了对应判断
                        check.setCheck_date(DateUtil.getDateTime());
                        check.setReviewer(user.getId());
                        check.setIs_checked("N");
                        check.setCheck_opinion(StringUtil.null2String(check_opinion));
                        check.setStatus(1);
                        cmsContentCheckService.updateIgnoreNull(check);
                      /*  Cms_content_check c = new Cms_content_check();
                        c.setContent_id(content_id);
                        c.setStatus(0);
                        c.setFlowid(flowid);
                        c.setCheck_step(step - 1);
                        c.setUser_id(user.getId());
                        c.setCheck_opinion(StringUtil.null2String(check_opinion) + "\r\n");
                        c.setIs_checked("");
                        cmsContentCheckService.insert(c);*/
                        //退回
                        this.update(Chain.make("status", ContentStatusEnum.RETURN.getKey()), Cnd.where("id", "=", content_id));
                        return "cms.content.check.result.check.success";
                    }
                }
            }
        }
        //若栏目未定义工作流，审核直接为终审
        Cms_content_check c = new Cms_content_check();
        c.setContent_id(content_id);
        c.setCheck_date(DateUtil.getDateTime());
        c.setFlowid("0");
        c.setCheck_step(0);
        c.setCheck_opinion("终审通过");
        c.setReviewer(user.getId());
        c.setIs_checked("Y");
        c.setStatus(1);//设置状态为可用
        //设置栏目为需要静态化
        this.update(Chain.make("status", ContentStatusEnum.FINALIZED.getKey()).add("is_static", CmsIsStaticEnum.RELEASED.getKey()).add("is_index", "0"), Cnd.where("id", "=", content_id));
        cmsChannelService.update(Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()), Cnd.where("site_id", "=", content.getSite_id()).and("id", "=", content.getChannel_id()).or("parentId", "=", content.getChannel_id()));
        cmsContentCheckService.insert(c);
        cmsLogService.info(content.getId(), 3, content.getSite_id(), "审核文章", "ID:" + content.getId() + ";TITLE:" + content.getTitle(), user, ip, resUrl);

        //mq文章同步es jxx
        if (SpringUtil.isRabbitEnabled()) {
            String exchange = "topicExchange";
            String routeKey = "topic.es.cmsContent";
            RabbitMessage msg = new RabbitMessage(exchange, routeKey, NutMap.NEW().addv("contentId", content.getId()).addv("action", "create"));
            rabbitProducer.sendMessage(msg);
        }
        return "cms.content.check.result.final.success";
    }

    @Transactional
    public void savechecks(String ids, Sys_user user, String ip, String resUrl) {
        String[] content_ids = ids.split(",");
        List<String> contentList = Arrays.asList(content_ids);
        for (String id : contentList) {
            String content_id = id.trim();
            Cms_content content = this.fetch(content_id);
            Cms_content_check c = new Cms_content_check();
            c.setContent_id(content_id);
            c.setCheck_date(DateUtil.getDateTime());
            c.setFlowid("0");
            c.setCheck_step(0);
            c.setCheck_opinion("终审通过");
            c.setIs_checked("Y");
            c.setReviewer(user.getId());
            c.setStatus(1);//设置状态为可用
            //设置栏目为需要静态化
            this.update(Chain.make("status", ContentStatusEnum.FINALIZED.getKey()).add("is_static", CmsIsStaticEnum.RELEASED.getKey()).add("is_index", "0"), Cnd.where("id", "=", content_id));
            cmsChannelService.update(Chain.make("is_static", CmsIsStaticEnum.RELEASED.getKey()), Cnd.where("site_id", "=", content.getSite_id()).and("id", "=", content.getChannel_id()).or("parentId", "=", content.getChannel_id()));
            cmsContentCheckService.insert(c);
            cmsLogService.info(content.getId(), 3, content.getSite_id(), "审核文章", "ID:" + content.getId() + ";TITLE:" + content.getTitle(), user, ip, resUrl);
            //加入搜索引擎job
/*            Cms_content_estemp estemp = new Cms_content_estemp();
            estemp.setAction("create");
            estemp.setContentId(content.getId());
            estemp.setCmsContent(content);
            cmsContentEstempService.insert(estemp);*/
        }
        //mq文章同步es jxx
        if (SpringUtil.isRabbitEnabled()) {
            String exchange = "topicExchange";
            String routeKey = "topic.es.cmsContent";
            RabbitMessage msg = new RabbitMessage(exchange, routeKey, NutMap.NEW().addv("contentId", ids).addv("action", "create"));
            rabbitProducer.sendMessage(msg);
        }
    }

    @Transactional
    public void pushSave(String[] content_ids, String channel_id) {
        if (content_ids.length != 0) {
            Cms_content newContent = new Cms_content();
            Cms_channel cms_channel = cmsChannelService.fetch(channel_id);
            for (int i = 0, count = content_ids.length; i < count; i++) {
                Cms_content oldContent = this.fetch(content_ids[i].trim());
                newContent = oldContent;
                newContent.setId(null);
                String old_id = oldContent.getId();
                newContent.setSite_id(cms_channel.getSite_id());       // 更新siteID
                newContent.setChannel_id(channel_id);
                newContent.setStatus(ContentStatusEnum.DRAFTS.getKey());
                newContent.setIs_static(CmsIsStaticEnum.RELEASED.getKey());
                Cms_content ccc = this.insert(newContent);
                String new_id = ccc.getId();
                Cms_content_file content_file = cmsContentFileService.fetch(Cnd.where("content_id", "=", old_id));
                Cms_content_picture content_picture = cmsContentPictureService.fetch(Cnd.where("content_id", "=", old_id));
                Cms_content_tag content_tag = cmsContentTagService.fetch(Cnd.where("content_id", "=", old_id));
                Cms_content_txt content_txt = cmsContentTxtService.fetch(Cnd.where("content_id", "=", old_id));
                Cms_log content_log = cmsLogService.fetch(Cnd.where("content_id", "=", old_id));
                if (content_file != null) {
                    content_file.setContent_id(new_id);
                    cmsContentFileService.insert(content_file);
                }
                if (content_picture != null) {
                    content_picture.setContent_id(new_id);
                    cmsContentPictureService.insert(content_picture);
                }
                if (content_tag != null) {
                    content_tag.setContent_id(new_id);
                    cmsContentTagService.insert(content_tag);
                }
                if (content_txt != null) {
                    content_txt.setContent_id(new_id);
                    cmsContentTxtService.insert(content_txt);
                }
                if (content_log != null) {
                    content_log.setContent_id(new_id);
                    cmsLogService.insert(content_log);
                }
            }
        }
    }

    /**
     * 保存标签
     */
    private void saveTag(String tagNames, String contentId) {
        if (!"".equals(StringUtil.null2String(tagNames)) && StringUtil.null2String(tagNames).indexOf(",") > 0) {
            //对Tag关键词进行解析处理
            String[] tag = tagNames.split(",");
            String tagid = "";
            cmsContentTagService.clear(Cnd.where("content_id", "=", contentId));
            Cms_content_tag cms_content_tag = new Cms_content_tag();
            for (int i = 0; i < tag.length; i++) {
                if ("".equals(StringUtil.null2String(tag[i])))
                    continue;
                List<Cms_tag> cms_tags = cmsTagService.query(Cnd.where("name", "=", StringUtil.null2String(tag[i])));
                Cms_tag cms_tag = null;
                if (cms_tags != null && cms_tags.size() > 0) {
                    cms_tag = cms_tags.get(0);
                }
                if (cms_tag == null) {
                    Cms_tag t = new Cms_tag();
                    t.setName(StringUtil.null2String(tag[i]));
                    t.setCounter(0);
                    tagid = cmsTagService.insert(t).getId();
                } else {
                    tagid = cms_tag.getId();
                    cms_tag.setCounter(cms_tag.getCounter() + 1);
                    cmsTagService.updateIgnoreNull(cms_tag);
                }
                cms_content_tag.setContent_id(contentId);
                cms_content_tag.setTag_id(tagid);
                cms_content_tag.setPriority(i);
                cmsContentTagService.clear(Cnd.where("content_id", "=", contentId).and("tag_id", "=", tagid));
                cmsContentTagService.insert(cms_content_tag);
            }
        }
    }

    /**
     * 保存图片集合
     */
    private void savePic(String picPaths, String contentId) {
        if (Strings.isNotBlank(picPaths)) {
            List<String> uploadImageList = Json.fromJsonAsList(String.class, picPaths);
            cmsContentPictureService.clear(Cnd.where("content_id", "=", contentId));
            for (int i = 0; i < uploadImageList.size(); i++) {
                String uploadImage = uploadImageList.get(i);
                Cms_content_picture cms_content_picture = new Cms_content_picture();
                cms_content_picture.setContent_id(contentId);
                cms_content_picture.setPriority(i);
                cms_content_picture.setImg(uploadImage);
                cmsContentPictureService.insert(cms_content_picture);
            }
        }
    }

    /**
     * 保存附件集合
     */
    private void saveAttachment(String attachmentInfo, String contentId) {
        //附件
        if (Strings.isNotBlank(attachmentInfo)) {
            List<Cms_content_file> uploadFileList = Json.fromJsonAsList(Cms_content_file.class, attachmentInfo);
            cmsContentFileService.clear(Cnd.where("content_id", "=", contentId));
            for (int i = 0; i < uploadFileList.size(); i++) {
                Cms_content_file cms_content_file = uploadFileList.get(i);
                // TODO: 2017/7/25 文件的原始文件名
                // cms_content_file.setFile_name(StringUtil.null2String(uploadFile).substring(StringUtil.null2String(uploadFile).lastIndexOf("/") + 1));
                //cms_content_file.setFilename(attachmentNames.length > i ? StringUtil.null2String(attachmentNames[i]) : "");
                cms_content_file.setDownload_count(0);
                cms_content_file.setContent_id(contentId);
                cmsContentFileService.insert(cms_content_file);
            }
        }
    }

    /**
     * 组装站点及栏目条件查询
     *
     * @param site_id
     * @param channel_id
     * @param sb
     */
    private void getSql(String site_id, String channel_id, StringBuilder sb) {
        if ("root".equals(channel_id) || Strings.isBlank(channel_id)) {

        } else {
            sb.append(" and channel_id ='" + channel_id + "'");
        }
        sb.append(" and site_id ='" + site_id + "'");
    }

    @Override
    public List<Cms_content> getReadMoreContent(Integer pageNo, Integer pageSize) {
        Sql sql = Sqls.create("SELECT a.* FROM CMS_CONTENT a WHERE a.STATUS = 3 AND a.IS_STATIC = 1 AND a.CHANNEL_ID IN (@cids) ORDER BY (SELECT count(ARTID) FROM CMS_ACCESS_RECORD b WHERE b.ARTID = a.ID) DESC");
        List<Cms_channel> channels = cmsChannelService.query(Cnd.where("path", "like", "sczx%"));
        List<String> cids = new ArrayList<>();
        for (Cms_channel c : channels) {
            cids.add(c.getId());
        }
        sql.setParam("cids", cids.toArray());
        Pager pager = this.dao().createPager(pageNo, pageSize);
        pager.setRecordCount((int) Daos.queryCount(this.dao(), sql));
        sql.setPager(pager);
        sql.setCallback(Sqls.callback.entities());
        sql.setEntity(dao().getEntity(Cms_content.class));
        this.dao().execute(sql);
        List<Cms_content> list = sql.getList(Cms_content.class);
        return list;
    }

    private static final String CONTENT_AND_TEXT="from cms_content c " +
            "left join cms_content_txt t on c.id=t.content_id";   //content 与 text 关联
    @Override
    public List<Cms_content> getContentByChinnelId(String channel_id) {
        Sql sql=Sqls.create("select c.id,c.title,t.txt,c.has_title_img "+CONTENT_AND_TEXT+"$cnd order by c.pub_time");
        if(Strings.isNotBlank(channel_id)){
            sql.setVar("cnd",Cnd.where("c.channel_id","=",channel_id).and("c.delflag","=","0"));
        }
        sql.setCallback(new SqlCallback() {
            @Override
            public Object invoke(Connection connection, ResultSet resultSet, Sql sql) throws SQLException {
                List<Cms_content> list = new ArrayList<>();
                while (resultSet.next()){
                    Cms_content cms_content=new Cms_content();
                    cms_content.setId(resultSet.getString(1));
                    cms_content.setTitle(resultSet.getString(2));
                    cms_content.setContent_txt(resultSet.getString(3));
                    cms_content.setTitle_img(resultSet.getString(4));
                    list.add(cms_content);
                }
                return list;
            }
        });
        this.dao().execute(sql);
        return sql.getList(Cms_content.class);
    }
}
