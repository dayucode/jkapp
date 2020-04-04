package com.dayu.jkapp.server.mapper;

import com.dayu.jkapp.server.pojo.Step;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author DaYu
 * @className UserBeanDao.java
 * @description TODO
 * @createTime 2020年03月14日
 */

@Component
public interface StepDao {
	void save(Step step);
	void update(Step step);
}

