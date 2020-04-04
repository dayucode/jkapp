package com.jk.wyq.jkapp.HealthModule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jk.wyq.jkapp.BaseModule.DataManager;
import com.jk.wyq.jkapp.HeartBeatModule.HeartBeatActivity;
import com.jk.wyq.jkapp.R;
import com.jk.wyq.jkapp.UserModule.UserBean;

public class AdvanceHealthActivity extends AppCompatActivity {

    TextView resultView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_health);
        resultView = findViewById(R.id.result);
    }

    private void gotoResult(){
        Intent intent = new Intent(this, HealthResultActivity.class);
        startActivity(intent);
    }

    public void test(View view){

        EditText txt_low_press =(EditText) findViewById (R.id.low_press);
        EditText txt_high_press =(EditText) findViewById (R.id.high_press);
        EditText txt_bless_suger =(EditText) findViewById (R.id.bless_suger);
        if (txt_low_press.getText().toString() == ""
                ||txt_high_press.getText().toString() == ""
                ||txt_bless_suger.getText().toString() == ""){
            Toast.makeText(this,"请输入信息后重试",Toast.LENGTH_LONG).show();
            return;
        }

        HealthBean health = DataManager.healthBean(this);
        health.presslow = txt_low_press.getText().toString();
        health.presshigh = txt_high_press.getText().toString();
        health.bloodsugar = txt_bless_suger.getText().toString();
        Integer presslow = Integer.parseInt(health.presslow);
        Integer presshigh = Integer.parseInt(health.presshigh);
        Integer bloodsugar = Integer.parseInt(health.bloodsugar);

        String tips = "";
        if (bloodsugar<3.9){
            tips += "血糖过低  ";
        }else if (bloodsugar<6.1){
            tips += "血糖过高  ";
        }else {
            tips += "血糖正常  ";
        }

        if (presslow<60||presshigh<90){
            tips += "血压过低  ";
        }else if (presslow>90||presshigh>140){
            tips += "血压过高  ";
        }else {
            tips += "血压正常  ";
        }
        resultView.setText(tips);
        Toast.makeText(this,"测试完成！奖励10积分！",Toast.LENGTH_LONG).show();
        UserBean user = DataManager.currentUser(this);
        int point = Integer.parseInt(user.point)+10;
        user.point = point+"";
        DataManager.saveCurrentUser(this,user);
        DataManager.saveHealthBean(this,health);
        DataManager.closeKeyboard(getWindow(),(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
    }
}
