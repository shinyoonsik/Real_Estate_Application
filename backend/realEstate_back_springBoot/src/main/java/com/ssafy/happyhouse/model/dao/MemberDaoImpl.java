package com.ssafy.happyhouse.model.dao;

import java.nio.channels.SeekableByteChannel;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ssafy.happyhouse.model.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSession session;
	
	private final String ns="com.ssafy.happyhouse.model.dao.MemberDao";
	
	@Override
	public void memberInsert(MemberDto mem) {
		session.insert(ns+".memberInsert", mem);
	}

	@Override
	public void memberDelete(String id) {
		session.delete(ns+".memberDelete", id);
	}

	@Override
	public void memberUpdate(MemberDto mem) {
		System.out.println(mem.getName() + "*************************");
		session.update(ns + ".memberUpdate", mem);
		
	}

	@Override
	public MemberDto search(String id) {
		return session.selectOne(id);
	}

}
