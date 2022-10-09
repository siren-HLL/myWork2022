package com.ruoyi.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.Collator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Siren 2022/9/14 12:11
 * jinhuo字符工具类
 */
@Slf4j
public class UtilString {
    private static final Collator chineseCollator = Collator.getInstance(Locale.CHINA); //中文比较顺序；
    /**
     * 中文算两个长度的支付；
     * @param value 串
     * @return 长度；
     */
    public static int lengthCn(String value) {
        int valueLength = 0;
        String chinese = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中⽂字符，则每个中⽂字符长度为2，否则为1 */
        for (int i = 0; i < value.length(); i++) {
            /* 获取⼀个字符 */
            String temp = value.substring(i, i + 1);
            /* 判断是否为中⽂字符 */
            if (temp.matches(chinese)) {
                /* 中⽂字符长度为2 */
                valueLength += 2;
            } else {
                /* 其他字符长度为1 */
                valueLength += 1;
            }
        }
        return valueLength;
    }
    /**
     * strSrc中寻找第一个strSe并且返回以其分隔的Left部分，汉字长度也为1
     * @param strSrc 源字符串
     * @param strSe 分割字符
     * @return String 返回
     */
    public static String getLeft(String strSrc, String strSe) {
        if (UtilPub.isEmpty(strSrc))
            return "";
        if (UtilPub.isEmpty(strSe))
            strSe = " ";
        String result = "";
        int pos = strSrc.indexOf(strSe);
        if (pos >= 0)
            result = strSrc.substring(0, pos);
        return result;
    }
    /**
     * 返回字符串的左边部分，汉字长度也为1
     * @param strSrc 源串,如果为空，则返回“”；
     * @param count 要获取的右边字符串长度,负数将返回“”，如果count>字符串长度，则返回整个字符串；
     * @return String return
     */
    public static String getLeft(String strSrc, int count) {
        if (UtilPub.isEmpty(strSrc) || count <= 0) {
            return "";
        }
        if (strSrc.length() < count) {
            return strSrc;
        } else {
            return strSrc.substring(0, count);
        }
    }
    //显示指定字符串的前 len个字符或汉字，并追加“...”结尾；
    public static String getLeftTip(String title, int len) {
        if (title.length() > len + 2) {
            return title.substring(0, len) + "...";
        } else {
            return title;
        }
    }
    /**
     * strSrc中寻找第一个strSe并且返回以其分隔的Right部分,汉字长度也为1
     * @param strSrc 源串
     * @param strSe 分隔符,一个字符
     * @return String right部分
     */

    public static String getRight(String strSrc, String strSe) {
        if (UtilPub.isEmpty(strSrc))return "";
        if (UtilPub.isEmpty(strSe))strSe = " ";
        String result = strSrc;
        int pos = strSrc.indexOf(strSe);
        if (pos >= 0)
            result = strSrc.substring(pos + 1);
        return result;
    }
    /**
     * 返回字符串的右边部分，汉字长度也为1
     * @param strSrc 源串
     * @param count 要获取的右边字符串长度,负数将返回“”，如果count>字符串长度，则返回整个字符串；
     * @return String return
     */
    public static String getRight(String strSrc, int count) {
        if (UtilPub.isEmpty(strSrc) || count <= 0) {
            return "";
        }
        int l = strSrc.length();
        if (l <= count) {
            return strSrc;
        }
        return strSrc.substring(l - count);
    }
    /**
     * 取得指定字符串左边的有效数字,首先去掉两边空格
     * @param s 源串
     * @return 串左边的有效数字
     */
    public static String getStringLeftNumber(String s) {
        StringBuilder ret = new StringBuilder();
        int dotCount = 0;
        s = s.trim();
        char[] carr = s.toCharArray();
        for (char aCarr : carr) {
            if (Character.isDigit(aCarr)) {
                ret.append(aCarr);
            } else if (aCarr == '.' && dotCount == 0) {
                ret.append(aCarr);
                dotCount++;
            } else {
                break;
            }
        }
        if (ret.toString().endsWith(".")) {
            ret = new StringBuilder(ret.substring(0, ret.length() - 1));
        }
        return ret.toString();
    }
    //获取子字符串的个数；(区分大小写)
    public static int getSubCounter(String str1, String str2) {
        int counter = 0;
        int sl1 = str1.length();
        int sl2 = str2.length();
        for (int i = 0; i <= sl1 - sl2; i++) {
            //if (str1.substring(i, i + sl2).equalsIgnoreCase(str2)) {
            if (str1.substring(i, i + sl2).equals(str2)) {
                counter++;
            }
        }
        //log.println("子字符串的个数为： " + counter);
        return counter;
    }
    public static String getMidstr(String str,String begin,String end){
        //String str="xxdsdaaaaxxxxxxxxbbbb";
        //String begin="aaaa";
        //String end="bbbb";
        Matcher m= Pattern.compile("^" + begin+"(.*)" +end + "$").matcher(str);
        if(m.find())
            return m.group(1);
        return "";
    }
    //*******************************************************************************************
    /**
     * 左边补齐字符
     * @param src 源串
     * @param pad 补齐字符
     * @param length 最终长度
     * @return 补齐后的字符串
     */
    public static String padLeft(String src, String pad, int length) {
        StringBuilder sb = new StringBuilder(repeatString(pad, length));
        sb.append(src);
        return sb.substring(sb.length() - length);
    }
    public static String padLeft(long src, String pad, int length) {
        StringBuilder sb = new StringBuilder(repeatString(pad, length));
        sb.append(src);
        return sb.substring(sb.length() - length);
    }
    public static String padRight(String src, String pad, int length) {
        StringBuilder sb = new StringBuilder(length * pad.length() + src.length());
        sb.append(src).append(repeatString(pad, length));
        return sb.substring(0, length);
    }
    public static String padRight(long src, String pad, int length) {
        StringBuilder sb = new StringBuilder(length * pad.length());
        sb.append(src).append(repeatString(pad, length));
        return sb.substring(0, length);
    }
    /**
     * 由于jdk1.3提供的replace函数不能满足替换要求，自己写一个
     * @param src 源串
     * @param oldS 将...
     * @param newS 替换成...
     * @return 替换后的字符串
     */
    public static String replaceString(String src, String oldS, String newS) {
        StringBuilder ret = new StringBuilder(64);
        int pos = src.indexOf(oldS);
        while (pos >= 0) {
            ret.append(src, 0, pos).append(newS);
            src = src.substring(pos + oldS.length());
            pos = src.indexOf(oldS);
        }
        ret.append(src);
        return ret.toString();
    }
    /**
     * 取得重复字串
     * @param repeatString 重复字串
     * @param count 重复次数
     * @return String
     */
    public static String repeatString(String repeatString, int count) {
        if (count <= 0) return "";
        StringBuilder ret = new StringBuilder(repeatString.length() * count);
        for (int i = 1; i <= count; i++) {
            ret.append(repeatString);
        }
        return ret.toString();
    }

    /**
     * 去除字符串左边的指定字符串
     * @param src 源字符串
     * @param cut 要去掉的字符串；
     * @return 处理结果
     */
    public static String cutLeft(String src, String cut) {
        if (UtilPub.isEmpty(src) || UtilPub.isEmpty(cut)) {
            return "";
        }
        if (src.startsWith(cut)) {
            return cutLeft(src.substring(cut.length()), cut);
        } else {
            return src;
        }
    }
    /**
     * 去掉字符串右边指定字符
     * @param src 源字符串
     * @param cut 要去掉的字符串；
     * @return 处理结果
     */
    public static String cutRight(String src, String cut) {
        if (UtilPub.isEmpty(src) || UtilPub.isEmpty(cut)) {
            return "";
        }
        while (src.endsWith(cut))
            src = src.substring(0, src.length() - cut.length());

        return src;
    }
    /**
     * 去掉字符串最后一个字符,一般用于去逗号","处理 add by Cr
     * @param str 字符串
     * @return 去掉最后一个支付的字符串
     */
    public static String cutLastChar(String str) {
        if (UtilPub.isEmpty(str))
            return "";
        return str.substring(0, str.length() - 1);
    }
    /**
     * 去掉前后的指定字符 cutLeft + cutRight
     * @param src 源字符串
     * @param cut 要去掉的字符串；
     * @return 处理结果
     */
    public static String cutRightAndLeft(String src, String cut) {
        return UtilString.cutRight(UtilString.cutLeft(src, cut), cut);
    }

    /**
     * Removes all spaces from a string
     * 可以替换大部分空白字符， 不限于空格,\s 可以匹配空格、制表符、换页符等空白字符的其中任意一个
     */
    public static String removeAllSpaces(String str) {
        if(str==null) return "";
        return str.replaceAll("\\s*", "");
    }

    /**
     * Creates a single string from a List of strings seperated by a delimiter.
     * @param list a list of strings to join
     * @param delim the delimiter character(s) to use. (null value will join with no delimiter)
     * @return a String of all values in the list seperated by the delimiter
     */
    public static String join(Collection<String> list, String delim) {
        if(UtilPub.isEmpty(list)) return "";
        StringJoiner joiner = new StringJoiner(delim);
        for(String s:list){
            joiner.add(s);
        }
        return joiner.toString();
    }
    public static String join(Collection<String> list, String delim,boolean removeRepeat) {
        if(!removeRepeat) {
            return join(list,delim);
        }
        Set<String> ll = new HashSet<>(list);
        return join(ll,delim);
    }
    public static String join(String[] list, String delim) {
        if(UtilPub.isEmpty(list)) return "";
        StringJoiner buf = new StringJoiner(",");
        for (String s : list) {
            buf.add(s);
        }
        return buf.toString();
    }

    //将字符串用分隔字符拆分为List，处理其他空格或字符,同时会去掉重复字符串；
    public static String splitToNoRepeatString(String str, String delim) {
        List<String> ss = splitToNoRepeat(str,delim);
        if (ss.size() < 1) return "";
        StringJoiner buf = new StringJoiner(",");
        for (String s : ss) {
            buf.add(s);
        }
        return buf.toString();
    }
    public static List<String> splitToNoRepeat(String str) {
        return split(str,true,true,"[\\\\/,，、|\n]");
    }
    //将字符串用分隔字符拆分为List，处理其他空格或字符,同时会去掉重复字符串；
    public static List<String> splitToNoRepeat(String str, String regex) {
        return split(str,true,true,regex);
    }
    //"[\\\\/,，、|\n]" 逗号
    public static Set<String> splitByComma(String str) {
        if(UtilPub.isEmpty(str)) return new HashSet<>();
        String[] _arr = str.split("[,]");
        Set<String> stringSet = new HashSet<>();
        for (String s : _arr) {
            String s1 = s.trim();
            if (UtilPub.isNotEmpty(s1)) {
                stringSet.add(s1);
            }
        }
        return stringSet;
    }
    public static Set<String> splitByCommaIdString(String str) {
        if(UtilPub.isEmptyId(str)) return new HashSet<>();
        String[] _arr = str.split("[,]");
        Set<String> stringSet = new HashSet<>();
        for (String s : _arr) {
            String s1 = s.trim();
            if (UtilPub.isNotEmptyId(s1)) {
                stringSet.add(s1);
            }
        }
        return stringSet;
    }

    //将字符串用分隔字符拆分为List，处理其他空格或字符,同时会去掉重复字符串；
    public static List<String> split(String str, boolean removeRepeat,boolean removeEmpty, String regex) {
        if(UtilPub.isEmpty(str)) return new ArrayList<>();
        String[] _arr = str.split(regex);
        ArrayList<String> _arr_r = new ArrayList<>();
        for (String s : _arr) {
            String s1 = s.trim();
            if (UtilPub.isNotEmpty(s1)) {
                if(removeRepeat){
                    if(!_arr_r.contains(s1)) _arr_r.add(s1);
                }else{
                    _arr_r.add(s1);
                }
            } else{
                if(!removeEmpty) _arr_r.add("");
            }

        }
        return _arr_r;
    }
    public static List<String> split(String str, boolean removeRepeat,boolean removeEmpty) {
        return split(str,removeRepeat,removeEmpty,"[,，]");
    }
    public static List<String> split(String str, String regex) {
        return split(str,false,false,regex);
    }
    //默认逗号分隔,不去重，不去空格；
    public static List<String> split(String str) {
        return split(str,false,false,"[,，]");
    }
    //默认逗号分隔,去重，去空格；
    public static List<String> splitRemove(String str) {
        return split(str,true,true,"[,，]");
    }

    //boolean,判断(1,y,true,yes)
    public static boolean toBoolean(String v) {
        return "1".equals(v) ||"on".equalsIgnoreCase(v) || "y".equalsIgnoreCase(v) || "true".equalsIgnoreCase(v) || "yes".equalsIgnoreCase(v);
    }
    //boolean,判断（0,n,false,no）
    public static boolean toBooleanByFalse(String v) {
        return !("0".equals(v) || "n".equalsIgnoreCase(v) || "false".equalsIgnoreCase(v) || "no".equalsIgnoreCase(v));
    }
    //中文比较顺序
    public static int chineseCompare(String s1, String s2) {
        return chineseCollator.compare(s1, s2);
    }
    //函数功能: 数字，不含0
    public static boolean isPureNumber(String inputString) {
        return inputString.matches("^[1-9]\\d*$");
    }
    //函数功能: 数字，含0
    public static boolean isNumber(String inputString) {
        return inputString.matches("^[0-9]\\d*$");
    }

    //转换成int；(自动去掉最左边的0)
    public static int toInt(String s){
        if(UtilPub.isEmpty(s)) return 0;
        try {
            if(isNumber(s)){
                String ss = cutLeft(s, "0");
                if(UtilPub.isEmpty(ss)) return 0;
                return Integer.parseInt(ss);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //转换成int(自动去掉最左边的0)
    public static long toLong(String s){
        if(UtilPub.isEmpty(s)) return 0;
        if(isNumber(s)){
            String ss = cutLeft(s, "0");
            return Long.parseLong(ss);
        }
        return 0;
    }

    //首字母大写
    public static String upFirst(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
    //首字母小写
    public static String lowerFirst(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }
    //sql单引号处理；
    public static String escapeSql(String str) {
        if (str == null) {
            return "";
        }
        return StringUtils.replace(str, "'", "''").replace("\\", "\\\\").replace("/", "\\/").replace("\"", "\\\"");
    }
    public static void main(String[] args) {
        String str="plugin-xw-jobs.xml";
        String begin="plugin-";
        String end="-jobs.xml";
        log.info("getMidstr(str,begin,end) = " + getMidstr(str, begin, end));

        //log.println("xxx = " + splitToNoRepeatString("sdkfjsdlfjs;束带- 结发;ddd  dd;   ",";"));
    }
}
