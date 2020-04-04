package com.dayu.jkapp.server.service.serviceimpl;

import com.dayu.jkapp.server.mapper.TimeDao;
import com.dayu.jkapp.server.pojo.TimeBean;
import com.dayu.jkapp.server.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author DaYu
 * @className HealthServiceImpl.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public class TimeServiceImpl implements TimeService {

	@Autowired
	private TimeDao timeDao;

	@Override
	public void save(TimeBean time) {
		timeDao.save(time);
	}

	@Override
	public void update(TimeBean time) {
		timeDao.update(time);
	}
}
