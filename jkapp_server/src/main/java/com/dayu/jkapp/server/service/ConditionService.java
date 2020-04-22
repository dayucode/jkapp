package com.dayu.jkapp.server.service;

import com.dayu.jkapp.server.pojo.Condition;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DaYu
 * @className Service.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public interface ConditionService {

	void insert(Condition condition);

	List<Condition> doctor2select(long did);

	List<Condition> user2select(long uid);
}
