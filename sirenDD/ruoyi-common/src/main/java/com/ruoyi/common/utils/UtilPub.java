package com.ruoyi.common.utils;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author Siren 2022/9/14 12:13
 * jinhuo工具类
 */
public class UtilPub {
    //判断Id是否为空id,有可能是"-","-1"
    static String[] EMPTY_ID_ARR = {"0", "-", "-1", "null", "undefined","'", ","};
    static List<String> EMPTY_ID_List = Arrays.asList(EMPTY_ID_ARR);
    private static final DecimalFormat df2Format = new DecimalFormat("###,###,###,###,##0.00");
    public static <K, V> Map<K, V> toMap(K k1, V v1) {
        Map<K, V> map = new HashMap<>();
        map.put(k1, v1);
        return map;
    }
    public static <K, V> Map<K, V> toMap(K k1, V v1, K k2, V v2) {
        Map<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }
    public static <K, V> Map<K, V> toMap(K k1, V v1, K k2, V v2, K k3, V v3) {
        Map<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        return map;
    }
    public static <K, V> Map<K, V> toMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        Map<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        return map;
    }
    public static <K, V> Map<K, V> toMap(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        Map<K, V> map = new HashMap<>();
        map.put(k1, v1);
        map.put(k2, v2);
        map.put(k3, v3);
        map.put(k4, v4);
        map.put(k5, v5);
        return map;
    }

    /**
     * 判断两个对象是否相等，使用equals方法；
     * @param a 对象a
     * @param b 对象 b
     * @return boolean 只要有一个对象为null,则认为不相等；
     */
    public static boolean isEquals(Object a, Object b) {
        return a != null && a.equals(b);
    }
    @SafeVarargs
    public static <T> T[] asArray(T... args) {
        return args;
    }
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0 ;
    }
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.size() == 0;
    }
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
    public static boolean isEmpty(Object[] o) {
        return o == null || o.length == 0;
    }
    public static boolean isNotEmpty(Object[] o) {
        return !isEmpty(o);
    }
    public static boolean isEmptyStr(String str) {
        return str == null || str.trim().length() == 0 || "-".equals(str);
    }
    public static boolean isNotEmptyStr(String str) {
        return !isEmptyStr(str);
    }
    public static boolean isEmptyId(String id) {
        return id == null || id.trim().length() == 0 || id.contains("(") || id.contains("（") || EMPTY_ID_List.contains(id);
    }
    public static boolean isNotEmptyId(String str) {
        return !isEmptyId(str);
    }
    public static boolean isEqualsStr(String a,String b){
        if(a==null) return false;
        if(b==null) return false;
        return a.equals(b);
    }
    /**
     * 获取浮点数(有错误默认为0)，可以识别金额中的逗号格式
     * @param str 带转换的字符串
     * @return 浮点数
     */
    public static double getDoubleIgnoreErr(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.equals("")) return 0;
        str = str.replaceAll(",", "").replaceAll("，", "");
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0;
        }
    }
    /**
     * 获取浮点数(有错误默认为0)，可以识别金额中的逗号格式
     * @param str 带转换的字符串
     * @return 浮点数
     */
    public static float getFloatIgnoreErr(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.equals("")) return 0;
        str = str.replaceAll(",", "").replaceAll("，", "");
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 得到int 获取转换的int值，有错返回0
     * @param str 带转换的字符串
     * @return int
     */
    public static int getIntIgnoreErr(String str) {
        return getIntIgnoreErr(str, 0);
    }
    public static int getIntIgnoreErr(String str, int defValue) {
        if (str == null)
            return defValue;
        str = str.trim();
        if (str.equals(""))
            return defValue;
        str = str.replaceAll(",", "").replaceAll("，", "");
        if (str.contains("."))
            str = str.substring(0, str.indexOf('.'));
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 得到Long 获取转换的Long值，有错返回0
     * @param str 带转换的字符串
     * @return long
     */
    public static long getLongIgnoreErr(String str) {
        return getLongIgnoreErr(str, 0L);
    }
    public static long getLongIgnoreErr(String str, long defValue) {
        if (str == null)
            return defValue;
        str = str.trim();
        if (str.equals(""))
            return defValue;
        str = str.replaceAll(",", "").replaceAll("，", "");
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * Func:为空，返回""
     * @param str 带检查的字符串
     * @return ""
     */
    public static String checkNull(String str) {
        if (isEmpty(str))
            return "";
        return str.trim();
    }
    public static String checkNull(Integer n) {
        return checkNull(n, true);
    }
    public static String checkNull(Integer n, boolean zeroToEmpty) {
        int v;
        if (n == null || n == 0)
            v = 0;
        else
            v = n;
        return v != 0 ? String.valueOf(v) : zeroToEmpty ? "" : "0";
    }
    public static String checkNull(Long n) {
        return checkNull(n, 0L);
    }
    public static String checkNull(Long n, long defValue) {
        return n != null ? String.valueOf(n) : String.valueOf(defValue);
    }
    public static int checkNull(Integer n, Integer defValue) {
        return n != null ? n : defValue;
    }
    public static double checkNull(Double n) {
        return checkNull(n, 0d);
    }
    public static double checkNull(Double n, double defValue) {
        return (n != null) ? n : defValue;
    }

    /**
     * Func:str字符串是否包含subStr
     * @param str 字符串
     * @param subStr 待查找的串
     * @return boolean
     */
    public static boolean isContain(String str, String subStr) {
        return !isEmpty(str) && !isEmpty(subStr) && str.contains(subStr);
    }
    /**
     * 功能：null过滤，返回默认值
     * @param strValue 带检查的字符串
     * @param defaultValue 为空返回的字符串
     * @return str
     */
    public static String checkNull(String strValue, String defaultValue) {
        return strValue == null ? defaultValue : strValue;
    }
    /**
     * 功能：空值过滤，返回默认值
     * @param strValue 待检查的字符串
     * @param defaultValue 为空返回的字符串
     * @return str
     */
    public static String checkEmpty(String strValue, String defaultValue) {
        return isEmpty(strValue) ? defaultValue : strValue;
    }

    /**
     * 功能：空值过滤，返回默认值
     * @param strValue 待检查的字符串
     * @param defaultValue 为空返回的字符串
     * @return str
     */
    public static String checkEmptyStr(String strValue, String defaultValue) {
        return isEmptyStr(strValue) ? defaultValue : strValue;
    }
    public static String checkEmptyObject(Object obj, String defaultValue) {
        if (obj == null) return defaultValue;
        String strValue = obj.toString();
        return isEmptyStr(strValue) ? defaultValue : strValue;
    }
    public static String checkEmptyId(String strValue, String defaultValue) {
        return isEmptyId(strValue) ? defaultValue : strValue;
    }

    /** List是否包含str
     * @param str 字符串
     * @param lt 列表
     * @return boolean
     */
    public static boolean isInList(String str, List lt) {
        if(isEmpty(str) || isEmpty(lt)){
            return false;
        }
        return lt.contains(str);
    }
    /**
     * 比较两个数组长度是否相等.
     */
    public static boolean arrayLengthIsSame(Object[] a, Object[] b) {
        int size_a = (a == null ? 0 : a.length);
        int size_b = (b == null ? 0 : b.length);
        return size_a == size_b;
    }
    public static String getOrigMsg(Throwable e) {
        String s = e.getMessage();
        Throwable t = e.getCause();
        while (t != null) {
            s = t.getMessage();
            t = t.getCause();
        }
        return s;
    }

    //从map中取值，key不区分大小写
    public static <V> V getValueFromMapIgnoreCase(Map<String, V> map, String key) {
        for (String k : map.keySet()) {
            if (k.equalsIgnoreCase(key)) {
                return map.get(k);
            }
        }
        return null;
    }
    //将object转换为对应类型的list
    public static <T> List<T> getObjToList(Object obj, Class<T> cla){
        List<T> list = new ArrayList<T>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                list.add(cla.cast(o));
            }
            return list;
        }
        return null;
    }


    /**
     * 字符串转Map
     * @param str 源串，格式 name1=value1&name2=value2
     * @param map 目标map
     * @param upperKey 是否需要将key转为大写
     */
    public static void strToMap(String str, Map<String, String> map, boolean upperKey) {
        String[] ss = str.split("&");
        String[] sp;
        String key;
        for (String s : ss) {
            if (UtilPub.isEmpty(s))
                continue;
            sp = s.split("=");
            if (upperKey)
                key = sp[0].toUpperCase();
            else
                key = sp[0];
            if (sp.length > 1) {
                map.put(key, sp[1]);
            } else
                map.put(key, "");
        }
    }

    //计算一个字符串source的长度(英文和数字占一个长度,其他字符(中文和特殊符号等)占2个长度)
    public static int strLength(String source) {
        int totalLength = source.length();// 总长度
        String otherStr = source.replaceAll("\\d|\\w", "");// 去掉字符串中的数字和英文字符
        int otherLength = otherStr.length();// 占2个长度的字符
        return (totalLength - otherLength) + otherLength * 2;
    }

    public static String getAmountStr(double amount) {
        return df2Format.format(amount);
    }
    public static String getAmountStr(double amount, boolean zeroShowEmpty) {
        if (zeroShowEmpty && UtilMath.isEqualsZero(amount))
            return "";
        else
            return df2Format.format(amount);
    }
    public static String getIntStr(int d) {
        if (d == 0)
            return "";
        else
            return String.valueOf(d);
    }

    /**
     * 转换为boolean
     */
    public static boolean toBoolean(String v, boolean b) {
        if (v == null) return b;
        return "1".equals(v) || "true".equalsIgnoreCase(v) || "Y".equalsIgnoreCase(v) || "yes".equalsIgnoreCase(v);
    }
}
