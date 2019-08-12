package com.cui.gaodemapdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.cui.gaodemapdemo.Json2Bean.UsersBean;
import com.cui.gaodemapdemo.R;
import com.cui.gaodemapdemo.base.Const;
import com.cui.gaodemapdemo.premission.Premission;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 登录方法
 *
 * @author cui7dr by 2019/08/03
 */

public class LoginActivity extends Premission {

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private String username;
    private String password;
    private static final String code = "0";

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public void initView() {
        et_username = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_pwd);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = et_username.getText().toString();
                password = et_password.getText().toString();
                doPost();
            }
        });
    }

    private void doPost() {
        JSONObject urlJson = new JSONObject();
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("loginname", username);
        paramsJson.put("password", password);
        urlJson.put("version", Const.version);
        urlJson.put("method", Const.method_login_xc);
        urlJson.put("pubKey", "");
        urlJson.put("sign", "");
        urlJson.put("data", paramsJson);
        Map<String, String> headerMap = new HashMap<String, String>();
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("requestURL", Const.url_xc);
        try {
            paramsMap.put("json", URLEncoder.encode(urlJson.toJSONString(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String request = paramsMap.toString().replaceAll(", ", "");
        String url = request.substring(12, request.length() - 1);
        OkHttpUtils.get()
                .url(url)
                .build()
                .connTimeOut(30000)
                .readTimeOut(30000)
                .writeTimeOut(30000)
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(LoginActivity.this, Const.OUT_TIME, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Gson gson = new Gson();
                        UsersBean usersBean = gson.fromJson(s, UsersBean.class);
                        if (usersBean != null) {
                            String result = usersBean.getCode();
                            if (code.equals(result)) {
                                Intent intent = new Intent(LoginActivity.this, PointActivity.class);
                                intent.putExtra("name", username);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, usersBean.getInfo(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, Const.OUT_TIME, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
