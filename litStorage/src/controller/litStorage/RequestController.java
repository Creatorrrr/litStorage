package controller.litStorage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.InviteRequest;
import domain.LitStorage;
import domain.Member;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;

@WebServlet("/litStorage/request.do")
public class RequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberService mService = new MemberServiceLogic();

		// litStorageProfile.jsp will give sender, receiver, message,
		// litStorageId
		String receiverId = request.getParameter("receiverId");
		String senderId = (String) request.getSession().getAttribute("loginId");
		String message = request.getParameter("message");
		String litStorageId = request.getParameter("litStorageId");

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

		mService.registerInviteRequest(ir);
		
		response.sendRedirect(request.getContextPath() + "/litStorage/profile.do?id=" + litStorageId);
	}

}
