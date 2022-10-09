package com.ruoyi.common.utils.time;

import com.ruoyi.common.enums.IncludingBeforeAfterEnum;
import com.ruoyi.common.utils.UtilPub;
import com.ruoyi.common.utils.UtilString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author Siren 2022/9/26 11:13
 */
@Slf4j
public class UtilDateTimeDL {
    public final static java.text.SimpleDateFormat simpleTimeFormat = new java.text.SimpleDateFormat("HHmmss");
    public final static java.text.SimpleDateFormat simpleStdDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
    public final static java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyyMMdd");
    public final static java.text.SimpleDateFormat simpleDatetimeFormat = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
    public final static java.text.SimpleDateFormat simpleLongDatetimeFormat = new java.text.SimpleDateFormat("yyyyMMddHHmmssS");
    public final static String[] weekOfDays = {"日", "一", "二", "三", "四", "五", "六"};
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        log.info("当前时间 getTimesNowL：" + getTimesNowL());
        log.info("当天0点时间 getTimesMorningL：" + getTimesMorningL());
        log.info("昨天0点时间 getYesterdayMorningL：" + getYesterdayMorningL());
        log.info("当天24点时间 getTimesNightL：" + getTimesNightL());
        log.info("本周周一0点时间 getTimesWeekMorningL：" + getTimesWeekMorningL());
        log.info("本周周日24点时间 getTimesWeekNightL：" + getTimesWeekNightL());
        log.info("本月初0点时间 getTimesMonthMorningL：" + getTimesMonthMorningL());
        log.info("本月未24点时间 getTimesMonthNightL：" + getTimesMonthNightL());
        log.info("上月初0点时间 getLastMonthStartMorningL：" + getLastMonthStartMorningL());
        log.info("近7天时间 getWeekFromNowL：" + getWeekFromNowL());
        log.info("本季度开始点时间 getCurrentQuarterStartTimeL：" + getCurrentQuarterStartTimeL());
        log.info("本季度结束点时间 getCurrentQuarterEndTimeL：" + getCurrentQuarterEndTimeL());
        log.info("本年开始点时间 getCurrentYearStartTimeL：" + getCurrentYearStartTimeL());
        log.info("本年结束点时间 getCurrentYearEndTimeL：" + getCurrentYearEndTimeL());
        log.info("上年开始点时间 getLastYearStartTimeL：" + getLastYearStartTimeL());
    }
    private static final TimeZone mTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
    static {
        simpleTimeFormat.setTimeZone(mTimeZone);
        simpleDateFormat.setTimeZone(mTimeZone);
        simpleDatetimeFormat.setTimeZone(mTimeZone);
        simpleLongDatetimeFormat.setTimeZone(mTimeZone);
    }
    public static long getTimesNowL(){
        return Long.parseLong(simpleDatetimeFormat.format(new Date()));
    }
    // 获得当天0点时间
    public static Date getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static long getTimesMorningL(){
        return Long.parseLong(simpleDatetimeFormat.format(getTimesMorning()));
    }
    // 获得当天24点时间(第二天0点)
    public static Date getTimesNight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    public static long getTimesNightL(){
        return Long.parseLong(simpleDatetimeFormat.format(getTimesNight()));
    }

    // 获得昨天0点时间
    public static Date getYesterdayMorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesMorning().getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }
    public static long getYesterdayMorningL(){
        return Long.parseLong(simpleDatetimeFormat.format(getYesterdayMorning()));
    }

    // 获得当天近7天时间
    public static Date getWeekFromNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getTimesMorning().getTime() - 3600 * 24 * 1000 * 7);
        return cal.getTime();
    }
    public static long getWeekFromNowL(){
        return Long.parseLong(simpleDatetimeFormat.format(getWeekFromNow()));
    }

    // 获得本周一0点时间
    public static Date getTimesWeekMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }
    public static long getTimesWeekMorningL(){
        return Long.parseLong(simpleDatetimeFormat.format(getTimesWeekMorning()));
    }

    // 获得本周日24点时间
    public static Date getTimesWeekNight() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesWeekMorning());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }
    public static long getTimesWeekNightL(){
        return Long.parseLong(simpleDatetimeFormat.format(getTimesWeekNight()));
    }


    // 获得本月第一天0点时间
    public static Date getTimesMonthMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
    public static long getTimesMonthMorningL(){
        return Long.parseLong(simpleDatetimeFormat.format(getTimesMonthMorning()));
    }

    // 获得本月最后一天24点时间
    public static Date getTimesMonthNight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return cal.getTime();
    }
    public static long getTimesMonthNightL(){
        return Long.parseLong(simpleDatetimeFormat.format(getTimesMonthNight()));
    }

    //上月初0点时间
    public static Date getLastMonthStartMorning() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getTimesMonthMorning());
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
    public static long getLastMonthStartMorningL(){
        return Long.parseLong(simpleDatetimeFormat.format(getLastMonthStartMorning()));
    }
    //本季度开始点时间
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }
    public static long getCurrentQuarterStartTimeL(){
        return Long.parseLong(simpleDatetimeFormat.format(getCurrentQuarterStartTime()));
    }

    /**
     * 当前季度的结束时间，即2012-03-31 23:59:59
     * @return d
     */
    public static Date getCurrentQuarterEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentQuarterStartTime());
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }
    public static long getCurrentQuarterEndTimeL(){
        return Long.parseLong(simpleDatetimeFormat.format(getCurrentQuarterEndTime()));
    }
    //本年开始点时间
    public static Date getCurrentYearStartTime() {
        Calendar cal = Calendar.getInstance();
        //cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(cal.get(Calendar.YEAR), Calendar.JANUARY, 0, 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
        return cal.getTime();
    }
    public static long getCurrentYearStartTimeL(){
        return Long.parseLong(simpleDatetimeFormat.format(getCurrentYearStartTime()));
    }
    //本年结束点时间
    public static Date getCurrentYearEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, 1);
        return cal.getTime();
    }
    public static long getCurrentYearEndTimeL(){
        return Long.parseLong(simpleDatetimeFormat.format(getCurrentYearEndTime()));
    }
    //上年开始点时间
    public static Date getLastYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStartTime());
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }
    public static long getLastYearStartTimeL(){
        return Long.parseLong(simpleDatetimeFormat.format(getLastYearStartTime()));
    }

    public static String getYearFirstDay() {
        return UtilDateTime.nowYear() + "-01-01";
    }
    public static String getYearLastDay() {
        return UtilDateTime.nowYear() + "-12-31";
    }

    /** 得到本季度的第一天 YYYY-MM-DD */
    public static String getSeasonFirstDay() {
        return UtilDateTime.toStdDateString(getSeasonBegin());
    }
    /** 得到本季度最后一天 YYYY-MM-DD */
    public static String getSeasonLastDay() {
        String s = UtilDateTime.toStdDateString(getNextSeasonBegin());
        return UtilDateTime.stdDateFormat.format(getAfterDays(s, -1));
    }
    //季度开始
    public static Date getSeasonBegin() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        int curMonth = now.get(Calendar.MONTH);
        int seasonMonth = UtilDateTime.m_seasonBeginTable[curMonth];

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, seasonMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        return new Timestamp(calendar.getTime().getTime());
    }
    //下个季度开始
    public static Date getNextSeasonBegin() {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        int curMonth = now.get(Calendar.MONTH);
        int seasonMonth = UtilDateTime.m_seasonBeginTable[curMonth] + 3;

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH, seasonMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        return new Timestamp(calendar.getTime().getTime());
    }

    /** 取得当月第一天日期串YYYY-MM-DD @return 月第一天的标准日期串*/
    public static String getMonthStart() {
        return UtilDateTime.nowStdYearMonth() + "-01";
    }
    /** 取得指定日期所在月第一天日期串YYYY-MM-DD @return 月第一天的标准日期串 */
    public static String getMonthStart(Date d) {
        if (d == null) return "";
        return UtilDateTime.toStdDateString(d).substring(0, 7) + "-01";
    }
    /** 取得当月最后一天YYYY-MM-DD @return 当前月最后一天的日期串；*/
    public static String getMonthEnd() {
        return UtilDateTime.stdDateFormat.format(
                getAfterDays(getAfterMonth(getMonthStart(), 1), -1)
        );
    }
    /** 取得指定日期所在月最后一天YYYY-MM-DD @return 所在前月最后一天的日期串；*/
    public static String getMonthEnd(Date d) {
        return UtilDateTime.stdDateFormat.format(
                getAfterDays(getAfterMonth(getMonthStart(d), 1), -1)
        );
    }

    /** 取得上月第一天日期串YYYY-MM-DD @return 月第一天的标准日期串*/
    public static String getLastMonthStart() {
        String lastMonth = UtilDateTimeDL.getPrevYm(UtilDateTime.StdYearMonth());//上个月YYYYMM
        String lastMonthStart = UtilString.getLeft(lastMonth,4) + "-" +UtilString.getRight(lastMonth,2)+ "-01";
        return lastMonthStart;
    }
    /** 取得上月最后一天YYYY-MM-DD @return 当前月最后一天的日期串；*/
    public static String getLastMonthEnd() {
        return UtilDateTime.stdDateFormat.format(
                getAfterDays(getAfterMonth(getLastMonthStart(), 1), -1)
        );
    }

    //获取两个年月之间的月份数量
    public static int getBetweenMonths(int yyyymm1, int yyyymm2) {
        int m1 = yyyymm1 / 100 * 12 + yyyymm1 % 100;
        int m2 = yyyymm2 / 100 * 12 + yyyymm2 % 100;
        return m1 - m2;
    }

    /** 得到当前日期所在周次的星期一 @return 返回的时间戳 */
    public static Timestamp getWeekStart() {
        return getWeekStart(new Timestamp(System.currentTimeMillis()));
    }
    /**
     * 得到日期所在周次的星期一
     * @param stamp 参数时间戳，如果传入为null,则返回为null
     * @return 返回时间戳
     */
    public static Timestamp getWeekStart(Timestamp stamp) {
        if (stamp == null) return null;
        GregorianCalendar tempCal = new GregorianCalendar(mTimeZone);
        tempCal.setTime(stamp);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        if (tempCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            // AKzz fix bug:
            // 如果今天是星期天, 天数再减1,找到上周(外国人的星期天是本周).
            tempCal.add(Calendar.DATE, -1);                 // 再将天数减一
        }
        tempCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 设为星期一.

        Timestamp retStamp = new Timestamp(tempCal.getTime().getTime());
        retStamp.setNanos(0);
        return retStamp;
    }
    /**
     * 当前日期的下周星期一
     */
    public static Timestamp getNextWeekStart() {
        return getNextWeekStart(new Timestamp(System.currentTimeMillis()));
    }
    /**
     * 当前日期的下周星期一,如果传入null,则返回为null
     * @param t 要计算的时间戳
     * @return 返回的时间戳
     */
    public static Timestamp getNextWeekStart(Timestamp t) {
        if (t == null) return null;
        Calendar tempCal = new GregorianCalendar(mTimeZone);
        tempCal.setTime(t);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DAY_OF_MONTH),
                // 没有时,分,秒
                0, 0, 0);
        tempCal.add(Calendar.WEEK_OF_MONTH, 1);             // 周数加1
        tempCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 设为星期一.

        Timestamp retStamp = new Timestamp(tempCal.getTime().getTime());
        retStamp.setNanos(0);
        return retStamp;
    }

    /**
     * 当前日期的上周星期一
     */
    public static Timestamp getLastWeekStart() {
        return getLastWeekStart(new Timestamp(System.currentTimeMillis()));
    }
    /**
     /**
     * 当前日期的上周星期一,如果传入null,则返回为null
     * 一周：  周一-周日
     * @param t 要计算的时间戳
     * @return 返回的时间戳
     */
    public static Timestamp getLastWeekStart(Timestamp t) {
        if (t == null) return null;
        Calendar tempCal = new GregorianCalendar(mTimeZone);
        tempCal.setTime(t);
        tempCal.set(tempCal.get(Calendar.YEAR), tempCal.get(Calendar.MONTH), tempCal.get(Calendar.DAY_OF_MONTH),
                // 没有时,分,秒
                0, 0, 0);
        //tempCal.add(Calendar.WEEK_OF_MONTH, -1);             // 周数减1
        //tempCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 设为星期一.
        tempCal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        tempCal.add(Calendar.DAY_OF_WEEK, -7);
        Timestamp retStamp = new Timestamp(tempCal.getTime().getTime());
        retStamp.setNanos(0);
        return retStamp;
    }

    /** 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     * @param date 2021年05月30日
     * @return String，获取当前是星期几；
     */
    public static String getWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){w = 0;}
        return weekOfDays[w];
    }
    /**
     * 判定指定日期是一年的第几周；:::::::: 在中华人民共和国，包含1月4日的那个星期为当年的第一个星期 GB/T 7408-2005
     * @param date 2021年09月12日
     * @return 第几周；
     */
    public static Integer getWeekOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int mouth = calendar.get(Calendar.MONTH);
        // JDK think 2015-12-31 as 2016 1th week
        //如果月份是12月，且求出来的周数是第一周，说明该日期实质上是这一年的第53周，也是下一年的第一周
        if (mouth >= 11 && week <= 1) {
            week += 52;
        }
        return week;
    }
    /**
     * 获得指定年，指定周的开始结束日期；
     * @param year 年；
     * @param week 周 1...52
     */
    public static DateBeginEndBo getWeekofYearBeginEndDate(int year, int week){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY); //设定每周从周一开始；

        //获得指定年的第几周的开始日期（dayOfWeek是从周日开始排序的）
        calendar.setWeekDate(year, week, Calendar.MONDAY); //星期1开始
        Date starttime = calendar.getTime();
        //获得指定年的第几周的结束日期
        calendar.setWeekDate(year, week, Calendar.SUNDAY); //星期天结束
        Date endtime = calendar.getTime();

        DateBeginEndBo bo = new DateBeginEndBo();
        bo.setStartDate(starttime);
        bo.setEndDate(endtime);
        bo.setStartDateStr(simpleStdDateFormat.format(starttime));
        bo.setEndDateStr(simpleStdDateFormat.format(endtime));
        return bo;
    }

    //返回日期是星期几: 1 - 7
    public static int getDayOfWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //一周第一天是否为星期天
        boolean isFirstSunday = (cal.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        //若一周第一天为星期天，则-1
        if(isFirstSunday){
            weekDay = weekDay - 1;
            if(weekDay == 0){weekDay = 7;}
        }
        return weekDay;
    }



    //获取季度开始年月，YYYYMM格式
    public static String getSeasonBeginYm() {
        return getDateYm(getSeasonBegin());
    }
    //获取季度结束年月，YYYYMM格式
    public static String getSeasonEndYm() {
        return getDateYm(DateUtils.addDays(getNextSeasonBegin(), -1));
    }
    //获取年度开始年月，YYYYMM格式
    public static String getYearBeginYm() {
        return UtilDateTime.nowYear() + "01";
    }
    //获取年度结束年月，YYYYMM格式
    public static String getYearEndYm() {
        return UtilDateTime.nowYear() + "12";
    }
    //获取上一月，YYYYMM格式
    public static String getPrevYm(String yyyymm) {
        return getNextYm(yyyymm, -1);
    }
    //获取下一月，YYYYMM格式
    public static String getNextYm(String yyyymm) {
        return getNextYm(yyyymm, 1);
    }
    public static String getNextYm(String yyyymm, int add) {
        if (UtilPub.isEmptyId(yyyymm)) return yyyymm;
        int curYear = Integer.parseInt(yyyymm.substring(0, 4));
        int curMonth = Integer.parseInt(yyyymm.substring(4)) + add;
        if (curMonth > 12) {
            do {
                curYear++;
                curMonth -= 12;
            } while (curMonth > 12);
        } else if (curMonth <= 0) {
            do {
                curYear--;
                curMonth += 12;
            } while (curMonth <= 0);
        }
        return curYear + UtilString.getRight("00" + curMonth, 2);
    }



    /**
     * return a.minute() + minutes
     */
    public static Date getAfterMinute(Date d, int minutes) {
        if (d == null) return null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeZone(mTimeZone);
        gc.setTime(d);
        gc.add(GregorianCalendar.MINUTE, minutes);
        return new java.util.Date(gc.getTimeInMillis());
    }
    /**
     * 得到date + days的日期
     * @param dt 日期
     * @param days 间隔天数，正数往前，负数往后
     * @return String java.util.Date.toString()
     */
    public static Date getAfterDays(Date dt, int days) {
        return DateUtils.addDays(dt,days);
    }
    /**
     * 得到date + days的日期
     * @param dt 日期
     * @param weeks 间隔周数，正数往前，负数往后
     * @return String java.util.Date.toString()
     */
    public static Date getAfterWeeks(Date dt, int weeks) {
        return DateUtils.addDays(dt,weeks*7);
    }
    /**
     * 得到date + days的日期
     * @param date 日期(yyyy-mm-dd)
     * @param days 间隔天数，正数往前，负数往后
     * @param include67 是否包含星期六和星期日
     */
    public static Date getAfterDays(String date, int days, boolean include67) {
        Date in = UtilDateTime.toDateDefaultNow(date);
        if (include67 || days == 0) return getAfterDays(in, days);
        //要剔除信息，需要特殊处理
        int absd = Math.abs(days);
        int step = days > 0 ? 1 : -1;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeZone(mTimeZone);
        gc.setTimeInMillis(in.getTime());
        //这里要判断days是正数还是负数，正数是未来，负数是过去
        for (int d = 0; d < absd; ) {
            //获取当前是星期几；
            int w = gc.get(GregorianCalendar.DAY_OF_WEEK);
            // [ 星期日 | w | 星期六 ]
            if (w > GregorianCalendar.SUNDAY && w < GregorianCalendar.SATURDAY) {
                ++d;
            }
            // 向前或向后逐天循环；
            gc.add(GregorianCalendar.DATE, step);
        }
        return new java.util.Date(gc.getTimeInMillis());
    }
    /**
     * 得到date + days的日期
     * @param dt 日期(yyyy-mm-dd)
     * @param days 间隔天数，正数往前，负数往后
     * @return String java.util.Date.toString()
     */
    public static Date getAfterDays(String dt, int days) {
        Date in = UtilDateTime.toDateDefaultNow(dt);
        if (days == 0) return in;
        try {
            return getAfterDays(java.sql.Date.valueOf(dt), days);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 取与指定日期间隔月的日期(yyyy-mm-dd)
     * @return String java.util.Date.toString()
     */
    public static Date getAfterMonth(Date dt, int months) {
        if (months == 0) return dt;
        GregorianCalendar dy = new GregorianCalendar();
        dy.setTime(dt);
        dy.add(GregorianCalendar.MONTH, months);
        return new java.util.Date(dy.getTimeInMillis());
    }
    /**
     * 取与指定日期间隔月的日期(yyyy-mm-dd)
     * @param dt (yyyy-mm-dd)
     * @param months 间隔月
     * @return java.util.Date.toString()
     */
    public static Date getAfterMonth(String dt, int months) {
        return getAfterMonth(java.sql.Date.valueOf(dt), months);
    }
    /**
     * 取与指定日期间隔年的日期
     * @return String java.util.Date.toString()
     */
    public static Date getAfterYear(java.util.Date dt, int years) {
        if (years == 0) return dt;
        GregorianCalendar dy = new GregorianCalendar();
        dy.setTime(dt);
        dy.add(GregorianCalendar.YEAR, years);
        return new java.util.Date(dy.getTimeInMillis());
    }
    /**
     * 取与指定日期间隔年的日期
     * @param dt (yyyy-mm-dd)
     * @param years 间隔年         j
     * @return java.util.Date.toString()
     */
    public static Date getAfterYear(String dt, int years) {
        return getAfterYear(java.sql.Date.valueOf(dt), years);
    }


    //获取两个年月之间的月份数量
    public static int getDifferMonths(int yyyymm1, int yyyymm2) {
        int m1 = yyyymm1 / 100 * 12 + yyyymm1 % 100;
        int m2 = yyyymm2 / 100 * 12 + yyyymm2 % 100;
        return m1 - m2;
    }
    /**两个日期相差天数；
     * @param f  开始时间
     * @param t  截止时间
     * @return 天数，如果f>t将返回负数，根据秒数计算，取的整数；
     */
    public static int getDifferDays(Date f, Date t){
        return getDifferSeconds(f, t) / (24 * 60 * 60);
    }
    /**两个日期相差分钟数；
     * @param f  开始时间
     * @param t  截止时间
     * @return 分钟数，如果f>t将返回负数；
     */
    public static int getDifferMinutes(Date f, Date t){
        return getDifferSeconds(f, t) / 60;
    }

    /**两个日期相差分钟数；
     * @param f  开始时间
     * @param t  截止时间
     * @return 分钟数，如果f>t将返回负数；
     */
    public static int getDifferMinutes(String f, String t) {
        return getDifferSeconds(Timestamp.valueOf(f), Timestamp.valueOf(t)) / 60;
    }

    /**
     * 两个日期相差秒数；
     * @param f  开始时间
     * @param t  截止时间
     * @return 秒数，如果f>t将返回负数；
     */
    public static int getDifferSeconds(Date f, Date t) {
        if (f == null || t == null) return 0;
        //int fh =1;
        //if (t.compareTo(f) < 0) {fh=-1;}
        return (int) ((t.getTime() - f.getTime()) / 1000);  // * fh;
    }


    /**
     * 获得两个日期之间的每一天
     * @param start 开始日期
     * @param end 截止日期
     * @param beforeAfterEnum 数据包围方式；
     * @return 每一天日期的List
     */
    public static List<Date> getBetweenEveryDate(Date start, Date end, IncludingBeforeAfterEnum beforeAfterEnum) {
        List<Date> result = new ArrayList<>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        // Date1.after(Date2),当Date1大于Date2时，返回TRUE，当小于等于时，返回false；
        // Date1.before(Date2)，当Date1小于Date2时，返回TRUE，当大于等于时，返回false；
        // 如果业务数据存在相等的时候，而且相等时也需要做相应的业务判断或处理时，请注意。
        // 如果有这样的需求，在某个日期内的业务check，那么你需要使用：！Date1.after(Date2)
        if(start.after(end)){
            return result;
        }
        //是否包括前；
        if(IncludingBeforeAfterEnum.INCLUDING_BEFORE.equals(beforeAfterEnum) || IncludingBeforeAfterEnum.INCLUDING_BEFORE_AFTER.equals(beforeAfterEnum)){
            if(tempStart.before(tempEnd)){
                result.add(tempStart.getTime());
            }
        }
        tempStart.add(Calendar.DAY_OF_YEAR, 1);
        while (tempStart.before(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        //是否包括后；
        if(IncludingBeforeAfterEnum.INCLUDING_AFTER.equals(beforeAfterEnum) || IncludingBeforeAfterEnum.INCLUDING_BEFORE_AFTER.equals(beforeAfterEnum)){
            if(end.after(start)){
                result.add(tempEnd.getTime());
            }
        }
        return result;
    }
    //当前时间  距离当天凌晨  秒数
    public static long getTodaySeconds() {
        long now = System.currentTimeMillis();
        SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return (now - (sdfOne.parse(sdfOne.format(now)).getTime())) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    //当前时间  距离当天凌晨 分钟数
    public static int getTodayMinutes() {
        return (int) (getTodaySeconds() / 60);
    }
    //获取年月，YYYYMM格式
    public static String getDateYm(Date dt) {
        String s = UtilDateTime.simpleDateFormat.format(dt);
        return s.substring(0, 6);
    }
    //获取年月，YYYYMM格式
    public static String getNowYm() {
        return getDateYm(UtilDateTime.nowTimestamp());
    }
    //获取年月，YYYYMM格式
    public static int getNowYmInt() {
        return Integer.parseInt(getNowYm());
    }
}
