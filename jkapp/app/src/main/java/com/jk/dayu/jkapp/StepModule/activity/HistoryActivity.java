package com.jk.dayu.jkapp.StepModule.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.StepModule.adapter.CommonAdapter;
import com.jk.dayu.jkapp.StepModule.adapter.CommonViewHolder;
import com.jk.dayu.jkapp.StepModule.step.bean.StepBean;
import com.jk.dayu.jkapp.BaseModule.DbUtils;


public class HistoryActivity extends AppCompatActivity {
    private LinearLayout layout_titlebar;
    private ImageView iv_left;
    private ImageView iv_right;
    private ListView lv;

    private void assignViews() {
        layout_titlebar = (LinearLayout) findViewById(R.id.layout_titlebar);
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        assignViews();
        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        initData();
    }

    private void initData() {
        setEmptyView(lv);
        DbUtils.createDb(this);
        String name = DataManager.currentUserName(this);
        List<StepBean> stepDatas = DbUtils.getQueryByWhere(StepBean.class,"name",new String[]{name});
        lv.setAdapter(new CommonAdapter<StepBean>(this,stepDatas,R.layout.item_history_step) {
            @Override
            protected void convertView(View item, StepBean stepData) {
                TextView tv_date= CommonViewHolder.get(item,R.id.tv_date);
                TextView tv_step= CommonViewHolder.get(item,R.id.tv_step);
                tv_date.setText(stepData.date);
                tv_step.setText(stepData.step+"步");
            }
        });
    }

    protected <T extends View> T setEmptyView(ListView listView) {
        TextView emptyView = new TextView(this);
        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        emptyView.setText("暂无数据！");
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        emptyView.setVisibility(View.GONE);
        ((ViewGroup) listView.getParent()).addView(emptyView);
        listView.setEmptyView(emptyView);
        return (T) emptyView;
    }
}
