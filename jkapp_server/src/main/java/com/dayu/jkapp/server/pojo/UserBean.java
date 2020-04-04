package com.dayu.jkapp.server.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author DaYu
 * @className UserBean.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Data
public class UserBean implements Serializable {

	private long id;

	public String name;

	public String password;

	public String point;
}
