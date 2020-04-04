package com.dayu.jkapp.server.service;

import com.dayu.jkapp.server.pojo.UserBean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DaYu
 * @className Service.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public interface UserService {

	public UserBean select(UserBean user);
	public boolean insert(UserBean user);
	public void update(UserBean userBean);
}
