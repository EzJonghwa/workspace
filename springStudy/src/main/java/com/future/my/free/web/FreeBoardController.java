package com.future.my.free.web;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.future.my.common.service.CodeService;
import com.future.my.common.vo.CodeVO;
import com.future.my.free.vo.FreeBoardSearchVO;

@Controller
@RequestMapping("/free")

public class FreeBoardController {
   
   @Autowired
   CodeService codeService;
   @ModelAttribute("comList")
   public ArrayList<CodeVO> getCodeList(){
       return codeService.getCodeList("BC00");  // 게시판 관련
   }
   
    //자유게시판
    @RequestMapping("/freeList")
    public String freeList(Model model
          //모델 객체 바인딩& 뷰 데이터 전달
      , @ModelAttribute("searchVO") FreeBoardSearchVO searchVO) {
       
      System.out.println(searchVO);
       
       return "free/freeList";
    }

}
