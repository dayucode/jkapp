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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.UserModule.Doctor;
import com.jk.dayu.jkapp.service.Service;

import java.util.List;

import static com.alibaba.fastjson.JSON.parseObject;

public class ShowReplyActivity extends BaseActivity {

    private ListView listView;
    private List<Reply> dataList;
    int role = 0;
    long uid;
    Handler mHandler;
    private Doctor doctor;
    String result;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_reply);

        Intent intent = getIntent();
        doctor = (Doctor) intent.getSerializableExtra("doctor");
        System.out.println("=====////====" + doctor + "=====///=====");
        initData();
        listView = (ListView) findViewById(R.id.lv_show_reply);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                MyAdpater myAdapter = new MyAdpater(ShowReplyActivity.this, R.layout.adapter_reply, (List<Reply>) msg.obj);
                listView.setAdapter(myAdapter);
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
                    result = Service.doctorSelectReply(doctor.getDid());
                    Log.i("========>result", "result:" + result);
                    List<Reply> replyList = ((List<Reply>) parseObject(result, new TypeReference<List<Reply>>() {
                    }));
                    Log.i("========>replyList", "replyList:" + replyList);
                    dataList = replyList;
                    Message message = new Message();
                    message.obj = dataList;
                    mHandler.sendMessage(message);

                } else {
                    uid = DataManager.currentUser(getApplicationContext()).getId();
                    System.out.println("======" + uid + "========================");
                    System.out.println("====pppp====" + role + "========");
                    //病人用户登录，查看个人病症信息
                    result = Service.userSelectReply(uid);
                    System.out.println(result);
                    Log.i("========>result", "result:" + result);
                    List<Reply> replyList = ((List<Reply>) parseObject(result, new TypeReference<List<Reply>>() {
                    }));
                    //Log.i("========>replyList", "replyList:" + replyList.get(0));
                    dataList = replyList;
                    Message message = new Message();
                    message.obj = dataList;
                    mHandler.sendMessage(message);
                }
            }
        }.start();
    }


    class MyAdpater extends ArrayAdapter<Reply> {

        private Context context;
        private List<Reply> dataList;
        private int resourceId;


        public MyAdpater(Context context, int resource, List<Reply> objects) {
            super(context, resource, objects);
            this.context = context;
            this.dataList = objects;
            this.resourceId = resource;
        }


        @SuppressLint("SetTextI18n")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            @SuppressLint("ViewHolder") View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

            TextView tvId = (TextView) view.findViewById(R.id.tv_id);
            TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
            TextView tvCid = (TextView) view.findViewById(R.id.tv_cid);

            tvId.setText(dataList.get(position).getDid() + "");
            tvCid.setText(dataList.get(position).getCid() + "");
            tvContent.setText(dataList.get(position).getContent());


            return view;
        }
    }


}
