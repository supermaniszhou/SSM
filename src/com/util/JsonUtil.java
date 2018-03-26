package com.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/18 0018.
 */
public class JsonUtil {

    public static void main(String[] args) {
        Map<String,String> map=new HashMap<String,String>();
        map.put("01","tom");
        map.put("03","lili");
        String s=maptoJson(map);
        System.out.println(s);
    }

    public static String maptoJson(Map<String,String > map){
        String tmp="[";
        if(map.size()>0){
            String content="";
            for (Map.Entry<String,String> entry:map.entrySet()){
                String key =entry.getKey();
                String value=entry.getValue();
                content+="{\"key\":\""+key+"\","+"\"value\":\""+value+"\"},";
            }
            tmp=tmp+content.substring(0,content.length()-1)+"]";
        }else{
            tmp="[{}]";
        }
        return tmp;
    }

}
