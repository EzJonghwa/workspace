package com.future.my.chat.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.my.chat.dao.IChatDAO;
import com.future.my.chat.vo.ChatVO;
import com.future.my.chat.vo.RoomVO;

@Service
public class ChatService {
	
	@Autowired
	IChatDAO dao;
	
	public ArrayList<RoomVO> getRoomList(){
		return dao.getRoomList();
	}
	
	public void createRoom(RoomVO roomVO) {
		dao.createRoom(roomVO);
	}
	
	
	// 대화 기록
	public ArrayList<ChatVO> getChatList(int roomNo){
		return dao.getChatList(roomNo);
	}
	
	//대화 저장
	public void insertChat(ChatVO vo) {
		dao.insertChat(vo);
	}
}
