package com.future.my.free.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.free.vo.FreeBoardSearchVO;
import com.future.my.free.vo.FreeBoardVO;

@Mapper
public interface IFreeBoardDAO {

	 //전체 건수(조회시 -searchVO)
	public int getTotalRowCount(FreeBoardSearchVO searchVO);
	
	// 자유 게시판 조회  (리턴시 FreeboardVO)
	public ArrayList<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO);
	
	// 게시글 조회
	public FreeBoardVO getBoard(int boNo);
	
	public int insertFreeBoard(FreeBoardVO vo);
	
	public FreeBoardVO updateFreeBoard(int boNo);
	
}
