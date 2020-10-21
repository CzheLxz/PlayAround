package com.czhe.sysmanage.controller;

import com.czhe.sysmanage.entity.Person;
import com.czhe.sysmanage.util.ObjectCopy;
import org.springframework.beans.BeanUtils;

import java.text.NumberFormat;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/6/20 15:12
 * @description 计算
 **/
public class DemoController {

    public static void main(String[] args) throws Exception {

        //System.out.println(Calculation(5));
        //System.out.println("数值3-7之间取2位数组合不重复的总数:" + combination(3, 7, 2));
        //int[] array = {2, 1, 3, 6, 5, 4, 8, 7, 9};
        //System.out.println("从数组中取2位数组合不重复的总数: " + combinationArray(array, 2));
        //System.out.println("从1加到n的和为: " + accumulate(100));
        /*System.out.println("两颗骰子摇两次至少出现一个一点的概率为:" + random(2, 2) + "%");*/
        /*从1加到N*/

        Person personA = new Person("A001", "czhe", "JAVA", "male", 22, 1000000000);
        Person personB = new Person();
        Person personC = new Person();
        System.out.println(personB.toString());
        System.out.println(personC.toString());
        ObjectCopy.copyProperties(personA, personB, "name");
        BeanUtils.copyProperties(personA, personC, "id");
        System.out.println(personB.toString());
        System.out.println(personC.toString());


    }

    /**
     * 递归算法
     *
     * @param n
     * @return
     */
    public static int Calculation(int n) {
        if (n == 1) {
            return n;
        }
        return n + Calculation(n - 1);
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
     * 从数组选取位数组合不重复情况下的总数
     *
     * @param array 数组
     * @param group 位数
     * @return
     */
    public static int combinationArray(int[] array, int group) {
        if (array.length < group) {
            //范围不够取group位数
            return -1;
        }
        int max = array.length;
        int min = 1;
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
     * 累加从1加到n
     *
     * @param n
     * @return
     */
    public static int accumulate(int n) {
        return n * (n + 1) / 2;
    }

}
