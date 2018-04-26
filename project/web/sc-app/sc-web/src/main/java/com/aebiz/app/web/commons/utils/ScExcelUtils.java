package com.aebiz.app.web.commons.utils;

import com.aebiz.baseframework.view.annotation.SFile;
import org.apache.poi.hssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by bin on 2017/8/15.
 */
public class ScExcelUtils {
    @SFile
    public static File getExcel(HttpServletRequest req, HttpServletResponse res, String fname, List<Map<String, String>> maplist, String[] titles) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(fname);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式


        for (int n = 0; n < titles.length; n++) {
            sheet.setColumnWidth(n, 4000);
        }

        HSSFCell cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(titles[0]);
        cell.setCellStyle(style);
        for (int i = 1; i < titles.length; i++) {
            cell = row.createCell(i, HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }

        for (int i = 0; i < maplist.size(); i++) {
            HSSFRow row2 = sheet.createRow(i + 1);//index：第几行
            Map<String, String> map = maplist.get(i);
            for (int j = 0; j < titles.length; j++) {
                cell = row2.createCell(j);//第几列：从0开始
                cell.setCellValue(map.get(titles[j]));
                cell.setCellStyle(style);
            }
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");//可以方便地修改日期格式
        Date now = new Date();
        String time = dateFormat.format(now);

        res.addHeader("content-type", "application/x-msdownload;");
        res.addHeader("content-disposition", "attachment; filename=" + URLEncoder.encode(fname + time + ".xls", "utf-8"));
        ServletOutputStream out = res.getOutputStream();

        try {
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            out = null;
        }
        return new File(fname + ".xls");
    }


    //注意模板的顺序
    public static List<Map<String, String>> parseExcel(InputStream fis) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();

        try {
            HSSFWorkbook book = new HSSFWorkbook(fis);
            HSSFSheet sheet = book.getSheetAt(0);
            int firstRow = sheet.getFirstRowNum();
            int lastRow = sheet.getLastRowNum();

            //除去第一行
            for (int i = firstRow + 1; i < lastRow + 1; i++) {
                Map<String, String> map = new HashMap<String, String>();

                HSSFRow row = sheet.getRow(i);
                int firstCell = row.getFirstCellNum();
                int lastCell = row.getLastCellNum();

                for (int j = firstCell; j < lastCell; j++) {

                    String key = "CELL" + (j + 1);//从1开始
                    HSSFCell cell = row.getCell(j);
                    String val = "";
                    if (cell != null) {
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        val = cell.getStringCellValue();
                    }
                    if (i == firstRow) {
                        break;
                    } else {
                        map.put(key, val == null ? "" : val);
                    }
                }
                if (i != firstRow) {
                    data.add(map);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String[] getErro(Map<String, String> maptitle, List<Map<String, String>> data) {
        String errorNull = "";
        String errorLength = "";

        for (int i = 0; i < data.size(); i++) {
            Map<String, String> map = data.get(i);

            for (String key : maptitle.keySet()) {
                int result = 0;
                for (String keyy : map.keySet()) {
                    if (key.equals(keyy)) {
                        if ("1".equals(maptitle.get(key).substring(maptitle.get(key).indexOf("@") + 1, maptitle.get(key).length())) && "".equals(map.get(keyy))) {
                            if (!"".equals(errorNull)) {
                                errorNull += "," + String.valueOf(i + 1);
                            } else {
                                errorNull += String.valueOf(i + 1);
                            }
                            result = 1;
                        }
                    }

                }
                if (result == 1) break;
            }
            for (String key : maptitle.keySet()) {
                int result = 0;
                for (String keyy : map.keySet()) {
                    if (key.equals(keyy)) {
                        if (Integer.parseInt(maptitle.get(key).substring(0, maptitle.get(key).lastIndexOf("@"))) < map.get(keyy).length()) {
                            if (!"".equals(errorLength)) {
                                errorLength += "," + String.valueOf(i + 1);
                            } else {
                                errorLength += String.valueOf(i + 1);
                            }
                            result = 1;
                        }
                    }

                }
                if (result == 1) break;
            }
        }
        String[] error = {errorNull, errorLength};
        return error;
    }

    public static String getErroresult(String errorId, String[] error) {
        String totalerro = "";
        if (!"".equals(errorId)) {
            totalerro += "第" + errorId + "<br/>行平台编码已存在;<br/>";
        }
        if (!"".equals(error[0])) {
            totalerro += "第" + error[0] + "<br/>行有必填字段未填写；<br/>";
        }
        if (!"".equals(error[1])) {
            totalerro += "第" + error[1] + "<br/>行有字段超长；<br/>";
        }
        totalerro += "请按模板核对数据修改!!<br/>";
        return totalerro;
    }

    public static String getErroresultdata(String errorId, String[] error, String errordate) {
        String totalerro = "";
        if (!"".equals(errorId)) {
            totalerro += "第" + errorId + "<br/>行平台编码已存在；<br/>";
        }
        if (!"".equals(error[0])) {
            totalerro += "第" + error[0] + "<br/>行有必填字段未填写；<br/>";
        }
        if (!"".equals(error[1])) {
            totalerro += "第" + error[1] + "<br/>行有字段超长；<br/>";
        }
        if (!"".equals(errordate)) {
            totalerro += "第" + errordate + "<br/>行日期格式错误；<br/>";
        }
        totalerro += "请按模板核对数据修改!!<br/>";
        return totalerro;
    }

    public static String getErroresultzd(String errorId, String[] error, String errorzd) {
        String totalerro = "";
        if (!"".equals(errorId)) {
            totalerro += "第" + errorId + "<br/>行平台编码已存在；<br/>";
        }
        if (!"".equals(error[0])) {
            totalerro += "第" + error[0] + "<br/>行有必填字段未填写；<br/>";
        }
        if (!"".equals(error[1])) {
            totalerro += "第" + error[1] + "<br/>行有字段超长；<br/>";
        }
        if (!"".equals(errorzd)) {
            totalerro += "第" + errorzd + "<br/>行数据字典填写错误；<br/>";
        }
        totalerro += "请按模板核对数据修改!!<br/>";
        return totalerro;
    }

    public static String getErroresultall(String errorId, String[] error, String errordate, String errorzd) {
        String totalerro = "";
        if (!"".equals(errorId)) {
            totalerro += "第" + errorId + "<br/>行平台编码已存在；<br/>";
        }
        if (!"".equals(error[0])) {
            totalerro += "第" + error[0] + "<br/>行有必填字段未填写；<br/>";
        }
        if (!"".equals(error[1])) {
            totalerro += "第" + error[1] + "<br/>行有字段超长；<br/>";
        }
        if (!"".equals(errordate)) {
            totalerro += "第" + errordate + "<br/>行日期格式错误；<br/>";
        }
        if (!"".equals(errorzd)) {
            totalerro += "第" + errorzd + "<br/>行数据字典填写错误；<br/>";
        }
        totalerro += "请按模板核对数据修改!!<br/>";
        return totalerro;
    }
    //全部校验项
    public static String getErroresultPEA(String errorId, String[] error,  String errorzd,String errorphone,String erroremail,String errorarea,
                                          String errorNumber,String errordate) {
        String totalerro = "";
        if (!"".equals(errorId)) {
            totalerro += "第" + errorId + "<br/>行平台编码已存在；<br/>";
        }
        if (!"".equals(error[0])) {
            totalerro += "第" + error[0] + "<br/>行有必填字段未填写；<br/>";
        }
        if (!"".equals(error[1])) {
            totalerro += "第" + error[1] + "<br/>行有字段超长；<br/>";
        }
        if (!"".equals(errordate)) {
            totalerro += "第" + errordate + "<br/>行日期格式错误；<br/>";
        }
        if (!"".equals(errorzd)) {
            totalerro += "第" + errorzd + "<br/>行数据字典填写错误；<br/>";
        }
        if (!"".equals(errorphone)) {
            totalerro += "第" + errorphone + "<br/>行手机格式错误；<br/>";
        }
        if (!"".equals(erroremail)) {
            totalerro += "第" + erroremail + "<br/>行邮箱格式错误；<br/>";
        }
        if (!"".equals(errorarea)) {
            totalerro += "第" + errorarea + "<br/>行区划代码填写错误；<br/>";
        }
        if (!"".equals(errorNumber)) {
            totalerro += "第" + errorNumber + "<br/>行数字精度错误；<br/>";
        }
        totalerro += "请按模板核对数据修改!!<br/>";
        return totalerro;
    }
    public static String getAllErroresult(String errorId, String errorzd, String errordate, String errorarea, String errorphone, String erroremail, String errorNumber, String errordateRange, String[] error) {
        String totalerro = "";
        if (!"".equals(errorId)) {
            totalerro += "第" + errorId + "<br/>行平台编码已存在；<br/>";
        }
        if (!"".equals(error[0])) {
            totalerro += "第" + error[0] + "<br/>行有必填字段未填写；<br/>";
        }
        if (!"".equals(error[1])) {
            totalerro += "第" + error[1] + "<br/>行有字段超长；<br/>";
        }
        if (!"".equals(errordate)) {
            totalerro += "第" + errordate + "<br/>行日期格式错误；<br/>";
        }
        if (!"".equals(errorzd)) {
            totalerro += "第" + errorzd + "<br/>行数据字典填写错误；<br/>";
        }
        if (!"".equals(errorphone)) {
            totalerro += "第" + errorphone + "<br/>行手机格式错误；<br/>";
        }
        if (!"".equals(erroremail)) {
            totalerro += "第" + erroremail + "<br/>行邮箱格式错误；<br/>";
        }
        if (!"".equals(errorarea)) {
            totalerro += "第" + errorarea + "<br/>行区划代码填写错误；<br/>";
        }
        if (!"".equals(errorNumber)) {
            totalerro += "第" + errorNumber + "<br/>行数字精度错误；<br/>";
        }
        if (!"".equals(errorNumber)) {
            totalerro += "第" + errordateRange + "<br/>行开始日期大于结束日期；<br/>";
        }
        totalerro += "请按模板核对数据修改!!<br/>";
        return totalerro;
    }
}
