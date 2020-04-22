package com.jk.dayu.jkapp.UserModule;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.fastjson.TypeReference;
import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.HealthModule.HealthResultActivity;
import com.jk.dayu.jkapp.Interrogation.InterrogationActivity;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.StepModule.activity.StepActivity;
import com.jk.dayu.jkapp.service.Service;
import com.jk.dayu.jkapp.untils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

public class DoctorListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private List<Map<String, Object>> lists;
    private SimpleAdapter adapter;
    private ListView listView;
    private TextView did;

    private int imageViews = R.mipmap.ic_launcher;  //用到的图片是mipmap中的ic_launcher

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        @SuppressLint("HandlerLeak") final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                List<Doctor> doctorList = (List<Doctor>) msg.obj;
                lists = new ArrayList<>();
                for (int i = 0; i <doctorList.size();) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("image", imageViews);
                    map.put("did",doctorList.get(i).getDid());
                    map.put("theme", doctorList.get(i).getUsername());
                    map.put("content", doctorList.get(i).getIntroduce());
                    lists.add(map);
                    i++;
                }
                //适配器指定应用自己定义的xml格式
                adapter = new SimpleAdapter(DoctorListActivity.this, lists, R.layout.item_doctor_list, new String[]{"image", "did", "theme", "content"}, new int[]{R.id.image1, R.id.did, R.id.name, R.id.introduce});
                listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(DoctorListActivity.this);

            }
        };
        new Thread() {
            public void run() {
                String result = Service.listDoctor();
                Log.i("========>result", "result:" + result);
                List<Doctor > doctorList = ((List<Doctor>) parseObject(result, new TypeReference<List<Doctor>>(){}));
                Log.i("========>doctorList", "doctorList:" + doctorList.get(1));
                Log.i("========>doctorList", "doctorList:" + doctorList.size());
                Message message = new Message();
                message.obj = doctorList;
                mHandler.sendMessage(message);
            }
        }.start();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        did = view.findViewById(R.id.did);
        String sDid = (String) did.getText();
        long lDid = Long.parseLong(sDid);
        System.out.println("============="+lDid);
        if(!sDid.isEmpty()){
            Intent intent = new Intent(DoctorListActivity.this, InterrogationActivity.class);
            intent.putExtra("did",lDid+"");
            startActivity(intent);
            finish();
        }


    }
}

