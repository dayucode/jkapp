package com.jk.dayu.jkapp.BaseModule;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import com.jk.dayu.jkapp.HealthModule.HealthBean;
import com.jk.dayu.jkapp.HealthModule.TimeBean;
import com.jk.dayu.jkapp.StepModule.step.bean.StepBean;
import com.jk.dayu.jkapp.UserModule.LoginActivity;
import com.jk.dayu.jkapp.UserModule.PunchBean;
import com.jk.dayu.jkapp.UserModule.RegisterActivity;
import com.jk.dayu.jkapp.UserModule.UserBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class DataManager {

    public static String currentUserName(Context context){
        SharedPreferencesUtils sp = new SharedPreferencesUtils(context,"loginInfo");
        String current_name = (String) sp.getParam("name","");
        return current_name;
    }

    public static void saveCurrentUserName(Context context,String name){
        SharedPreferencesUtils sp = new SharedPreferencesUtils(context,"loginInfo");
        sp.setParam("name",name);
    }

    public static UserBean currentUser(Context context){
        String name = DataManager.currentUserName(context);
        List<UserBean> list = DbUtils.getQueryByWhere(UserBean.class,"name",new String[]{name});
        Log.i("list", list.toString());
        if (list.size() == 1) {
            return list.get(0);
        } else {
            UserBean user = new UserBean();
            user.name = name;
            user.point = "0";
            return user;
        }
    }

    public static void saveCurrentUser(Context context,UserBean user){
        String name = DataManager.currentUserName(context);
        List<UserBean> list = DbUtils.getQueryByWhere(UserBean.class,"name",new String[]{name});
        if (list.size() == 1) {
            DbUtils.update(user);
        } else {
            user.name = name;
            DbUtils.insert(user);
        }
    }

    public static void saveUser(RegisterActivity context, UserBean user){
        String name = user.name;
        List<UserBean> list = DbUtils.getQueryByWhere(UserBean.class,"name",new String[]{name});
        if (list.size() == 1) {
            DbUtils.update(user);
        } else {
            user.point = "0";
            DbUtils.insert(user);
        }
    }

    public static StepBean currentStep(Context context){
        String name = DataManager.currentUserName(context);
        Date date = new Date(System.currentTimeMillis());
        String CURRENT_DATE = new SimpleDateFormat("yyyy-MM-dd").format(date);
        List<StepBean> list = DbUtils.getQueryByWhere2(StepBean.class,"name" ,"date", new String[]{name,CURRENT_DATE});
        if (list.size() == 1) {
            return list.get(0);
        }else {
            StepBean data = new StepBean();
            data.date = CURRENT_DATE;
            data.name = name;
            data.step = "0";
            DbUtils.insert(data);
            return data;
        }
    }

    public static void setCurrentStep(Context context,int tempStep){
        String name = DataManager.currentUserName(context);
        Date date = new Date(System.currentTimeMillis());
        String CURRENT_DATE = new SimpleDateFormat("yyyy-MM-dd").format(date);
        List<StepBean> list = DbUtils.getQueryByWhere2(StepBean.class,"name" ,"date", new String[]{name,CURRENT_DATE});
        if (list.size() == 0 || list.isEmpty()) {
            StepBean data = new StepBean();
            data.date = CURRENT_DATE;
            data.step = tempStep + "";
            data.name = name;
            DbUtils.insert(data);
        } else if (list.size() == 1) {
            StepBean data = list.get(0);
            data.step = tempStep + "";
            data.name = name;
            DbUtils.update(data);
        }
    }

    public static HealthBean healthBean(Context context){
        String name = DataManager.currentUserName(context);
        List<HealthBean> list = DbUtils.getQueryByWhere(HealthBean.class,"name",new String[]{name});
        if (list.size() == 1) {
            return list.get(0);
        } else {
            HealthBean health = new HealthBean();
            health.name = name;
            return health;
        }
    }

    public static void saveHealthBean(Context context,HealthBean health){
        String name = DataManager.currentUserName(context);
        List<HealthBean> list = DbUtils.getQueryByWhere(HealthBean.class,"name",new String[]{name});
        if (list.size() == 1) {
            DbUtils.update(health);
        } else {
            health.name = name;
            DbUtils.insert(health);
        }
    }

    // 健康提示
    public static List<TimeBean> timeBean(Context context){
        String name = DataManager.currentUserName(context);
        List<TimeBean> list = DbUtils.getQueryByWhere(TimeBean.class,"name",new String[]{name});
        if (list.size() != 0) {
            return list;
        } else {
            return null;
        }
    }

    public static void remove(Context context,TimeBean timeBean){
        DbUtils.deleteWhere(TimeBean.class,"id",new String[]{timeBean.id+""});
    }

    public static void saveTimeBean(Context context,TimeBean timeBean){
        String name = DataManager.currentUserName(context);
        timeBean.name = name;
        DbUtils.insert(timeBean);
    }

    // 打卡记录
    public static List<PunchBean> punchBean(Context context){
        String name = DataManager.currentUserName(context);
        List<PunchBean> list = DbUtils.getQueryByWhere(PunchBean.class,"name",new String[]{name});
        if (list.size() != 0) {
            return list;
        } else {
            return null;
        }
    }

    public static boolean isPunchToday(Context context){

        String name = DataManager.currentUserName(context);
        String date = DataManager.currentDate();
        List<PunchBean> list = DbUtils.getQueryByWhere2(PunchBean.class,"name","date",new String[]{name,date});
        if (list.size() == 1) {
            return true;
        } else {
            PunchBean bean = new PunchBean();
            bean.name = name;
            bean.date = date;
            DbUtils.insert(bean);
            return false;
        }
    }

    // 是否登录
    public static boolean isLogin(Context context){
        boolean login = DataManager.currentUserName(context) != "";
        //boolean login = DataManager.currentUserName(context) != "";
        //Log.i("isLogin:", DataManager.currentUserName(context));
        Log.i("isLogin:", DataManager.currentUserName(context));
        if (login) return true;
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        return login;
    }

    // 用户是否存在
    public static Boolean userExist(Context context,String name){
        List<UserBean> list = DbUtils.getQueryByWhere(UserBean.class,"name",new String[]{name});
        Log.i("list", list.toString());
        if (list.size() == 1) {
            return true;
        } else {
            return false;
        }
    }

    // 健康报告
    public static String healthReport(Context context){
        String report = "";
        HealthBean health = DataManager.healthBean(context);
        if (health.bmi!=null) {
            float bmif = Float.parseFloat(health.bmi);
            String bmi = health.bmi;
            if (bmif < 18.5) {
                report += "BMI值为:" + bmi + "   体重过低 ;";
            } else if (bmif >= 18.5 && bmif < 24.9) {
                report += "BMI值为:" + bmi + "   正常范围 ;";
            } else {
                report += "BMI值为:" + bmi + "   注意减肥啦，超重啦 ;";
            }
        }

        if (health.presslow!=null) {
            float lowf = Float.parseFloat(health.presslow);
            float highf = Float.parseFloat(health.presslow);
            if (lowf<60||highf<90){
                report += "血压过低  ;";
            }else if (lowf>90||highf>140){
                report += "血压过高  ;";
            }else {
                report += "血压正常  ;";
            }
        }

        if (health.bloodsugar!=null) {
            float bloodsugar = Float.parseFloat(health.bloodsugar);
            if (bloodsugar<3.9){
                report += "血糖过低  ;";
            }else if (bloodsugar<6.1){
                report += "血糖过高  ;";
            }else {
                report += "血糖正常  ;";
            }
        }
        if (health.beat!=null) {
            float beat = Float.parseFloat(health.beat);
            if (beat<60){
                report += "心率较慢  ;";
            }else if (beat>100){
                report += "心率较快  ;";
            }else {
                report += "心率正常  。";
            }
        }
        if (report.equals("")) report = "您的数据不全无法评估，请点击未录入数据进行基础和进阶测试后查看测试报告";
        return report;
    }

    // 健康指数
    public static int healthIndex(){
        return 0;
    }

    public static List<String> needTips(Context context) {
        List<String> list = new ArrayList<>();
        HealthBean health = DataManager.healthBean(context);
        if (health.bmi != null) {
            float bmif = Float.parseFloat(health.bmi);
            String bmi = health.bmi;
            if (bmif < 18.5 || bmif > 24.9) {
                list.add("weight");
            }
        }
        if (health.presslow != null) {
            float lowf = Float.parseFloat(health.presslow);
            float highf = Float.parseFloat(health.presslow);
            if (lowf < 60 || highf < 90) {
                list.add("press");
            } else if (lowf > 90 || highf > 140) {
                list.add("press");
            }
        }

        if (health.bloodsugar != null) {
            float bloodsugar = Float.parseFloat(health.bloodsugar);
            if (bloodsugar < 3.9) {
                list.add("sugar");
            } else if (bloodsugar < 6.1) {
                list.add("sugar");
            }
        }
        if (health.beat != null) {
            float beat = Float.parseFloat(health.beat);
            if (beat < 60) {
                list.add("rate");
            } else if (beat > 100) {
                list.add("rate");
            }
        }
        return list;
    }


    // 搜索文章
    public static List<JSONObject> searchTipsWith(Context context,String name){
        List<String> nameList = new ArrayList<String>();
        nameList.add("rate");
        nameList.add("press");
        nameList.add("sugar");
        nameList.add("weight");
        List<JSONObject> list = DataManager.readTipsJson(context,nameList);
        List<JSONObject> resultList = new ArrayList<>();
        for (JSONObject item:list){
            try {
                String title = (String)item.get("title");
                String content = (String)item.get("content");
                if (title.indexOf(name)!=-1||content.indexOf(name)!=-1){
                    resultList.add(item);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }


    // 读取json文件
    public static List<JSONObject> readTipsJson(Context context,List<String> list){
        StringBuilder sb = new StringBuilder();
        AssetManager am = context.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open("tips.json")));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        try {
            List<JSONObject> resultlist = new ArrayList<>();
            JSONObject jObject = new JSONObject(sb.toString());
            JSONObject jsono =  (JSONObject) jObject.getJSONObject("data");
            list.add("general");
            for (String key:list){
                JSONArray array = (JSONArray) jsono.getJSONArray(key);
                if(array.length()>0){
                    for(int i=0;i<array.length();i++){
                        JSONObject job = (JSONObject)array.get(i);
                        resultlist.add(job);
                    }
                }
            }
            return resultlist;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 关闭键盘
    public static void closeKeyboard(Window window,InputMethodManager manager) {
        View view = window.peekDecorView();
        if (view != null) {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    // 当前日期
    public static String currentDate(){
        Date date = new Date(System.currentTimeMillis());
        String dateStr = new SimpleDateFormat("yyyy-M-d").format(date);
        return dateStr;
    }

    // 当前时间
    public static String currentTime(){
        Date date = new Date(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }

    // 判断当前日期是不是本月
    public static boolean isMonth(String date){
        String pre = DataManager.currentDate().substring(0,7);
        return (date.indexOf(pre) != -1);
    }
}
