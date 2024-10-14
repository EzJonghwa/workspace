package com.future.my.chat.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.future.my.chat.dao.IChatDAO;
import com.future.my.chat.vo.RoomVO;

@Service
public class ChatService {
   @Autowired
   IChatDAO dao;
   
   public ArrayList<RoomVO> getRoomList() {
      return dao.getRoomList();
   }
   
   public void createRoom(RoomVO roomVO) {
      dao.createRoom(roomVO);
   }
}