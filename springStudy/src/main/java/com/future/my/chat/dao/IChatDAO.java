package com.future.my.chat.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.future.my.chat.vo.RoomVO;

@Mapper
public interface IChatDAO {
   // 채팅방 리스트
   public ArrayList<RoomVO> getRoomList();
   // 채팅방 생성
   public int createRoom(RoomVO roomVO);
}
