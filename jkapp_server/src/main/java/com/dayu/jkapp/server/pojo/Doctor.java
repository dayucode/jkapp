package com.dayu.jkapp.server.pojo;

import lombok.Data;

/**
 * @author DaYu
 * @className Doctor.java
 * @description TODO
 * @createTime 2020年04月17日
 */
@Data
public class Doctor {
	private long did;
	private String username;
	private String password;
	private String introduce;
	int role;
}
