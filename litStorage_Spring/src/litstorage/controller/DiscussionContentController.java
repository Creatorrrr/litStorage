package litstorage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import litstorage.domain.DiscussionContent;
import litstorage.domain.DiscussionPlace;
import litstorage.domain.LitStorage;
import litstorage.domain.Member;
import litstorage.service.facade.DiscussionPlaceService;
import litstorage.service.facade.LitStorageService;

@Controller
@RequestMapping("discussionContent")
public class DiscussionContentController {

	@Autowired
	private DiscussionPlaceService dpService;
	@Autowired
	private LitStorageService lsService;
	
	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public String registerDiscussionContent(String discussionContentText, String discussionPlaceId, String litStorageId, HttpSession session) {
		DiscussionContent discussionContent = new DiscussionContent();
		discussionContent.setContent(discussionContentText);
		
		Member member = new Member();
		member.setId((String)session.getAttribute("loginId"));
		discussionContent.setWriter(member);
		
		DiscussionPlace discussionPlace = new DiscussionPlace();
		discussionPlace.setId(discussionPlaceId);
		discussionContent.setDiscussionPlace(discussionPlace);
		
		dpService.registerDiscussionContent(discussionContent);
		
		return "redirect:/discussionPlace/detail.do?id=" + discussionPlaceId + "&litStorageId=" + litStorageId;
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.GET)
	public String deleteDiscussionContent(String pid, String cid, String litStorageId, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		if(!loginId.isEmpty()){//로그인 했을때
			//삭제
			boolean check = dpService.removeDiscussionContent(cid);
			if(check){
				model.addAttribute("message", "삭제 되었습니다.");
			}else{
				model.addAttribute("message", "실패 했습니다.");
			}
			
			//sideNav를 위해 litStorage 재포함
			LitStorage ls = lsService.findLitStorageById(litStorageId);
			model.addAttribute("litStorage", ls);
			
			return "/discussionPlace/detail.do?id=" + pid + "&litStorageId=" + litStorageId;
		} else{
			model.addAttribute("message", "정상적인 접근이 아닙니다.");
			
			return "redirect:/login.do";
		}
	}

}
