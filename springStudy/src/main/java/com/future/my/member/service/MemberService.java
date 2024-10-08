package com.future.my.member.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.future.my.member.dao.IMemberDAO;
import com.future.my.member.vo.MemberVO;

@Service // 비즈니스 로직을 처리하는 계층 
public class MemberService {
	// spring 의존성 주입(Dependency Injection)을 자동으로 처리 
	@Autowired
	IMemberDAO dao;
	
	public void registMember(MemberVO vo) throws Exception {
		int result = dao.registMember(vo);
		if(result == 0 ) {
			throw new Exception();
		}
	}
	
	public MemberVO loginMember(MemberVO vo) throws Exception {
		 
		MemberVO user = dao.loginMember(vo);
		System.out.println(user);
		if(user == null) {
			throw new Exception();
		}
		return user; 
	}
	
	//이미지 업로드
	public String profileUpload(MemberVO vo, String uploadDir, String webPath, MultipartFile file) throws Exception {
		
		// 파일명 생성
		String origin = file.getOriginalFilename();
		String uniqe =UUID.randomUUID().toString()+"_"+origin; 
		String dbPath = webPath + uniqe;
		Path filePath = Paths.get(uploadDir, uniqe);
		// 물리적으로 저장

			try {
				Files.copy(file.getInputStream(),filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new Exception("file to save the file",e);
			}
		
		// db 저장
		vo.setProfileImg(dbPath);
		int result = dao.profileUpload(vo);
		if(result ==0) {
			throw new Exception();
		}
		
		return dbPath;
		
	}
}
