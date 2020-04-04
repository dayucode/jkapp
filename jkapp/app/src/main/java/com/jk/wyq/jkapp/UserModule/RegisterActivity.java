package com.jk.wyq.jkapp.UserModule;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jk.wyq.jkapp.BaseModule.DataManager;
import com.jk.wyq.jkapp.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText accountTxt;
    private EditText pwdTxt;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        accountTxt = (EditText) findViewById(R.id.etxt_account);
        pwdTxt = findViewById(R.id.etxt_pwd);
        registerBtn = findViewById(R.id.login);
    }

    public void register(View view){
        if (accountTxt.getText().length()==0||pwdTxt.getText().length()==0){
            Toast.makeText(this,"重新输入",Toast.LENGTH_LONG).show();
            return;
        }
        String name = accountTxt.getText().toString();
        String pwd = pwdTxt.getText().toString();
        UserBean user = new UserBean();
        user.name = name;
        user.password = pwd;
        DataManager.saveUser(this,user);
        Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
        finish();
    }

}
