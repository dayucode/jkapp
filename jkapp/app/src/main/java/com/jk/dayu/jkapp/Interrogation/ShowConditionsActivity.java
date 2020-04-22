package com.jk.dayu.jkapp.Interrogation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.UserModule.Doctor;
import com.jk.dayu.jkapp.UserModule.DoctorListActivity;
import com.jk.dayu.jkapp.service.Service;
import com.jk.dayu.jkapp.untils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;

public class ShowConditionsActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private List<Condition> dataList;
    int role = 0;
    long uid;
    Handler mHandler;
    private Doctor doctor;
    String result;

    @SuppressLint({"LongLogTag", "HandlerLeak"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        doctor = (Doctor) intent.getSerializableExtra("doctor");
        System.out.println("=====////====" + doctor + "=====///=====");
        setTitle("症状信息");
        setContentView(R.layout.activity_show_conditions);

        //初始化对象
        listView = (ListView) findViewById(R.id.show_lv_conditisons);

        //根据角色获得数据
        initData();

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                List<Condition> conditionList = (List<Condition>) msg.obj;
                MyAdpater adpater = new MyAdpater(ShowConditionsActivity.this, R.layout.adapter_condition, dataList);
                listView.setAdapter(adpater);
                //System.out.println("======ooo======="+doctor.getRole());
                if (doctor!=null&&doctor.getRole()==1) {
                    listView.setOnItemClickListener(ShowConditionsActivity.this);
                }
            }
        };

    }

    private void initData() {
        new Thread() {
            public void run() {
                if (doctor != null && doctor.getRole() == 1) {
                    Log.i("========>role", "role:" + doctor.getRole());
                    Log.i("========>did", "did:" + doctor.getDid());
                    //管理员用户登录，查看所有病症信息
                    result = Service.doctor2Condition(doctor.getDid());
                    Log.i("========>result", "result:" + result);
                    List<Condition> conditionList = ((List<Condition>) parseObject(result, new TypeReference<List<Condition>>() {
                    }));
                    Log.i("========>conditionList", "conditionList:" + conditionList.get(1));
                    dataList = conditionList;
                    Message message = new Message();
                    message.obj = dataList;
                    mHandler.sendMessage(message);
                } else {
                    uid = DataManager.currentUser(getApplicationContext()).getId();
                    System.out.println("======" + uid + "========================");
                    System.out.println("====pppp====" + role + "========");
                    //病人用户登录，查看个人病症信息
                    result = Service.user2Condition(uid);
                    System.out.println(result);
                    Log.i("========>result", "result:" + result);
                    List<Condition> conditionList = ((List<Condition>) parseObject(result, new TypeReference<List<Condition>>() {
                    }));
                    //Log.i("========>conditionList", "conditionList:" + conditionList.get(0));
                    dataList = conditionList;
                    Message message = new Message();
                    message.obj = dataList;
                    mHandler.sendMessage(message);
                }
            }
        }.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("========>", "position:" + position);
        //管理员点击每一病人病情信息后，跳转到回复页面
        Intent intent = new Intent(ShowConditionsActivity.this, AddReplyActivity.class);
        Log.i("========>dataList", "dataList:" + dataList.get(position));
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataPosition", dataList.get(position));
        bundle.putSerializable("doctor",doctor);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    static class MyAdpater extends ArrayAdapter<Condition> {

        private List<Condition> dataList;
        private int resourceId;

        public MyAdpater(Context context, int resource, List<Condition> objects) {
            super(context, resource, objects);
            this.resourceId = resource;
            this.dataList = objects;
        }

        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

            TextView uid = view.findViewById(R.id.cid);
            TextView time = view.findViewById(R.id.time);
            TextView symptoms = view.findViewById(R.id.symptoms);
            TextView detail = view.findViewById(R.id.detail);

            uid.setText(dataList.get(position).getUid() + "");
            time.setText(dataList.get(position).getTime());
            symptoms.setText(dataList.get(position).getSymptoms());
            detail.setText(dataList.get(position).getDetails());
            return view;
        }
    }
}




