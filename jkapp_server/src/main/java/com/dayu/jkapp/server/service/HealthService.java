package com.dayu.jkapp.server.service;

import com.dayu.jkapp.server.pojo.Health;
import org.springframework.stereotype.Service;

/**
 * @author DaYu
 * @className Service.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public interface HealthService {

	public void save(Health health);
	public void update(Health health);
}
