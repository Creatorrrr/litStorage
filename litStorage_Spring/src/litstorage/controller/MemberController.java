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
import org.springframework.web.bind.annotation.ResponseBody;

import litstorage.domain.InviteRequest;
import litstorage.domain.LitStorage;
import litstorage.domain.Member;
import litstorage.domain.MemberLitStorage;
import litstorage.domain.wrapper.Wrapper;
import litstorage.service.facade.LitStorageService;
import litstorage.service.facade.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private LitStorageService lsService;
	
	@RequestMapping(value="register.do", method=RequestMethod.POST)
	public String registerMember(String id, String password, String name, String email, Model model) {
		Member memberDB = memberService.findMemberById(id);
		
		if(memberDB == null){//회원 정보가 없으면
			Member member = new Member();
			member.setId(id);
			member.setPassword(password);
			member.setName(name);
			member.setEmail(email);

			memberService.registerMember(member);
			
			model.addAttribute("message", "회원가입이 되었습니다.");
			
			return "main";
		} else if(id.equals(memberDB.getId())){
			model.addAttribute("message", "이미 가입된 회원입니다.");
			
			return "register";
		} else {	//회원 가입에 실패 했을떄
			model.addAttribute("message", "비밀번호가 일치 하지 않습니다.");
			
			return "register";
		}
	}
	
	@RequestMapping(value="detail.do", method=RequestMethod.GET)
	public String showMemberDetail(Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		Member memberDB = memberService.findMemberById(loginId);
		memberDB.setPassword("");
		
		model.addAttribute("member", memberDB);
		
		return "memberDetail";
	}
	
	@RequestMapping(value="modify.do", method=RequestMethod.GET)
	public String showMemberModifyForm(Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		Member memberDB = memberService.findMemberById(loginId);
		memberDB.setPassword("");
		
		model.addAttribute("member", memberDB);
		
		return "memberModify";
	}
	
	@RequestMapping(value="modify.do", method=RequestMethod.POST)
	public String modifyMember(String name, String password, String email, Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		Member member = new Member();
		member.setId(loginId);
		member.setName(name);
		member.setPassword(password);
		member.setEmail(email);
		
		boolean check = memberService.modifyMember(member);
		if(check){
			model.addAttribute("member", member);
			
			return "redirect:/member/detail.do";
		}else{
			model.addAttribute("message", "회원정보 수정 되지 않았습니다.");
			
			return "/member/modify.do";
		}
	}
	
	@RequestMapping(value="delete.do", method=RequestMethod.GET)
	public String deleteMember(Model model, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		if(loginId == null || loginId.isEmpty()){
			//error
		} else{
			boolean check= memberService.removeMember(loginId);
			if(check){
				model.addAttribute("message", "탈퇴 처리가 되었습니다.");
				return "redirect:/logout.do";
			}
		}
		
		return "redirect:/member/modify.do";
	}
	
	@RequestMapping(value="inviteList.do", method=RequestMethod.GET)
	public String showInviteList(Model model, HttpSession session) {
		/*find inviteRequest list that receiverId is loginId*/
		String receiverId =(String)session.getAttribute("loginId");
		
		List<InviteRequest>list = memberService.findInviteRequestsByReceiverId(receiverId);
		
		/*make new ArrayList and add ir instance which form is I only*/
		List<InviteRequest> inviteList = new ArrayList<>();
		for(InviteRequest ir :list){
			if(ir.getForm().equals("I")){
				inviteList.add(ir);
			}
		}
		
		model.addAttribute("inviteLists", inviteList);
		
		return "memberInviteList";
	}
	
	@RequestMapping(value="requestList.do", method=RequestMethod.GET)
	public String showRequestList(Model model, HttpSession session) {
		/*find inviteRequest list that receiverId is loginId*/
		String receiverId =(String)session.getAttribute("loginId");
		
		List<InviteRequest>list=memberService.findInviteRequestsByReceiverId(receiverId);
		
		/*make new ArrayList and add ir instance which form is R only*/
		List<InviteRequest> inviteList = new ArrayList<>();
		for(InviteRequest ir :list){
			if(ir.getForm().equals("R")){
				inviteList.add(ir);
			}
		}
		
		model.addAttribute("inviteLists", inviteList);
		return "memberRequestList";
	}
	
	@RequestMapping(value="search.do", method=RequestMethod.GET)
	public String showMemberSearchForm(String litStorageId, Model model) {
		/*
		 * find litStorage info from prev page. if litStorageId exist, set
		 * LitStorage to inviteMemberSearch Page. if don't contain LitStorge
		 * info then go to member search page for admin user.
		 */
		if (litStorageId != null) {
			LitStorage ls = lsService.findLitStorageById(litStorageId);
			
			model.addAttribute("litStorage", ls);
			
			return "memberInviteSearch";
		} else {
			// this tab is for admin user. if you made search page for admin,
			// write code for redirect.
			return "redirect:/main.do";	// temporary direction
		}
	}
	
	@RequestMapping(value="search.do", method=RequestMethod.POST)
	public void searchMember(String type, String keyword, HttpSession session, HttpServletResponse response) {
		response.setContentType("text/xml;charset=utf-8");
		OutputStream out;
		try {
			out = response.getOutputStream();

			JAXBContext context;
	
			List<Member> list = new ArrayList<>();
			if (type.equals("id")) {
				Member member = memberService.findMemberById(keyword);
				list.add(member);
			} else {
				list = memberService.findMemberByName(keyword);
				// delete from list if result equals me
				for (Member m : list) {
					if (m.getId().equals((String)session.getAttribute("loginId"))) {
						list.remove(m);
					}
				}
			}
	
			// send memberSearchResult as list to memberInviteSearch.jsp using AJAX
			try {
				context = JAXBContext.newInstance(Wrapper.class, Member.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				QName qname = new QName("members");
				Wrapper<Member> wrapper = new Wrapper<>(list);
				JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname, Wrapper.class, wrapper);
				m.marshal(element, out);
			} catch (JAXBException e) {
				e.printStackTrace();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@ResponseBody
	@RequestMapping(value="decision.do", method=RequestMethod.GET)
	public void decideMemberJoin(String senderId, String receiverId, String litStorageId, String form) {
		Member sender = new Member();
		sender.setId(senderId);
		
		Member receiver = new Member();
		receiver.setId(receiverId);
		
		LitStorage litStorage = new LitStorage();
		litStorage.setId(litStorageId);
		
		//make inviteRequest instance for deleting ir at db
		InviteRequest ir = new InviteRequest();
		ir.setLitStorage(litStorage);
		ir.setReceiver(receiver);
		ir.setSender(sender);
		//if deleting inviteRequest returns true, join logined Member to target litStorage
		if(memberService.removeInviteRequest(ir)){
			MemberLitStorage mls = new MemberLitStorage();
			mls.setLitStorage(litStorage);
			if(form.equals("I")) {
				mls.setMember(receiver);
			} else if(form.equals("R")) {
				mls.setMember(sender);
			}
			lsService.registerMemberLitStorage(mls);
		}
	}
	
	@RequestMapping(value="invite.do", method=RequestMethod.POST)
	public void inviteMember(String receiverId, String message, String litStorageId, HttpSession session) {
		String senderId = (String)session.getAttribute("loginId");
		
		LitStorage ls = new LitStorage();
		ls.setId(litStorageId);

		// make new Members which only have ID(sender, receiver)
		Member sender = new Member();
		Member receiver = new Member();
		sender.setId(senderId);
		receiver.setId(receiverId);

		InviteRequest ir = new InviteRequest();
		ir.setForm("I");
		ir.setMessage(message);
		ir.setReceiver(receiver);
		ir.setSender(sender);
		ir.setLitStorage(ls);

		memberService.registerInviteRequest(ir);
	}
	
	@RequestMapping(value="request.do", method=RequestMethod.POST)
	public String requestMember(String receiverId, String message, String litStorageId, HttpSession session) {
		// litStorageProfile.jsp will give sender, receiver, message,
		// litStorageId
		String senderId = (String)session.getAttribute("loginId");

		// make new Members which only have ID(sender, receiver)
		Member sender = new Member();
		Member receiver = new Member();
		sender.setId(senderId);
		receiver.setId(receiverId);
		
		//make LitStorage Which only have ID
		LitStorage ls = new LitStorage();
		ls.setId(litStorageId);

		InviteRequest ir = new InviteRequest();
		ir.setForm("R");
		ir.setMessage(message);
		ir.setReceiver(receiver);
		ir.setSender(sender);
		ir.setLitStorage(ls);

		if(!memberService.registerInviteRequest(ir)) {
			throw new RuntimeException("invite request failed");
		}
		
		return "redirect:/litStorage/profile.do?id=" + litStorageId;
	}
}
