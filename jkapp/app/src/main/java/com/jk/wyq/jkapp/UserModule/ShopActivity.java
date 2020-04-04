package com.jk.wyq.jkapp.UserModule;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.jk.wyq.jkapp.BaseModule.DataManager;
import com.jk.wyq.jkapp.BaseModule.HomeAdapter;
import com.jk.wyq.jkapp.BaseModule.HomeBean;
import com.jk.wyq.jkapp.HealthModule.TimeBean;
import com.jk.wyq.jkapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopActivity extends AppCompatActivity {

    private ListView listView;
    private List<Map<String, Object>> list;
    private Context context;
    private String[] point = new String[]{"169000", "169000", "369000", "59000"
            , "199000", "79000", "20000", "30000"};
    private String[] name = new String[]{"家用静音健腹轮", "S型俯卧撑支架", "多功能引体向上器", "蔡依林同款哑铃"
            , "多功能拉力绳", "跳绳", "瑜伽垫", "瑜伽球"};
    private String[] rmb = new String[]{"169", "169", "369", "59"
            , "199", "79", "20", "30"};
    private int[] imgIds = new int[]{R.mipmap.shop1, R.mipmap.shop2, R.mipmap.shop3, R.mipmap.shop4
            ,R.mipmap.shop5,R.mipmap.shop6,R.mipmap.shop7,R.mipmap.shop8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        listView = findViewById(R.id.listView);
        context = this;
        list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imgIds.length; i++) {
           addToList(imgIds[i],point[i]+"积分",name[i],rmb[i]+"元");
        }
        String[] from = {"image","point","name","rmb"};
        int[] to = {R.id.image,R.id.point,R.id.name,R.id.rmb};
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.item_mine_shop,from,to);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("请联系客服：010-99999999");
                builder.setTitle("获取方式");
                builder.create().show();
            }
        });
    }

    private void addToList(int image,String point,String name , String rmb){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("image",image);
        map.put("point",point);
        map.put("name",name);
        map.put("rmb",rmb);
        list.add(map);
    }
}
