package com.jk.wyq.jkapp.HealthModule;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jk.wyq.jkapp.BaseModule.DataManager;
import com.jk.wyq.jkapp.BaseModule.HomeAdapter;
import com.jk.wyq.jkapp.HeartBeatModule.HeartBeatActivity;
import com.jk.wyq.jkapp.R;
import com.jk.wyq.jkapp.UserModule.DatePicker.DatePickerView;
import com.litesuits.orm.db.annotation.Mapping;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HealthResultActivity extends AppCompatActivity {

    private ListView listView;
    private TextView health_result;
    private List<Map<String, String>> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_result);
        listView = findViewById(R.id.health_list);
        health_result = findViewById(R.id.health_result);

    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList<Map<String, String>>();
        HealthBean health = DataManager.healthBean(this);
        addToList("用户名",health.name);
        addToList("年龄",health.age==null?"未录入":health.age);
        addToList("身高",health.height==null?"未录入":health.height);
        addToList("体重",health.weight==null?"未录入":health.weight);
        addToList("BMI",health.bmi==null?"未录入":health.bmi);
        addToList("血压",health.presslow==null?"未录入":health.presslow+"/"+health.presshigh);
        addToList("血糖",health.bloodsugar==null?"未录入":health.bloodsugar);
        addToList("心跳",health.beat==null?"未录入":health.beat+"次/分");
        String[] from = {"title","value"};
        Log.i("list", list.toString());
        int[] to = {R.id.tv_name,R.id.tv_result};
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.item_health_result,from,to);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                if(position<=4){
                    gotoBase();
                }else if(position==7){
                    gotoHeartBeat();
                }else {
                    gotoAdvance();
                }
            }
        });
        health_result.setText(DataManager.healthReport(this));
    }

    private void gotoBase(){
        Intent intent = new Intent(this, BaseHealthActivity.class);
        startActivity(intent);
    }


    private void gotoAdvance(){
        Intent intent = new Intent(this, AdvanceHealthActivity.class);
        startActivity(intent);
    }

    private void gotoHeartBeat(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        2);
            }
            return;
        }
        Intent intent = new Intent(this, HeartBeatActivity.class);
        startActivity(intent);
    }

    private void addToList(String title,String value){
        Map<String,String> map = new HashMap<String,String>();
        map.put("title",title);
        map.put("value",value);
        list.add(map);
    }

}
