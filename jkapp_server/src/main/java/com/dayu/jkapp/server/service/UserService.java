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

	UserBean select(UserBean user);

	boolean insert(UserBean user);

	void update(UserBean userBean);

	UserBean selectUser(long id);
}
