package com.dayu.jkapp.server.pojo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;

/**
 * @author DaYu
 * @className TimeBean.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Data
public class TimeBean implements Serializable {

	private int id;

	private long cid;

	private String name;

	private String time;

	private String tip;

}
