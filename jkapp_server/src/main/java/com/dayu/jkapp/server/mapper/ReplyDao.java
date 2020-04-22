package com.dayu.jkapp.server.mapper;

import com.dayu.jkapp.server.pojo.Reply;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DaYu
 * @className UserBeanDao.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Component
public interface ReplyDao {

	void insert(Reply reply);

	List<Reply> user2select(long uid);

	List<Reply> doctor2select(long did);

}
