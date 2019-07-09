package com.glz.authentication.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author czhe
 * @Description 验证工具类
 */
public class VerifyUtils {

    /**
     * 第三方注册验证
     *
     * @param registerTpye
     * @return
     */
    public static boolean verifyRegisterTpye(String registerTpye) {
        String[] sndChnlNoArray = {"1", "2", "3"};
        for (String sndNo : sndChnlNoArray) {
            if (sndNo.equals(registerTpye)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 登录渠道认证
     *
     * @param sndChnlNo 登录渠道
     * @return
     */
    public static boolean verifySndChnlNo(String sndChnlNo) {
        String[] sndChnlNoArray = {"A01", "I01", "W01", "W02", "T03"};
        for (String sndNo : sndChnlNoArray) {
            if (sndNo.equals(sndChnlNo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 登录渠道认证
     *
     * @param loginType 登录方式
     * @return
     */
    public static boolean verifyLoginType(String loginType) {
        String[] sndChnlNoArray = {"CG", "MB", "QQ", "WX", "XL"};
        for (String sndNo : sndChnlNoArray) {
            if (sndNo.equals(loginType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 密码校验器
     *
     * @param pwd 密码
     * @return 由数字和字母组成，并且要同时含有数字和字母可包含特殊字符，且长度要在8-16位之间
     */
    public static boolean verifyPassword(String pwd) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\s\\S]{8,16}$";
        return pwd.matches(regex);
    }

    /**
     * IPV4地址校验
     *
     * @param str IPV4地址
     */
    public static boolean isIPAddressByRegex(String str) {
        String regex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        // 判断ip地址是否与正则表达式匹配
        if (str.matches(regex)) {
            String[] arr = str.split("\\.");
            for (int i = 0; i < 4; i++) {
                int temp = Integer.parseInt(arr[i]);
                //如果某个数字不是0到255之间的数 就返回false
                if (temp < 0 || temp > 255) return false;
            }
            return true;
        } else return false;
    }

    /**
     * 用户名校验器
     *
     * @param userName 用户名
     * @return 由数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间
     */
    public static boolean verifyUserName(String userName) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return userName.matches(regex);
    }


    /**
     * 获取指定日期
     *
     * @param date 指定日期
     * @param day  天数
     */
    public static Date addDate(Date date, long day) {
        long time = date.getTime(); // 得到指定日期的毫秒数
        day = day * 24 * 60 * 60 * 1000; // 要加上的天数转换成毫秒数
        time += day; // 相加得到新的毫秒数
        return new Date(time); // 将毫秒数转换成日期
    }


    /**
     * 检查用户输入的姓名、性别、生日是否合法
     * 姓名要求长度不能超过10个，性别根据数据库
     */
    public static boolean checkLawful(String name, String sex, String birthday) {
        if (sex.equals("1") || sex.equals("0") || sex.equals("-1")) {
            if (checkBirthday(birthday) == false) {
                return false;
            }
            if (name.length() > 10) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }


    /**
     * 判断输入的生日是否符合
     */
    public static boolean checkBirthday(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01
            format.setLenient(false);
            Date date = format.parse(strDate);
            //判断传入的yyyy年-MM月-dd日 字符串是否为数字
            String[] sArray = strDate.split("-");
            for (String s : sArray) {
                boolean isNum = s.matches("[0-9]+");
                if (!isNum) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])| (17[0,1,3,5-8]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * @Description 测试
     */
    public static void main(String[] args) {
    }
}
