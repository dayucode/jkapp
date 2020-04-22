package com.dayu.jkapp.server.service.serviceimpl;

import com.dayu.jkapp.server.mapper.ReplyDao;
import com.dayu.jkapp.server.pojo.Reply;
import com.dayu.jkapp.server.service.ReplyService;
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
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao replyDao;

	@Override
	public void insert(Reply reply) {
		replyDao.insert(reply);
	}

	@Override
	public List<Reply> doctor2select(long did) {
		return replyDao.doctor2select(did);
	}

	@Override
	public List<Reply> user2select(long uid) {
		return replyDao.user2select(uid);
	}
}
