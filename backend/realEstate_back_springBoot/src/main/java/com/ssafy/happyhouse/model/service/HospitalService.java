package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;

public interface HospitalService {
	public List<HospitalDto> listHospital() throws Exception;
}
