package com.future.my.free.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.my.free.dao.IFreeBoardDAO;
import com.future.my.free.vo.FreeBoardSearchVO;
import com.future.my.free.vo.FreeBoardVO;

@Service

public class FreeBoardService {

	@Autowired
	IFreeBoardDAO dao;
	
	public ArrayList<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO){
		// 전체 건수 조회
		
		int totalRowCount = dao.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		
		//검색 조건으로 검색된 전체 건수를 기준으로 세팅!
		searchVO.pageSetting();
		return dao.getBoardList(searchVO);
	}
	
	public void getTest(FreeBoardSearchVO searchVO){

		int totalRowCount = dao.getTotalRowCount(searchVO);
		System.out.println(totalRowCount);
		searchVO.pageSetting();
		System.out.println(searchVO);

	}
	
	
}
