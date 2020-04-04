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
public class Health implements Serializable {

	private long id;

	private long cid;

	private String name;

	private String date;

	private String age;

	private String weight;

	private String height;

	private String bmi;

	private String beat;

	private String presshigh;

	private String presslow;

	private String bloodsugar;
}
