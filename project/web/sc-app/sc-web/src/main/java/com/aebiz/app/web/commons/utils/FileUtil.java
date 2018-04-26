package com.aebiz.app.web.commons.utils;

import com.aebiz.app.web.commons.base.Globals;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: jxx
 * @date: 2017/7/12 14:26
 * 文件处理
 */
public class FileUtil {
    /**
     * 组装文件分类树
     *
     * @param dir  文件
     * @param obj  具体的分类树引用
     * @param tree 分类树引用
     */
    public static void getFileTree(String basePath, File dir, Map<String, Object> obj, List<Map<String, Object>> tree) {
        File[] files = dir.listFiles();
        if (StringUtils.isBlank(basePath)) basePath = Globals.APP_ROOT.replaceAll("\\\\", "/");
        if (basePath.endsWith("/")) basePath = StringUtils.removeEnd(basePath, "/");
        if (null != files) {
            for (File f : files) {
                if (f.isDirectory()) {

                    obj = new HashMap<>();
                    obj.put("id", f.getPath().replaceAll("\\\\", "/").replace(basePath, ""));
                    obj.put("pId", f.getParent().replaceAll("\\\\", "/").replace(basePath, ""));
                    obj.put("text", f.getName());
                    obj.put("url", "javascript:openPath(\"" + f.getPath().replaceAll("\\\\", "/").replace(basePath, "") + "\")");
                    File[] files2 = f.listFiles();
                    if (null != files2 && files2.length > 0) {
                        obj.put("children", true);
                    } else {
                        obj.put("children", false);
                    }
                    tree.add(obj);
                } else {
                    obj = new HashMap<>();
                    String flname = f.getName();
                    String suname = flname.substring(flname.lastIndexOf(".") + 1).toLowerCase();
                    obj.put("icon", "/assets/platform/images/icons/filetype/" + suname + ".gif");
                    obj.put("id", f.getPath().replaceAll("\\\\", "/").replace(basePath, ""));
                    obj.put("pId", f.getParent().replaceAll("\\\\", "/").replace(basePath, ""));
                    obj.put("text", f.getName());
                    obj.put("url", "javascript:openFile(\"" + f.getPath().replaceAll("\\\\", "/").replace(basePath, "") + "\")");
                    obj.put("children", false);
                    tree.add(obj);
                }
            }
        }
    }
}
