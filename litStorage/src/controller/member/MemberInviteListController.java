package controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.InviteRequest;
import domain.Member;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;

@WebServlet("/member/inviteList.do")
public class MemberInviteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberService service = new MemberServiceLogic();
		Member member = new Member();
		String senderId =request.getParameter("senderId");
		List<InviteRequest>list=service.findInviteRequestsBySenderId(senderId);
		request.setAttribute("inviteLists", list);
		request.getRequestDispatcher("/views/memberInviteList.jsp").forward(request, response);
	}

}
