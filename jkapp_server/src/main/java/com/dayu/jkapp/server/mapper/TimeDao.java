package com.dayu.jkapp.server.mapper;

import com.dayu.jkapp.server.pojo.TimeBean;
import org.springframework.stereotype.Component;

/**
 * @author DaYu
 * @className UserBeanDao.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Component
public interface TimeDao {
	void save(TimeBean time);
	void update(TimeBean time);
}
