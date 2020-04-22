package com.jk.dayu.jkapp.MineModule;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jk.dayu.jkapp.HealthModule.HealthResultActivity;
import com.jk.dayu.jkapp.Interrogation.InterrogationActivity;
import com.jk.dayu.jkapp.StepModule.activity.StepActivity;
import com.jk.dayu.jkapp.UserModule.DoctorListActivity;
import com.jk.dayu.jkapp.UserModule.LoginActivity;
import com.jk.dayu.jkapp.UserModule.PunchActivity;
import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.UserModule.UserBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {

    ListView listView;
    public TextView txt_name;
    public TextView txt_point;
    private String[] mListStr = {"打卡记录","健康报告","今日运动","在线问诊","退出登录"};
    public MineFragment() {
        // Required empty public constructor
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResume() {
        String name = DataManager.currentUserName(getContext());
        UserBean user = DataManager.currentUser(getContext());
        if (name == ""|| name==null){
            txt_name.setText("未登录");
            mListStr[4] = "点击登录";
        }else {
            txt_name.setText(name);
            String point = "积分:" + DataManager.currentUser(getContext()).point;
            txt_point.setText(point);
        }

        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        listView =  view.findViewById(R.id.listView);
        txt_name = view.findViewById(R.id.name);
        txt_point = view.findViewById(R.id.txt_point);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, mListStr));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                if (position==3){
                    Intent intent=new Intent(getActivity(), DoctorListActivity.class);
                    startActivity(intent);
                    // 测步数
                }else if(position==2){
                    Intent intent=new Intent(getActivity(), StepActivity.class);
                    startActivity(intent);
                }else if (position==0){
                    Intent intent=new Intent(getActivity(), PunchActivity.class);
                    startActivity(intent);
                    // 健康报表
                }else if (position==1){
                    Intent intent=new Intent(getActivity(), HealthResultActivity.class);
                    startActivity(intent);
                    // 退出登录
                }else if (position==4){
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }


}
