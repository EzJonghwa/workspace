package com.future.my.member.dao;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.member.vo.MemberVO;
// Mybatis에서 Mapper 인터페이스임을 나타내는 어노테이션 
// SQL 쿼리와 Java객체 간의 매핑을 자동으로 처리함.
@Mapper          //mapper에 namespace와 매핑됨.
public interface IMemberDAO {
	// mapper xml 의 	id와 매핑됨.
	public int registMember(MemberVO vo);
	// 회원 조회
	public MemberVO loginMember(MemberVO vo);
	// 프로필 이미지 수정
	public int profileUpload(MemberVO vo);
}
