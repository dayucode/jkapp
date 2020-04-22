package com.dayu.jkapp.server.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DaYu
 * @className HealthBean.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Data
public class Reply implements Serializable {

	private long rid;
	private String content;
	private  long uid;
	private  long did;
	private  long cid;
}
