package com.future.my.chat.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.future.my.chat.service.ChatService;
import com.future.my.chat.vo.ChatVO;
import com.future.my.chat.vo.RoomVO;

@Controller
public class ChatController {

	@Autowired
	ChatService chatService;
	
	@RequestMapping("/chatListView")
	public String chatListView(Model model) {
		ArrayList<RoomVO> roomList = chatService.getRoomList();
		model.addAttribute("roomList", roomList);
		
		return "chat/chatListView";
	}
	
	@RequestMapping("/roomCreateDo")
	public String roomCreateDo(RoomVO roomVO, RedirectAttributes redirect) {
		chatService.createRoom(roomVO);
		System.out.println(roomVO);
		redirect.addAttribute("roomNo", roomVO.getRoomNo());
		return "redirect:/chatView";
	}
	
	@RequestMapping("/chatView")
	public String chatView(Model model, int roomNo) {
		System.out.println(roomNo);
		ArrayList<ChatVO> chatList = chatService.getChatList(roomNo);
		model.addAttribute("roomNo", roomNo);
		model.addAttribute("chatList", chatList);
		return "chat/chatView";
	}
	
	//채팅 메세지 전달
	@MessageMapping("/hello/{roomNo}")
	@SendTo("/subscribe/chat/{roomNo}")
	public ChatVO broadcasting(ChatVO chatVO) {
		// chatlog 기록	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		chatVO.setSendDate(sdf.format(new Date()));
		chatService.insertChat(chatVO);
		// 사람이 들어거나 나가거나 메세지를 보내면
		// DB에 기록하기위함
		return chatVO;
	}
	
}
