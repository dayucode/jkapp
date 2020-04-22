package com.jk.dayu.jkapp.BaseModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jk.dayu.jkapp.R;


import java.util.List;



public class HomeAdapter extends BaseAdapter {
    private List<HomeBean> dataList;
    private Context context;
    private LayoutInflater inflater;


    public static int TYPE_STEP = 1; // 步数
    public static int TYPE_WEATHER = 2; // 天气
    public static int TYPE_HEALTH = 3; // 健康
    public static int TYPE_TIME = 4; // 定时
    private final int TYPE_COUNT = 2; // 总个数

    public HomeAdapter(Context context,List dataList){
        this.context = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HomeBean bean = dataList.get(i);
        int type = bean.type;


        if (type==TYPE_WEATHER){
            view = inflater.inflate(R.layout.item_home_weather, null);
            TextView weather = (TextView) view.findViewById(R.id.weather);
            weather.setText(bean.time);
            TextView tip = (TextView) view.findViewById(R.id.tip);
            tip.setText(bean.tip);
            TextView ganmao = (TextView) view.findViewById(R.id.ganmao);
            ganmao.setText(bean.date);
        }else if (type==TYPE_STEP){
            view = inflater.inflate(R.layout.item_home_step, null);
            TextView finish = (TextView) view.findViewById(R.id.finish);
            TextView plan = (TextView) view.findViewById(R.id.plan);
            plan.setText(bean.plan);
            finish.setText(bean.step);
        }else if(type==TYPE_TIME){
            view = inflater.inflate(R.layout.item_home_time, null);
            TextView txt_tip = (TextView) view.findViewById(R.id.txt_tip);
            txt_tip.setText(bean.tip);
            TextView txt_time = (TextView) view.findViewById(R.id.txt_time);
            txt_time.setText(bean.time);

        }else if(type==TYPE_HEALTH) {
            view = inflater.inflate(R.layout.item_home_health, null);
            TextView txt_health = (TextView) view.findViewById(R.id.txt_health);
            TextView txt_date = (TextView) view.findViewById(R.id.txt_date);

            if (bean.bmi == null){
                txt_health.setText("点击健康测试");
            }else {
                txt_health.setText("BMI值:"+bean.bmi);
                txt_date.setText(bean.date);
            }
        }
        return view;
    }

    public void onDelete(){

    }
    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }
}
