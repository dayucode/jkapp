package com.jk.dayu.jkapp.Interrogation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.UserModule.Doctor;
import com.jk.dayu.jkapp.UserModule.LoginActivity;

import java.io.Serializable;

public class DoctorActivity extends BaseActivity {

    private Button showConditionsBtn;
    private Button showReplysBtn;
    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Intent intent = getIntent();
        doctor=(Doctor) intent.getSerializableExtra("doctor");
        //System.out.println("========="+doctor+"==========");
        showConditionsBtn = (Button) findViewById(R.id.show_conditisons_btn);
        showReplysBtn = (Button) findViewById(R.id.show_replys_btn);

        showConditionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorActivity.this,ShowConditionsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctor", (Serializable) doctor);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
        showReplysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorActivity.this,ShowReplyActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctor", (Serializable) doctor);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DoctorActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
