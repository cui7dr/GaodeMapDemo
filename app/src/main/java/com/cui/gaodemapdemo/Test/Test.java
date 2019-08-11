package com.cui.gaodemapdemo.Test;

import com.alibaba.fastjson.JSONObject;
import com.cui.gaodemapdemo.base.Const;
import com.cui.gaodemapdemo.util.HttpUtil;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试类
 *
 * @author cui7dr by 2019/08/06
 */

public class Test {

    private static HttpUtil hu = new HttpUtil();

    /**
     * 登录测试方法
     *
     * @{"code":"0","info":"成功"}
     */
    //  1.---- 通过 HttpUtil 请求
    private static void get1LoginHttputil() {
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
        String reuslt = hu.methodPost(headerMap, paramsMap);
        String login_url = paramsMap.toString().replaceAll(", ", "");
        System.out.println(reuslt);
        System.out.println(login_url);
    }

    /**
     * main 方法
     *
     * @param args
     */
    public static void main(String[] args) {
        get1LoginHttputil();
    }
}
