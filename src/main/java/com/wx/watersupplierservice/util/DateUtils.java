package com.wx.watersupplierservice.util;

import com.wx.watersupplierservice.exception.PublicException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DateUtils {

    public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_LONG_MIN = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_SLASH = "yyyy/MM/dd";

    public static final String DATE_FORMAT_MINUTE = "yyyy/MM/dd HH:mm";

    public static final String NO_YEAR_DATE_FORMAT_MINUTE = "MM/dd HH:mm";

    public static final String DATE_FORMAT_MINUTE_WC = "yyyy.MM.dd HH:mm";


    public static final String DATE_FORMAT_MINUTE_MIX = "yyyyMMddHHmmss";

    public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";

    public static final String DATE_FORMAT_MONTH = "yyyy-MM";

    public static final String DATE_FORMAT_HOUR = "HH:mm";

    public static final String DATE_FORMAT_MONTH_DAY = "MM.dd";

    public static final int ONE_MIN = 60; //一分钟 60秒

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getCurrentDateStr() {
        return sdf.format(new Date());
    }

    public static String formatDateNormal(Date date) {
        if (ObjectUtils.isEmpty(date)){
            return "";
        }
        return sdf.format(date);
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }


    public static Date getDateLongMin(Date date) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_LONG_MIN);
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 吧形如GMT+0.8 GMT+8.0 GMT+8:00 GMT+08:00中国的标准时间改为yyyy-MM-dd HH:mm:ss的格式
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getDateStr(String date) {
        if (!"".equals(date)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dest = format.format(Date.parse(date));
            return dest;
        } else {
            return "";
        }
    }

    /**
     * 获取时间格式： HH:mm
     *
     * @param date
     * @return
     */
    public static String getDateStrHM(String date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String dest = format.format(DateUtils.strToDateLong(date));
        return dest;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getDateLong(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return DateUtils.strToDateLong(dateString);
    }

    /**
     * 获取现在时间
     *
     * @throws ParseException
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() throws ParseException {
        Date currentTime = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = formatter.parse(dateString);
        return currentTime_2;
    }

    /**
     * 将长格式字符串改为yyyy-MM-dd格式
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static String getDateShortSlash(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_SLASH);
        if (null != currentTime) {
            String dateString = formatter.format(currentTime);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将长格式字符串改为yyyy/MM/dd格式
     *
     * @return返回短时间格式 yyyy/MM/dd
     */
    public static String getDateShort(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (null != currentTime) {
            String dateString = formatter.format(currentTime);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 返回日期为“yyyy年MM月dd日”格式
     *
     * @param date 日期
     * @return yyyy年MM月dd日
     */
    public static String getYearMonthDateChar(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        if (null != date) {
            String dateString = formatter.format(date);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 返回日期为“yyyy年MM月”格式
     *
     * @return返回短时间格式
     */
    public static String getYearMonth(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        if (null != currentTime) {
            String dateString = formatter.format(currentTime);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将长格式字符串改为yyyy-MM-dd hh:mm:ss格式
     *
     * @return返回短时间格式yyyy-MM-dd hh:mm:ss
     */
    public static String getDateLongL(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTimeShort(Date currentTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTimeShorts() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTimeShorts(Date str) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        if (str != null) {
            String dateString = formatter.format(str);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        if (strDate != null) {
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
        return null;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date specialStrToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        ParsePosition pos = new ParsePosition(0);
        if (strDate != null) {
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
        return null;
    }

    public static Date strToDateShort(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        if (strDate != null) {
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
        return null;
    }

    /**
     * 将时间mm/dd/yyyy格式转换为yyyy-mm-dd
     *
     * @param date 时间字符串
     * @return
     */
    public static String dateToStr(String date) {
        String[] strDate = date.split("/");
        String dateString = strDate[2] + "-" + strDate[1] + "-" + strDate[0];
        return dateString;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dateDate != null) {
            String dateString = formatter.format(dateDate);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (dateDate != null) {
            String dateString = formatter.format(dateDate);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy/MM/dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToSepcialStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        if (dateDate != null) {
            String dateString = formatter.format(dateDate);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将短时间格式时间转换为字符串 HH:mm
     *
     * @param date
     * @return
     */
    public static String dateToHourStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_HOUR);
        if (date != null) {
            String dateString = formatter.format(date);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将短时间格式时间转换为字符串 HH:mm
     *
     * @param date
     * @return
     */
    public static String dateToHourStrMix(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MINUTE_MIX);
        if (date != null) {
            String dateString = formatter.format(date);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将短时间格式时间转换为字符串 HH:mm
     *
     * @param date
     * @return
     */
    public static String dateToMinString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(NO_YEAR_DATE_FORMAT_MINUTE);
        if (date != null) {
            String dateString = formatter.format(date);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * weChat  DATE_FORMAT_LONG_MIN
     */
    public static String dateToStrLongWC(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MINUTE_WC);
        if (date != null) {
            String dateString = formatter.format(date);
            return dateString;
        } else {
            return "";
        }
    }

    public static String dateToStrLongMinWC(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_LONG_MIN);
        if (date != null) {
            String dateString = formatter.format(date);
            return dateString;
        } else {
            return "";
        }
    }


    /**
     * 将短时间格式时间转换为字符串 MM-dd
     *
     * @param date
     * @return
     */
    public static String dateToMonthHourStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_MONTH_DAY);
        if (date != null) {
            String dateString = formatter.format(date);
            return dateString;
        } else {
            return "";
        }
    }


    /**
     * 将短时间格式时间转换为字符串yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr1(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dateDate != null) {
            String dateString = formatter.format(dateDate);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将短时间格式时间转换为字符串yyyy-MM-dd HH:mm
     *
     * @param dateDate
     * @return
     */
    public static String dateToStr2(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (dateDate != null) {
            String dateString = formatter.format(dateDate);
            return dateString;
        } else {
            return "";
        }
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        if (strDate == null || "".equals(strDate.trim())) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy
     *
     * @param strDate
     * @return
     */
    public static Date strToDateYYYY(String strDate) {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        Date currentTime = new Date();
        return currentTime;
    }

    /**
     * 提取一个月中的最后一天
     *
     * @param day
     * @return
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss ");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在时间
     *
     * @return 学符串yyyyMMdd
     */
    public static String getStringShortToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在时间
     *
     * @return 学符串yyyyMM
     */
    public static String getTodayYearMonth() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在小时
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟
     *
     * @return
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
     *
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
     */
    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static Date getPreTime(Date date, int mm) {

        long Time = (date.getTime() / 1000) + mm * 60;
        Date date1 = new Date();
        date1.setTime(Time * 1000);
        return date1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (long) ((d.getTime() / 1000) + Float.parseFloat(delay) * 24 * 60 * 60);
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数 转换为yyyy-MM-dd HH:mm:ss
     */
    public static String getNextDayByNowDate(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * @param d        时间
     * @param delay    延后或前移的天数
     * @param timeUnit 时间单位 使用Calendar常量(Calendar.SECOND)
     * @return
     */
    public static Date getNextDay(Date d, int delay, int timeUnit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, delay);
        switch (timeUnit) {
            case Calendar.HOUR_OF_DAY:
                cal.set(Calendar.HOUR_OF_DAY, 0);
            case Calendar.MINUTE:
                cal.set(Calendar.MINUTE, 0);
            case Calendar.SECOND:
                cal.set(Calendar.SECOND, 0);
            case Calendar.MILLISECOND:
                cal.set(Calendar.MILLISECOND, 0);
            default:
                break;
        }
        return cal.getTime();
    }

    /**
     * @param d        时间
     * @param delay    偏移数量
     * @param timeUnit 时间偏移单位 使用Calendar常量(Calendar.SECOND)
     * @return
     */
    public static Date getLimitDate(Date d, int delay, int timeUnit) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(timeUnit, delay);
        return cal.getTime();
    }

    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     *
     * @param str
     * @return
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    /**
     * 获取一个月的最后一天
     *
     * @param dat
     * @return
     */
    public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(dat)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
     *
     * @param sdate
     * @param num
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = DateUtils.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = DateUtils.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static String getWeekStr(String sdate) {
        String str = "";
        str = DateUtils.getWeek(sdate);
        if ("星期日".equals(str)) {
            str = "周日";
        } else if ("星期一".equals(str)) {
            str = "周一";
        } else if ("星期二".equals(str)) {
            str = "周二";
        } else if ("星期三".equals(str)) {
            str = "周三";
        } else if ("星期四".equals(str)) {
            str = "周四";
        } else if ("星期五".equals(str)) {
            str = "周五";
        } else if ("星期六".equals(str)) {
            str = "周六";
        }
        // System.out.println(str);
        return str;
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 两个时间之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(Date end, Date start) {
        if (end == null)
            return 0;
        if (start == null)
            return 0;
        // 转换为标准时间
        end = org.apache.commons.lang3.time.DateUtils.truncate(end, Calendar.DATE);
        start = org.apache.commons.lang3.time.DateUtils.truncate(start, Calendar.DATE);
        long day = (end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期
     *
     * @param sdate
     * @return
     */
    public static String getNowMonth(String sdate) {
        // 取该时间所在月的一号
        sdate = sdate.substring(0, 8) + "01";
        // 得到这个月的1号是星期几
        Date date = DateUtils.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = DateUtils.getNextDay(sdate, (1 - u) + "");
        return newday;
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     *
     * @param k 表示是取几位随机数，可以自己定
     */

    public static String getNo(int k) {

        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }

    /**
     * 返回一个随机数
     *
     * @param i
     * @return
     */
    public static String getRandom(int i) {
        Random jjj = new Random();
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * @param args
     */
    public static boolean RightDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        ;
        if (date == null)
            return false;
        if (date.length() > 10) {
            sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
        }
        try {
            sdf.parse(date);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * 将formBean 里的字符时间(yyyy-MM-dd) 转换成Date类型
     *
     * @param formDate
     * @return
     */
    public static Date formBeanDateToPODate(String formDate) {
        try {
            if (formDate != null) {
                if (!formDate.trim().equals("")) {
                    System.out.println("---------formdate:" + formDate);
                    return java.sql.Date.valueOf(formDate);
                }
            }
        } catch (Exception e) {
            System.out.println("DateUtils:时间转换异常");
            return new Date();
        }
        return null;

    }

    // 获取年月的方法
    public static String getYearAndMonth() {
        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy年M月");
        return dateformat2.format(new Date());
    }

    // 获取年月日的方法
    public static String getYearMonthDay() {
        SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy年MM月dd日");
        return dateformat2.format(new Date());
    }

    // 获取月日的方法
    public static String getMonthDay(String datestr) {
        Date date = DateUtils.strToDate(datestr);
        SimpleDateFormat dateformat2 = new SimpleDateFormat("M月d日");
        return dateformat2.format(date);
    }

    /**
     * 格式化 XX月XX日 星期X
     *
     * @param date
     * @return XX月XX日 星期X
     */
    public static String getMonthDayWeek(Date date) {
        SimpleDateFormat dateformat2 = new SimpleDateFormat("M月d日");

        String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DAY_OF_WEEK);

        return dateformat2.format(date) + " 星期" + weeks[day - 1];
    }

    // 获取月日的方法
    public static String getDay(String datestr) {
        Date date = DateUtils.strToDate(datestr);
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd");
        return dateformat2.format(date);
    }

    // 获取第几周
    public static String getDayMonth(String sdate) {
        // 再转换为时间
        Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.WEEK_OF_MONTH);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return hour + "";
    }

    /**
     * 得到当天是本月第几周星期几
     *
     * @return
     */
    public static String getMonthWeekInToday() {
        String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);// 获致是本周的第几天地, 1代表星期天...7代表星期六
        // System.out.println("今天是本月的第" + week + "周");
        // System.out.println("今天是星期" + weeks[day-1]);
        return weeks[day - 1];
    }

    /**
     * 得到传过来的日期是本月第几周星期几
     *
     * @return
     */
    public static String getMonthWeekInToday(String date) {
        String[] weeks = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar c = Calendar.getInstance();
        c.setTime(strToDate(date));
        int day = c.get(Calendar.DAY_OF_WEEK);// 获致是本周的第几天地, 1代表星期天...7代表星期六
        // System.out.println("今天是本月的第" + week + "周");
        // System.out.println("今天是星期" + weeks[day-1]);
        return weeks[day - 1];
    }

    public static String getDayWeek(String sdate, String strs) {
        // 再转换为时间
        Date date = DateUtils.strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String str = "";
        int hour = c.get(Calendar.DAY_OF_WEEK);
        if (hour == 1) {
            str = strs + "日";
        } else if (hour == 2) {
            str = strs + "一";
        } else if (hour == 3) {
            str = strs + "二";
        } else if (hour == 4) {
            str = strs + "三";
        } else if (hour == 5) {
            str = strs + "四";
        } else if (hour == 6) {
            str = strs + "五";
        } else if (hour == 7) {
            str = strs + "六";
        }
        return str;
    }

    private static int days; // 天数
    private static int hours; // 时
    private static int minutes; // 分
    private static int seconds; // 秒

    public static String getQuot(String time1, String time2) {
        long quot = 0;
        String str = "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();

            int totalSeconds = (int) (quot / 1000);

            // 得到总天数
            days = totalSeconds / (3600 * 24);
            if (days > 0) {
                str = days + "天前";
                return str;
            }
            int days_remains = totalSeconds % (3600 * 24);

            // 得到总小时数
            hours = days_remains / 3600;
            if (hours > 0) {
                str = hours + "小时前";
                return str;
            }
            int remains_hours = days_remains % 3600;

            // 得到分种数
            minutes = remains_hours / 60;
            if (minutes > 0) {
                str = minutes + "分钟前";
                return str;
            }
            // 得到总秒数
            seconds = remains_hours % 60;
            if (seconds > 0) {
                str = seconds + "秒前";
                return str;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    private static int tdays; // 天数
    private static int thours; // 时
    private static int tminutes; // 分
    private static int tseconds; // 秒

    public static String getQuott(String time1, String time2) {
        long quot = 0;
        String str = "";
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            quot = date1.getTime() - date2.getTime();

            int totalSeconds = (int) (quot / 1000);

            // 得到总天数
            tdays = totalSeconds / (3600 * 24);
            if (tdays > 0) {
                str = tdays + "天";
                return str;
            }
            int days_remains = totalSeconds % (3600 * 24);

            // 得到总小时数
            thours = days_remains / 3600;
            if (thours > 0) {
                str = thours + "时";
                return str;
            }
            int remains_hours = days_remains % 3600;

            // 得到分种数
            tminutes = remains_hours / 60;
            if (tminutes > 0) {
                str = tminutes + "分";
                return str;
            }
            // 得到总秒数
            tseconds = remains_hours % 60;
            if (tseconds > 0) {
                str = tseconds + "秒";
                return str;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 判断日期时间段
     *
     * @param date
     * @return
     */
    public static String excuteOneTime(String date) {
        StringBuffer restr = new StringBuffer();
        // date="2013-01-30 8:20:04";
        String[] a = date.split(" ");
        // String a0=a[0];
        // restr.append(a0);
        // restr.append(" ");
        String str = a[1];
        String[] b = str.split(":");
        String b0 = b[0];
        String b1 = b[1];
        // String b2=b[2];
        if (Integer.valueOf(b1) >= 0 && Integer.valueOf(b1) <= 30) {
            restr.append(b0 + ":00" + "-" + b0 + ":30");
        } else if (Integer.valueOf(b1) > 30) {
            int b_0 = Integer.valueOf(b0) + 1;
            restr.append(b0 + ":30" + "-" + Integer.valueOf(b_0) + ":00");
        }
        // System.out.println(b0+b1+b2);
        // System.out.println(b.length);
        return restr.toString();
    }

    /**
     * 得到指定月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到本月最后一个周日的日期
     *
     * @param args
     * @throws Exception
     */
    public static String getMonthAndSunday(int year, int month) {
        String sundays = "";
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        SimpleDateFormat re = new SimpleDateFormat("yyyy-MM-dd");
        Calendar setDate = Calendar.getInstance();
        // 从第一天开始
        int day;
        for (day = 1; day <= getMonthLastDay(year, month); day++) {
            setDate.set(Calendar.DATE, day);
            String str = sdf.format(setDate.getTime());
            if (str.equals("星期日")) {
                sundays = re.format(setDate.getTime());
            }
        }
        return sundays;
    }

    /**
     * 得到本月第一个周六日期
     *
     * @param args
     * @throws Exception
     */
    public static String getFirstSaturdayOfWeek(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            cal.add(Calendar.DATE, 1);
        }
        Date date = cal.getTime();
        return dateToStr(date);
    }

    /**
     * 获取当前时间所在周的周一时间
     *
     * @return
     */
    public static Date getMonday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.CHINA);
        Calendar rl = Calendar.getInstance(Locale.CHINA);
        // 当前时间，貌似多余，其实是为了所有可能的系统一致
        // rl.setTimeInMillis(System.currentTimeMillis());
        // System.out.println("当前时间:"+format.format(rl.getTime()));
        // rl.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // System.out.println("周一时间:"+format.format(rl.getTime()));
        int mondayPlus = DateUtils.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        rl.setTime(monday);
        return DateUtils.strToDateLong(format.format(rl.getTime()));
    }

    // （1）获得当前日期与本周一相差的天数
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 获取当前时间所在周的周日时间
     *
     * @return
     */
    public static Date getSunday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00", Locale.CHINA);
        Calendar rl = Calendar.getInstance(Locale.CHINA);
        rl.setFirstDayOfWeek(Calendar.MONDAY);
        // 当前时间，貌似多余，其实是为了所有可能的系统一致
        // rl.setTimeInMillis(System.currentTimeMillis());
        // System.out.println("当前时间:"+format.format(rl.getTime()));
        rl.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // System.out.println("周一时间:"+format.format(rl.getTime()));
        return DateUtils.strToDateLong(format.format(rl.getTime()));
    }

    /**
     * 获取时间提示信息，某一时间距离现在n秒、n分钟、n小时、n天.时间距现在比较精确到秒
     *
     * @param args
     * @throws Exception
     */
    public static String getDateTips(Date beginDate) {
        Long now = (DateUtils.getNow().getTime()) / 1000L;
        Long sourse = (Long) (beginDate.getTime() / 1000L);
        Long differ = now - sourse;
        String showDate = "";
        if (differ <= 60L) {
            // 0-60(包含)秒显示 秒之前
            showDate = differ + "秒前";
        } else if (differ <= (60L * 60L)) {
            // 如果是在60分钟内
            showDate = (int) (Math.floor(differ / 60L)) + "分钟前";
        } else if (differ <= 60L * 60L * 24) {
            // 如果是在1天内
            showDate = (int) (Math.floor(differ / (60L * 60L))) + "小时前";
        } else if (differ <= 60L * 60L * 24 * 5) {
            // 如果是在5天内
            showDate = (int) (Math.floor(differ / (60L * 60L * 24L))) + "天前";
        } else {
            Format format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            showDate = format.format(beginDate);
        }
        if (null != showDate && showDate.length() > 18 && showDate.contains(".")) {
            showDate = showDate.substring(0, showDate.length() - 2);
        }
        return showDate;
    }

    /**
     * 将长时间格式字符串转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @return
     * @throws ParseException
     */
    public static String longToStrng(String time) throws ParseException {
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        // dateFormat.setLenient(false);
        if ("".equals(time)) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.parse(time);
            return dateToStrLong(strToDateLong(time));
        } catch (Exception e) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.parse(time);
                return dateToStrLong(strToDate(time));
            } catch (Exception e2) {
                try {
                    Date date = new Date(Long.parseLong(time.trim()));
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(date);
                    // System.out.println("TIME:::" + dateString);
                    return dateString;
                } catch (Exception e3) {
                    try {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                        return DateUtils.dateToStrLong(df.parse(time));
                    } catch (Exception e4) {
                        SimpleDateFormat parser = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZ yyyy", Locale.ENGLISH);
                        Date date = parser.parse(time);
                        // System.out.println(DateUtils.dateToStrLong(date));
                        return DateUtils.dateToStrLong(date);
                    }

                }

            }
        }
    }

    /**
     * 将长时间格式字符串转换为字符串 上午 HH:mm
     *
     * @return
     * @throws ParseException
     */
    public static String dateToTime(Date date) throws ParseException {
        Date now = getNowDate();
        Date todayMiddle = strToDateLong(dateToStr(now) + " 12:00:00");
        if (getDays(now, date) != 0) {
            return dateToStrLong(date);
        }
        if (todayMiddle.before(date)) {
            Date d = strToDateLong(getPreTime(dateToStrLong(date), "-720"));
            return "下午  " + getTimeShorts(d);
        } else {
            return "上午  " + getTimeShorts(date);
        }
    }

    /**
     * 比较两个日期相差天数(A-B)日期格式：yyyy-MM-dd
     *
     * @param dateA
     * @param dateB
     * @return 相差天数
     */
    public static int compareDate(String dateA, String dateB) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int days;
        try {
            Date dayA = simpleDateFormat.parse(dateA);
            Date dayB = simpleDateFormat.parse(dateB);
            days = (int) ((dayA.getTime() - dayB.getTime()) / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            days = 0;
        }
        return days;
    }

    /**
     * 指定时间当月第一天
     *
     * @param date
     * @return
     */
    public static Date getMonthFirstDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 指定时间后一月的第一天
     *
     * @param date
     * @return
     */
    public static Date getNextMonthFirstDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static void main(String[] args) throws Exception {
        // String s=getMonthWeekInToday("2013-01-31");
        // String a=getFirstSaturdayOfWeek(2013,04);
        // System.out.println(a);

        // SimpleDateFormat format=new
        // SimpleDateFormat("yyyy-MM-dd hh:mm:ss",Locale.CHINA);
        // Calendar rl=Calendar.getInstance(Locale.CHINA);
        // 当前时间，貌似多余，其实是为了所有可能的系统一致
        // rl.setTimeInMillis(System.currentTimeMillis());
        // System.out.println("当前时间:"+format.format(rl.getTime()));
        // rl.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        // System.out.println("周一时间:"+DateUtils.strToDateLong(format.format(rl.getTime())));

        // Date date=new Date(1372003200000L);
        // Date dd=DateUtils.getDateLong(date);
        // System.out.println(dd);
        // String start = DateUtils.getStringDateShort().concat(" 00:00:00");
        // String end = DateUtils.getStringDateShort().concat(" 59:59:59");
        // // System.out.println(DateUtils.strToDateLong(start));
        // System.out.println(DateUtils.getMonday());
        // System.out.println(DateUtils.getSunday());

        // SimpleDateFormat parser = new
        // SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZ yyyy", Locale.ENGLISH);
        // Date date = parser.parse("Mon Sep 16 22:36:02 CST 2013");
        // System.out.println(DateUtils.dateToStrLong(date));
//        System.out.println(dateToTime(getNow()));
//        System.out.println(dateToTime(strToDateLong("2013-10-24 12:00:00")));
//        System.out.println(dateToTime(strToDateLong("2013-10-25 12:00:00")));
//        System.out.println(dateToTime(strToDateLong("2013-10-25 11:15:00")));
        System.out.println("");
        System.out.println(getNextDay(new Date(), 2, Calendar.HOUR_OF_DAY));

        System.out.println(getDateBeforeMin(new Date(), 30));

    }

    /**
     * 验证日期合法性
     *
     * @param str
     * @param format
     * @return
     */
    public static boolean isValidDate(String str, String format) {
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = (Date) formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取当月第一天
     *
     * @param dateStr
     * @param srcFormat
     * @param targetFormat
     * @return
     * @throws Exception
     */

    public static String getFirstDay(String dateStr, String srcFormat, String targetFormat) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat(srcFormat).parse(dateStr));
        int maxday = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxday);
        return new SimpleDateFormat(targetFormat).format(calendar.getTime());

    }

    /**
     * 获取当月第一天
     *
     * @param dateStr
     * @param srcFormat
     * @param targetFormat
     * @return
     * @throws Exception
     */
    public static Date getFirstDay(String dateStr, String srcFormat) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat(srcFormat).parse(dateStr));
        int maxday = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxday);
        return calendar.getTime();

    }

    /**
     * 获取当月最后一天+
     *
     * @param dateStr
     * @param srcFormat
     * @param targetFormat
     * @return
     * @throws Exception
     */

    public static String getLastDay(String dateStr, String srcFormat, String targetFormat) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat(srcFormat).parse(dateStr));
        int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxday);
        return new SimpleDateFormat(targetFormat).format(calendar.getTime());
    }

    /**
     * 获取当月最后一天+
     *
     * @param dateStr
     * @param srcFormat
     * @param targetFormat
     * @return
     * @throws Exception
     */
    public static Date getLastDay(String dateStr, String srcFormat) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat(srcFormat).parse(dateStr));
        int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, maxday);
        return calendar.getTime();
    }

    /**
     * 转换日期格式
     *
     * @param dateStr
     * @param srcFormat
     * @param targetFormat
     * @return
     * @throws Exception
     */
    public static String formatDateStr(String dateStr, String srcFormat, String targetFormat) throws Exception {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat sdfSrc = new SimpleDateFormat(srcFormat);
        SimpleDateFormat sdfTarget = new SimpleDateFormat(targetFormat);
        calendar.setTime(sdfSrc.parse(dateStr));
        return sdfTarget.format(calendar.getTime());
    }

    public static int getCurrentUnixTime() {
        return getUnixTimeByDate(new Date());
    }

    /**
     * 将Date型时间转换成int型时间(1970年至今的秒数)
     *
     * @param unixTime 1970年至今的秒数
     * @return
     */
    public static int getUnixTimeByDate(Date date) {
        return (int) (date.getTime() / 1000);
    }

    /**
     * 获取增加后的月
     *
     * @param dateStr
     * @param srcFormat
     * @param monthAdd
     * @return
     * @throws Exception
     */
    public static String getMonthAdd(String dateStr, String srcFormat, int monthAdd) throws Exception {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new SimpleDateFormat(srcFormat).parse(dateStr));
        calendar.add(Calendar.MONTH, monthAdd);
        return new SimpleDateFormat(srcFormat).format(calendar.getTime());
    }

    /**
     * 获取指定日期的第二天
     *
     * @param date
     * @return
     */
    public static Date getSecondDay(Date date) {
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(date);
        endDate.add(Calendar.DAY_OF_MONTH, 1);
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);
        return endDate.getTime();

    }

    /**
     * 将日期和时间字符串组合起来并转成Date类型
     **/
    public static Date getDateByDateStrAndDayStr(String dateStr, String timeStr) {
        String dateTimeStr = dateStr + " " + timeStr;
        Date resultDate = null;
        try {
            if (StringUtils.isNotBlank(dateTimeStr)) {
                resultDate = org.apache.commons.lang3.time.DateUtils.parseDate(dateTimeStr, DateUtils.DATE_FORMAT_MINUTE);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resultDate;
    }

    public static String calculatePath() {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String date = String.valueOf(calendar.get(Calendar.DATE));

        return year + "/" + month + "/" + date;
    }

    public static String calculateSeconds(long seconds) {
        int days = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (days * 24);
        long minutes = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        StringBuilder sb = new StringBuilder("");
        if (days > 0) {
            sb.append(days).append("天");
        }
        if (hours > 0) {
            sb.append(hours).append("小时");
        }
        if (minutes > 0) {
            sb.append(minutes).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        return sb.toString();
    }

    public static Date validateTime(String date, String time, String errMsgPre) {
        checkArgument(StringUtils.isNotBlank(date), errMsgPre.concat("日期不能为空"));
        checkArgument(StringUtils.isNotBlank(time), errMsgPre.concat("时间不能为空"));
//        checkArgument(date.matches("\\d{4}/\\d{2}/\\d{2}"), errMsgPre.concat("日期格式不合法"));
//        checkArgument(time.matches("\\d{2}:\\d{2}"), errMsgPre.concat("时间格式不合法"));
        return DateUtils.getDateByDateStrAndDayStr(date, time);
    }

    private static void checkArgument(boolean expression, String errorMessage) {
        if (!expression) {
            throw new PublicException(errorMessage);
        }
    }

    /**
     * 分钟转为秒
     *
     * @param minutes
     * @return int
     */
    public static int switchMinToSeconds(int minutes) {
        return Math.multiplyExact(minutes, ONE_MIN);
    }

    /**
     * 分钟转为秒
     *
     * @param minutes
     * @return Long
     */
    public static long switchMinToSeconds(long minutes) {
        return Math.multiplyExact(minutes, ONE_MIN);
    }


    /**
     * 获取当天的某个时间点
     **/
    public static Date getTodayDate(int hours, int minutes, int seconds) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hours);
        c.set(Calendar.MINUTE, minutes);
        c.set(Calendar.SECOND, seconds);
        return c.getTime();
    }

    /**
     * 计算两个日期相差天数小时数
     *
     * @param date
     * @return
     */
    public static long getDatePoor(Date endDate, Date startDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();

        // 计算差多少小时
        long hour = diff / nh;
        return hour;
    }

    /**
     * 获取指定日期前 minutes 时间戳
     *
     * @param minutes
     * @return
     */
    public static Long getDateBeforeMin(Date date, int minutes) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.MINUTE, -minutes);
        long time = now.getTimeInMillis();
        return time;
    }

    /**
     * 获取当天23点59分59秒毫秒数
     *
     * @return
     */

    public static long getTwelveTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar.getTime().getTime();
    }

    /**
     * yyyy-MM-dd HH:mm:ss 转换为时间戳
     *
     * @param dateStr
     * @return
     */
    public static long dateToStamp(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String formatDateString(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.format(format.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}

