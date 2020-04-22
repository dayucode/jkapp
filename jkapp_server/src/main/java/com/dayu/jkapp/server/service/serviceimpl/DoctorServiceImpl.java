package com.dayu.jkapp.server.service.serviceimpl;

import com.dayu.jkapp.server.mapper.DoctorDao;
import com.dayu.jkapp.server.mapper.ReplyDao;
import com.dayu.jkapp.server.pojo.Doctor;
import com.dayu.jkapp.server.pojo.Reply;
import com.dayu.jkapp.server.service.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorDao doctorDao;

	@Override
	public void insert(Doctor doctor) {
		doctorDao.insert(doctor);
	}

	@Override
	public Doctor select(Doctor doctor) {
		return doctorDao.select(doctor);
	}

	@Override
	public List<Doctor> queryAllDoctor() {
		return doctorDao.queryAllDoctor();
	}
}
