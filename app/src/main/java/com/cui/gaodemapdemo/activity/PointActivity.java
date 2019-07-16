package com.cui.gaodemapdemo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.cui.gaodemapdemo.R;

public class PointActivity extends AppCompatActivity {

    private AMap aMap;
    private MapView mapView;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point);
        // 初始化并操作地图
        operationMap(savedInstanceState);
    }

    // 初始化地图的控件
    private void operationMap(@Nullable Bundle savedInstanceState){
        // 找到地图控件
        mapView=(MapView)findViewById(R.id.map);
        // 设置控件可见
        mapView.setVisibility(View.VISIBLE);
        // 创建地图（在 activity 执行 onCreate 时执行 mMapView.onCreate(savedInstanceState)
        mapView.onCreate(savedInstanceState);
        // 初始化地图控制器对象
        if(aMap==null){
            aMap=mapView.getMap();
        }
        // 设置高德经纬度（郑州火车站
        double gd_lng=113.658109;// 经度
        double gd_lat=34.745803;// 纬度
        LatLng gd_latlng=new LatLng(gd_lat,gd_lng);
        MarkerOptions options=new MarkerOptions();
        options.position(gd_latlng);
        aMap.moveCamera(CameraUpdateFactory.changeLatLng(gd_latlng));// 中心点
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));// 缩放比例
        aMap.getUiSettings().setZoomControlsEnabled(false);// 隐藏缩放按钮
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

}
