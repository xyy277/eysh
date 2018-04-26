package com.aebiz.app.sys.modules.models;

import com.aebiz.baseframework.base.model.BaseModel;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;

/**
 * 文件管理表
 * Created by zyang on 2017/8/15.
 */
@Table("sys_file")
public class Sys_file extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column
    @Name
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    private String id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 256)
    @Comment("文件名")
    private String name;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 512)
    @Comment("远程文件服务器")
    private String fdfs_file_id;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 512)
    @Comment("文件存放路径")
    private String path;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 512)
    @Comment("文件访问路径")
    private String url;

    @Column
    @ColDefine(type = ColType.VARCHAR, width = 128 )
    @Comment("文件类型")
    private String type;

    @Column
    @ColDefine(type = ColType.INT, width = 32)
    @Comment("文件大小")
    private Long fileSize;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFdfs_file_id() {
        return fdfs_file_id;
    }

    public void setFdfs_file_id(String fdfs_file_id) {
        this.fdfs_file_id = fdfs_file_id;
    }
}
