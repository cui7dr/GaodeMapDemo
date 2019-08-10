package com.cui.gaodemapdemo.Test;

import com.alibaba.fastjson.JSONObject;
import com.cui.gaodemapdemo.base.Const;
import com.cui.gaodemapdemo.util.HttpUtil;
import com.cui.gaodemapdemo.util.HttpUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 *
 * @author cui7dr by 2019/08/06
 */

public class Test {

    private static HttpUtils hus = new HttpUtils();

    //登录方法测试
    private static void getLogin() {
        JSONObject usersJson = new JSONObject();
        JSONObject urlJson = new JSONObject();
        usersJson.put("loginname", "yunwei");
        usersJson.put("password", "xinghe123");
        urlJson.put("version", Const.version);
        urlJson.put("method", Const.method_login_xc);
        urlJson.put("pubKey", "");
        urlJson.put("sign", "");
        urlJson.put("data", usersJson);
        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("requestURL", Const.url_xc);
        try {
            paramsMap.put("json", URLEncoder.encode(urlJson.toJSONString(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String reuslt = hus.methodPost(headerMap, paramsMap);
        String login_url=paramsMap.toString().replaceAll(", ","");
        System.out.println(reuslt);
        System.out.println(login_url);
    }

    public static void main(String[] args) {
        getLogin();
    }
}
