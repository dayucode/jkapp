package com.dayu.jkapp.server.service.serviceimpl;

import com.dayu.jkapp.server.mapper.HealthDao;
import com.dayu.jkapp.server.pojo.Health;
import com.dayu.jkapp.server.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author DaYu
 * @className HealthServiceImpl.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public class HealthServiceImpl implements HealthService {

	@Autowired
	private HealthDao healthDao;

	@Override
	public void save(Health health) {
		healthDao.save(health);
	}

	@Override
	public void update(Health health) {
		healthDao.update(health);
	}
}
