package com.dayu.jkapp.server.service;

import com.dayu.jkapp.server.pojo.Step;
import org.springframework.stereotype.Service;

/**
 * @author DaYu
 * @className Service.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public interface StepService {

	void save(Step step);

	void update(Step step);
}
