package com.jk.dayu.jkapp.HealthModule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.BaseModule.MainActivity;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.UserModule.UserBean;


public class BaseHealthActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;
    TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_health);
        resultView = findViewById(R.id.result);
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab_bar);
        radioGroup.setOnCheckedChangeListener(this);

    }
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton rb_home = findViewById(R.id.rb_home);
        switch (checkedId) {
            case R.id.rb_home:
                Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent3);
                finish();
                break;
            default:
                Toast.makeText(this, "secret", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public void test(View view) {

        EditText txt_height = (EditText) findViewById(R.id.height);
        EditText txt_wight = (EditText) findViewById(R.id.weight);
        EditText txt_age = (EditText) findViewById(R.id.age);
        if (txt_height.getText().toString() == ""
                || txt_wight.getText().toString() == ""
                || txt_age.getText().toString() == "") {
            Toast.makeText(this, "请输入信息后重试", Toast.LENGTH_LONG).show();
            return;
        }

        HealthBean health = DataManager.healthBean(this);
        health.height = txt_height.getText().toString();
        health.weight = txt_wight.getText().toString();
        health.age = txt_age.getText().toString();
        Integer height = Integer.parseInt(health.height);
        Integer weight = Integer.parseInt(health.weight);
        Integer age = Integer.parseInt(health.age);

        float result = weight * 10000 / height / height;
        String resultStr = "";
        String bmi = String.valueOf(result);

        if (result < 18.5) {
            resultStr = "BMI值为:" + bmi + "   体重过低";
        } else if (result >= 18.5 && result < 24.9) {
            resultStr = "BMI值为:" + bmi + "   正常范围";
        } else {
            resultStr = "BMI值为:" + bmi + "   注意减肥啦，超重啦";
        }
        health.bmi = bmi;
        health.date = DataManager.currentTime();
        resultView.setText(resultStr);

        Toast.makeText(this, "测试完成！奖励10积分！", Toast.LENGTH_LONG).show();

        UserBean user = DataManager.currentUser(this);
        int point = 10;
        try {
            point = Integer.parseInt(user.point) + 10;
        }catch (Exception e){
            e.printStackTrace();
        }
        user.point = point + "";
        DataManager.saveCurrentUser(this, user);

        DataManager.saveHealthBean(this, health);
        DataManager.closeKeyboard(getWindow(), (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
    }
}
