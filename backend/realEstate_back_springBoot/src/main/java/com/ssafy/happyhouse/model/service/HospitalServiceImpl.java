package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HospitalDto;
import com.ssafy.happyhouse.model.dao.HospitalMapper;

@Service
public class HospitalServiceImpl implements HospitalService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<HospitalDto> listHospital() throws Exception {
		return sqlSession.getMapper(HospitalMapper.class).listHospital();
	}
	
}
