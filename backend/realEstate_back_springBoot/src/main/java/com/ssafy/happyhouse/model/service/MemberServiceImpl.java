package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.dao.MemberDao;



@Service
public class MemberServiceImpl implements MemberService {


	@Autowired
	private MemberDao memberRepo;
	
	
	@Override
	public void memberInsert(MemberDto mem) {
		memberRepo.memberInsert(mem);
	}

	@Override
	public void memberDelete(String id) {
		memberRepo.memberDelete(id);
	}

	@Override
	public void memberUpdate(MemberDto mem) {
		memberRepo.memberUpdate(mem);
	}

	@Override
	public MemberDto search(String id) {
		return memberRepo.search(id);
	}

}
