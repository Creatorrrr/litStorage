package litstorage.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import litstorage.domain.LitStorage;
import litstorage.domain.Member;
import litstorage.domain.MemberLitStorage;
import litstorage.domain.wrapper.Wrapper;
import litstorage.service.facade.LitStorageService;

@Controller
@RequestMapping("litStorage")
public class LitStorageController {

	@Autowired
	private LitStorageService lsService;
	
	@RequestMapping(value="register.do", method=RequestMethod.GET)
	public String showLitStorageRegisterForm() {
		return "litStorageRegister";
	}

	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public String registerLitStorage(String name, String introduce, HttpSession session) {
		if(name == null || name.isEmpty()) {
			throw new RuntimeException("Please write LitStorage's name");
		}
		
		LitStorage litStorage = new LitStorage();
		
		Member creator = new Member();
		creator.setId((String)session.getAttribute("loginId"));	// Must be logined
		litStorage.setCreator(creator);
		litStorage.setName(name);
		litStorage.setIntroduce(introduce);
		
		if(!lsService.registerLitStorage(litStorage)) {
			throw new RuntimeException("LitStorage register failed");
		}
		
		// go to controller for loading LitStorage info
		return "redirect:/litStorage/myList.do";
	}
	
	@RequestMapping(value="profile.do", method=RequestMethod.GET)
	public String showLitStorageProfile(String id, Model model, HttpSession session) {
		//add LitStorage to request and send to jsp
		LitStorage ls = lsService.findLitStorageById(id);
		
		String loginId = (String)session.getAttribute("loginId");
		
		/*search whether member already join that LitStorage or not.
		 * if already joined or member is creator, flag will set false.
		 * or not, flag will set true. if true is set to request, join request
		 * button will be appear on litStorageProfile.jsp*/
		boolean flag = true;
		if(!loginId.equals(ls.getCreator().getId())){
			for(Member m : ls.getParticipants()){
				if(m.getId().equals(loginId)){
					flag = false;
				}
			}
		}else{
			flag = false;
		}
		
		boolean onGroupFlag = false;
		
		for(Member m : ls.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		
		model.addAttribute("litStorage", ls);
		model.addAttribute("isNotJoined", flag);
		
		return "litStorageProfile";
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.GET)
	public String deleteLitStorage(String litStorageId, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		if(!lsService.removeLitStorage(litStorageId)) {
			throw new RuntimeException("litStorage remove failed");
		}
		
		//find MemberLitStorage by login Id
		List<LitStorage> lsList = new ArrayList<>();

		List<MemberLitStorage> mlsList = lsService.findMemberLitStoragesByMemberId(loginId);
		
		// rearrange List<LitStorage> from List<MemberLitStorag>e
		for(MemberLitStorage mls : mlsList) {
			lsList.add(mls.getLitStorage());
		}
		
		model.addAttribute("litStorages", lsList);
		
		return "litStorageMyStorageList";
	}
	
	@RequestMapping(value="myList.do", method=RequestMethod.GET)
	public String showMyStorageList(Model model, HttpSession session) {
		//get loginId from session
		String id = (String)session.getAttribute("loginId");
			
		if(id == null){
			// if not logined, send to loginPage
			return "redirect:/login.do";
		}
		
		//find MemberLitStorage by login Id
		List<LitStorage> lsList = null;

		List<MemberLitStorage> mlsList = lsService.findMemberLitStoragesByMemberId(id);
		
		// rearrange List<LitStorage> from List<MemberLitStorage>
		if(mlsList.size() > 0) {
			lsList = new ArrayList<>();
			for(MemberLitStorage mls : mlsList) {
				lsList.add(mls.getLitStorage());
			}
		}
		
		model.addAttribute("litStorages", lsList);
		
		return "litStorageMyStorageList";
	}
	
	@RequestMapping(value="allList.do", method=RequestMethod.GET)
	public String showAllStorageList(String pageNum, Model model, HttpSession session) {
		if(pageNum == null || pageNum.isEmpty()) {
			pageNum = "1";
		}
		
		List<LitStorage> litStorageList = lsService.findAllWithPage(pageNum);
		String rows = lsService.findRows();
		
		model.addAttribute("litStorages", litStorageList);
		model.addAttribute("rows", rows);
		
		return "litStorageList";
	}
	
	@RequestMapping(value="search.do", method=RequestMethod.GET)
	public String showLitStroageSearchForm() {
		return "litStorageSearch";
	}
	
	@RequestMapping(value="search.do", method=RequestMethod.POST)
	public void searchLitStroage(String type, String keyword, HttpServletResponse response) {
		response.setContentType("text/xml;charset=utf-8");
		OutputStream out;
		try {
			out = response.getOutputStream();

			JAXBContext context;
			
			List<LitStorage> list = new ArrayList<>();
			if (type.equals("id")) {
				list = lsService.findLitStoragesByMemberId(keyword);
			} else {
				list = lsService.findLitStoragesByName(keyword);
			}
	
			// send memberSearchResult as list to memberInviteSearch.jsp using AJAX
			try {
				context = JAXBContext.newInstance(Wrapper.class, LitStorage.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				QName qname = new QName("litStorages");
				Wrapper<LitStorage> wrapper = new Wrapper<>(list);
				JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname, Wrapper.class, wrapper);
				m.marshal(element, out);
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@RequestMapping(value="memberList.do", method=RequestMethod.GET)
	public String searchLitStroage(String id, Model model, HttpSession session) {
		/*if we call this memberlist method then method type is get and must contain
		 * parameter named "id", which represent litStorage */
		LitStorage ls = lsService.findLitStorageById(id);
		String loginId = (String)session.getAttribute("loginId");
		
		boolean onGroupFlag = false;
		
		for(Member m : ls.getParticipants()) {
			if(m.getId().equals(loginId)) {
				onGroupFlag = true;
			}
		}
		
		model.addAttribute("onGroup", onGroupFlag);	// set user is on group or not
		model.addAttribute("litStorage", ls);
		
		return "joinedMemberList";
	}
}
