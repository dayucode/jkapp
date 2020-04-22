package com.dayu.jkapp.server.controller;

import com.dayu.jkapp.server.pojo.*;
import com.dayu.jkapp.server.service.*;
import com.dayu.jkapp.server.untils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DaYu
 * @className UserController.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@RestController
public class Controller {
	@Autowired
	UserService userService;
	@Autowired
	StepService stepService;
	@Autowired
	TimeService timeService;
	@Autowired
	HealthService healthService;
	@Autowired
	ConditionService conditionService;
	@Autowired
	ReplyService replyService;
	@Autowired
	DoctorService doctorService;

	/**
	 * 登录注册
	 */
	@PostMapping("/user/register")
	public boolean register(UserBean user) {
		System.out.println("register_request===>" + user);
		boolean b = userService.insert(user);
		System.out.println("register_back===>" + b);
		return b;
	}

	@PostMapping("/user/login")
	public UserBean userLogin(UserBean user) {
		System.out.println("login_request===>" + user);
		UserBean bean = userService.select(user);
		System.out.println("login_Back===>" + bean);
		return bean;
	}

	@GetMapping("/select/user")
	public UserBean selectUser(long id) {
		UserBean userBean = userService.selectUser(id);
		return userBean;
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
	public String timeSave(TimeBean time) {
		System.out.println(time);
		timeService.save(time);
		return "ok";
	}

	@PutMapping("/user/time2")
	public void timeUpdate(TimeBean time) {
		timeService.update(time);
	}

	/**
	 * Condition
	 */
	@PostMapping("/save/condition")
	public String saveCondition(Condition condition) {
		System.out.println(condition);
		conditionService.insert(condition);
		return "success";
	}

	@GetMapping("/doctor/select/condition")
	public List<Condition> doctor2Condition(long did) {
		return conditionService.doctor2select(did);
	}

	@GetMapping("/user/select/condition")
	public List<Condition> user2Condition(long uid) {
		return conditionService.user2select(uid);
	}

	/**
	 * Reply
	 */
	@PostMapping("/save/reply")
	public String saveReply(Reply reply) {
		System.out.println(reply);
		replyService.insert(reply);
		return "success";
	}

	@GetMapping("/doctor/select/reply")
	public List<Reply> doctor2reply(Long did) {
		System.out.println(did);
		return replyService.doctor2select(did);
	}

	@GetMapping("/user/select/reply")
	public List<Reply> user2reply(Long uid) {
		System.out.println(uid);
		return replyService.user2select(uid);
	}

	@PostMapping("/save/doctor")
	public void save(Doctor doctor) {
		doctor.setDid(IDUtils.getNewId());
		System.out.println(doctor);
		doctorService.insert(doctor);
	}
	@GetMapping("/list/doctor")
	public List<Doctor> doctorList() {
		System.out.println(doctorService.queryAllDoctor());
		return doctorService.queryAllDoctor();
	}
	@PostMapping("/doctor/login")
	public Doctor DoctorLogin(Doctor doctor) {
		System.out.println("login_request===>" + doctor);
		Doctor doc = doctorService.select(doctor);
		System.out.println("login_Back===>" + doc);
		return doc;
	}





}
