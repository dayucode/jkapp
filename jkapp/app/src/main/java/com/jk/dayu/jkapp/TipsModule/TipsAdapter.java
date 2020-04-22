package com.jk.dayu.jkapp.TipsModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jk.dayu.jkapp.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;



public class TipsAdapter extends BaseAdapter{
    private List<JSONObject> tipsArray;
    private Context mContext;

    public TipsAdapter(List tipsArray,Context context){
        this.tipsArray = tipsArray;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return tipsArray.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(mContext).inflate(R.layout.item_tips,null);
        TextView txt_title = view.findViewById(R.id.title);
        TextView txt_content = view.findViewById(R.id.content);
        try {
            JSONObject tip = (JSONObject)tipsArray.get(i);
            txt_content.setText(tip.getString("content"));
            txt_title.setText(tip.getString("title"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }
}
