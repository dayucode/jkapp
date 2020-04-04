package com.dayu.jkapp.server.controller;

import com.dayu.jkapp.server.pojo.Health;
import com.dayu.jkapp.server.pojo.Step;
import com.dayu.jkapp.server.pojo.TimeBean;
import com.dayu.jkapp.server.pojo.UserBean;
import com.dayu.jkapp.server.service.HealthService;
import com.dayu.jkapp.server.service.StepService;
import com.dayu.jkapp.server.service.TimeService;
import com.dayu.jkapp.server.service.UserService;
import com.dayu.jkapp.server.untils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author DaYu
 * @className UserController.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	StepService stepService;
	@Autowired
	TimeService timeService;
	@Autowired
	HealthService healthService;

	/**
	 * 登录注册
	 */
	@PostMapping("/user/register")
	public boolean register(UserBean user) {
		user.setId(IDUtils.getUserId());
		System.out.println("register_request===>"+user);
		boolean b = userService.insert(user);
		System.out.println("register_back===>"+b);
		return b;
	}
	@PostMapping("/user/login")
	public UserBean login(UserBean user) {
		System.out.println("login_request===>"+user);
		UserBean bean = userService.select(user);
		System.out.println("login_Back===>"+bean);
		return bean;
	}

	@PostMapping("/user/update")
	public void updateUser(UserBean user) {
		System.out.println(user);
		userService.update(user);
	}

	/**
	 * 健康
	 */
	@PostMapping("/user/health")
	public String healthSave(Health health) {
		healthService.save(health);
		return "ok";
	}
	@PutMapping("/user/health")
	public void healthUpdate(Health health) {
		healthService.update(health);
	}
	/**
	 * 步数
	 */
	@PostMapping("/user/step")
	public String stepSave(Step step) {
		stepService.save(step);
		return "ok";
	}
	@PutMapping("/user/step")
	public void stepUpdate(Step step) {
		stepService.update(step);
	}

	/**
	 * 提示
	 */
	@PostMapping("/user/time")
	public String  timeSave(TimeBean time) {
		System.out.println(time);
		timeService.save(time);
		return "ok";
	}
	@PutMapping("/user/time2")
	public void timeUpdate(TimeBean time) {
		timeService.update(time);
	}



}
