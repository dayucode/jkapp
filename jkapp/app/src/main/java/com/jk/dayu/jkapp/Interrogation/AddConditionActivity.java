package com.jk.dayu.jkapp.Interrogation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.UserModule.Doctor;
import com.jk.dayu.jkapp.service.Service;
import com.jk.dayu.jkapp.untils.IDUtils;
import com.jk.dayu.jkapp.untils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static com.alibaba.fastjson.JSON.parseObject;

public class AddConditionActivity extends BaseActivity {

    private EditText symptoms;
    private Spinner time;
    private EditText details;
    private Button confrim;
    private Condition condition = new Condition();
    private List<String> data_list;

    private long did;
    int i = 0;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_condition);
        initView();

        Intent intent = getIntent();
        String sDid = intent.getStringExtra("did");
        did = Long.parseLong(sDid);
        Log.i("========>did", "add_name:" + did);

        data_list = new ArrayList<String>();
        data_list.add("");
        data_list.add("三天以内");
        data_list.add("一周以内");
        data_list.add("一月以内");
        data_list.add("一年以内");
        data_list.add("一年以上");


        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                condition.setTime(data_list.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int role = DataManager.currentUser(getApplicationContext()).getRole();
                //System.out.println("===========" + role + "==========");
                condition.setCid(IDUtils.getNewId());
                condition.setUid(DataManager.currentUser(getApplicationContext()).getId());

                i++;
                condition.setDid(did);
                condition.setSymptoms(symptoms.getText().toString());
                condition.setDetails(details.getText().toString());
                Log.i("========>condition", "condition:" + condition);


                new Thread() {
                    public void run() {
                        String result = Service.saveCondition(condition);
                        Log.i("========>result", "result:" + result);
                        if (result.equals("success")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddConditionActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(AddConditionActivity.this, InterrogationActivity.class);
//                                    startActivity(intent);
//                                    finish();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(AddConditionActivity.this, "提交失败，请重试", Toast.LENGTH_SHORT).show();

                                }
                            });
                        }
                    }
                }.start();

            }
        });


    }


    /**
     * 初始化视图
     */
    private void initView() {
        symptoms = (EditText) findViewById(R.id.add_c_symptoms);
        time = (Spinner) findViewById(R.id.time);
        details = (EditText) findViewById(R.id.add_c_detial);
        confrim = (Button) findViewById(R.id.add_c_btn_confrim);
    }


}
