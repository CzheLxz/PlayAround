package com.czhe.sysmanage.util;

/**
 * @author czhe
 * @create 2021/7/6
 * @description
 */
public class Binary {

    public static String conversion(int num) {
        StringBuffer sb = new StringBuffer();
        while (num >= 2) {
            sb.append(num % 2);
            num = num / 2;
        }
        sb.append(num);
        sb.reverse();
        return sb.toString();
    }

    public static int reversion(String binary) {
        if (binary.length() == 1) {
            return Integer.parseInt(binary);
        }
        StringBuffer sb = new StringBuffer(binary);
        int result = 1;
        char[] k = binary.toCharArray();
        for (int i = 1; i < k.length; i++) {
            if (k[i] == '0') {
                result *= 2;
            } else {
                result = result * 2 + 1;
            }
        }
        return result;
    }

}
