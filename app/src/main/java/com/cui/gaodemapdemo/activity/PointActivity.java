package com.cui.gaodemapdemo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import com.cui.gaodemapdemo.util.HttpUtil;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

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
    private HttpUtil hu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        // 初始化并操作地图
        operationMap(savedInstanceState);
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
        initData();

    }

    //获取所有数据----(HttpUtil + Gson
    private void initData() {
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
