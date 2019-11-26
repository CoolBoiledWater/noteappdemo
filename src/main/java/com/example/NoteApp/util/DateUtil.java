package com.example.NoteApp.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 *
 * @author czk
 * @since jdk 1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";

    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = TimeFormat.LONG_DATE_PATTERN_LINE.formatter;

    /**
     * 日期格式的字符串格式化成数字格式字符串
     *
     * @param str yyyy-MM-dd
     *            HH:mm 等
     * @return yyyyMMddHHmm 等
     */
    public static String getDateToNum(String str) {
        String[] t = str.split("/|-|:| ");
        String re = "";
        for (String a : t) {
            re = re + a;
        }
        return re;
    }

    /**
     * date类型转换为long类型
     *
     * @param date 要转换的date类型的时间
     * @return
     */
    public static long dateToLong(Date date) {
        return NumberUtils.toLong(getTime(date.getTime(), new SimpleDateFormat("yyyyMMddHHmmss")));
    }

    /**
     * @param timeInMillis 时间戳
     * @param dateFormat   格式化
     * @return
     * @Title: getTime
     * @Description: 时间戳格式话
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * date类型转换为String类型
     *
     * @param date       Date类型的时间
     * @param formatType 格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
     * @return
     */
    public static String dateToString(Date date, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return sdf.format(new Date());
        }
    }

    /**
     * 获取当前时间
     *
     * @return yyyy-MM-dd HH:mm:ss 格式时间
     */
    public static String getCurrentDateTime() {
        return DEFAULT_DATETIME_FORMATTER.format(LocalDateTime.now());
    }

    /**
     * 获取两个时间差/秒
     *
     * @param startTime yyyyMMddHHmmss 格式时间
     * @param endTime   yyyyMMddHHmmss 格式时间
     * @return
     */
    public static long getSecondsBetween(long startTime, long endTime) {
        LocalDateTime start = LocalDateTime.parse(startTime + "", TimeFormat.LONG_DATE_PATTERN_NONE.formatter);
        LocalDateTime end = LocalDateTime.parse(endTime + "", TimeFormat.LONG_DATE_PATTERN_NONE.formatter);
        Duration between = Duration.between(start, end);
        return between.getSeconds();
    }

    /**
     * 时间偏移 天数
     *
     * @param datetime yyyyMMddHHmmss 时间
     * @param days     偏移量 正数+ 负数-
     * @return
     */
    public static long datePlusDays(long datetime, long days) {
        LocalDateTime start = LocalDateTime.parse(datetime + "", TimeFormat.LONG_DATE_PATTERN_NONE.formatter);
        LocalDateTime plusHours = start.plusDays(days);
        String longdate = TimeFormat.LONG_DATE_PATTERN_NONE.formatter.format(plusHours);
        return NumberUtils.toLong(longdate);
    }

    /**
     * 时间偏移 小时数
     *
     * @param datetime yyyyMMddHHmmss 时间
     * @param hours    偏移量 正数+ 负数-
     * @return
     */
    public static long datePlusHours(long datetime, long hours) {
        LocalDateTime start = LocalDateTime.parse(datetime + "", TimeFormat.LONG_DATE_PATTERN_NONE.formatter);
        LocalDateTime plusHours = start.plusHours(hours);
        String longdate = TimeFormat.LONG_DATE_PATTERN_NONE.formatter.format(plusHours);
        return NumberUtils.toLong(longdate);
    }

    /**
     * 时间偏移 分钟数
     *
     * @param datetime yyyyMMddHHmmss 时间
     * @param minutes  偏移量 正数+ 负数-
     * @return
     */
    public static long datePlusMinutes(long datetime, long minutes) {
        LocalDateTime start = LocalDateTime.parse(datetime + "", TimeFormat.LONG_DATE_PATTERN_NONE.formatter);
        LocalDateTime plusHours = start.plusMinutes(minutes);
        String longdate = TimeFormat.LONG_DATE_PATTERN_NONE.formatter.format(plusHours);
        return NumberUtils.toLong(longdate);
    }

    /**
     * 获取当前时间
     *
     * @param timeFormat 时间格式
     * @return
     */
    public static String getCurrentDateTime(TimeFormat timeFormat) {
        return timeFormat.formatter.format(LocalDateTime.now());
    }

    /**
     * 获取当前long类型时间
     *
     * @return yyyyMMddHHmmss 格式时间
     */
    public static long dateToLong() {
//        String longdate = TimeFormat.LONG_DATE_PATTERN_NONE.formatter.format(LocalDateTime.now());
//        return NumberUtils.toLong(longdate);
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return NumberUtils.toLong(df.format(new Date()));
    }

    /**
     * 根据当前日期获取本周第一天
     *
     * @return yyyyMMddHHmmss
     */
    public static long GetDateWeekFirstDay() {
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        int dayOfWeek = ca.get(Calendar.DAY_OF_WEEK);
        // 周一是一周的开始
        if (dayOfWeek == 1) {
            dayOfWeek = 7;
        } else {
            dayOfWeek--;
        }
        Calendar cal = (Calendar) ca.clone();

        cal.add(Calendar.DATE, 1 - dayOfWeek);
        Date date1 = cal.getTime();
        String str1 = f.format(date1);
        return NumberUtils.toLong(str1.replace("-", "")) * 1000000;
    }

    /**
     * long类型时间转换格式
     *
     * @param date yyyyMMddHHmmss 格式时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String longToString(long date) {
        return longToString(date, TimeFormat.LONG_DATE_PATTERN_LINE);
    }

    /**
     * long类型时间转换格式
     *
     * @param date       yyyyMMddHHmmss 格式时间
     * @param timeFormat 时间格式
     * @return
     */
    public static String longToString(long date, TimeFormat timeFormat) {
        return formatTime(date + "", TimeFormat.LONG_DATE_PATTERN_NONE, timeFormat);
    }

    /**
     * 格式化时间
     *
     * @param time          时间
     * @param oldTimeFormat 原格式
     * @param newTimeFormat 新格式
     * @return
     */
    public static String formatTime(String time, TimeFormat oldTimeFormat, TimeFormat newTimeFormat) {
        LocalDateTime parse = LocalDateTime.parse(time, oldTimeFormat.formatter);
        return newTimeFormat.formatter.format(parse);
    }

    public static String stringToNewFormat(String strTime, SimpleDateFormat oldFormat, SimpleDateFormat newFormat) {
        try {
            if (!StrUtil.isNullOrEmpty(strTime) && oldFormat != null) {
                Date date = oldFormat.parse(strTime);
                return newFormat.format(date);
            }
        } catch (ParseException e) {
        }
        return newFormat.format(new Date());
    }


    /**
     * 返回当前日期
     *
     * @return yyyyMMdd
     */
    public static Long getLongtoday() {
        Calendar cal = Calendar.getInstance();
        return Long.parseLong((cal.get(Calendar.YEAR) + DateUtil.getMonth(cal.get(Calendar.MONTH))
                + DateUtil.getDay(cal.get(Calendar.DATE))));
    }

    /**
     * 返回当前日期
     *
     * @return yyyyMMdd
     */
    public static Long getLongToday() {
        Date date = new Date();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyyMMdd");
        return StrUtil.toLong(dataFormat.format(date), 0L);

    }

    /**
     * 获取日期是星期几
     *
     * @param pTime
     * @return
     * @throws Throwable
     */
    public static int getDayForWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        int dayForWeek = 0;
        try {
            cal.setTime(format.parse(pTime));
            cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

            if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
                dayForWeek = 7;
            } else {
                dayForWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dayForWeek;
    }

    /**
     * 星期
     *
     * @param weekno
     * @return
     */
    public static String getWeekName(int weekno) {
        String weekName = "";
        switch (weekno) {
            case 1:
                weekName = "星期一";
                break;
            case 2:
                weekName = "星期二";
                break;
            case 3:
                weekName = "星期三";
                break;
            case 4:
                weekName = "星期四";
                break;
            case 5:
                weekName = "星期五";
                break;
            case 6:
                weekName = "星期六";
                break;
            case 7:
                weekName = "星期日";
                break;
        }
        return weekName;
    }


    /**
     * 返回今天最后一天日期
     *
     * @return yyyyMMdd
     */
    public static Long getThisYearlastday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String lastday;
        Calendar calendar = Calendar.getInstance();
        int tyear = calendar.get(Calendar.YEAR);
        calendar.clear();
        calendar.set(Calendar.YEAR, tyear);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
        lastday = format.format(currYearLast);
        return NumberUtils.toLong(lastday);
    }

    /**
     * 返回当月最后一天
     *
     * @return yyyyMMdd
     */
    public static Long getThisMonthlastday() {
        String lastday;
        LocalDate today = LocalDate.now();
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        lastday = TimeFormat.SHORT_DATE_PATTERN_NONE.formatter.format(lastDayOfThisMonth);
        return NumberUtils.toLong(lastday);
    }

    /**
     * 返回当月第一天
     *
     * @return yyyyMMdd
     */
    public static Long getThisMonthfirstDay() {
        String firstDay;
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        firstDay = TimeFormat.SHORT_DATE_PATTERN_NONE.formatter.format(firstDayOfMonth);
        return NumberUtils.toLong(firstDay);
    }

    public static String getMonth(Integer m) {
        if (m < 9) {
            return "0" + (m + 1);
        }
        return m.toString();
    }

    public static String getDay(Integer m) {
        if (m < 10) {
            return "0" + m;
        }
        return m.toString();
    }

    /**
     * string类型转换为date类型
     *
     * @param strTime    的时间格式必须要与formatType的时间格式相同
     * @param formatType 要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd
     *                   HH:mm:ss//yyyy年MM月dd日HH时mm分ss秒
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType) {
        try {
            if (StringUtils.isEmpty(strTime)) {
                return new Date();
            }
            SimpleDateFormat formatter = new SimpleDateFormat(formatType);
            Date date = null;
            date = formatter.parse(strTime);
            return date;
        } catch (Exception e) {
            return new Date();
        }

    }

    /**
     * long类型转换为String类型
     *
     * @param currentTime 要转换的long类型的时间
     * @param formatType  要转换的string类型的时间格式
     * @return
     * @throws ParseException
     */
    public static String longToString(long currentTime, String oldFormatType, String formatType) throws ParseException {
        String strTime = StrUtil.toStr(currentTime, "0001-01-01"); // long类型转成String
        Date date = stringToDate(strTime, oldFormatType);
        return dateToString(date, formatType);
    }

    /**
     * 是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDate(String strDate, String dateFormat) {
        SimpleDateFormat sdf = null;
        ParsePosition pos = new ParsePosition(0);//指定从所传字符串的首位开始解析
        try {
            sdf = new SimpleDateFormat(dateFormat);
            sdf.setLenient(false);
            Date date = sdf.parse(strDate, pos);
            if (date == null) {
                return false;
            } else {
                if (pos.getIndex() > sdf.format(date).length()) {
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getTimePosition(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date;
        try {
            date = format.parse(time + "");
            long conversionTime = date.getTime();
            long currentTime = System.currentTimeMillis();
            long relativeTime = currentTime - conversionTime;

            long yesterdayTime = currentTime - 24 * 3600 * 1000;
            long tomorrowdayTime = currentTime + 24 * 3600 * 1000;
            //昨天的这个时候 -> YYYYMMDD
            String yesterdayStr = DateUtil.longToString(yesterdayTime, TimeFormat.SHORT_DATE_PATTERN_NONE);
            //今天
            String todaydayStr = DateUtil.longToString(currentTime, TimeFormat.SHORT_DATE_PATTERN_NONE);
            //明天
            String tomorrowdayStr = DateUtil.longToString(tomorrowdayTime, TimeFormat.SHORT_DATE_PATTERN_NONE);
            //time变量
            String currentdayStr = DateUtil.longToString(time, TimeFormat.SHORT_DATE_PATTERN_NONE);

            if (relativeTime < 30 * 60 * 1000 && relativeTime >= 0) {
                return "刚刚";
            } else if (relativeTime < 60 * 60 * 1000 && relativeTime >= 0) {
                return "30分钟前";
            } else if (currentdayStr.equals(todaydayStr)) {
                return "今天 " + DateUtil.longToString(time, TimeFormat.HHMM);
            } else if (currentdayStr.equals(yesterdayStr)) {
                return "昨天 " + DateUtil.longToString(time, TimeFormat.HHMM);
            } else if (currentdayStr.equals(tomorrowdayStr)) {
                return "明天 " + DateUtil.longToString(time, TimeFormat.HHMM);
            } else {
                return DateUtil.longToString(time, TimeFormat.YYMMDDHHMM);
            }
        } catch (ParseException e) {
            return DateUtil.longToString(time, TimeFormat.YYMMDDHHMM);
        }
    }

    public static String getTimeAgo(long loogtime) {
        String time=DateUtil.longToString(loogtime, TimeFormat.YYMMDDHHMM);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        return time;
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }
    /**
     * @param date       时间
     * @param dateFormat 格式化
     * @return
     * @Title: getTime
     * @Description: 时间格式化
     */
    public static String simpleDateFormat(Date date, SimpleDateFormat dateFormat) {
        date = date == null ? new Date() : date;
        return dateFormat.format(date);
    }


    /**
     * 时间格式
     */
    public enum TimeFormat {
        /**
         * 时间格式：yyyy-MM-dd
         */
        SHORT_DATE_PATTERN_LINE("yyyy-MM-dd"),
        /**
         * 时间格式：yyyyMMdd
         */
        SHORT_DATE_PATTERN_NONE("yyyyMMdd"),

        /**
         * 时间格式：yyyy-MM-dd HH:mm:ss
         */
        LONG_DATE_PATTERN_LINE("yyyy-MM-dd HH:mm:ss"),

        /**
         * 时间格式：yyyyMMddHHmmss
         */
        LONG_DATE_PATTERN_NONE("yyyyMMddHHmmss"),

        /**
         * 时间格式：yyyyMMddHHmm
         */
        LONG_DATE_PATTERN_NONE_NM("yyyyMMddHHmm"),
        /**
         * 时间格式：YYMM
         */
        YYMM("YYMM"),
        /**
         * 时间格式：HH:mm
         */
        HHMM("HH:mm"),
        /**
         * 时间格式：DDMM
         */
        MMDD("MM-dd"),
        MMDD_China("MM月dd日"),
        /**
         * 时间格式：yyyy-MM-dd HH:mm
         */
        YYMMDDHHMM("yyyy-MM-dd HH:mm"),
        /**
         * 时间格式：yyMMddHHmmss
         */
        YYMMDDHHMMSS("yyMMddHHmmss"),
        /**
         * 时间格式：MMddHHmmss
         */
        MMDDHHMMSS("MMddHHmmss"),

        SHORT_DATE_PATTERN_LINE_MM("MM-dd HH:mm"),

        MMDDHHSS_CH("MM月dd日 HH:mm");
        private transient DateTimeFormatter formatter;

        TimeFormat(String pattern) {
            formatter = DateTimeFormatter.ofPattern(pattern);
        }
    }
}
