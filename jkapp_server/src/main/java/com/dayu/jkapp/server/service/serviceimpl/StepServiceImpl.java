package com.dayu.jkapp.server.service.serviceimpl;

import com.dayu.jkapp.server.mapper.HealthDao;
import com.dayu.jkapp.server.mapper.StepDao;
import com.dayu.jkapp.server.pojo.Health;
import com.dayu.jkapp.server.pojo.Step;
import com.dayu.jkapp.server.service.HealthService;
import com.dayu.jkapp.server.service.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author DaYu
 * @className HealthServiceImpl.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public class StepServiceImpl implements StepService {

	@Autowired
	private StepDao stepDao;

	@Override
	public void save(Step step) {
		stepDao.save(step);
	}

	@Override
	public void update(Step step) {
		stepDao.update(step);
	}
}
