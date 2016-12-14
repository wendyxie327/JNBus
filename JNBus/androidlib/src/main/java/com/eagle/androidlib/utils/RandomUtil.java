package com.eagle.androidlib.utils;

import java.util.Random;

/**
 * Created by Administrator on 2016/4/21 0021.
 */
public class RandomUtil {

    /**
     * 取当前时间的毫秒后八位，作为八位随机码
     * @return
     */
    public static int currentTimeMillisRandom(){
        return (int) (System.currentTimeMillis()%100000000);
    }


    /**
     * 获取随机数(仅数字)
     * @param len 几位
     * @return
     */
    public static String getRandom(int len){
        Random random = new Random();
        String result="";
        if(len > 0){
            for(int i=0;i<len;i++){
                result+=random.nextInt(9);
            }
        }
        return result;
    }


    /**
     * 获取N位验证码
     * @param digit 验证码为几位数
     * @throws Exception
     */
    public static String randomNum(int digit){
        if(digit<=0)return "-1";
        char[] result=new char[digit];
        Random r = new Random();
        for(int i=0;i<digit;i++){
            result[i]=(char)(r.nextInt(10)+48);
        }
        return new String(result);
    }
}
