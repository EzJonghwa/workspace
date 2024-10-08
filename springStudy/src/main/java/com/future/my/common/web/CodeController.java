package com.future.my.common.web;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.future.my.common.service.CodeService;
import com.future.my.common.vo.CodeVO;


@RestController
@RequestMapping("/api")	// 하위 메소드 경로를 다 /api/ 로 시작
		
public class CodeController {
	
	@Autowired
	private CodeService codeService;
	
	@GetMapping("/getSubCodes")
	public ArrayList<CodeVO> getSubCodes(String commParent){	
		ArrayList<CodeVO> codeList = codeService.getCodeList(commParent);
		return codeList;
	}
	// 삽입
	@PostMapping(value="/addCode", produces="application/json; charset=UTF-8")		// Post 방식으로 json 데이터 형식으로 요청 되었을 때
	public ResponseEntity<String> addCode(@RequestBody CodeVO vo){
		System.out.println(vo);
		try {
			codeService.addCode(vo);
			// responseEntity 메세지와 상태코드를 리턴
			return new ResponseEntity<>("저장 되었습니다",HttpStatus.CREATED);
			
		}catch(Exception e) {
			return new ResponseEntity<>("저장 오류",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	// 삭제
	@DeleteMapping(value="/deleteCode/{comm_cd}", produces="application/json; charset=UTF-8")	//Pathvariable 리소스의 경로와 일부만을 전달 받을 때
	public ResponseEntity<String> deleteCode(@PathVariable String comm_cd){
		
		System.out.println(comm_cd);
	try {
		codeService.deleteCode(comm_cd);
		return new ResponseEntity<>("삭제 되었습니다",HttpStatus.OK);
		
	}catch(Exception e) {
		return new ResponseEntity<>("삭제 오류",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	}
}
