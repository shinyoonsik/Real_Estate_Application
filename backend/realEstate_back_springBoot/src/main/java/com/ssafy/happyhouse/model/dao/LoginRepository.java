package com.ssafy.happyhouse.model.dao;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface LoginRepository {
	//String login(String id, String pass);
	
	public MemberDto login(MemberDto memberDto) throws SQLException;
}