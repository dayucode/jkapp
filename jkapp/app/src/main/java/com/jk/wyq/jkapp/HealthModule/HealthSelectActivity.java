package com.jk.wyq.jkapp.HealthModule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jk.wyq.jkapp.R;
public class HealthSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_select);
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
