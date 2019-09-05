package com.cui.gaodemapdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.cui.gaodemapdemo.R;

/**
 * 站点实时数据查看方法
 *
 * @author cui7dr by 2019/08/27
 */

public class RealtimeActivity extends AppCompatActivity {

    private TextView tv_null;
    private ListView lv_data;
    private String contains;
    private String siteCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime);
    }
}
