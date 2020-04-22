package com.dayu.jkapp.server.service.serviceimpl;

import com.dayu.jkapp.server.mapper.UserDao;
import com.dayu.jkapp.server.pojo.UserBean;
import com.dayu.jkapp.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author DaYu
 * @className HealthServiceImpl.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;


	@Override
	public UserBean select(UserBean user) {
		return userDao.select(user);
	}

	@Override
	public boolean insert(UserBean user) {
		return userDao.insert(user);
	}

	@Override
	public void update(UserBean userBean) {
		userDao.update(userBean);
	}

	@Override
	public UserBean selectUser(long id) {
		return userDao.selectUser(id);
	}

}
