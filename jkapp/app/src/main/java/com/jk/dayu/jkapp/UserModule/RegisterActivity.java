package com.jk.dayu.jkapp.UserModule;

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
import android.widget.RadioButton;
import android.widget.Toast;

import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.Interrogation.DBManager;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.service.Service;
import com.jk.dayu.jkapp.untils.IDUtils;
import com.jk.dayu.jkapp.untils.StringUtil;

import static com.alibaba.fastjson.JSON.parseObject;

public class RegisterActivity extends BaseActivity {

    private final static String TAG = "Register===>";

    private EditText accountTxt;
    private EditText pwdTxt;
    private EditText ageTxt;
    private RadioButton sex1;
    private RadioButton sex2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_register);
        accountTxt = (EditText) findViewById(R.id.etxt_account);
        pwdTxt = findViewById(R.id.etxt_pwd);
        sex1 = findViewById(R.id.sex1);
        sex2 = findViewById(R.id.sex2);
        ageTxt = findViewById(R.id.etxt_age);
    }

    public void register(View view) {
        if (accountTxt.getText().length() == 0 || pwdTxt.getText().length() < 3) {
            Toast.makeText(this, "重新输入", Toast.LENGTH_LONG).show();
            return;
        }

        new Thread() {
            public void run() {
                String name = accountTxt.getText().toString();
                String pwd = pwdTxt.getText().toString();
                String age = ageTxt.getText().toString();
                Log.i("========>", "ageTxt:" + age);
                if (StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(pwd)) {
                    final UserBean user = new UserBean();
                    user.setId(IDUtils.getNewId());
                    user.setName(name);
                    user.setPassword(pwd);
                    user.setAge(age);
                    if(sex1.isChecked()){
                        user.setSex("男");
                    }else if(sex2.isChecked()){
                        user.setSex("女");
                    }
                    user.setRole(0);
                    String result = null;
                    try {
                        result = Service.registerPost(user);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (result.equals("true")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                DataManager.saveUser(RegisterActivity.this, user);
                                UserBean userBean = DataManager.currentUser(getApplicationContext());
                                /**===========================*/
                                System.out.println("============//"+userBean+"//=============");
                                /**===========================*/

                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    } else if (result.equals("false")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "请求失败...", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "用户名或者密码为空", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            ;
        }.start();
    }

}
