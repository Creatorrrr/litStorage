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

@WebServlet("/litStorage/invite.do")
public class InviteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberService mService = new MemberServiceLogic();

		
		// litStorageProfile.jsp will give sender, receiver, message
		String receiverId = request.getParameter("receiverId");
		String senderId = (String) request.getSession().getAttribute("loginId");
		String message = request.getParameter("message");
		LitStorage ls = new LitStorage();
		ls.setId(request.getParameter("litStorageId"));

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

		mService.registerInviteRequest(ir);
		
	}

}
