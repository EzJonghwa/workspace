package com.future.my.board.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.board.vo.BoardVO;
import com.future.my.board.vo.ReplyVO;

@Mapper
public interface IBoardDAO {
	
	// 게시글 목록 조회
	public ArrayList<BoardVO> getBoardList();
	// 게시글 상세 조회
	public BoardVO getBoard(int boardNo);
	// 게시글 작성 
	public int writeBoard(BoardVO vo);
	// 게시글 수정
	public int updateBoard(BoardVO board);
	// 댓글 리스트 
	public ArrayList<ReplyVO> getReplyList(int boardNo);
	// 댓글 조회
	public ReplyVO getReply (String replyNo);
	// 댓글 작성
	public int writeReply(ReplyVO vo);
	// 댓글 삭제 
	public int delReply(String replyNo);
	
	
	
	
}
