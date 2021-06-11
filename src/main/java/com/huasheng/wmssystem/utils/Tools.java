package com.huasheng.wmssystem.utils;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/4/28 10:19
 * @Description ：自定义工具类
 */
public class Tools {

    /**
     * 检测字符串是否为空(null,"","null")
     *
     * @param s
     * @return 为空返回true，否则返回false
     */
    public static boolean isEmpty(String s) {
        return s == null || "".equals(s) || "null".equals(s);
    }

    /**
     * 检测字符串是否不为空(null,"","null")
     *
     * @param s
     * @return 不为空返回true，否则返回false
     */
    public static boolean notEmpty(String s) {
        return s != null && !"".equals(s) && !"null".equals(s);
    }

    /**
     * 给一个指定的日期加上天数，返回相加之后的日期
     *
     * @param timeParam：指定日期
     * @param day:           要加上的天数
     * @throws ParseException
     */
    public static String addDate(String timeParam, long day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = sdf.parse(timeParam);//指定日期
        long time = date.getTime();//得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000;//要加上的天数转换成毫秒数
        time += day;//相加得到最终的毫秒数

        Date newDate = new Date(time);//将毫秒数转换成时间格式
        return sdf.format(newDate);//格式化成日期 年-月-日
    }

    /**
     * 整数（秒数）转换成为 时分秒 格式（xx:xx:xx）
     *
     * @param time:秒数
     */
    public static String numberFormatTime(int time) {

        String timeStr = "";

        int hour = time / 3600;//时
        int minute = time / 60 % 60;//分
        int second = time % 60;//秒

        if (hour == 0) {
            if (minute == 0) {
                timeStr = "00:" + timeUnitFormat(second);
            } else {
                timeStr = timeUnitFormat(minute) + ":" + timeUnitFormat(second);
            }
        } else {
            timeStr = timeUnitFormat(hour) + ":" + timeUnitFormat(minute) + ":" + timeUnitFormat(second);
        }
        return timeStr;

    }

    /**
     * 判断时间数字小于等于9，往前补0
     *
     * @param time
     */
    public static String timeUnitFormat(int time) {
        String timeStr = String.valueOf(time);
        if (time <= 9) {
            return "0" + timeStr;
        } else {
            return timeStr;
        }
    }

    /**
     * 返回 日时分秒
     *
     * @param second 秒数
     */
    public static String secondToTIme(long second) {

        long days = second / 86400;//转化天数       1天= 1 *24 * 60 * 60 =86400秒
        second = second % 86400;//剩余秒数

        long hours = second / 3600;//转化小时数    1小时= 1 * 60 * 60 =3600秒
        second = second % 3600;//剩余秒数

        long minutes = second / 60;//转化分钟数    1小时= 1 * 60 =60秒
        second = second % 60;//剩余秒数

        if (days > 0) {
            return days + "天," + hours + "小时," + minutes + "分钟," + second + "秒";
        } else {
            return hours + "小时," + minutes + "分钟," + second + "秒";
        }
    }

    /**
     * 获取数字随机数
     *
     * @param length
     * @return
     */
    public static String getNumberRandom(int length) {

        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }

    /**
     * 获取字母随机数
     *
     * @param length
     * @return
     */
    public static String getCharRandom(int length) {

        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            //输出是大写字母还是小写字母
            int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
            val += (char) (random.nextInt(26) + temp);
        }
        return val;
    }

    /**
     * 获取字母数字随机数
     *
     * @param length
     * @return
     */
    public static String getStringRandom(int length) {

        String val = "";
        Random random = new Random();
        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 判断字符串数组中是否包含某字符串
     *
     * @param arr             ：字符串数组
     * @param targetValue：字符串
     * @return
     */
    public static boolean indexString(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    /**
     * 判断字符串是否是数字
     *
     * @param number 字符串
     * @return
     */
    public static boolean isNumber(String number) {
        boolean isInt = Pattern.compile("^-?[1-9]\\d*$").matcher(number).find();
        boolean isDouble = Pattern.compile("^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$").matcher(number).find();
        return isInt || isDouble;
    }

    /**
     * 计算距离生日还有多少天
     *
     * @param addtime：生日日期
     * @return
     */
    public static int getBirthDay(String addtime) {
        int days = 0;
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String clidate = addtime;
            Calendar cToday = Calendar.getInstance(); // 存今天
            Calendar cBirth = Calendar.getInstance(); // 存生日
            cBirth.setTime(myFormatter.parse(clidate)); // 设置生日
            cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
            if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
                // 生日已经过了，要算明年的了
                days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
                days += cBirth.get(Calendar.DAY_OF_YEAR);
            } else {
                // 生日还没过
                days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 将请求参数与数值放入Map中
     *
     * @param request
     * @return
     */
    public static Map<String, Object> formatRequestParams(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        //request.getParameterNames 将发送请求页面中form表单里所有具有name属性的表单对象获取(包括button).返回一个Enumeration类型的枚举.
        Enumeration<String> paramNames = request.getParameterNames();
        //通过Enumeration的hasMoreElements()方法遍历
        while (paramNames.hasMoreElements()) {
            //再由nextElement()方法获得枚举的值.此时的值是form表单中所有控件的name属性的值.
            String paramName = paramNames.nextElement();
            //request.getParameterValues 将获取所有form表单中name属性为"name"的值.该方法返回一个数组.遍历数组就可得到value值.
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }


    /**
     * 获取当前时间的三种方法
     */
    public static String getNowTimeStr() {
        //方法1：通过Util包中的Date获取
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return dateFormat.format(date);

        //方法2：通过Util包的Calendar 获取
//		Calendar calendar= Calendar.getInstance();
//		SimpleDateFormat dateFormat2= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
//		return dateFormat2.format(calendar.getTime());

        //方法3：通过Util包的Calendar 获取时间，分别获取年月日时分秒
//		Calendar cal=Calendar.getInstance();
//		int y=cal.get(Calendar.YEAR);
//		int m=cal.get(Calendar.MONTH);
//		int d=cal.get(Calendar.DATE);
//		int h=cal.get(Calendar.HOUR_OF_DAY);
//		int mi=cal.get(Calendar.MINUTE);
//		int s=cal.get(Calendar.SECOND);
        //System.out.println("现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒");
//		return "现在时刻是"+y+"年"+m+"月"+d+"日"+h+"时"+mi+"分"+s+"秒";

    }


    /**
     * @return java.lang.String
     * @Description 获取空白ID:FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF
     * @Param []
     * @Author xjTang
     * @Date Create by 2021/4/28 10:45
     */
    public static String GetParentId() {
        return "FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF";
    }

    /**
     * 获取uuid
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();

        return str;
    }

    /**
     * @return java.sql.Timestamp
     * @Description 获取当前时间戳
     * @Param []
     * @Author xjTang
     * @Date Create by 2021/4/30 10:17
     */
    public static Timestamp getNowTime() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }


    /**
     * 直接输出JSON 格式的Responses信息
     *
     * @param response HttpServletResponse
     * @param msg      JSON
     * @throws IOException ignore
     */
    public static void defaultPrintJson(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(msg);
        printWriter.flush();
        printWriter.close();
    }

    /**
     * @return boolean
     * @Description uuid校验
     * @Param [uuid]
     * @Author xjTang
     * @Date Create by 2021/6/10 14:15
     */
    public static boolean checkUuid(String uuid) {
        return uuid.matches("([0-9a-fA-F]{8}(-[0-9a-fA-F]{4}){3}-[0-9a-fA-F]{12}?)");
    }


}
