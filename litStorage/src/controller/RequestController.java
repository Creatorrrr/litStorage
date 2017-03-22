package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.InviteRequest;
import domain.Member;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;

@WebServlet("/litStorage/request.do")
public class RequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberService mService = new MemberServiceLogic();

		// litStorageProfile.jsp will give sender, receiver, message
		String receiverId = request.getParameter("receiverId");
		String senderId = (String) request.getSession().getAttribute("loginId");
		String message = request.getParameter("message");

		// make new Members which only have ID(sender, receiver)
		Member sender = new Member();
		Member receiver = new Member();
		sender.setId(senderId);
		receiver.setId(receiverId);

		InviteRequest ir = new InviteRequest();
		ir.setForm("R");
		ir.setMessage(message);
		ir.setReceiver(receiver);
		ir.setSender(sender);

		mService.registerInviteRequest(ir);

		response.sendRedirect(
				request.getContextPath() + "/litStorage/profile.do?id=" 
		+ request.getParameter("litStorageId"));
	}

}
