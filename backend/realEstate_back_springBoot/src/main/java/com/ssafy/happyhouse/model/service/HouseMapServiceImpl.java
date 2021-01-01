package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.villaDto;
import com.ssafy.happyhouse.model.dao.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong);
	}

	@Override   // 추가된 부분!!!!!!!!!!!!!!!!!!!!!!
	public List<HouseInfoDto> getAptPriceByDong(String dong) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptPriceByDong(dong);
	}
	
	@Override
	public List<HouseInfoDto> getAptPrice(int price) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getAptPrice(price);
	}

	@Override
	public List<ClinicDto> getClinic(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getClinic(sido);
	}

	@Override
	public List<HouseInfoDto> getRentPrice(int price) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getRentPrice(price);
	}

	@Override
	public List<villaDto> getHousePrice(int price) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getHousePrice(price);
	}

	@Override
	public List<villaDto> getHouseRentPrice(int price) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getHouseRentPrice(price);
	}
}
