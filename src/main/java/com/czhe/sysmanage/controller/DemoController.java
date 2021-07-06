package com.czhe.sysmanage.controller;

import java.text.NumberFormat;
import java.util.*;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/6/20 15:12
 * @description 计算
 **/
public class DemoController {

    static Map<Integer, String> allValue = new HashMap<>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20};
        int k = 12;
        findVal(a, k);
        System.out.println(result);
    }

    /**
     * 给定一个整数数组nums和一个整数目标值target 在该数组中找出两个差值为target
     *
     * @param nums
     * @param target
     * @return
     */
    public static void findVal(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int k;
            if (nums[i] > target) {
                k = nums[i] - target;
                allValue.put(i, nums[i] + " " + k);
            } else {
                k = target - nums[i];
                allValue.put(i, (nums[i] + " " + k));
            }

        }
        for (int i = 0; i < nums.length; i++) {
            for (Integer j : allValue.keySet()) {
                if (j == i)
                    continue;
                int k = Integer.valueOf(allValue.get(j).split(" ")[1]);
                if (k == nums[i]) {
                    int l = Integer.valueOf(allValue.get(j).split(" ")[0]);
                    result.add(l);
                    result.add(nums[i]);
                }
            }
        }
    }

    /**
     * 递归算法
     *
     * @param n
     * @return
     */
    public static int calculation(int n) {
        if (n == 1) {
            return n;
        }
        return n + calculation(n - 1);
    }


    /**
     * 摇骰子算概率
     *
     * @param n 摇骰子次数
     * @param m 骰子个数
     * @return
     */
    public static String random(int n, int m) {
        int max = 6;//总概率
        int num = 5;//其余5点概率
        int i = 1;
        while (n > 1) {
            i++;
            max *= 6;
            n--;
        }
        while (m > 1) {
            i++;
            max *= 6;
            m--;
        }
        while (i > 1) {
            num *= 5;
            i--;
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) (max - num) / (float) max * 100);
        return result;
    }

    /**
     * 从最小值到最大值之间选取位数组合不重复情况下的总数
     *
     * @param min   最小值
     * @param max   最大值
     * @param group 位数
     * @return
     */
    public static int combination(int min, int max, int group) {
        if ((max - min) + 1 < group) {
            //范围不够取group位数
            return -1;
        }
        max = (max - min) + 1;
        min = 1;
        return combinationUtil(min, max, group);
    }

    /**
     * 计算
     *
     * @param min
     * @param max
     * @param group
     * @return
     */
    public static int combinationUtil(int min, int max, int group) {
        int b = min;
        int a = max;
        while (group > 1) {
            min++;
            max--;
            a *= max;
            b *= min;
            group--;
        }
        return a / b;
    }

    /**
     * 数据公式累加
     *
     * @param n
     * @return
     */
    public static int accumulate(int n) {
        return n * (n + 1) / 2;
    }

}
