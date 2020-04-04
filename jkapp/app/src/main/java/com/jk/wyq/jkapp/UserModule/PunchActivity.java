package com.jk.wyq.jkapp.UserModule;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import com.jk.wyq.jkapp.BaseModule.DataManager;
import com.jk.wyq.jkapp.BaseModule.DbUtils;
import com.jk.wyq.jkapp.R;

import com.jk.wyq.jkapp.UserModule.DatePicker.DPCManager;
import com.jk.wyq.jkapp.UserModule.DatePicker.*;
import com.jk.wyq.jkapp.UserModule.UserBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PunchActivity extends AppCompatActivity implements DatePickerView.OnClickSignIn{

    private DatePickerView myDatepicker;

    private Context mContext;

    private DPCManager dpcManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punch);
        myDatepicker = findViewById(R.id.datepicker);
        init();
    }

    private void punch(){
        UserBean user = DataManager.currentUser(this);
        int point = Integer.parseInt(user.point)+10;
        Log.i("point", "punch: "+point);
        user.point = point+"";
        DataManager.saveCurrentUser(this,user);
    }

    private void init() {
        dpcManager = DPCManager.getInstance();
        dpcManager.clearnDATE_CACHE(); //清除cache
        if (!DataManager.isPunchToday(this)){
            punch();
        }
        List<PunchBean> punchList = DataManager.punchBean(this);
        List<String> tmp = new ArrayList<>();
        for(PunchBean punch:punchList){
            if(DataManager.isMonth(punch.date)) tmp.add(punch.date);
        }
        Log.i("init: ", DataManager.currentDate());
        dpcManager.setDecorBG(tmp); //预先设置日期背景 一定要在在开始设置
        Date date = new Date(System.currentTimeMillis());
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;

        myDatepicker.setDate(year, month); //设置日期

        myDatepicker.setMode(com.jk.wyq.jkapp.UserModule.DatePicker.DPMode.NONE); //设置选择模式

        myDatepicker.setFestivalDisplay(false); //是否显示节日
        myDatepicker.setTodayDisplay(false); //是否高亮显示今天
        myDatepicker.setHolidayDisplay(false); //是否显示假期
        myDatepicker.setDeferredDisplay(false); //是否显示补休
        myDatepicker.setIsScroll(false); //是否允许滑动 false表示左右上下都不能滑动  单项设置上下or左右 你需要自己扩展
        myDatepicker.setIsSelChangeColor(true, getResources().getColor(R.color.bg_white)); //设置选择的日期字体颜色,不然有的背景颜色和默认的字体颜色不搭

        myDatepicker.setLeftTitle(month + "月"); //左上方text
        myDatepicker.setRightTitle(true); //是否签到
        myDatepicker.setOnClickSignIn(this); //点击签到事件

        //设置预先选中日期的背景颜色
        myDatepicker.setDPDecor(new com.jk.wyq.jkapp.UserModule.DatePicker.DPDecor() {
            @Override
            public void drawDecorBG(Canvas canvas, Rect rect, Paint paint) {
                paint.setColor(getResources().getColor(R.color.color_main));
                canvas.drawCircle(rect.centerX(), rect.centerY(), rect.width() / 4F, paint);
            }
        });
        myDatepicker.invalidate(); //刷新
    }

    @Override
    public void signIn() {
        //动态更新的时候必须  清除cache
    }
}
