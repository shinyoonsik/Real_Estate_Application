package com.ssafy.happyhouse.model.dao;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberDao {
	void memberInsert(MemberDto mem);
	void memberDelete(String id);
	void memberUpdate(MemberDto mem);
	MemberDto search(String id);
	
}
