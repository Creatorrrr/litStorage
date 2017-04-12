package litstorage.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import litstorage.domain.DiscussionPlace;
import litstorage.domain.LitStorage;
import litstorage.domain.Member;
import litstorage.service.facade.DiscussionPlaceService;
import litstorage.service.facade.LitStorageService;

@Controller
@RequestMapping("discussionPlace")
public class DiscussionPlaceController {

	@Autowired
	private DiscussionPlaceService dpService;
	@Autowired
	private LitStorageService lsService;
	
	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public String registerDiscussionPlace(String title, String litStorageId, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		DiscussionPlace d = new DiscussionPlace();
		d.setTitle(title);
		
		Member member= new Member();
		member.setId((String)session.getAttribute("loginId"));
		d.setCreator(member);
		
		LitStorage l= new LitStorage();
		l.setId(litStorageId);
		//l.setId("333");
		d.setLitStorage(l);
		
		boolean onGroupFlag = false;
		System.out.println(litStorageId + "ASfasdfsadfasd a               asfdsasdf\n\n\n\n\n\n\n\\n");
		for(Member m : lsService.findLitStorageById(litStorageId).getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not

		dpService.registerDiscussionPlace(d);
		
		return "redirect:/discussionPlace/list.do?litStorageId=" + litStorageId;
	}
	
	@RequestMapping(value="detail.do", method=RequestMethod.GET)
	public String showDiscussionPlaceDetail(String id, String litStorageId, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		DiscussionPlace discussionPlace = dpService.findDiscussionPlaceById(id);
		
		LitStorage ls = lsService.findLitStorageById(litStorageId);
		model.addAttribute("litStorage", ls);
		
		boolean onGroupFlag = false;
		
		for(Member m : ls.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("discussionPlace", discussionPlace);
		
		return "discussionPlaceDetail";
	}
	
	@RequestMapping(value="list.do", method=RequestMethod.GET)
	public String showDiscussionPlaceList(String litStorageId, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		List<DiscussionPlace> list = dpService.findDiscussionPlacesByLitStorageId(litStorageId);
		
		model.addAttribute("discussionPlaces", list);
		
		//sideNav를 위해 litStorage 재포함
		LitStorage ls = lsService.findLitStorageById(litStorageId);
		
		boolean onGroupFlag = false;
		
		for(Member m : ls.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("litStorage", ls);
		
		return "discussionPlaceList";
	}
	
	@RequestMapping(value="search.do", method=RequestMethod.GET)
	public String searchDiscussionPlace(String searchType, String searchText, String litStorageId, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		List<DiscussionPlace> list = null;
		
		if("title".equals(searchType)){
			list = dpService.findDiscussionPlacesByName(searchText);
		}else if("userId".equals(searchType)){
			list = dpService.findDiscussionPlacesByMemberId(searchText);
		}
		
		model.addAttribute("discussionPlaces", list);
		
		//sideNav를 위해 litStorage 재포함
		LitStorage ls = lsService.findLitStorageById(litStorageId);
		
		boolean onGroupFlag = false;
		
		for(Member m : ls.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("litStorage", ls);
		
		return "discussionPlaceList";
	}
	
	
}
