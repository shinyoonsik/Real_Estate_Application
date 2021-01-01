package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.MemberDto;

public interface LoginService {
	//String login(String id, String pass);
	
	public MemberDto login(MemberDto memberDto) throws SQLException, Exception;
	public String getServerInfo();
}