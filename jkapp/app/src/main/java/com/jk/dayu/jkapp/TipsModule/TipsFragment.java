package com.jk.dayu.jkapp.TipsModule;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.jk.dayu.jkapp.BaseModule.DataManager;
import com.jk.dayu.jkapp.BaseModule.WebActivity;
import com.jk.dayu.jkapp.R;

import org.json.JSONObject;

import java.util.List;


public class TipsFragment extends Fragment {
    public ListView listView;
    public List jsonArray;
    public SearchView searchView;
    public Context context;
    static String rate = "rate"; // 心率
    static String press = "press"; //血压
    static String sugar = "sugar"; // 学堂
    static String weight = "weight"; // 超重

    public TipsFragment() {

    }

    @Override
    public void onResume() {
        jsonArray = DataManager.readTipsJson(getActivity(),DataManager.needTips(getActivity()));
        TipsAdapter adapter = new TipsAdapter(jsonArray,this.getContext());
        listView.setAdapter(adapter);
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();
        View view = inflater.inflate(R.layout.fragment_tips, container, false);
        listView = view.findViewById(R.id.listView);
        searchView = view.findViewById(R.id.search);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position,
                                    long id) {
                try {
                    JSONObject tip = (JSONObject)jsonArray.get(position);
                    String url = tip.getString("url");
                    Intent intent=new Intent(getActivity(), WebActivity.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<JSONObject> list = DataManager.searchTipsWith(getActivity(),query);
                TipsAdapter adapter = new TipsAdapter(list,context);
                listView.setAdapter(adapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener(){
            @Override
            public boolean onClose() {
                TipsAdapter adapter = new TipsAdapter(jsonArray,context);
                listView.setAdapter(adapter);
                return true;
            }
        });

        return view;
    }

}
