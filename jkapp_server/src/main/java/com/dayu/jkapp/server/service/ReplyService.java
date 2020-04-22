package com.dayu.jkapp.server.service;

import com.dayu.jkapp.server.pojo.Condition;
import com.dayu.jkapp.server.pojo.Reply;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DaYu
 * @className Service.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public interface ReplyService {

	void insert(Reply reply);

	List<Reply> doctor2select(long did);

	List<Reply> user2select(long uid);
}
