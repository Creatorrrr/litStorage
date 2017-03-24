package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Member;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;

@WebServlet("/member/register.do")
public class MemberRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		MemberService service = new MemberServiceLogic();
		Member memberDB = service.findMemberById(id);
		if (password.equals(password2)) {
			if(memberDB == null){//회원 정보가 없으면
				
				Member member = new Member();
				member.setId(id);
				member.setPassword(password);
				member.setName(name);
				member.setEmail(email);

				service.registerMember(member);
				request.setAttribute("message", "회원가입이 되었습니다.");
				request.getRequestDispatcher("/views/index.jsp").forward(request, response);
				return ;
				
			}else if(id.equals(memberDB.getId())){
				
				request.setAttribute("message", "이미 가입된 회원입니다.");
				request.getRequestDispatcher("/views/register.jsp").forward(request, response);
				return ;
			}
			
		} 
		
		//회원 가입에 실패 했을떄
		request.setAttribute("message", "비밀번호가 일치 하지 않습니다.");
		request.getRequestDispatcher("/views/register.jsp").forward(request, response);
		return ;
		
	}

}
