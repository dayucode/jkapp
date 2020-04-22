package com.jk.dayu.jkapp.Interrogation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.jk.dayu.jkapp.UserModule.LoginActivity;
import com.jk.dayu.jkapp.UserModule.UserBean;
import com.jk.dayu.jkapp.service.Service;
import com.jk.dayu.jkapp.untils.IDUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alibaba.fastjson.JSON.parseObject;


public class AddReplyActivity extends BaseActivity {

    private TextView username;
    private TextView sex;
    private TextView age;
    private TextView symptom;
    private TextView time;
    private TextView detial;
    private EditText reply;
    private TextView cid;
    private TextView did;
    private TextView uid;
    private Button doBtn;

    private Doctor doctor;

    private UserBean userBean;
    private Condition condition;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reply);

        Intent intent = getIntent();
        doctor = (Doctor) intent.getSerializableExtra("doctor");
        condition = (Condition) intent.getSerializableExtra("dataPosition");
        Log.i("========>dataPosition", "dataPosition:" + condition);

        initView();
        initDate();
        //showData();

        doBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Reply r = new Reply();
                r.setRid(IDUtils.getNewId());
                r.setCid(condition.getCid());
                r.setDid(condition.getDid());
                r.setUid(condition.getUid());
                r.setContent(reply.getText().toString());

                new Thread() {
                    public void run() {
                        try {
                            result = Service.saveReply(r);
                            Log.i("========>result", "result:" + result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (result.equals("success")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddReplyActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AddReplyActivity.this, ShowConditionsActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("doctor", (Serializable) doctor);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddReplyActivity.this, "添加失败，请重试", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                }.start();
            }
        });

    }

    private void initView() {
        username = (TextView) findViewById(R.id.add_reply_user_name);
        sex = (TextView) findViewById(R.id.add_reply_user_sex);
        age = (TextView) findViewById(R.id.add_reply_user_age);
        symptom = (TextView) findViewById(R.id.add_reply_cond_symptom);
        time = (TextView) findViewById(R.id.add_reply_cond_time);
        detial = (TextView) findViewById(R.id.add_reply_cond_detial);
        reply = (EditText) findViewById(R.id.add_reply_et);
        doBtn = (Button) findViewById(R.id.add_reply_btn);
        cid = findViewById(R.id.cid);
        uid = findViewById(R.id.uid);
        did = findViewById(R.id.did);
    }

    private void initDate() {
        @SuppressLint("HandlerLeak") final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                userBean = (UserBean) msg.obj;
                Log.i("====kkk====>condition", "condition:" + condition);
                Log.i("====kkk====>userBean", "userBean:" + condition);
                if (condition != null && userBean != null) {
                    System.out.println("======11" + condition + "11=======");
                    username.setText("用户姓名：" + userBean.getName());
                    sex.setText("用户性别：" + userBean.getSex());
                    age.setText("用户年龄：" + userBean.getAge());
                    symptom.setText("主要症状：" + condition.getSymptoms());
                    time.setText("持续时间：" + condition.getTime());
                    detial.setText("详细描述:" + condition.getDetails());
//            cid.setText(condition.getCid()+"");
//            uid.setText(condition.getUid()+"");
//            did.setText(condition.getDid()+"");

                }

            }
        };
        new Thread() {
            public void run() {
                String result = Service.userSelect(condition.getUid());
                Log.i("===///???=====>result", "result:" + result);
                UserBean userBean = (UserBean) parseObject(result, UserBean.class);
                Message message = new Message();
                message.obj = userBean;
                mHandler.sendMessage(message);
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), ShowConditionsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("doctor", doctor);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
