package com.cui.gaodemapdemo.test;

import com.alibaba.fastjson.JSONObject;
import com.cui.gaodemapdemo.json2bean.UsersBean;
import com.cui.gaodemapdemo.base.Const;
import com.cui.gaodemapdemo.util.HttpUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

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
    private static void getLogin1ByHttputil() {
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
        String result = hu.methodPost(headerMap, paramsMap);
        String login_url = paramsMap.toString().replaceAll(", ", "");
        System.out.println("HttpUtil 请求返回 json 信息 === " + result);
        System.out.println("HttpUtil 请求链接 === " + login_url);
    }

    //  2.---- 通过 HttpUtil + Gson 获取返回信息
    private static void getLogin2ByHttpUtilAddGson() {
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
        String result = hu.methodPost(headerMap, paramsMap);
        String login_url = paramsMap.toString().replaceAll(", ", "");
        System.out.println("HttpUtil 请求返回 json 信息 === " + result);
        System.out.println("HttpUtil 请求链接 === " + login_url);
        Gson gson = new Gson();
        UsersBean usersBean = gson.fromJson(result, UsersBean.class);
        if (usersBean != null) {
            String code = usersBean.getCode();
            if ("0".equals(code)) {
                System.out.println("HttpUtil + Gson 成功时返回 info 信息 === " + usersBean.getInfo());
            } else {
                System.out.println("HttpUtil + Gson 失败时返回 info 信息 ===" + usersBean.getInfo());
            }
        } else {
            System.out.println("HttpUtil + Gson 连接超时！");
        }
    }

    //  3.---- 通过 OkHttp 请求
    private static void getLogin2ByOkHttp() {
        JSONObject usersJson = new JSONObject();
        JSONObject urlJson = new JSONObject();
        usersJson.put("loginname", "xuchang");
        usersJson.put("password", "1");
        urlJson.put("version", Const.version);
        urlJson.put("method", Const.method_login_xc);
        urlJson.put("pubKey", "");
        urlJson.put("sign", "");
        urlJson.put("data", usersJson);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("requestURL", Const.url_xc);
        try {
            paramsMap.put("json", URLEncoder.encode(urlJson.toJSONString(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String request = paramsMap.toString().replaceAll(", ", "");
        String login_url = request.substring(12, request.length() - 1);
        System.out.println("OkHttp 请求链接 === " + login_url);
        OkHttpUtils.get()
                .url(login_url)
                .build()
                .connTimeOut(30000)
                .readTimeOut(30000)
                .writeTimeOut(30000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        System.out.println("连接超时！");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Gson gson = new Gson();
                        UsersBean usersBean = gson.fromJson(s, UsersBean.class);
                        if (usersBean != null) {
                            String result = usersBean.getCode();
                            if ("0".equals(result)) {
                                System.out.println("OkHttp 成功时返回 info 信息 === " + usersBean.getInfo());
                            } else {
                                System.out.println("OkHttp 失败时返回 info 信息 ===" + usersBean.getInfo());
                            }
                        } else {
                            System.out.println("连接超时！");
                        }
                    }
                });

    }

    /**
     * 测试获取点位经纬度方法
     */
    //  1.---- 通过 HttpUtil 获取
    private static void getLatLng1HttpUtil(){
        JSONObject PointLatLngJson = new JSONObject();
        JSONObject urlJson = new JSONObject();
        urlJson.put("version", Const.version);
        urlJson.put("method", Const.method_pointLatLng_xc);
        urlJson.put("pubKey", "");
        urlJson.put("sign", "");
        urlJson.put("data", PointLatLngJson);
        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("requestURL", Const.url_xc);
        try {
            paramsMap.put("json", URLEncoder.encode(urlJson.toJSONString(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result=hu.methodPost(headerMap, paramsMap);
        String login_url = paramsMap.toString().replaceAll(", ", "");
        System.out.println("HttpUtil 请求返回 json 信息 === " + result);
        System.out.println("HttpUtil 请求链接 === " + login_url);
    }

    /**
     * main 方法
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("1.-------通过 HttpUtil 请求操作 ++ 登录-------");
        getLogin1ByHttputil();
        System.out.println();
        System.out.println("2.-------通过 OkHttp 请求操作 ++ 登录-------");
        getLogin2ByOkHttp();
        System.out.println();
        System.out.println("1.-------通过 HttpUtil 获取 ++ 点位经纬度-------");
        getLatLng1HttpUtil();
    }
}
