package com.cui.gaodemapdemo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.CoordinateConverter;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.cui.gaodemapdemo.R;
import com.cui.gaodemapdemo.base.Const;
import com.cui.gaodemapdemo.json2bean.LatlngBean;
import com.cui.gaodemapdemo.json2bean.PtDataBean;
import com.cui.gaodemapdemo.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * 点位信息方法
 *
 * @author cui7dr by 2019/08/21
 */

public class PointActivity extends AppCompatActivity {

    private TextView tv_hphm;
    private TextView tv_time;
    private TextView tv_no;
    private TextView tv_opaque;
    private TextView tv_result;
    private LinearLayout title;
    private LinearLayout context;
    private AMap aMap;
    private MapView mapView;
    private Marker marker;
    private Handler handler;
    private HttpUtil hu;
    private String id = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        // 初始化并操作地图
        operationMap(savedInstanceState);
        initView();
    }

    // 初始化地图的控件
    private void operationMap(@Nullable Bundle savedInstanceState) {
        // 找到地图控件
        mapView = (MapView) findViewById(R.id.map);
        // 设置控件可见
        mapView.setVisibility(View.VISIBLE);
        // 创建地图（在 activity 执行 onCreate 时执行 mMapView.onCreate(savedInstanceState)
        mapView.onCreate(savedInstanceState);
        // 初始化地图控制器对象
        if (aMap == null) {
            aMap = mapView.getMap();
        }
        // 高德经纬度（郑州火车站
        double gd_lng = 113.658109;// 经度
        double gd_lat = 34.745803;// 纬度
        // 百度经纬度
        double bd_lng = 113.666136;
        double bd_lat = 34.752394;
        // 许昌各站点近似中心点
        double xc_lng = 113.763535;
        double xc_lat = 33.967344;
        LatLng gd_latlng = new LatLng(gd_lat, gd_lng);
        LatLng bd_latlng = new LatLng(bd_lat, bd_lng);
        LatLng xc_latlng = new LatLng(xc_lat, xc_lng);
        CoordinateConverter converter = new CoordinateConverter();
        // CoordType 待转换坐标类型（GPS/Google/Baidu...
        converter.from(CoordinateConverter.CoordType.BAIDU);
        // 待转换坐标点
        converter.coord(bd_latlng);
        // 执行转换操作
        MarkerOptions options = new MarkerOptions();
        options.position(gd_latlng);
        // 显示转换后坐标
        marker = aMap.addMarker(options);
        marker.showInfoWindow();
        // 中心点
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(xc_latlng));
        // 缩放比例
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        // 隐藏缩放按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);
    }

    private void initView() {
//        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
//            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color));
//        }
        getLatlng();
        getId();
    }

    //定时刷新任务
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getRefreshData();
            handler.postDelayed(this, 10000);
        }
    };

    //获取所有站点经纬度数据----(HttpUtil + Gson
    private void getLatlng() {
        JSONObject urlJson = new JSONObject();
        JSONObject paramsJson = new JSONObject();
        urlJson.put("version", Const.version);
        urlJson.put("method", Const.method_pointLatLng_xc);
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
        String result = hu.methodHttpsPost(headerMap, paramsMap);
        Gson gson = new Gson();
        LatlngBean latlngBean = gson.fromJson(result, LatlngBean.class);
        if (latlngBean != null) {
            String code = latlngBean.getCode();
            if ("0".equals(code)) {
                LatlngBean.DataBean llb_lld = latlngBean.getData();
                if (llb_lld.getData_info().size() > 0) {
                    for (int i = 0; i < llb_lld.getData_info().size(); i++) {
                        LatLng latLng = new LatLng(llb_lld.getData_info().get(i).getDdwd(), llb_lld.getData_info().get(i).getDdjd());
                        MarkerOptions options = new MarkerOptions();
                        if ("(0,0)" != latLng.toString()) {
                            options.position(latLng);
                            options.title(llb_lld.getData_info().get(i).getJzmc());
                        }
                        marker = aMap.addMarker(options);
                        marker.showInfoWindow();
                    }
                }
            } else {
                Toast.makeText(PointActivity.this, latlngBean.getInfo() + "", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // 获取站点 id (HttpUtil + OkHttp
    private void getId() {
        title = (LinearLayout) findViewById(R.id.linearTitle);
        context = (LinearLayout) findViewById(R.id.linearTitle);
        tv_hphm = (TextView) findViewById(R.id.tv_hphm);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_no = (TextView) findViewById(R.id.tv_no);
        tv_opaque = (TextView) findViewById(R.id.tv_opaque);
        tv_result = (TextView) findViewById(R.id.tv_result);
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker1) {
                String pointTitle = marker1.getTitle();
                if ("南外环梨园转盘".equals(pointTitle)) {
                    id = "1";
                } else if ("许南路椹涧超限站".equals(pointTitle)) {
                    id = "4";
                } else if ("许禹路禹毫铁路桥".equals(pointTitle)) {
                    id = "9";
                } else if ("南外环许繁路".equals(pointTitle)) {
                    id = "10";
                } else {
                    id = "1";
                }
                JSONObject urlJson = new JSONObject();
                JSONObject paramsJson = new JSONObject();
                urlJson.put("version", Const.version);
                urlJson.put("method", Const.method_pointData_xc);
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
                String request = paramsJson.toString().replaceAll(", ", "");
                String url = request.substring(12, request.length() - 1);
                OkHttpUtils.get().url(url).build().connTimeOut(30000).readTimeOut(30000).writeTimeOut(30000).execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(PointActivity.this, Const.OUT_TIME, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String s, int i) {
                        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                        PtDataBean ptData = gson.fromJson(s, PtDataBean.class);
                        if (ptData != null) {
                            PtDataBean.PointData.DataInfo info = ptData.getPointData().getData_info();
                            tv_hphm.setText(info.getHphm());
                            tv_time.setText(info.getCreateTime() + "");
                            tv_no.setText(info.getNojg());
                            tv_opaque.setText(info.getYdz() + "");
                            tv_result.setText(info.getPdjgStr());
                            if ("黄色".equals(info.getHpysStr())) {
                                tv_hphm.setBackgroundColor(0xffeeee00);
                            } else if ("黑色".equals(info.getHpysStr())) {
                                tv_hphm.setTextColor(0xfffffafa);
                                tv_hphm.setBackgroundColor(0xff000000);
                            } else if ("白色".equals(info.getHpysStr())) {
                                tv_hphm.setBackgroundColor(0xfffffafa);
                            } else {
                                tv_hphm.setTextColor(0xfffffafa);
                                tv_hphm.setBackgroundColor(0xff1874cd);
                            }
                            title.setBackgroundColor("通过".equals(info.getPdjgStr()) ? 0xff00ee00 : 0xffff0000);
                            context.setBackgroundColor("通过".equals(info.getPdjgStr()) ? 0xff00ee00 : 0xffff0000);
                        } else {
                            Toast.makeText(PointActivity.this, "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                return false;
            }
        });
        handler.postDelayed(runnable, 1000);
    }

    // 根据点位 id 定时刷新 (HttpUtil + Gson
    private void getRefreshData() {
        title = (LinearLayout) findViewById(R.id.linearTitle);
        context = (LinearLayout) findViewById(R.id.linearTitle);
        tv_hphm = (TextView) findViewById(R.id.tv_hphm);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_no = (TextView) findViewById(R.id.tv_no);
        tv_opaque = (TextView) findViewById(R.id.tv_opaque);
        tv_result = (TextView) findViewById(R.id.tv_result);
        JSONObject urlJson = new JSONObject();
        JSONObject paramsJson = new JSONObject();
        paramsJson.put("ptInfoId", id);
        urlJson.put("version", Const.version);
        urlJson.put("method", Const.method_pointData_xc);
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
        String result = hu.methodHttpsPost(headerMap, paramsMap);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        PtDataBean ptData = gson.fromJson(result, PtDataBean.class);
        if (ptData != null) {
            PtDataBean.PointData.DataInfo info = ptData.getPointData().getData_info();
            tv_hphm.setText(info.getHphm());
            tv_time.setText(info.getCreateTime() + "");
            tv_no.setText(info.getNojg());
            tv_opaque.setText(info.getYdz() + "");
            tv_result.setText(info.getPdjgStr());
            if ("黄色".equals(info.getHpysStr())) {
                tv_hphm.setBackgroundColor(0xffeeee00);
            } else if ("黑色".equals(info.getHpysStr())) {
                tv_hphm.setTextColor(0xfffffafa);
                tv_hphm.setBackgroundColor(0xff000000);
            } else if ("白色".equals(info.getHpysStr())) {
                tv_hphm.setBackgroundColor(0xfffffafa);
            } else {
                tv_hphm.setTextColor(0xfffffafa);
                tv_hphm.setBackgroundColor(0xff1874cd);
            }
            title.setBackgroundColor("通过".equals(info.getPdjgStr()) ? 0xff00ee00 : 0xffff0000);
            context.setBackgroundColor("通过".equals(info.getPdjgStr()) ? 0xff00ee00 : 0xffff0000);
        } else {
            Toast.makeText(PointActivity.this, "", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        // 重新绘制加载地图（在 activity 执行 onResume 时执行 mapView.onResume()
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        // 暂停地图的绘制（在 activity 执行 onPause 时执行 mapView.onPause()
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 销毁地图（在 activity 执行 onDestroy 时执行 mapView.onDestroy()
        mapView.onDestroy();
    }

}
