package com.jk.dayu.jkapp.Interrogation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jk.dayu.jkapp.BaseModule.BaseActivity;
import com.jk.dayu.jkapp.R;
public class InterrogationActivity extends BaseActivity implements View.OnClickListener {


    private Button addBtn;
    private Button showBtn;
    private Button showConditionsBtn;
    private long did;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_inquiry);
        initView();
        addBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
        showConditionsBtn.setOnClickListener(this);
        Intent intent = getIntent();
        String sDid = intent.getStringExtra("did");
        did = Long.parseLong(sDid);
        Log.i("========>did========", "did:" + did);

    }

    /**
     * 初始化视图
     */
    private void initView() {

        addBtn = (Button) findViewById(R.id.main_addBtn);
        showBtn = (Button) findViewById(R.id.main_showComBtn);
        showConditionsBtn = (Button) findViewById(R.id.main_shwoConsBtn);
    }

    //按钮点击事件
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        switch (id) {
            case R.id.main_addBtn:
                //进入病情录入界面
                intent = new Intent(InterrogationActivity.this, AddConditionActivity.class);
                intent.putExtra("did", did + "");
                break;
            case R.id.main_showComBtn:
                //进入回复查看界面
                intent = new Intent(InterrogationActivity.this, ShowReplyActivity.class);
                break;
            case R.id.main_shwoConsBtn:
                //进入病症查看页面
                intent = new Intent(InterrogationActivity.this, ShowConditionsActivity.class);
                break;
        }
        startActivity(intent);
    }
}
