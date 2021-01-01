package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.ClinicDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.villaDto;

public interface HouseMapService {
	
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getAptPrice(int price) throws Exception;
	List<HouseInfoDto> getAptPriceByDong(String dong) throws Exception; // 이 부분!!!!! 추가
	List<HouseInfoDto> getRentPrice(int price) throws Exception;
	List<ClinicDto> getClinic(String sido) throws Exception;
	List<villaDto> getHousePrice(int price) throws Exception;
	List<villaDto> getHouseRentPrice(int price) throws Exception;
}
