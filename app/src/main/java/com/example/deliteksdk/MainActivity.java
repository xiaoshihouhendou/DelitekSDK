package com.example.deliteksdk;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.example.dlksdk.BQSDK;
import com.example.dlksdk.Content.Content;
import com.example.dlksdk.http.entity.DevicesEntity;
import com.example.dlksdk.http.entity.LightAreaEntity;
import com.example.dlksdk.http.entity.LightChannelEntity;
import com.example.dlksdk.http.entity.LightDevicesByAreaEntity;
import com.example.dlksdk.http.entity.LightPresetEntity;
import com.example.dlksdk.http.entity.RoomAirsEntity;
import com.example.dlksdk.http.entity.RoomAreaEntity;
import com.example.dlksdk.http.entity.RoomChannelEntity;
import com.example.dlksdk.http.entity.RoomDevicesByAreaEntity;
import com.example.dlksdk.until.ControlEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements BQSDK.DataBackListener, View.OnClickListener, BQSDK.AllDataListener {

    private TextView tvContent;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;

    private TextView tv8;
    private TextView tv9;
    private TextView tv10;
    private TextView tv11;
    private TextView tv12;
    private TextView tv13;
    private TextView tv14;

    private EditText et_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BQSDK.Init("192.168.175.188");
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);
        tv5 = findViewById(R.id.tv_5);
        tv6 = findViewById(R.id.tv_6);
        tv7 = findViewById(R.id.tv_7);

        tv8 = findViewById(R.id.tv_8);
        tv9 = findViewById(R.id.tv_9);
        tv10 = findViewById(R.id.tv_10);
        tv11 = findViewById(R.id.tv_11);
        tv12 = findViewById(R.id.tv_12);
        tv13 = findViewById(R.id.tv_13);
        tv14 = findViewById(R.id.tv_14);
        tvContent = findViewById(R.id.content);
        et_ip = findViewById(R.id.et_ip);


        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tv10.setOnClickListener(this);
        tv11.setOnClickListener(this);
        tv12.setOnClickListener(this);
        tv13.setOnClickListener(this);
        tv14.setOnClickListener(this);


        BQSDK.Init().setAllDataListener(this);
        BQSDK.Init().setDataBackListener(this);

        et_ip.setText("192.168.175.87");
        findViewById(R.id.btn_init).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_ip.getText().toString().trim())) {
                    Toast.makeText(MainActivity.this, "请输入ip", Toast.LENGTH_SHORT).show();
                    return;
                }
                BQSDK.Init(et_ip.getText().toString().trim());
                tvContent.setText("ip设置完成");
            }
        });
    }


    @Override
    public void result(Content.TYPE type, Object o) {
        switch (type) {
            case SEARCH_LIGHT_AREA:
                tvContent.setText("搜索成功》》》》》数据长度" + JSON.toJSONString((LightAreaEntity) o));
                break;
            case SEARCH_LIGHT_DEVICES_AREA:
                tvContent.setText("搜索成功》》》》》数据长度" + JSON.toJSONString((LightDevicesByAreaEntity) o));
                break;
            case SEARCH_LIGHT_CHANNEL:
                tvContent.setText("搜索成功》》》》》数据长度" + JSON.toJSONString((LightChannelEntity) o));
                break;
            case SEARCH_LIGHT_PRESET:
                tvContent.setText("搜索成功》》》》》数据长度" + JSON.toJSONString((LightPresetEntity) o));
                break;
            case SEARCH_ROOM_AIRS:
                tvContent.setText("搜索成功》》》》》数据长度" + JSON.toJSONString((RoomAirsEntity) o));
                break;
            case SEARCH_ROOM_AREA:
                tvContent.setText("搜索成功》》》》》数据长度" + JSON.toJSONString((RoomAreaEntity) o));
                break;
            case SEARCH_ROOM_AREA_DEVICES:
                tvContent.setText("搜索成功》》》》》数据长度" + JSON.toJSONString((RoomDevicesByAreaEntity) o));
                break;
            case SEARCH_ROOM_CHANNEL:
                tvContent.setText("搜索成功》》》》》数据长度" + JSON.toJSONString((RoomChannelEntity) o));
                break;
            case CONTROL_LIGHT_PRESET:
            case CONTROL_LIGHT_CHANNEL:
            case CONTROL_ROOM_AIRS:
            case CONTROL_ROOM_CHANNEL:
            case CONTROL_ROOM_CURTAIN:
            case CONTROL_ROOM_PRESET:
                tvContent.setText("控制成功》》》》》控制结果" + ((ControlEntity) o).getResult());
                break;
        }

    }



    @Override
    public void resultDevices(List<DevicesEntity> entity) {

        tvContent.setText(JSON.toJSONString(entity));
    }

    @Override
    public void error(Content.TYPE type, String season) {
        if (type == Content.TYPE.SEARCH_ALL_AREA_DEVICES) {
            tvContent.setText("获取全部数据失败>>" + season);
        } else {
            tvContent.setText("操作失败>>" + season);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_1:
                BQSDK.Init().Control(Content.TYPE.CONTROL_LIGHT_PRESET)
                        .addParams("areaId", 1)
                        .addParams("presetId", 101)
                        .Build();
                break;
            case R.id.tv_2:
                try {
                    JSONArray array = new JSONArray();
                    JSONObject object = new JSONObject();
                    JSONObject object1 = new JSONObject();
                    object.put("id", 1);
                    object.put("value", "100");
                    object1.put("id", 1);
                    object1.put("value", "100");
                    array.put(0, object);
                    array.put(1, object1);
                    BQSDK.Init().Control(Content.TYPE.CONTROL_LIGHT_CHANNEL)
                            .addParams("areaId", 1)
                            .addParams("datas", array)
                            .Build();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tv_3:
//                "room":"8888","areaId":1,"presetId":1
                BQSDK.Init().Control(Content.TYPE.CONTROL_ROOM_PRESET)
                        .addParams("room", "8888")
                        .addParams("areaId", 1)
                        .addParams("presetId", 1)
                        .Build();
                break;
            case R.id.tv_4:
//                "room":"8888""areaId":1,"datas":[{"id":1,"value":100},{"id":2,"value":100}]
                JSONArray array1 = new JSONArray();
                JSONObject object2 = new JSONObject();
                JSONObject object11 = new JSONObject();
                try {
                    object2.put("id", 1);
                    object2.put("value", "100");
                    object11.put("id", 1);
                    object11.put("value", "100");
                    array1.put(0, object2);
                    array1.put(1, object11);
                    BQSDK.Init().Control(Content.TYPE.CONTROL_ROOM_CHANNEL)
                            .addParams("room", "8888")
                            .addParams("areaId", 1)
                            .addParams("datas", array1)
                            .Build();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_5:
//                "room":"8888","areaId":1,"datas":[{"id":1,"fan":"high","mode":"cold","setT":24},{"id":2,"fan":"middle","mode":"fan","setT":26}]
                JSONArray array2 = new JSONArray();
                JSONObject object21 = new JSONObject();
                JSONObject object111 = new JSONObject();
                try {
                    object21.put("id", 1);
                    object21.put("fan", "high");
                    object21.put("mode", "cold");
                    object21.put("setT", "24");
                    object21.put("switch", "off");

                    object111.put("id", 2);
                    object111.put("fan", "high");
                    object111.put("mode", "cold");
                    object111.put("setT", "24");
                    object111.put("switch", "off");
                    array2.put(0, object21);
                    array2.put(1, object111);
                    BQSDK.Init().Control(Content.TYPE.CONTROL_ROOM_AIRS)
                            .addParams("room", "8888")
                            .addParams("areaId", 1)
                            .addParams("datas", array2)
                            .Build();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.tv_6:
//                "room":"8888","areaId":1,"datas":[{"id":1,"value":"open"},{"id":2,"value":"stop"}]
                JSONArray array = new JSONArray();
                JSONObject object = new JSONObject();
                JSONObject object1 = new JSONObject();
                try {
                    object.put("id", 1);
                    object.put("value", "100");
                    object1.put("id", 2);
                    object1.put("value", "100");
                    array.put(0, object);
                    array.put(1, object1);
                    BQSDK.Init().Control(Content.TYPE.CONTROL_ROOM_CURTAIN)
                            .addParams("room", "8888")
                            .addParams("areaId", 1)
                            .addParams("datas", array)
                            .Build();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_7:
                JSONArray array8 = new JSONArray();
                array8.put(1);
                array8.put(2);
                Map<String, Object> mapRoom = new HashMap<>();
                Map<String, Object> mapLight = new HashMap<>();
                mapRoom.put("areaIds", array8);
                mapRoom.put("room", "8888");
                mapLight.put("areaIds", array8);


                BQSDK.Init().SearchAllDevices(mapRoom, mapLight)
                        .setPassTime(4000)
                        .BuildAll();
                break;

            case R.id.tv_8:


                BQSDK.Init().Search(Content.TYPE.SEARCH_LIGHT_AREA)
                        .Build();
                break;
            case R.id.tv_9:
                //                "areaId":1,"presetIds":[101,102]
                JSONArray array3 = new JSONArray();
                array3.put(101);
                array3.put(102);
                BQSDK.Init().Search(Content.TYPE.SEARCH_LIGHT_PRESET)
                        .addParams("areaId", 1)
                        .addParams("presetIds", array3)
                        .Build();
                break;
            case R.id.tv_10:

                JSONArray array4 = new JSONArray();
                array4.put(1);
                array4.put(2);
                BQSDK.Init().Search(Content.TYPE.SEARCH_LIGHT_CHANNEL)
                        .addParams("areaId", 1)
                        .addParams("channelIds", array4)
                        .Build();
                break;
            case R.id.tv_11:
//                "room":"8888"
                BQSDK.Init().Search(Content.TYPE.SEARCH_ROOM_AREA)
                        .addParams("room", "8888")
                        .Build();
                break;
            case R.id.tv_12:
//                "room":"8888",,"areaId":1,"channelIds":[1,2],
                JSONArray array5 = new JSONArray();
                array5.put(1);
                array5.put(2);
                BQSDK.Init().Search(Content.TYPE.SEARCH_ROOM_CHANNEL)
                        .addParams("room", "8888")
                        .addParams("areaId", 1)
                        .addParams("channelIds", array5)
                        .Build();
                break;
            case R.id.tv_13:
//                "room":"8888","areaId":1,"airIds":[1,2]
                JSONArray array6 = new JSONArray();
                array6.put(1);
                array6.put(2);
                BQSDK.Init().Search(Content.TYPE.SEARCH_ROOM_AIRS)
                        .addParams("room", "8888")
                        .addParams("areaId", 1)
                        .addParams("airIds", array6)
                        .Build();
                break;
            case R.id.tv_14:
                try {
                    JSONArray array11 = new JSONArray();
                    JSONObject object112 = new JSONObject();
                    JSONObject object1112 = new JSONObject();
                    object112.put("id", 1);
                    object112.put("value", "10");
                    object1112.put("id", 2);
                    object1112.put("value", "200");
                    array11.put(0, object112);
                    array11.put(1, object1112);
                    BQSDK.Init().Control(Content.TYPE.CONTROL_LIGHT_CHANNEL)
                            .addParams("areaId", 1)
                            .addParams("datas", array11)
                            .Build();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}


