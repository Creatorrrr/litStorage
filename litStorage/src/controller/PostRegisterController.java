package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Post;


@WebServlet("/postRegister.do")
public class PostRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String PostName = request.getParameter("PostName");
		String PostContent= request.getParameter("PostContent");
		String HashTag =request.getParameter("HashTag");
		
//		Post post = new Post(PostName, PostContent, HashTag);
//		
//		PostService service = new PostServiceLogic();
//		
//		service.register(lecture);
		
		response.sendRedirect("list.do");
	
	
	}

}
