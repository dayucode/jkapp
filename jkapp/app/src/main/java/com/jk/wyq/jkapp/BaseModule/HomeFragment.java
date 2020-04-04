package com.jk.wyq.jkapp.BaseModule;


import android.Manifest;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.jk.wyq.jkapp.HealthModule.AddTimeActivity;
import com.jk.wyq.jkapp.HealthModule.HealthSelectActivity;
import com.jk.wyq.jkapp.HealthModule.TimeBean;
import com.jk.wyq.jkapp.HeartBeatModule.HeartBeatActivity;
import com.jk.wyq.jkapp.R;
import com.jk.wyq.jkapp.StepModule.activity.StepActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jk.wyq.jkapp.BaseModule.HomeAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button hbBtn;
    private Button stepBtn;
    private Button jkBtn;
    public Button loginBtn;
    private ArrayList<HomeBean> dataList;

    private ListView listView;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        hbBtn = (Button)view.findViewById(R.id.heartBeat);
        hbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHeartBeatAct();
            }
        });

        stepBtn = (Button)view.findViewById(R.id.step);
        stepBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoStepAct();
            }
        });

        jkBtn = (Button)view.findViewById(R.id.jktest);
        jkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoJKTest();
            }
        });

        loginBtn = (Button)view.findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        listView = (ListView)view.findViewById(R.id.listView);
        return view;
    }

    private String  getWeather(){
        return HttpUtils.get("https://www.sojson.com/open/api/weather/json.shtml?city=北京");
    }

    public void initData(){


        dataList = new ArrayList<>();
        SharedPreferencesUtils sp = new SharedPreferencesUtils(getActivity(),"stepplan");
        String planWalk = (String) sp.getParam("planWalk_QTY", "7000");
        HomeBean home1 = new HomeBean(HomeAdapter.TYPE_STEP);
        home1.step = DataManager.currentStep(getContext()).step;
        home1.plan = planWalk;//planWalk;
        HomeBean home2 = new HomeBean(HomeAdapter.TYPE_HEALTH);
        home2.bmi = DataManager.healthBean(getContext()).weight;
        home2.date = DataManager.healthBean(getContext()).date;
        dataList.add(home1);
        dataList.add(home2);

        String weather = getWeather();
        HomeBean home3 = new HomeBean(HomeAdapter.TYPE_WEATHER);
        if (weather==null||weather==""){
            home3.time = "暂无网络连接";
            home3.tip = "";
            home3.date = "";
            dataList.add(home3);
        }else {
            try {
                List<JSONObject> resultlist = new ArrayList<>();
                JSONObject jObject = new JSONObject(weather);
                JSONObject data = jObject.getJSONObject("data");
                JSONArray wendua =  data.getJSONArray("forecast");
                JSONObject wenduo = (JSONObject) wendua.get(0);
                home3.time = wenduo.getString("high")+"  "+wenduo.getString("low");
                home3.tip = wenduo.getString("fx")+wenduo.getString("fl")+","+wenduo.getString("type");
                home3.date = data.getString("ganmao");
                dataList.add(home3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 健康提示
        List<TimeBean> timeList = DataManager.timeBean(getActivity());
        if (timeList==null||timeList.size()==0){
            HomeBean home = new HomeBean(HomeAdapter.TYPE_TIME,"点击添加提示","");
            dataList.add(home);
        }else {
            for(TimeBean time:timeList){
                HomeBean home = new HomeBean(HomeAdapter.TYPE_TIME,time.tip,time.time);
                NotiManager.addAlert(getActivity(),time.time,time.tip,time.id);
                dataList.add(home);
            }
        }

        HomeAdapter adapter = new HomeAdapter(getActivity(), dataList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id){
                if(dataList.get(position).type==HomeAdapter.TYPE_HEALTH){
                    gotoJKTest();
                }else if(dataList.get(position).type==HomeAdapter.TYPE_STEP){
                    gotoStepAct();
                }else if(dataList.get(position).type==HomeAdapter.TYPE_TIME){
                    gotoTime();
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                if (position<=2) return true;
                //定义AlertDialog.Builder对象，当长按列表项的时候弹出确认删除对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setMessage("确定删除?");
                builder.setTitle("提示");
                //添加AlertDialog.Builder对象的setPositiveButton()方法
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataList.remove(position);
                        List<TimeBean> timeList = DataManager.timeBean(getActivity());
                        DataManager.remove(getActivity(),(TimeBean)timeList.get(position-3));
                        if(dataList.size()==3){
                            HomeBean home = new HomeBean(HomeAdapter.TYPE_TIME,"点击添加提示","");
                            dataList.add(home);
                        }
                        HomeAdapter adapter = new HomeAdapter(getActivity(), dataList);
                        listView.setAdapter(adapter);
                    }
                });

                //添加AlertDialog.Builder对象的setNegativeButton()方法
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });

                builder.create().show();
                return true;
            }
        });
    }

    // 跳转心跳activity
    private void gotoHeartBeatAct(){

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA},
                        2);
            }
            return;
        }

        Intent intent = new Intent(getActivity(), HeartBeatActivity.class);
        startActivity(intent);
    }

    // 跳转记步activity
    private  void gotoStepAct(){
        if(!DataManager.isLogin(getActivity())) return;
        Intent intent = new Intent(getActivity(), StepActivity.class);
        startActivity(intent);
    }
    // 跳转健康测试activity
    private  void gotoJKTest(){
        if(!DataManager.isLogin(getActivity())) return;
        Intent intent = new Intent(getActivity(), HealthSelectActivity.class);
        startActivity(intent);
    }

    // 跳转添加健康时间activity
    private  void gotoTime(){
        if(!DataManager.isLogin(getActivity())) return;
        Intent intent = new Intent(getActivity(), AddTimeActivity.class);
        startActivity(intent);
    }

    public void login(){
        DataManager.isLogin(getActivity());
    }
}
