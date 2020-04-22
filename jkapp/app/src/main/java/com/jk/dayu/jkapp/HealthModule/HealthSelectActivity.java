package com.jk.dayu.jkapp.HealthModule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.BaseModule.MainActivity;
import com.jk.dayu.jkapp.R;
public class HealthSelectActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_select);
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

    public void gotoBaseHealth(View view){
        Intent intent = new Intent(this, BaseHealthActivity.class);
        startActivity(intent);
    }

    public void gotoAdvance(View view){
        Intent intent = new Intent(this, AdvanceHealthActivity.class);
        startActivity(intent);
    }
}
