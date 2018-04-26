package com.aebiz.app.cms.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * @author: jxx
 * @date: 2017/7/18 10:02
 * 附件表
 * */
@Table("cms_content_file")
public class Cms_content_file  extends BaseModel implements Serializable {
    @Column
    @Comment("内容id")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String content_id;
    @Column
    @Comment("排序")
    @ColDefine(type =ColType.INT, width = 32)
    private Integer priority;
    @Column
    @Comment("文件路径")
    @ColDefine(type = ColType.VARCHAR, width = 501)
    private String file_path;
    @Column
    @Comment("生成的文件名称")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String file_name;
    @Column
    @Comment("原文件名")
    @ColDefine(type = ColType.VARCHAR, width = 100)
    private String filename;
    @Column
    @Comment("")
    @ColDefine(type = ColType.INT, width = 32)
    private Integer download_count;

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getDownload_count() {
        return download_count;
    }

    public void setDownload_count(Integer download_count) {
        this.download_count = download_count;
    }
}
