package com.dayu.jkapp.server.mapper;

import com.dayu.jkapp.server.pojo.Condition;
import com.dayu.jkapp.server.pojo.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author DaYu
 * @className UserBeanDao.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Component
public interface DoctorDao {

	void insert(Doctor doctor);

	Doctor select(Doctor doctor);

	List<Doctor> queryAllDoctor();
}
