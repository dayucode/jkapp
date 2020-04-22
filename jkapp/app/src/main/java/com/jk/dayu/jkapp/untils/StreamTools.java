package com.jk.dayu.jkapp.untils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTools {
	/**
	 * 把输入流的内容转化成字符串
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String readInputStream(InputStream is){
		try {
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			int len = 0;
			byte[] buffer = new byte[1024];
			while((len=is.read(buffer))!=-1){
				stream.write(buffer, 0, len);
			}
			is.close();
			stream.close();
			byte[] result = stream.toByteArray();
			//试着解析 result 里面的字符串
			String temp = new String(result);
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return "获取失败";
		}
 
	}

	/**
	 * 将输入流转化成ByteArray
	 */
	public static byte[] convertIsToByteArray(InputStream inputStream) {
		ByteArrayOutputStream stream=new ByteArrayOutputStream();
		byte buffer[]=new byte[1024];
		int length=0;
		try {
			while ((length=inputStream.read(buffer))!=-1) {
				stream.write(buffer, 0, length);
			}
			inputStream.close();
			stream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream.toByteArray();
	}
}