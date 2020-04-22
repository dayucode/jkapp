package com.dayu.jkapp.server.service;

import com.dayu.jkapp.server.pojo.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DaYu
 * @className Service.java
 * @description TODO
 * @createTime 2020年03月14日
 */
@Service
public interface DoctorService {

	void insert(Doctor doctor);

	Doctor select(Doctor doctor);

	List<Doctor> queryAllDoctor();
}
