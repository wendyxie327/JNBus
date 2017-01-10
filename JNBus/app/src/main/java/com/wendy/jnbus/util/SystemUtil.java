package com.wendy.jnbus.util;

import com.eagle.androidlib.utils.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Wendy on 2017/1/10.
 */
public class SystemUtil {

    private static final String TAG = "SystemUtil";

    public static List<String> parseLineOperTime(String str) {
        Logger.d(TAG,"len="+str.length());
        String regEx = "[\\u4e00-\\u9fa5]{1,15}\\s{0,5}(:|：)(((\\s*\\d.*?)(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(-|－)(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9]))+)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        List<String> list = new ArrayList<>();
        while (matcher.find()){
            Logger.d(TAG,"matcher.find="+matcher.group()+",start="+matcher.start()+",end="+matcher.end()+",count="+matcher.groupCount());
            list.add(matcher.group());
        }
        return list;
    }

    public static String parseLineOperTimeStr(String str){
        List<String> list = parseLineOperTime(str);
        StringBuffer buffer = new StringBuffer("");
        try {
            if (list!=null && list.size()>0){
                for (int i=0; i<list.size();i++){
                    buffer.append(list.get(i)).append("\n");
                }
                return buffer.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
}
