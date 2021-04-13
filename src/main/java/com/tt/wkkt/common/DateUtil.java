package com.tt.wkkt.common;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author tianting
 * @Description
 * @Param
 * @return
 **/

public class DateUtil {
    private final static String DATE_FORMATE_STYLE = "yyyy-MM-dd";
    private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private final static String YMDHMS = "yyyy年MM月dd日HH时mm分ss秒";
    private final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    /**
     * 获取当前的日期
     *
     * @return
     */
    public static String currTime() {
        SimpleDateFormat format = new SimpleDateFormat(YMDHMS);
        Calendar ca = Calendar.getInstance();
        String currTime = format.format(ca.getTime());
        return currTime;
    }

    /**
     * @return 获得当前Calendar
     */
    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }


    /**
     * 获取日期的时间戳 失败返回0
     * @param date 日期字符串，格式yyyy-MM-dd
     * @return 时间戳
     */
    public static long getMillis(String date) {
        if (date==null || date.trim().equals("")) return 0;
        return getMillis(StringToDate(date,"yyyy-MM-dd"));
    }

    /**
     * 获取日期的时间戳 失败返回0
     * @param date 日期
     * @return 时间戳
     */
    public static long getMillis(Date date) {
        if (date==null) return 0;
        return date.getTime();
    }
    /**
     * @return 获得今年
     */
    public static int getThisYear() {
        return getCalendar().get(Calendar.YEAR);
    }

    /**
     * @return 获得本月
     */
    public static int getThisMonth() {
        return getCalendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当天的日期
     *
     * @return
     */
    public static int getThisDay() {
        return getCalendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @return 获得当前时间
     */
    public static Date getNow() {
        return getCalendar().getTime();
    }

    /**
     * 根据传入的时间戳转换为Date对象
     *
     * @param date
     * @return
     */
    public static Date getDate(long date) {
        return new Date(date);
    }

    /**
     * 将传入的时间戳转换为对应时间的字符串
     *
     * @param date
     * @return
     */
    public static String getDateStr(long date) {
        return new SimpleDateFormat(DATE_FORMATE_STYLE).format(getDate(date));
    }

    public static String getDateStr(Date date) {
        return new SimpleDateFormat(DATE_FORMATE_STYLE).format(date);
    }

    public static String getYyyyMmDdHhMmSs(Date date) {
        return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).format(date);
    }

    /**
     * 获取yyyyMM格式的当前时间
     *
     * @return
     */
    public static String getYMStr() {
        return new SimpleDateFormat("yyyyMM").format(new Date());
    }

    /**
     * 获取yyyyMMdd格式的当前时间
     *
     * @return
     */
    public static String getYMDStr() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * 将字符串yyyyMMdd，转换为yyyy-MM-dd格式
     * @param date 字符串格式yyyy-MM-dd
     * @return
     */
    public static String getYMDStr(String date){
        Date time = StringToDate(date, "yyyyMMdd");
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * 将日期格式化为指定格式的字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formateDate(Date date, String pattern) {
        if (date == null || pattern == null || pattern.length() == 0) {
            throw new NullPointerException("日期或者格式化的模式不能为空。");
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String formateDate(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            throw new NullPointerException("日期或者格式化的模式不能为空。");
        }
        return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * 将字符串转换成时间
     *
     * @param date    字符串时间
     * @param pattern 转换的时间格式
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date, String pattern) throws ParseException {
        return new SimpleDateFormat(pattern).parse(date);
    }

    /**
     * begin of a day, 2015-01-03 00:00:00
     *
     * @param date
     * @return
     */
    public static Date getBeginTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);

        return c.getTime();
    }

    /**
     * end of a day, 2015-01-03 23:59:59
     *
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);

        return c.getTime();
    }


    public static long getLongTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static int getIntTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    public static int getInteger(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }



    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i), timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    // 如果timestamps的size为2，这是差值只有一个，因此要给默认值
                    minAbsoluteValue = absoluteValues.get(0);
                }
                for (int i = 0; i < absoluteValues.size(); i++) {
                    for (int j = i + 1; j < absoluteValues.size(); j++) {
                        if (absoluteValues.get(i) > absoluteValues.get(j)) {
                            minAbsoluteValue = absoluteValues.get(j);
                        } else {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                    } else if (absoluteValues.size() == 1) {
                        // 当timestamps的size为2，需要与当前时间作为参照
                        long dateOne = timestampsLastTmp[0];
                        long dateTwo = timestampsLastTmp[1];
                        if ((Math.abs(dateOne - dateTwo)) < 100000000000L) {
                            timestamp = Math.max(timestampsLastTmp[0], timestampsLastTmp[1]);
                        } else {
                            long now = new Date().getTime();
                            if (Math.abs(dateOne - now) <= Math.abs(dateTwo - now)) {
                                timestamp = dateOne;
                            } else {
                                timestamp = dateTwo;
                            }
                        }
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }



    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date     日期字符串
     * @param parttern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String parttern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(parttern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }


    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date     日期
     * @param parttern 日期格式
     * @return 日期字符串
     */
    public static String DateToString(Date date, String parttern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(parttern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }



    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }


    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int yearAmount) {
        return addInteger(date, Calendar.MONTH, yearAmount);
    }


    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }


    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date      日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date      日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int hourAmount) {
        return addInteger(date, Calendar.MINUTE, hourAmount);
    }



    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date      日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int hourAmount) {
        return addInteger(date, Calendar.SECOND, hourAmount);
    }



    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }


    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH);
    }



    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }



    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }



    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }






    /**
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        //	date = DateUtil.StringToDate(DateUtil.getDate(date));
        long time = Math.abs(date.getTime() - otherDate.getTime());
        return (int) (time / (24 * 60 * 60 * 1000));
    }

    /**
     * 两个时间相比较，若第一个大于第二个，则返回1，相等返回0，小于返回-1
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int compare(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        if (time1 == time2) {
            return 0;
        } else if (time1 > time2) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 获取当月第一天
     *
     * @return
     */
    public static String firstDayOnMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
    }

    /**
     * 获取当月最后一天
     *
     * @return
     */
    public static String lastDayOnMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        return last;
    }

    /**
     * 获取30天前的日期
     *
     * @return
     */
    public static String getBeforThirtyDay() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, -30);
        String beforThirtyDay = format.format(ca.getTime());
        return beforThirtyDay;
    }

    /**
     * 获取当前的日期
     *
     * @return
     */
    public static String getToday() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        String beforThirtyDay = format.format(ca.getTime());
        return beforThirtyDay;
    }



    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param seconds
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {

        if (seconds.length() > 10) {
            seconds = seconds.substring(0, 10);
        }

        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 增加、减少天数
     *
     * @param date     "2018-10-19"
     * @param dayCount 可以为负
     * @return
     * @throws ParseException
     */
    public static String addByDay(String date, int dayCount) throws ParseException {
        Date d = DateUtil.parseDate(date, DATE_FORMATE_STYLE);
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMATE_STYLE);
        String r = sdf.format((d.getTime() + 1000 * 60 * 60 * 24 * dayCount));
        return r;
    }

    /**
     * 获得时间10分钟的整数时间点
     * @return
     */
    public static String getTenMinutePoint(String time) throws ParseException{
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date =sdf.parse(time);
        calendar.setTime(date);
        calendar.setTime(date);
        int minute = calendar.get(Calendar.MINUTE);
        //计算10的整数分钟
        minute = Math.round(minute/10*10);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        String s = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
        return s;
    }

    /**
     * 将yyyyMMdd类的格式，转换为yyyy-MM-dd hh:mm:ss格式
     * @param date
     * @return
     */
    public static String getYMDOStr(String date){
        String dataType = "yyyy-MM-dd";
        if (!StringUtils.isEmpty(date)) {
            Date time = StringToDate(date, "yyyyMMdd");
            if (date.length()==6) {
                dataType = "yyyy-MM";
                time = StringToDate(date, "yyyyMM");
            } else if (date.length()==8) {
                dataType = "yyyy-MM-dd";
                time = StringToDate(date, "yyyyMMdd");
            } else if (date.length()==10) {
                dataType = "yyyy-MM-dd HH";
                time = StringToDate(date, "yyyyMMddhh");
            } else if (date.length()==12) {
                dataType = "yyyy-MM-dd HH:mm";
                time = StringToDate(date, "yyyyMMddHHmm");
            } else if (date.length()==14) {
                dataType = "yyyy-MM-dd HH:mm:ss";
                time = StringToDate(date, "yyyyMMddHHmmss");
            }
            return new SimpleDateFormat(dataType).format(time);
        }
        return "";
    }

    /**
     * 判断是否超过24小时
     */
    public static boolean overOneDay(String date1, String date2) throws Exception {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date start = sdf.parse(date1);
        java.util.Date end = sdf.parse(date2);
        long cha = end.getTime() - start.getTime();
        double result = cha * 1.0 / (1000 * 60 * 60);
        if(result<=24){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 将yyyyMMddHHmmss 转换成 YYYY_MM_DD_HH_MM_SS
     * @param date
     * @return
     */
    public static String stringToStringByReg(String date){
        String reg = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
        date = date.replaceAll(reg, "$1-$2-$3 $4:$5:$6");
        return date;
    }
}
