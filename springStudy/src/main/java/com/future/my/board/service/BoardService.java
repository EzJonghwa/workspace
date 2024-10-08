package com.future.my.board.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.my.board.dao.IBoardDAO;
import com.future.my.board.vo.BoardVO;
import com.future.my.board.vo.ReplyVO;

@Service
public class BoardService {
		@Autowired
		IBoardDAO dao;
		
		public ArrayList<BoardVO> getBoardList(){
			return dao.getBoardList();
		}
		public BoardVO getBoard(int boardNo) throws Exception {
			BoardVO result = dao.getBoard(boardNo);
			if(result == null) {
				throw new Exception();
			}
			return result;
		}
		public void writeBoard(BoardVO vo) throws Exception {
			int result = dao.writeBoard(vo);
			if( result == 0) {
				throw new Exception();
			}
		}
		public void updateBoard(BoardVO board) throws Exception {
			int result = dao.updateBoard(board);
			if( result == 0) {
				throw new Exception();
			}
		}
		
		// 댓글 리스트 
		public ArrayList<ReplyVO> getReplyList(int boardNo){
			return dao.getReplyList(boardNo);
		}
		// 댓글 조회
		public ReplyVO getReply (String replyNo) {
			return dao.getReply(replyNo);
		}
		// 댓글 작성
		public int writeReply(ReplyVO vo) throws Exception {
			int result = dao.writeReply(vo);
			if(result == 0) {
				throw new Exception();
			}
			return result;
		}
		// 댓글 삭제 
		public void delReply(String replyNo) throws Exception {
			int result = dao.delReply(replyNo);
			if(result == 0) {
				throw new Exception();
			}
		}
		
}
