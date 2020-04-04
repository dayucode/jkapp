package com.dayu.jkapp.server.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DaYu
 * @className StepBean.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Data
public class Step implements Serializable {

	private int id;

	private long cid;

	private String name;

	private String date;

	private String step;
}
