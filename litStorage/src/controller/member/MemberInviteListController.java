package controller.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.InviteRequest;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;

@WebServlet("/member/inviteList.do")
public class MemberInviteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberService service = new MemberServiceLogic();
		/*find inviteRequest list that receiverId is loginId*/
		String receiverId =(String)request.getSession().getAttribute("loginId");
		List<InviteRequest>list=service.findInviteRequestsByReceiverId(receiverId);
		
		/*make new ArrayList and add ir instance which form is I only*/
		List<InviteRequest> inviteList = new ArrayList<>();
		for(InviteRequest ir :list){
			if(ir.getForm().equals("I")){
				inviteList.add(ir);
			}
		}
		
		request.setAttribute("inviteLists", inviteList);
		request.getRequestDispatcher("/views/memberInviteList.jsp").forward(request, response);
	}

}
