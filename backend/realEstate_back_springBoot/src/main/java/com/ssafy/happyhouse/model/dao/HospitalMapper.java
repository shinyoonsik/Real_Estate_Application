package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.HospitalDto;


public interface HospitalMapper {
	public List<HospitalDto> listHospital() throws SQLException;
}
