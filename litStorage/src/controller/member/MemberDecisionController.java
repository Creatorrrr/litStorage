package controller.member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.InviteRequest;
import domain.LitStorage;
import domain.Member;
import domain.MemberLitStorage;
import service.facade.LitStorageService;
import service.facade.MemberService;
import service.logic.LitStorageServiceLogic;
import service.logic.MemberServiceLogic;

@WebServlet("/member/decision.do")
public class MemberDecisionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String senderId = request.getParameter("senderId");
		String receiverId = request.getParameter("receiverId");
		String litStorageId = request.getParameter("litStorageId");
		
		/*need 2 service cuz of deleting inviteRequest and registering memberLitStorage*/
		MemberService mService = new MemberServiceLogic();
		LitStorageService lsService = new LitStorageServiceLogic();
		
		Member sender = new Member();
		Member receiver = new Member();
		LitStorage litStorage = new LitStorage();
		sender.setId(senderId);
		receiver.setId(receiverId);
		litStorage.setId(litStorageId);
		//make inviteRequest instance for deleting ir at db
		InviteRequest ir = new InviteRequest();
		ir.setLitStorage(litStorage);
		ir.setReceiver(receiver);
		ir.setSender(sender);
		//if deleting inviteRequest returns true, join logined Member to target litStorage
		if(mService.removeInviteRequest(ir)){
			MemberLitStorage mls = new MemberLitStorage();
			mls.setLitStorage(litStorage);
			mls.setMember(receiver);
			lsService.registerMemberLitStorage(mls);
		}
		//get redirect url from form data
		if(request.getParameter("form").equals("I")){
			response.sendRedirect(request.getContextPath()+"/member/inviteList.do");
		}else{
			response.sendRedirect(request.getContextPath()+"/member/requestList.do");
		}
	}

}
