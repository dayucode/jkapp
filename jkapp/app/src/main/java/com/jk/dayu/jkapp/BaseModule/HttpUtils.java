package com.jk.dayu.jkapp.BaseModule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class HttpUtils {
        public static HttpUtils getPost = new HttpUtils();//单例

        public static HttpUtils HttpUtils() {
            return getPost;
        }

        /**
         * 发送get请求
         *
         * @param url
         * @return
         */
        public static String get(final String url) {
            final StringBuilder sb = new StringBuilder();
            FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    BufferedReader br = null;
                    InputStreamReader isr = null;
                    URLConnection conn;
                    try {
                        URL geturl = new URL(url);
                        conn = geturl.openConnection();//创建连接
                        conn.connect();//get连接
                        isr = new InputStreamReader(conn.getInputStream());//输入流
                        br = new BufferedReader(isr);
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);//获取输入流数据
                        }
                        System.out.println(sb.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {//执行流的关闭
                        if (br != null) {
                            try {
                                if (br != null) {
                                    br.close();
                                }
                                if (isr != null) {
                                    isr.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            } }}
                    return sb.toString();
                }
            });
            new Thread(task).start();
            String s = null;
            try {
                s = task.get();//异步获取返回值
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        }
}
