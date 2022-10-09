package com.ruoyi.common.utils.time;

import com.ruoyi.common.utils.UtilPub;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Siren 2022/9/26 11:12
 */
public class UtilDateTime {
    public final static java.text.SimpleDateFormat stdTimeFormat = new java.text.SimpleDateFormat("HH:mm:ss");
    public final static java.text.SimpleDateFormat stdHmFormat = new java.text.SimpleDateFormat("HH:mm");
    public final static java.text.SimpleDateFormat stdDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
    public final static java.text.SimpleDateFormat stdCnDateFormat = new java.text.SimpleDateFormat("yyyy年MM月dd日");
    public final static java.text.SimpleDateFormat stdDatetimeFormatMinute = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final static java.text.SimpleDateFormat stdDatetimeFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final static java.text.SimpleDateFormat stdLongDatetimeFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public final static java.text.SimpleDateFormat simpleTimeFormat = new java.text.SimpleDateFormat("HHmmss");
    public final static java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyyMMdd");
    public final static java.text.SimpleDateFormat simpleYearMonthFormat = new java.text.SimpleDateFormat("yyyyMM");
    public final static java.text.SimpleDateFormat simpleDatetimeFormat = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
    public final static java.text.SimpleDateFormat simpleLongDatetimeFormat = new java.text.SimpleDateFormat("yyyyMMddHHmmssS");
    final static int[] m_seasonBeginTable = new int[]{ //季节；
            Calendar.JANUARY, Calendar.JANUARY, Calendar.JANUARY,
            Calendar.APRIL, Calendar.APRIL, Calendar.APRIL,
            Calendar.JULY, Calendar.JULY, Calendar.JULY,
            Calendar.OCTOBER, Calendar.OCTOBER, Calendar.OCTOBER
    };
    private static final TimeZone mTimeZone = TimeZone.getTimeZone("Asia/Shanghai");

    static {
        stdTimeFormat.setTimeZone(mTimeZone);
        stdDateFormat.setTimeZone(mTimeZone);
        stdDatetimeFormat.setTimeZone(mTimeZone);
        stdLongDatetimeFormat.setTimeZone(mTimeZone);

        simpleTimeFormat.setTimeZone(mTimeZone);
        simpleDateFormat.setTimeZone(mTimeZone);
        simpleDatetimeFormat.setTimeZone(mTimeZone);
        simpleLongDatetimeFormat.setTimeZone(mTimeZone);
        simpleYearMonthFormat.setTimeZone(mTimeZone);
    }

    /**
     * 比较两个日期是否相等
     */
    public static int compareDateLevelDay(java.util.Date a, java.util.Date b) {
        // java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
        // date中,会保留有时间秒数.
        // java.sql.Date a = java.sql.Date.valueOf("2007-01-01");
        // a中,就没有时间秒数
        // 所以,在比较时就统一按字符串[yyyy-MM-dd]进行比较而不直接用a.compareTo(b)
        String stra = toStdDateString(a);
        String strb = toStdDateString(b);
        return stra.compareTo(strb);
    }

    /**
     * Return a Timestamp for right now
     * @return Timestamp for right now
     */
    public static Timestamp nowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取今日
     * @return 获取今日java.sql.Date类型日期；
     */
    public static Date nowDate() {
        return new Date(System.currentTimeMillis());
    }

    /** 取得系统今天的时间串 "yyyy-MM-dd HH:mm:ss". */
    public static String nowStdDateTimeString() {
        return stdDatetimeFormat.format(nowTimestamp());
    }
    public static String nowStdDateTimeMinuteString() {
        return stdDatetimeFormatMinute.format(nowTimestamp());
    }
    /** 取得系统今天的时间串,包含毫秒 "yyyy-MM-dd HH:mm:ss.SSS".  */
    public static String nowStdLongDateTimeString() {
        return stdLongDatetimeFormat.format(nowTimestamp());
    }

    /**获取标准今日日期串 yyyy-MM-dd；*/
    public static String nowStdDateString() {
        return stdDateFormat.format(nowTimestamp());
    }
    /**获取标准今日日期串、 @param splite 分隔串 今日日期字符串 yyyy MM dd，*/
    public static String nowStdDateString(String splite) {
        java.text.SimpleDateFormat stdDateFormat = new java.text.SimpleDateFormat("yyyy" +splite + "MM" +splite+ "dd");
        return stdDateFormat.format(nowTimestamp());
    }
    /**获取标准今日日期串 yyyy年MM月dd日； */
    public static String nowStdCnDateString() {
        return stdCnDateFormat.format(nowTimestamp());
    }

    /**获取当前标准时间串 HH:mm:ss */
    public static String nowStdTimeString() {
        return stdTimeFormat.format(nowTimestamp());
    }
    /**获取当前标准时间串 HH:mm */
    public static String nowStdMMssString() {
        return stdHmFormat.format(nowTimestamp());
    }
    /**返回当前标准年月 YYYY-MM */
    public static String nowStdYearMonth() {
        return nowStdDateString().substring(0, 7);
    }
    /**返回年月 YYYYMM */
    public static String StdYearMonth() {
        return simpleYearMonthFormat.format(nowTimestamp());
    }
    /**取得系统今天的时间串:"yyyyMMddHHmmss" */
    public static String nowDateTimeString() {
        return simpleDatetimeFormat.format(nowTimestamp());
    }

    /** 取得系统今天的时间串(包含毫秒) :"yyyyMMddHHmmssS" */
    public static String nowLongDateTimeString() {
        return simpleLongDatetimeFormat.format(nowTimestamp());
    }
    /** 获取今日日期串，不带分隔符  yyyyMMdd； */
    public static String nowDateString() {
        return simpleDateFormat.format(nowTimestamp());
    }
    /** 获取当前时间串，不带分隔符 HHmmss； */
    public static String nowTimeString() {
        return simpleTimeFormat.format(nowTimestamp());
    }

    /** 取当前时间数字串，例如20100909121359 标识2010年09月09日 12时13分59秒 */
    public static Long nowDateTimeNumber() {
        return Long.valueOf(nowDateTimeString());
    }
    /** 获取今日日期的数字，如20100909 */
    public static Integer nowDateNumber() {
        return Integer.valueOf(nowDateString());
    }
    public static String nowYear() {
        return nowStdDateString().substring(0, 4);
    }
    public static String nowYear(String stddate) {
        return stddate.substring(0, 4);
    }
    //两位的月 如11
    public static String nowMonth2() {
        return nowStdDateString().substring(5, 7);
    }
    //获取当前日期所处月,去掉了前置的0.
    public static int nowMonth() {
        String month = nowMonth2();
        if (month.charAt(0) == '0') {
            return Integer.parseInt(String.valueOf(month.charAt(1)));
        }
        return Integer.parseInt(month);
    }
    public static String nowYearMonthL() {
        return nowStdDateString().substring(0, 7).replaceAll("-", "");
    }
    public static int nowDay() {
        Calendar calendar = getGMT8Calendar();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public static int nowHour() {
        Calendar calendar = getGMT8Calendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 判断strDate 是否是日期格式的字符串(支持如 yyyy-mm-dd)。
     * @param strDate 字符串，判断其是否为日期格式。
     * @return boolean 如果为日期格式则返回=true;
     */
    public static boolean isDateFormat(String strDate) {
        return isDateFormat(strDate, "YYYY-MM-DD");
    }
    /**
     * 判断strDate 是否是日期格式的字符串。
     * @param strDate 字符串，判断其是否为日期格式。
     * @param format 字符串，YYYY-MM,YYYYMM,YYYYMMDD,YYYY-MM-DD,YYYY-MM-DD:HH,YYYY-MM-DD HH:MM,YYYY-MM-DD HH:MM:SS, 空表示所有上述格式,非上述内容将默认为YYYY-MM-DD；
     * @return boolean 如果为日期格式则返回=true;
     */
    public static boolean isDateFormat(String strDate, String format) {
        if (UtilPub.isEmpty(strDate)) {return false;}
        if (UtilPub.isEmpty(format)) {format = "YYYY-MM-DD";}
        switch (format) {
            case "YYYY-MM":
                return strDate.matches("\\d{4}-\\d{2}") && isDateValue(strDate.substring(0, 4), strDate.substring(5), "01");
            case "YYYYMM":
                return strDate.matches("\\d{6}") && isDateValue(strDate.substring(0, 4), strDate.substring(4), "01");
            case "YYYYMMDD":
                return strDate.matches("\\d{8}") && isDateValue(strDate.substring(0, 4), strDate.substring(4, 6), strDate.substring(6));
            case "YYYY-MM-DD":
                return strDate.matches("\\d{4}-\\d{2}-\\d{2}") && isDateValue(strDate.substring(0, 4), strDate.substring(5, 7), strDate.substring(8));
            case "YYYY-MM-DD:HH":
                return strDate.matches("\\d{4}-\\d{2}-\\d{2}:[0-5][0-9]") && isDateValue(strDate.substring(0, 4), strDate.substring(5, 7), strDate.substring(8, 10));
            case "YYYY-MM-DD HH:MM":
                return strDate.matches("\\d{4}-\\d{2}-\\d{2} [0-5][0-9]:[0-5][0-9]") && isDateValue(strDate.substring(0, 4), strDate.substring(5, 7), strDate.substring(8, 10));
            case "YYYY-MM-DD HH:MM:SS":
                return strDate.matches("\\d{4}-\\d{2}-\\d{2} [0-5][0-9]:[0-5][0-9]:[0-5][0-9]") && isDateValue(strDate.substring(0, 4), strDate.substring(5, 7), strDate.substring(8, 10));
            case "HHmmss":
                return strDate.matches("[0-5][0-9][0-5][0-9][0-5][0-9]") && isTimeValue(strDate.substring(0, 2), strDate.substring(2, 4), strDate.substring(4));
            default:
                return false;
        }
    }
    public static boolean isDateValue(String year, String month, String day) {
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        s.setLenient(false);//这个的功能是不把1996-13-3 转换为1997-1-3
        try {
            s.parse(year + "-" + month + "-" + day);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    public static boolean isTimeValue(String hour, String min, String sec) {
        SimpleDateFormat s = new SimpleDateFormat("HH:mm:ss");
        s.setLenient(false);
        try {
            s.parse(hour + ":" + min + ":" + sec);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 得到如下字符串 "YYYY-MM-DD HH:MM:SS"
     * @param d 时间戳，如果传入为null,则返回“”；
     * @return 日期时间串
     */
    public static String toStdDateTimeString(java.util.Date d) {
        if (d == null) return "";
        return stdDatetimeFormat.format(d);
    }

    /** 得到如下字符串 "YYYY-MM-DD HH:MM" 分钟
     * @param d long, 例如20110304131101
     * @return 日期时间串，如：2011-03-04 13:11:01
     */
    public static String toStdDateTimeMinuteString(long d) {
        String v = String.valueOf(d);
        return v.substring(0, 4) + "-" + v.substring(4, 6) + "-" + v.substring(6, 8) + " " + v.substring(8, 10) + ":" + v.substring(10, 12);
    }
    /** 转换时间对象 "yyyy-MM-dd HH:mm". */
    public static String toStdDateTimeMinuteString(Date date) {
        return stdDatetimeFormatMinute.format(date);
    }

    /**
     * 得到如下字符串 "YYYY-MM-DD HH:MM:SS"
     * @param d long, 例如20110304131101
     * @return 日期时间串，如：2011-03-04 13:11:01
     */
    public static String toStdDateTimeString(long d) {
        return toStdDateTimeString(String.valueOf(d));
    }
    /**
     * 得到如下字符串 "YYYY-MM-DD HH:MM:SS"
     * @param d String, 例如20110304131101
     * @return 日期时间串，如：2011-03-04 13:11:01
     */
    public static String toStdDateTimeString(String d) {
        if (d == null || d.length() != 14) return "-";
        return toStdDateString(d.substring(0, 8)) + " " + d.substring(8, 10) + ":" + d.substring(10, 12) + ":" + d.substring(12);
    }

    /**带毫秒的； 例如20110304131101xxx,转换成 yyyy-MM-dd HH:mm:ss.SSS
     * @param d 例如20110304131101xxx
     * @return x
     */
    public static String toStdDateTimeString17(String d) {
        if (d == null || d.length() != 17) return "-";
        return toStdDateString(d.substring(0, 8)) + " " + d.substring(8, 10) + ":" + d.substring(10, 12) + ":" + d.substring(12,14) + "." + d.substring(14,17);
    }

    /**得到如下格式字符串 "YYYY-MM-DD" 如果传入为null,则返回“”；*/
    public static String toStdDateString(Date d) {
        if (d == null) return "";
        return stdDateFormat.format(d);
    }
    /**
     * 将yyyyMMdd变为yyyy-mm-dd
     * @param s 原日期串，默认用-进行分割
     * @return yyyy-mm-dd
     */
    public static String toStdDateString(String s) {
        return toStdDateString(s, "-", false);
    }
    /**
     * 将yyyyMMdd日期串用分隔符进行分割
     * @param s 原日期串，默认用-进行分割
     * @param split 分隔符；
     * @param returnEmptyIfErrDate 如果不是8位的日期串，则控制是反馈“”，还是返回原串；
     * @return 用分隔符，如“-”，“.”进行分割后的日期串；
     */
    public static String toStdDateString(String s, String split, boolean returnEmptyIfErrDate) {
        try {
            if (s == null || s.length() < 8) {
                return returnEmptyIfErrDate ? "" : s;  //例如，如果是0，仍然会返回0
            }
            s = s.replace("-", ""); //去掉一下可能存在的-；
            return s.substring(0, 4) + split + s.substring(4, 6) + split + s.substring(6, 8);
        } catch (Exception e) {
            return "";
        }
    }
    /**  转换为 年 月 日 @param s yyyymmdd,或者yyyy-mm-dd都可以；*/
    public static String toStdDateCnString(String s, boolean returnEmptyIfErrDate) {
        try {
            if (s == null || s.length() < 8) {
                return returnEmptyIfErrDate ? "" : s;  //例如，如果是0，仍然会返回0
            }
            s = s.replace("-", ""); //去掉一下可能存在的-；
            return s.substring(0, 4) + "年" + s.substring(4, 6) + "月" + s.substring(6, 8) + "日";
        } catch (Exception e) {
            return "";
        }
    }
    /**  转换为 月 日 @param s yyyymmdd,或者yyyy-mm-dd都可以；*/
    public static String toStdDateMMDDCnString(String s, boolean returnEmptyIfErrDate) {
        try {
            if (s == null || s.length() < 8) {
                return returnEmptyIfErrDate ? "" : s;  //例如，如果是0，仍然会返回0
            }
            s = s.replace("-", ""); //去掉一下可能存在的-；
            return s.substring(4, 6) + "月" + s.substring(6, 8) + "日";
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 将yyyy-M-d转成yyyy-MM-dd格式
     * @param s x
     * @return x
     */
    public static String toStdDateStringEx(String s) {
        try {
            String[] ss = s.split("-");
            if (ss.length < 3) return "";
            return ss[0] + "-" + (ss[1].length() == 2 ? ss[1] : ("0" + ss[1])) + "-" + (ss[2].length() >= 2 ? ss[2] : ("0" + ss[2]));
        } catch (Exception e) {
            return "";
        }
    }
    /**
     * 将20010101类型数字分割为2001-01-01，默认分隔符为"-";
     * @param l long
     * @return yyyy-mm-dd
     */
    public static String toStdDateString(long l) {
        return toStdDateString(String.valueOf(l), "-", true);
    }
    /**
     * 将20010101格式的数据转换成2001?01?01格式的显示，?标识分隔符
     * @param l 日期
     * @param split 分隔符；
     * @param returnEmptyIfErrDate 如果不是8位的日期串，则控制是反馈“”，还是返回原串；
     * @return 2001-01-01
     */
    public static String toStdDateString(long l, String split, boolean returnEmptyIfErrDate) {
        return toStdDateString(String.valueOf(l), split, returnEmptyIfErrDate);
    }

    /**
     * 得到如下字符串 "HH:mm:ss"
     * @param date 时间戳，如果传入为null,则返回为当前日期；
     * @return 时间串
     */
    public static String toStdTimeString(java.util.Date date) {
        if (date == null) date = nowDate();
        return stdTimeFormat.format(date);
    }
    /**
     * 将日期变为"yyyyMMdd"
     * @param date 日期
     * @return 字符串，如果传入参数为null,则返回当前日期；
     */
    public static String toDateString(java.util.Date date) {
        if (date == null) date = nowDate();
        return simpleDateFormat.format(date);
    }
    /**
     * 得到如下字符串 "HHmmss"
     * @param date 时间戳 如果传入为NULL,则返回当前日期；
     * @return 时间串
     */
    public static String toTimeString(java.util.Date date) {
        if (date == null) date = nowDate();
        return simpleTimeFormat.format(date);
    }
    /**
     * 得到长日期时间字符[yyyyMMddHHmmss]
     * @param date 时间戳，如果传入为null,则返回当前日期
     * @return 日期时间串
     */
    public static String toDateTimeString(java.util.Date date) {
        if (date == null) date = nowDate();
        return simpleDatetimeFormat.format(date);
    }
    /**
     * 得到长日期时间字符[yyyyMMddHHmmssS]
     * @param date 时间戳，如果传入为null,则返回当前日期
     * @return 日期时间串，长
     */
    public static String toLongDateTimeString(java.util.Date date) {
        if (date == null) date = nowDate();
        return simpleLongDatetimeFormat.format(date);
    }

    /** 2010-01-01,2011.01.01,2011年01月01日, 2010-1-2 等转换成数字(YYYYMMDD)；*/
    public static int toDateNumber(String ds) {
        if (UtilPub.isEmptyId(ds)) return 0;
        try {
            String vdata = ds.replaceAll("[.年月/]","-");
            vdata = vdata.replaceAll("[日]","");
            String stand = toStdDateStringEx(vdata);
            return Integer.parseInt(stand.replace("-","").substring(0,8));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
    /** 转换成数字(YYYYMMDD)；*/
    public static int toDateNumber(Date ds) {
        return Integer.parseInt(stdDateFormat.format(ds).replaceAll("-",""));
    }
    /** yyyy-mm-dd 转换成 date,有可能传递进来的日期串是yyyy-m-d*/
    public static Date toDate(String src) {
        try {
            if (UtilPub.isEmpty(src)) return null;
            return stdDateFormat.parse(toStdDateStringEx(src));
        } catch (Exception e) {
            return null;
        }
    }
    /** yyyy-mm-dd HH:MM:SS 转换成date*/
    public static Date toDateTime(String src) {
        try {
            if (UtilPub.isEmpty(src)) return null;
            return stdDatetimeFormat.parse(src);
        } catch (Exception e) {
            return null;
        }
    }

    /** yyyymmdd 转换成 Date */
    public static Date toDate(Integer src) {
        try {
            return simpleDateFormat.parse(src.toString());
        } catch (Exception e) {
            return null;
        }
    }
    /**yyyyMMddHHmmss*/
    public static Date toDate(Long src) {
        try {
            return simpleDatetimeFormat.parse(src.toString());
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 将字符串转换成Java.sql.Date,不能转换，则返回当前日期
     * @param src 源串
     * @return java.sql.Date
     */
    public static Date toDateDefaultNow(String src) {
        try {
            if (UtilPub.isEmpty(src)) return nowDate();
            return java.sql.Date.valueOf(src);
        } catch (Exception e) {
            return nowDate();
        }
    }
    /**
     * 将字符串转换成Java.sql.Date,不能转换，则返回当前日期
     * @param datetime 源串
     * @return java.sql.Date
     */
    public static Date toDateDefaultNow(java.sql.Timestamp datetime) {
        if (datetime == null) return nowDate();
        return new java.sql.Date(datetime.getTime());
    }
    /**
     * Makes a Date from separate ints for month, day, year, hour, minute, and second.
     * @param month The month int
     * @param day The day int
     * @param year The year int
     * @param hour The hour int
     * @param minute The minute int
     * @param second The second int
     * @return A Date made from separate ints for month, day, year, hour, minute, and second.
     */
    public static Date toDate(int month, int day, int year, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        try {
            //noinspection MagicConstant
            calendar.set(year, month - 1, day, hour, minute, second);
        } catch (Exception e) {
            return null;
        }
        return new java.sql.Date(calendar.getTime().getTime());
    }
    /**
     * Makes a Date from separate Strings for month, day, year, hour, minute, and second.
     * @param monthStr The month String
     * @param dayStr The day String
     * @param yearStr The year String
     * @param hourStr The hour String
     * @param minuteStr The minute String
     * @param secondStr The second String
     * @return A Date made from separate Strings for month, day, year, hour, minute, and second.
     */
    public static Date toDate(String monthStr, String dayStr, String yearStr, String hourStr, String minuteStr, String secondStr) {
        int month, day, year, hour, minute, second;
        try {
            month = Integer.parseInt(monthStr);
            day = Integer.parseInt(dayStr);
            year = Integer.parseInt(yearStr);
            hour = Integer.parseInt(hourStr);
            minute = Integer.parseInt(minuteStr);
            second = Integer.parseInt(secondStr);
        } catch (Exception e) {
            return null;
        }
        return toDate(month, day, year, hour, minute, second);
    }

    public static Calendar getGMT8Calendar() {
        return Calendar.getInstance(mTimeZone);
    }
    /** 用中方格式化年月日 @param yearMonth YYYYMM*/
    public static String fmtZHCNYearMonth(String yearMonth) {
        if (UtilPub.getIntIgnoreErr(yearMonth) > 190000)
            return String.format("%s-%s", yearMonth.substring(0, 4), yearMonth.substring(4));
        return "";
    }
    /** 用中方格式化年月日@param stdYearMonthDay YYYY-MM-DD */
    public static String fmtZHCNYearMonthDay(String stdYearMonthDay) {
        return String.format("%s年%s月%s日", stdYearMonthDay.substring(0, 4), stdYearMonthDay.substring(5, 7), stdYearMonthDay.substring(8));
    }
    /** 获取星期@param datetime 日期 例:20171017 @return String 例:星期二 */
    public static String toCnWeekByyyyymmdd(String datetime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0) w = 0;
        return weekDays[w];
    }

    /** 判断时间范围：：：if(isInTime("00:00-18:00", "01:01" ) {}
     * @param scopeTime，如 00:00-07:00,07:00-19:00
     * @param curTime，如 01:19
     * @return boolean
     */
    public static boolean isInTime(String scopeTime, String curTime) {
        if (UtilPub.isEmptyStr(scopeTime)) return false;
        scopeTime = scopeTime.replaceAll("[：.]", ":");
        scopeTime = scopeTime.replaceAll("，", ",");

        if(!isOkTimeScorpeString(scopeTime)) return false;
        String[] ss = scopeTime.split(",");
        try {
            long now = stdHmFormat.parse(curTime).getTime();
            for (String sourceTime : ss) {
                String[] args = sourceTime.trim().split("-");
                if (args[1].equals("00:00") || args[1].equals("0:0") || args[1].equals("00:0") || args[1].equals("0:00")) {
                    args[1] = "24:00";
                }
                long start = stdHmFormat.parse(args[0]).getTime();
                long end = stdHmFormat.parse(args[1]).getTime();
                boolean isIn;
                if (end < start) {
                    isIn = now < end || now >= start;
                } else {
                    isIn = now >= start && now < end;
                }
                if (isIn) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            //throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
        }
        return false;
    }
    //验证是否有效的时间范围字符串如：00:00-07:00,07:00-19:00
    public static boolean isOkTimeScorpeString(String sourceTime){
        if(UtilPub.isEmptyStr(sourceTime)) return false;
        String[] ss = sourceTime.split(",");
        for (String s : ss) {
            if (!sourceTime.contains("-") || !sourceTime.contains(":")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取指定日期之间的每一天，包含开始和结束日期；
     * @param dBegin 开始日期
     * @param dEnd 结束日期
     * @return 日期列表
     */
    public static List<Date> findDates(Date dBegin, Date dEnd){
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())){
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }
    //获取本周每一天
    public static List<Date> findTheWeekDatas() throws ParseException {
        String yz_time=getTimeInterval(new Date());//获取本周时间
        String[] array =yz_time.split(",");
        String start_time=array[0];//本周第一天
        String end_time=array[1];  //本周最后一天
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = sdf.parse(start_time);
        Date dEnd = sdf.parse(end_time);
        return findDates(dBegin, dEnd);
    }
    //获取本周时间，xxx , xxx
    public static String getTimeInterval(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String imptimeBegin = sdf.format(cal.getTime());
        cal.add(Calendar.DATE, 6);
        String imptimeEnd = sdf.format(cal.getTime());
        return imptimeBegin + "," + imptimeEnd;
    }
    //是否周末
    public static  boolean isWeekend(Date date){
        int weekDay = UtilDateTimeDL.getDayOfWeek(date);
        return weekDay>5;  //6,7
    }

    public static void main(String[] args) {
        try {
            List<Date> dateList = findTheWeekDatas();
            for (Date date : dateList) {
                System.out.println("date = " + date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        /*
        //2010-01-01,2011.01.01,2011年01月01日
        String aaa = "20：21-29.11".replaceAll("[：.]", ":");
        System.out.println("aaa = " + aaa);
        try {
            System.out.println("04:59 = " + stdHmFormat.parse("04:59").getTime());
            System.out.println("06:59 = " + stdHmFormat.parse("06:59").getTime());
            System.out.println("6:59 = " + stdHmFormat.parse("6:59").getTime());
            System.out.println("16:59 = " + stdHmFormat.parse("16:59").getTime());
            System.out.println("18:59 = " + stdHmFormat.parse("18:59").getTime());
        } catch (ParseException e) {
            System.out.println("e = " + e);
        }
        */
    }
}
