package com.future.my.free.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.future.my.common.service.CodeService;
import com.future.my.common.vo.CodeVO;
import com.future.my.free.service.FreeBoardService;
import com.future.my.free.vo.FreeBoardSearchVO;
import com.future.my.free.vo.FreeBoardVO;

@Controller
@RequestMapping("/free")

public class FreeBoardController {
   
   @Autowired
   CodeService codeService;
   @ModelAttribute("comList")
   public ArrayList<CodeVO> getCodeList(){
       return codeService.getCodeList("BC00");  // 게시판 관련
   }
   @Autowired
   FreeBoardService freeService;
   
    //자유게시판
    @RequestMapping("/freeList")
    public String freeList(Model model
          //모델 객체 바인딩& 뷰 데이터 전달
      , @ModelAttribute("searchVO") FreeBoardSearchVO searchVO) {
       
      System.out.println(searchVO);
      ArrayList<FreeBoardVO> freeList = freeService.getBoardList(searchVO);
      model.addAttribute("freeList",freeList);
      return "free/freeList";
    }

    // 쟈유 게시판 상세
    @RequestMapping("/freeView")
    public String freeView(Model model, int boNo) throws Exception {
    	System.out.println(boNo);
    	FreeBoardVO free = freeService.getBoard(boNo);
    	model.addAttribute("free",free);
    	return "free/freeView";
    }
    @RequestMapping("/freeForm")
    public String freeForm() {
    	return "free/freeForm";
    }
    
    @PostMapping("/freeBoardWriteDo")
    public String freeBoardWriteDo(FreeBoardVO vo) throws Exception {
    	freeService.insertFreeBoard(vo);
    	return "redirect:/free/freeList";
    	
    }
}
