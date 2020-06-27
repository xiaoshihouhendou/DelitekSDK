package com.example.deliteksdk;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dlksdk.BQSDK;
import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.entity.AllDevicesEntity;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements BQSDK.SearchListener, View.OnClickListener, BQSDK.ControlListener {

    private TextView tvContent;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BQSDK.getInit("192.168.1.100");
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
        tv5 = findViewById(R.id.tv_5);
        tv6 = findViewById(R.id.tv_6);
        tv7 = findViewById(R.id.tv_7);
        tvContent = findViewById(R.id.content);


        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);

        BQSDK.getInit().SearchLight(Content.TYPE.SEARCH_LIGHT_AREA)
                .Build();

        BQSDK.getInit().setSearchListener(this);
        BQSDK.getInit().setControlListener(this);
    }


    @Override
    public void result(Content.TYPE type, Object o) {
        tvContent.setText("控制成功");
    }

    @Override
    public void result(Content.TYPE type, AllDevicesEntity entity) {
        tvContent.setText("灯控设备数量:》》"+entity.getLight().getDatas().size()+"\n客控设备数量:>>>>"+entity.getRoom().getDatas().size());
    }

    @Override
    public void error(Content.TYPE type, String season) {
        tvContent.setText("控制失败");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
                BQSDK.getInit().SearchLight(Content.TYPE.CONTROL_LIGHT_PRESET)
                        .Build();

                break;
            case R.id.tv_2:
                BQSDK.getInit().SearchLight(Content.TYPE.CONTROL_LIGHT_CHANNEL)
                        .Build();

                break;
            case R.id.tv_3:
                BQSDK.getInit().SearchLight(Content.TYPE.CONTROL_ROOM_PRESET)
                        .Build();

                break;
            case R.id.tv_4:
                BQSDK.getInit().SearchLight(Content.TYPE.CONTROL_ROOM_CHANNEL)
                        .Build();

                break;
            case R.id.tv_5:
                BQSDK.getInit().SearchLight(Content.TYPE.CONTROL_ROOM_AIRS)
                        .Build();

                break;
            case R.id.tv_6:
                BQSDK.getInit().SearchLight(Content.TYPE.CONTROL_ROOM_CURTAIN)
                        .Build();

                break;
            case R.id.tv_7:
                BQSDK.getInit().SearchLight(Content.TYPE.SEARCH_ALL_AREA_DEVICES)
                        .setPassTime(4000)
                        .BuildAll();
                break;


        }
    }
}


