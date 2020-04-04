package com.dayu.jkapp.server.service;

import com.dayu.jkapp.server.pojo.TimeBean;
import org.springframework.stereotype.Service;

/**
 * @author DaYu
 * @className Service.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public interface TimeService {

	public void save(TimeBean time);
	public void update(TimeBean time);
}
