package com.dayu.jkapp.server.mapper;

import com.dayu.jkapp.server.pojo.Health;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;


/**
 * @author DaYu
 * @className UserBeanDao.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Component
public interface HealthDao {

	void save(Health health);
	void update(Health health);
}
