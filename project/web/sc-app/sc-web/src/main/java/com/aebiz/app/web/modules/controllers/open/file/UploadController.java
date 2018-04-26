package com.aebiz.app.web.modules.controllers.open.file;

import com.aebiz.app.sys.modules.models.Sys_file;
import com.aebiz.app.sys.modules.services.SysFileService;
import com.aebiz.app.web.commons.base.Globals;
import com.aebiz.baseframework.base.Result;
import com.aebiz.baseframework.view.annotation.SJson;
import com.aebiz.commons.fastdfs.NameValuePair;
import com.aebiz.commons.utils.DateUtil;
import com.aebiz.fastdfs.*;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Files;
import org.nutz.lang.random.R;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Wizzer on 2016/7/5.
 */
@CrossOrigin
@Controller
@RequestMapping("/open/file/upload")
public class UploadController {
    private static final Log log = Logs.get();
    @Autowired
    private PropertiesProxy config;
    @Autowired
    private SysFileService sysFileService;

    private String filePathPrefix = "";

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @SJson
    public Object image(@RequestParam("Filedata") MultipartFile tf, HttpServletRequest request) {
        String u = StringUtils.trimToEmpty(request.getParameter("u"));
        boolean isUrl = StringUtils.isBlank(u) || Boolean.parseBoolean(u) || u.equals("1");
        if (!tf.isEmpty()) {
            List<String> imageList = config.getList("upload.suffix.image", ",");
            if (imageList.contains(tf.getOriginalFilename().substring(tf.getOriginalFilename().lastIndexOf(".") + 1))) {
                filePathPrefix = Globals.APP_UPLOAD_PATH + "/image/";
                return upload(tf, isUrl);
            } else {
                return Result.error("globals.upload.suffix");
            }
        }
        return Result.error("globals.upload.fail");
    }

    /**
     * 上传视频
     *
     * @param tf
     * @return
     */
    @RequestMapping(value = "/video", method = RequestMethod.POST)
    @SJson
    public Object video(@RequestParam("Filedata") MultipartFile tf, HttpServletRequest request) {
        String u = StringUtils.trimToEmpty(request.getParameter("u"));
        boolean isUrl = StringUtils.isBlank(u) || Boolean.parseBoolean(u) || u.equals("1");
        if (!tf.isEmpty()) {
            List<String> imageList = config.getList("upload.suffix.video", ",");
            if (imageList.contains(tf.getOriginalFilename().substring(tf.getOriginalFilename().lastIndexOf(".") + 1))) {
                filePathPrefix = Globals.APP_UPLOAD_PATH + "/video/";
                return upload(tf, isUrl);
            } else {
                return Result.error("globals.upload.suffix");
            }
        }
        return Result.error("globals.upload.fail");
    }

    /**
     * 上传文件
     *
     * @param tf
     * @return
     */
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    @SJson
    public Object file(@RequestParam("Filedata") MultipartFile tf, HttpServletRequest request) {
        String u = StringUtils.trimToEmpty(request.getParameter("u"));
        boolean isUrl = StringUtils.isBlank(u) || Boolean.parseBoolean(u) || u.equals("1");
        if (!tf.isEmpty()) {
            List<String> imageList = config.getList("upload.suffix.file", ",");
            if (imageList.contains(tf.getOriginalFilename().substring(tf.getOriginalFilename().lastIndexOf(".") + 1))) {
                filePathPrefix = Globals.APP_UPLOAD_PATH + "/file/";
                return upload(tf, isUrl);
            } else {
                return Result.error("globals.upload.suffix");
            }
        }
        return Result.error("globals.upload.fail");
    }

    private Result upload(MultipartFile tf, boolean isUrl) {
        try {
            String uploadMode = config.get("upload.mode", "file");
            String fileName = tf.getOriginalFilename();
            String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
            if ("fdfs".equalsIgnoreCase(uploadMode)) {
                //如果使用文件服务器
                byte[] fileBuff = tf.getBytes();
                ClientGlobal.init(config);
                //建立连接
                TrackerClient tracker = new TrackerClient();
                TrackerServer trackerServer = tracker.getConnection();
                StorageServer storageServer = null;
                StorageClient1 client = new StorageClient1(trackerServer, storageServer);
                //设置元信息
                NameValuePair[] metaList = new NameValuePair[3];
                metaList[0] = new NameValuePair("fileName", fileName);
                metaList[1] = new NameValuePair("fileExtName", fileExtName);
                metaList[2] = new NameValuePair("fileLength", String.valueOf(tf.getSize()));

                //上传文件
                String fdfsFileId = client.upload_file1(fileBuff, fileExtName, metaList);
                // 存进数据库
                Sys_file sysFile = new Sys_file();
                sysFile.setName(fileName);
                sysFile.setFileSize(tf.getSize());
                sysFile.setType(tf.getContentType());
                sysFile.setUrl(config.get("fdfs.domain", "") + "/" + fdfsFileId);
                sysFile.setId(R.UU32());
                sysFile.setFdfs_file_id(fdfsFileId);
                sysFile = sysFileService.insert(sysFile);
                if (isUrl) {
                    return Result.success("globals.upload.success", sysFile.getUrl());
                } else {
                    return Result.success("globals.upload.success", sysFile);
                }
            } else {
                String p = Globals.APP_ROOT;
                String fileId = R.UU32();
                if (filePathPrefix.equals("")) {
                    filePathPrefix = Globals.APP_UPLOAD_PATH + "/file/";
                }
                String f = filePathPrefix + DateUtil.format(new Date(), "yyyyMMdd") + "/" + fileId + "." + fileExtName;
                tf.transferTo(Files.createFileIfNoExists(p + f));
                // 存进数据库
                Sys_file sysFile = new Sys_file();
                sysFile.setName(fileName);
                sysFile.setFileSize(tf.getSize());
                sysFile.setType(tf.getContentType());
                sysFile.setUrl(Globals.SITE_URL + "/open/file/download/" + fileId);
                sysFile.setId(fileId);
                sysFile.setPath(f);
                sysFile = sysFileService.insert(sysFile);
                if (isUrl) {
                    return Result.success("globals.upload.success", sysFile.getUrl());
                } else {
                    sysFile.setPath("");
                    return Result.success("globals.upload.success", sysFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("globals.upload.error");
        }
    }

}
