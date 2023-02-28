/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.utils;

/**
 *
 * @author Dinh Chuong
 */
import com.bros.quanlythuvien.constant.QueryConstant;
import java.util.Collection;
import java.util.Map;



public class ValidateUtils {
    public static boolean isValid(Object obj) {
        boolean isTrue = null != obj && !QueryConstant.EMPTY_STRING.equals(obj.toString());

        if (isTrue) {
            if (obj instanceof String) {
                return true;
            } else if (obj instanceof Integer) {
                return 0 <= Integer.parseInt(obj.toString());
            } else if (obj instanceof Long) {
                return 0 <= Long.parseLong(obj.toString());
            } else if (obj instanceof Collection) {
                return !((Collection<?>) obj).isEmpty();
            }
        }
        return false;
    }

    public static boolean isNotBlank(String str) {
        return null != str && !QueryConstant.EMPTY_STRING.equals(str.trim());
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !collection.isEmpty() &&  collection != null;
    }
    public static boolean isEmptyMap (Map<String, Object> map) {
    	return map == null || map.keySet().isEmpty();
    }
}
