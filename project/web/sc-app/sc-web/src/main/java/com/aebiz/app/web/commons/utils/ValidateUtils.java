package com.aebiz.app.web.commons.utils;

import com.aebiz.app.sys.modules.services.SysAreaService;
import com.aebiz.app.sys.modules.services.impl.SysDictServiceImpl;
import com.aebiz.commons.utils.SpringUtil;
import org.nutz.lang.Strings;

import java.util.Map;

/**
 * Created by CZX on 2017/9/25.
 */
public class ValidateUtils {
    public static String testDate(String cell, String pattern, String errordate, Map<String, String> map, int i) {
        if ((Strings.isNotBlank(map.get(cell)) && !DateUtility.isValidDate(Strings.sBlank(map.get(cell)), pattern))) {
            if (Strings.isNotBlank(errordate)) {
                errordate += "," + String.valueOf(i + 1);
            } else {
                errordate += String.valueOf(i + 1);
            }
        }
        return errordate;
    }

    public static String testEmail(String cell, String erroremail, Map<String, String> map, int i) {
        if (Strings.isNotBlank(map.get(cell)) && !Strings.isEmail(map.get(cell))) {
            if (Strings.isNotBlank(erroremail)) {
                erroremail += "," + String.valueOf(i + 1);
            } else {
                erroremail += String.valueOf(i + 1);
            }
        }
        return erroremail;
    }

    public static String testPhone(String cell, String errorphone, Map<String, String> map, int i) {
        if (Strings.isNotBlank(map.get(cell)) && !Strings.isMobile(map.get(cell))) {
            if (Strings.isNotBlank(errorphone)) {
                errorphone += "," + String.valueOf(i + 1);
            } else {
                errorphone += String.valueOf(i + 1);
            }
        }
        return errorphone;
    }

    public static String testMoney(String cell, String errormoney, Map<String, String> map, int i) {
        if (Strings.isNotBlank(map.get(cell)) && !Strings.isMoney(map.get(cell))) {
            if (Strings.isNotBlank(errormoney)) {
                errormoney += "," + String.valueOf(i + 1);
            } else {
                errormoney += String.valueOf(i + 1);
            }
        }
        return errormoney;
    }

    public static String testArea(String cell, String errorarea, Map<String, String> map, int i) {
        if (Strings.isNotBlank(map.get(cell)) && (SpringUtil.getBean(SysAreaService.class).getByCode(Strings.sBlank(map.get(cell))) == null)) {
            if(Strings.isNotBlank(errorarea)){
                errorarea += "," + String.valueOf(i+1);
            }else{
                errorarea += String.valueOf(i+1);
            }
        }
        return errorarea;
    }

    public static String testDateRange(String cellfromdate, String cellenddate, String errorDateRange, Map<String, String> map, int i) {
        int res = map.get(cellfromdate).compareTo(map.get(cellenddate));
        if (res > 0) {
            errorDateRange += "," + String.valueOf(i + 1);
        }
        return errorDateRange;
    }

    public static boolean allTrue(String errorId, String errorzd, String errordate, String errorarea, String errorphone, String erroremail, String errorNumber, String errordateRange, String[] error) {
        if (Strings.isBlank(errorId) &&
                Strings.isBlank(errorzd) &&
                Strings.isBlank(errordate) &&
                Strings.isBlank(errorarea) &&
                Strings.isBlank(errorphone) &&
                Strings.isBlank(erroremail) &&
                Strings.isBlank(errorNumber) &&
                Strings.isBlank(errordateRange) &&
                Strings.isBlank(error[0]) &&
                Strings.isBlank(error[1])) {
            return true;
        } else {
            return false;
        }
    }
    public static String testDic(String cell, String diccode,String errorzd, Map<String, String> map, int i) {
        if (Strings.isNotBlank(map.get(cell))&&SpringUtil.getBean(SysDictServiceImpl.class).getSubCodeMap(diccode).get(map.get(cell)) == null ) {
            if(Strings.isNotBlank(errorzd)){
                errorzd += "," + String.valueOf(i+1);
            }else{
                errorzd += String.valueOf(i+1);
            }
        }
            return errorzd;
    }


    public static String testDicByName(String cell, String diccode,String errorzd, Map<String, String> map, int i) {
        if (Strings.isNotBlank(map.get(cell))&&SpringUtil.getBean(SysDictServiceImpl.class).getAllMapByCode(diccode).get(map.get(cell)) == null ) {
            if(Strings.isNotBlank(errorzd)){
                errorzd += "," + String.valueOf(i+1);
            }else{
                errorzd += String.valueOf(i+1);
            }
        }
        return errorzd;
    }
}
