package com.dayu.jkapp.server.mapper;

import com.dayu.jkapp.server.pojo.Condition;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DaYu
 * @className UserBeanDao.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Component
public interface ConditionDao {

	void insert(Condition condition);

	List<Condition> user2select(long uid);

	List<Condition> doctor2select(long did);

}
