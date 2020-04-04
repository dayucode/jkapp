package com.jk.wyq.jkapp.BaseModule;

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
//        /**
//         * POST请求
//         *
//         * @param url url
//         * @param map 请求参数的map集合形式
//         * @return
//         */
//        public static String post(final String url, final Map<String, String> map) {
//            final StringBuilder sb = new StringBuilder();
//            FutureTask<String> task = new FutureTask<String>(new Callable<String>() {
//                @Override
//                public String call() throws Exception {
//                    DataOutputStream out = null;
//                    BufferedReader br = null;
//                    URLConnection conn;
//                    URL posturl = new URL(url);
//                    try {
//                        conn = posturl.openConnection();//创建连接
//                        conn.setDoInput(true);//post请求必须设置
//                        conn.setDoOutput(true);//post请求必须设置
//                        out = new DataOutputStream(conn
//                                .getOutputStream());//输出流
//                        StringBuilder request = new StringBuilder();
//                        for (String key : map.keySet()) {
//                            request.append(key + "=" + URLEncoder.encode(map.get(key), "UTF-8") + "&");
//                        }//连接请求参数
//                        out.writeBytes(request.toString());//输出流写入请求参数
//                        out.flush();
//                        out.close();
//                        br = new BufferedReader(new InputStreamReader(conn.getInputStream()));//获取输入流
//                        String line;
//                        while ((line = br.readLine()) != null) {
//                            sb.append(line);
//                        }
//                        System.out.println(sb.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {//执行流的关闭
//                        if (br != null) {
//                            br.close();
//                        }
//                        if (out != null) {
//                            out.close();
//                        } }
//                    return sb.toString(); }
//            });
//            String s = null;
//            new Thread(task).start();
//            try {
//                s = task.get();//异步获取返回值
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return s;
//        }
}
