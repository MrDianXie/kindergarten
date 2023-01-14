package com.dq408.kindergarten.utils;

import java.util.HashMap;
import java.util.Map;


public class AjaxResult {

    public static Map<String,Object> success(){
        Map<String,Object> result = new HashMap<>();
        result.put("state",StateCode.SUCCEED);
        result.put("errmsg","成功");
        return result;
    }
    public static Map<String,Object> success(Object data){
        Map<String,Object> result = new HashMap<>();
        result.put("errno",StateCode.SUCCEED);
        result.put("errmsg","成功");
        result.put("data",data);
        return result;
    }


    public static Map<String,Object> fail(){
        Map<String,Object> result = new HashMap<>();
        result.put("errno",StateCode.FAIL);
        result.put("errmsg","失败");
        return result;
    }
    public static Map<String,Object> fail(int errno, String errmsg){
        Map<String,Object> result = new HashMap<>();
        result.put("errno",errno);
        result.put("errmsg",errmsg);
        return result;
    }


}
