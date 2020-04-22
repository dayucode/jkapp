package com.jk.dayu.jkapp.service;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.jk.dayu.jkapp.HealthModule.HealthBean;
import com.jk.dayu.jkapp.HealthModule.TimeBean;
import com.jk.dayu.jkapp.Interrogation.Condition;
import com.jk.dayu.jkapp.Interrogation.Reply;
import com.jk.dayu.jkapp.StepModule.step.bean.StepBean;
import com.jk.dayu.jkapp.UserModule.Doctor;
import com.jk.dayu.jkapp.UserModule.UserBean;


public class Service {



    //你要訪問的地址
    private static String url ="http://192.168.2.201:8080";
    //private static String url ="http://192.168.43.141:8080";

    static String url1=url+"/user/register";
    public static String registerPost(UserBean user){
        String json_body = JSONObject.toJSONString(user);
        Log.i("register========>", json_body);
        return HttpUntil.postUntil(url1,json_body);
    }

    static String url2=url+"/user/login";
    public static String userLoginPost(UserBean user){
        String json_body = JSONObject.toJSONString(user);
        Log.i("========>向后端登录", json_body);
        return HttpUntil.postUntil(url2,json_body);
    }


    static String url3=url+"/user/update";
    public static String updateUserPost(UserBean user,long id){
        user.setId(id);
        String json_body = JSONObject.toJSONString(user);
        Log.i("========>", json_body);
        return HttpUntil.postUntil(url3,json_body);
    }


    static String url4=url+"/user/time";
    public static String timePost(TimeBean time, long cid){
        time.setCid(cid);
        Log.i("<========>", "time"+time.toString());
        String json_body = JSONObject.toJSONString(time);
        Log.i("========>", json_body);
        return HttpUntil.postUntil(url4,json_body);
    }

    static String url5=url+"/user/health";
    public static String healthPost(HealthBean health, long cid){
        health.setCid(cid);
        Log.i("<========>", "health"+health.toString());
        String json_body = JSONObject.toJSONString(health);
        Log.i("========>", json_body);
        return HttpUntil.postUntil(url5,json_body);
    }

    static String url6=url+"/user/step";
    public static String stepPost(StepBean step, long cid){
        step.setCid(cid);
        String json_body = JSONObject.toJSONString(step);
        Log.i("========>", json_body);
        return HttpUntil.postUntil(url6,json_body);
    }
    static String url7=url+"/list/doctor";
    public static String listDoctor(){
        return HttpUntil.getUntil(url7);
    }
    static String url8=url+"/save/condition";
    public static String saveCondition(Condition condition){
        String json_body = JSONObject.toJSONString(condition);
        return HttpUntil.postUntil(url8,json_body);
    }

    static String url9=url+"/user/select/condition";
    public static String user2Condition (long uid){
        return HttpUntil.getUntil(url9+"?uid="+uid);
    }
    static String url10=url+"/doctor/select/condition";
    public static String doctor2Condition(long did){
        return HttpUntil.getUntil(url10+"?did="+did);
    }

    static String url11=url+"/doctor/login";
    public static String doctorLoginPost(Doctor doctor){
        String json_body = JSONObject.toJSONString(doctor);
        Log.i("========>向后端登录", json_body);
        return HttpUntil.postUntil(url11,json_body);
    }
    static String url12=url+"/select/user";
    public static String userSelect(long id){
        return HttpUntil.getUntil(url12+"?id="+id);
    }
    static String url13=url+"/save/reply";
    public static String saveReply(Reply reply){
        String json_body = JSONObject.toJSONString(reply);
        Log.i("========>向后端登录", json_body);
        return HttpUntil.postUntil(url13,json_body);
    }
    static String url14=url+"/select/user";
    public static String getReply(long uid){
        return HttpUntil.getUntil(url14+"?uid="+uid);
    }

    static String url15=url+"/user/select/reply";
    public static String userSelectReply(Long uid){
        System.out.println("||======"+uid+"=======||");
        return HttpUntil.getUntil(url15+"?uid="+uid);
    }
    static String url16=url+"/doctor/select/reply";
    public static String doctorSelectReply(Long did){
        return HttpUntil.getUntil(url16+"?did="+did);
    }


}
