package com.jk.dayu.jkapp.untils;

import java.io.IOException;
import java.io.InputStream;

public class InputStr2StrUntil {
    public static String InputStr2Str_byteArr(InputStream in, String encode)
    {
        StringBuffer sb = new StringBuffer();
        byte[] b = new byte[1024];
        int len = 0;
        try
        {
            if (encode == null || encode.equals(""))
            {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            while ((len = in.read(b)) != -1)
            {
                sb.append(new String(b, 0, len, encode));
            }
            return sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";

    }

}
