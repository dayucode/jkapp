package com.dayu.jkapp.server.service.serviceimpl;

import com.dayu.jkapp.server.mapper.ConditionDao;
import com.dayu.jkapp.server.mapper.UserDao;
import com.dayu.jkapp.server.pojo.Condition;
import com.dayu.jkapp.server.pojo.UserBean;
import com.dayu.jkapp.server.service.ConditionService;
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
public class ConditionServiceImpl implements ConditionService {

	@Autowired
	private ConditionDao conditionDao;


	@Override
	public void insert(Condition condition) {
		conditionDao.insert(condition);
	}

	@Override
	public List<Condition> doctor2select(long did) {
		return conditionDao.doctor2select(did);
	}

	@Override
	public List<Condition> user2select(long uid) {
		return conditionDao.user2select(uid);
	}
}
