package litstorage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import litstorage.domain.Member;
import litstorage.service.facade.MemberService;

@Controller
public class UserController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping(value="login.do", method=RequestMethod.GET)
	public String showLoginForm() {
		return "login";
	}
	
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String login(String loginId, String password, Model model, HttpSession session) {
		Member memberDB = memberService.findMemberById(loginId);
		
		//탈퇴
		if(memberDB != null && password.equals(memberDB.getPassword())){//입력한 회원이 있으며 비밀번호가 일치될떄 등록처리
			if(memberDB.getPassword().equals("*DEL*") && memberDB.getId().equals("*DEL*")){//탈퇴한 회원일때
				model.addAttribute("message", "탈퇴한 회원 입니다.");
				return "login";
				
			}else{
				session.setAttribute("loginId", loginId);
				if(loginId.equals("admin")){
					session.setAttribute("isAdmin", true);
				}
				model.addAttribute("message", "로그인 되었습니다.");
				return "redirect:/main.do";
			}
		}else {
			model.addAttribute("message", "로그인 실패 하였습니다.");
			return "login";
		}
	}
	
	@RequestMapping(value="logout.do", method=RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.invalidate();
		
		model.addAttribute("message", "로그아웃 되었습니다.");
		
		return "redirect:/main.do";
	}
}
