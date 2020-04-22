package com.dayu.jkapp.server.mapper;

import com.dayu.jkapp.server.pojo.UserBean;
import org.springframework.stereotype.Component;

/**
 * @author DaYu
 * @className UserBeanDao.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Component
public interface UserDao {

	UserBean select(UserBean user);
	boolean insert(UserBean user);
	void update(UserBean userBean);
	UserBean selectUser(long id);

}
