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

@WebServlet("/post/modify.do")
public class PostModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		BoardService service = new BoardServiceLogic();
		
		String postId= request.getParameter("postId");
		
		Post post = service.findPostById(postId);
		
		request.setAttribute("post", post);
		request.getRequestDispatcher("/views/postModify.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("postId");
		String title = request.getParameter("title");
		String content=request.getParameter("content");
		String hashTag = request.getParameter("hashtag");
	
		BoardService service = new BoardServiceLogic();
		
		Post post = service.findPostById(id);
		post.setTitle(title);
		post.setContent(content);
		post.setHashTag(hashTag);
		
		if(!service.modifyPost(post)) {
			throw new RuntimeException("post modify failed");
		}
		
		request.setAttribute("post", post);
		request.getRequestDispatcher("/views/postDetail.jsp").forward(request, response);
	}

}
