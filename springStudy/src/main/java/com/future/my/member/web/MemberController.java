package com.future.my.member.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.future.my.common.service.CodeService;
import com.future.my.common.vo.CodeVO;
import com.future.my.member.service.MemberService;
import com.future.my.member.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private  BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	CodeService codeService;
	
	//codeList라는 key로 MemberController 에서 연결되는 모든 뷰에서 사용 가능
	@ModelAttribute("comList")
	public ArrayList<CodeVO> getCodeList(){
		return codeService.getCodeList(null);
	}

	@Value("#{util['file.upload.path']}")
	private String CURR_IMAGE_PATH;
	
	@Value("#{util['file.download.path']}")
	private String WEB_PATH;
	
	
	@RequestMapping("/registView")
	public String registView() {
		return "member/registView";
	}
	
	@RequestMapping("/registDo")
	public String registDo(MemberVO vo) {
		vo.setMemPw(passwordEncoder.encode(vo.getMemPw()));
		System.out.println(vo);
		try {
			memberService.registMember(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	@RequestMapping("/loginView")
	public String loginView() {
		return "member/loginView";
	}

	@RequestMapping("/loginDo")
	public String loginDo(MemberVO vo, boolean remember
			            , HttpSession session
			            ,HttpServletResponse response) throws Exception {
		
		System.out.println(vo);
		MemberVO login = memberService.loginMember(vo);
		
		//입력한 비밀번호와 db의 암호화된 비번 비교 일치하면 true 그렇지 않으면 false
		boolean match = passwordEncoder.matches(vo.getMemPw(),login.getMemPw());
		System.out.println(match);
		if(login == null || !match) {
			return "redirect:/loginView";
		}
		
		session.setAttribute("login", login);
		
		if(remember) {
			// 쿠키생성
			Cookie cookie = new Cookie("rememberId", login.getMemId());
			response.addCookie(cookie);
		}else {
			//쿠키 삭제
			//동일한 key값을 가지는 쿠키의 유효시간을 0으로
			Cookie cookie = new Cookie("rememberId", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logoutDo")
	public String logout(HttpSession session) throws Exception {
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		System.out.println(CURR_IMAGE_PATH);
		System.out.println(WEB_PATH);
		
		
		if(session.getAttribute("login")==null) {
			return "redirect:/loginView";
		}
		return "member/mypage";
	}
	@ResponseBody
	@PostMapping("/files/upload")
	public Map<String, Object>uploadFiles(
			HttpSession session, @RequestParam("uploadImage") MultipartFile uploadImage) throws Exception{
		MemberVO vo =(MemberVO) session.getAttribute("login");
		String imgPath = memberService.profileUpload(vo, CURR_IMAGE_PATH, WEB_PATH, uploadImage);
		
		Map<String,Object> map = new HashMap<>();
		//
		map.put("message","success");
		map.put("imagePath",imgPath);
		return map;
	}
	
	@RequestMapping("/test")
	public String test(Model model) {
		
//		ArrayList<CodeVO> comList = codeService.getCodeList(null);
//		model.addAttribute("comList", comList);
		
		return "member/test";
	}
		
	
}
