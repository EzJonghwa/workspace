package com.future.my.common.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.my.common.dao.ICodeDAO;
import com.future.my.common.vo.CodeVO;

@Service
public class CodeService {

	
	// Service 는 Autowired
	@Autowired
	ICodeDAO dao;
	
	public ArrayList<CodeVO> getCodeList(String commParent){
		return dao.getCodeList(commParent);
	}
	
	public void addCode(CodeVO vo) throws Exception {
		int result = dao.addCode(vo);
		if(result ==0) {
			throw new Exception();
			
		}
		
		}
	
	public void deleteCode(String comm_cd) throws Exception {
			int result = dao.deleteCode(comm_cd);
			if(result ==0) {
				throw new Exception();

			}
		}
}
