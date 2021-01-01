package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {
	void memberInsert(MemberDto mem);
	void memberDelete(String id);
	void memberUpdate(MemberDto mem);
	MemberDto search(String id);
}
