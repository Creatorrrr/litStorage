package controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Post;
import service.facade.BoardService;
import service.logic.BoardServiceLogic;

@WebServlet("/postModify.do")
public class PostModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		BoardService service = new BoardServiceLogic();
		String id= request.getParameter("id");
		Post post = new Post();
		post=service.findPostById(id);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/views/postModify.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("modifyPostId");
		String title = request.getParameter("modifyPostTitle");
		String content=request.getParameter("modifyPostContent");
		String hashTag = request.getParameter("modifyPostHashtag");
	
		BoardService service = new BoardServiceLogic();
		
		
		Post post = new Post();
		post = service.findPostById(id);
		
		post.setTitle(title);
		post.setContent(content);
		post.setHashTag(hashTag);

//		boolean check = service.modifyPost(post);
//		
//		if(check){
//			response.sendRedirect(request.getContextPath()+"/post/postDetail.do");
//		}
		service.modifyPost(post);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/views/postDetail.jsp").forward(request, response);
	}

}
