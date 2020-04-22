package com.jk.dayu.jkapp.UserModule;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.BaseModule.MainActivity;
import com.jk.dayu.jkapp.Interrogation.DBManager;
import com.jk.dayu.jkapp.Interrogation.DoctorActivity;
import com.jk.dayu.jkapp.Interrogation.InterrogationActivity;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.service.Service;
import com.jk.dayu.jkapp.untils.StatusBarUtil;
import com.jk.dayu.jkapp.untils.StringUtil;

import java.io.Serializable;

import static com.alibaba.fastjson.JSON.parseObject;


public class LoginActivity extends BaseActivity {
    private EditText accountTxt;
    private EditText pwdTxt;
    private RadioButton rb1;
    private RadioButton rb2;
    int role;

    UserBean user = new UserBean();
    Doctor doctor = new Doctor();

    String result;
    String name;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        accountTxt = (EditText) findViewById(R.id.etxt_account);
        pwdTxt = findViewById(R.id.etxt_pwd);
    }

    public void login(View view) {
        if (accountTxt.getText().length() < 3 || pwdTxt.getText().length() < 3) {
            Toast.makeText(this, "字符长度不够，重新输入", Toast.LENGTH_LONG).show();
            return;
        }
        name = accountTxt.getText().toString();
        pwd = pwdTxt.getText().toString();

        new Thread() {
            @SuppressLint("LongLogTag")
            public void run() {
                Log.i("========>name", "name:" + name);
                Log.i("========>pwd", "pwd:" + pwd);
                rb1 = findViewById(R.id.radioButton1);
                rb2 = findViewById(R.id.radioButton2);
                if (StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(pwd)) {
                    if (rb1.isChecked()) {
                        user.setRole(0);
                        user.setName(name);
                        user.setPassword(pwd);
                        try {
                            Log.i("========>user", "user:" + user);
                            result = Service.userLoginPost(user);
                            Log.i("========>result", "result:" + result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        assert result != null;
                        user = parseObject(result, UserBean.class);
                        Log.i("========>user1", "user1:" + user);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
//                                if(!(user.getId()>0)){
//                                    DataManager.saveCurrentUserName(getApplicationContext(), user.getName());
//                                    DataManager.saveCurrentUser(getApplicationContext(), user);
//                                }
                                DataManager.saveCurrentUserName(getApplicationContext(), user.getName());
                                DataManager.saveCurrentUser(getApplicationContext(), user);
                                System.out.println("====login=///========" + DataManager.currentUser(getApplicationContext()) + "=============");
                                Toast.makeText(LoginActivity.this, "你登陆成功...", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else if (rb2.isChecked()) {
                        doctor.setRole(1);
                        doctor.setUsername(name);
                        doctor.setPassword(pwd);
                        try {
                            Log.i("========>doctor", "doctor:" + doctor);
                            result = Service.doctorLoginPost(doctor);
                            Log.i("========>result", "result:" + result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        doctor = parseObject(result, Doctor.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("=============" + doctor + "=============");
                                Intent intent = new Intent(LoginActivity.this, DoctorActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("doctor", (Serializable) doctor);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "请选择角色", Toast.LENGTH_LONG).show();
                        return;
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "用户名或者密码为空", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            ;
        }.start();
    }

    public void register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
