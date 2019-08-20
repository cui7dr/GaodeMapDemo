package com.cui.gaodemapdemo.test;

import com.alibaba.fastjson.JSONObject;
import com.cui.gaodemapdemo.base.Const;
import com.cui.gaodemapdemo.json2bean.LatlngBean;
import com.cui.gaodemapdemo.json2bean.UsersBean;
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
        System.out.println("HttpUtil 登录1请求返回 json 信息 === " + result);
        System.out.println("HttpUtil 登录1请求链接 === " + login_url);
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
        System.out.println("HttpUtil 登录2请求返回 json 信息 === " + result);
        System.out.println("HttpUtil 登录2请求链接 === " + login_url);
        Gson gson = new Gson();
        UsersBean usersBean = gson.fromJson(result, UsersBean.class);
        if (usersBean != null) {
            String code = usersBean.getCode();
            if ("0".equals(code)) {
                System.out.println("HttpUtil + Gson 登录2成功时返回 info 信息 === " + usersBean.getInfo());
            } else {
                System.out.println("HttpUtil + Gson 僧录2失败时返回 info 信息 ===" + usersBean.getInfo());
            }
        } else {
            System.out.println("HttpUtil + Gson 登录2连接超时！");
        }
    }

    //  3.---- 通过 OkHttp 请求
    private static void getLogin3ByOkHttp() {
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
        final String request = paramsMap.toString().replaceAll(", ", "");
        String login_url = request.substring(12, request.length() - 1);
        System.out.println("OkHttp 登录3请求链接 === " + login_url);
        OkHttpUtils.get()
                .url(login_url)
                .build()
                .connTimeOut(30000)
                .readTimeOut(30000)
                .writeTimeOut(30000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        System.out.println("登录3连接超时！");
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Gson gson = new Gson();
                        UsersBean usersBean = gson.fromJson(s, UsersBean.class);
                        if (usersBean != null) {
                            String result = usersBean.getCode();
                            if ("0".equals(result)) {
                                System.out.println("OkHttp 登录3成功时返回 info 信息 === " + usersBean.getInfo());
                            } else {
                                System.out.println("OkHttp 登录3失败时返回 info 信息 ===" + usersBean.getInfo());
                            }
                        } else {
                            System.out.println("登录3连接超时！");
                        }
                    }
                });

    }

    /**
     * 测试获取点位经纬度方法
     *
     * @{"code":"0","data":{"data_info":[{"ddwd":33.987567,"ddjd":113.862191,"jzmc":"南外环梨园转盘","jzbh":"A411023001","id":1,"status":1},{"ddwd":33.974457,"ddjd":113.663660,"jzmc":"许南路椹涧超限站","jzbh":"A411023002","id":4,"status":1},{"ddwd":34.060290,"ddjd":113.710969,"jzmc":"许禹路禹毫铁路桥","jzbh":"A411023003","id":9,"status":1},{"ddwd":33.980772,"ddjd":113.823672,"jzmc":"南外环许繁路","jzbh":"B411023004","id":10,"status":1},{"ddwd":0.000000,"ddjd":0.000000,"jzmc":"市一号遥感车","jzbh":"C411023005","id":11,"status":1},{"ddwd":0.000000,"ddjd":0.000000,"jzmc":"市二号遥感车","jzbh":"C411023006","id":12,"status":1},{"ddwd":34.060035,"ddjd":114.205041,"jzmc":"鄢陵县","jzbh":"A411024001","id":13,"status":1},{"ddwd":34.127870,"ddjd":113.542519,"jzmc":"禹州市","jzbh":"B411081001","id":14,"status":1}],"page_data":{"currentPage":1,"currentRecord":0,"nextPage":1,"pageSize":20,"prePage":1,"totalPage":1,"totalRecord":8}},"info":"成功"}
     */
    //  1.---- 通过 HttpUtil 获取经纬度
    private static void getLatlng1HttpUtil() {
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
        String result = hu.methodPost(headerMap, paramsMap);
        String latlng_url = paramsMap.toString().replaceAll(", ", "");
        System.out.println("HttpUtil 请求经纬度1返回 json 信息 === " + result);
        System.out.println("HttpUtil 请求经纬度1链接 === " + latlng_url);
    }

    //  2.---- 通过 HttpUtil + Gson 获取经纬度
    private static void getLatlng2ByHttpUtilAddGson() {
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
        String result = hu.methodPost(headerMap, paramsMap);
        String latlng_url = paramsMap.toString().replaceAll(", ", "");
        System.out.println("HttpUtil 请求经纬度2返回 json 信息 === " + result);
        System.out.println("HttpUtil 请求经纬度2链接 === " + latlng_url);
        Gson gson = new Gson();
        LatlngBean latlngBean = gson.fromJson(result, LatlngBean.class);
        if (latlngBean != null) {
            String code = latlngBean.getCode();
            if ("0".equals(code)) {
                System.out.println("HttpUtil + Gson 获取经纬度2成功时返回 info 信息 === " + latlngBean.getInfo());
                System.out.println(latlngBean.getLatlngData());
                LatlngBean.LatlngData llb_lld = latlngBean.getLatlngData();
                if (llb_lld.getDataInfo().size() > 0) {
                    for (int i = 0; i < llb_lld.getDataInfo().size(); i++) {
                        double wd = llb_lld.getDataInfo().get(i).getDdwd();
                        double jd = llb_lld.getDataInfo().get(i).getDdjd();
                        String mc = llb_lld.getDataInfo().get(i).getJzmc();
                        System.out.println("站点名称：" + mc + "----经度：" + jd + "----纬度：" + wd);
                    }
                }
            } else {
                System.out.println("HttpUtil + Gson 获取经纬度2失败时返回 info 信息 ===" + latlngBean.getInfo());
            }
        } else {
            System.out.println("HttpUtil + Gson 获取经纬度2连接超时！");
        }
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
        System.out.println("2.-------通过 HttpUtil + Gson 请求操作 ++ 登录-------");
        getLogin2ByHttpUtilAddGson();
        System.out.println();
        System.out.println("3.-------通过 OkHttp 请求操作 ++ 登录-------");
        getLogin3ByOkHttp();
        System.out.println();
        System.out.println("1.-------通过 HttpUtil 获取 ++ 点位经纬度-------");
        getLatlng1HttpUtil();
        System.out.println();
        System.out.println("2.-------通过 HttpUtil + Gson 获取 ++ 点位经纬度-------");
        getLatlng2ByHttpUtilAddGson();
    }
}
