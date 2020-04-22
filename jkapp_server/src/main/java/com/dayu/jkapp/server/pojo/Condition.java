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
public class Condition implements Serializable {

	private long cid;
	private String symptoms;
	private String time;
	private String details;
	private long uid;
	private long did;
}
