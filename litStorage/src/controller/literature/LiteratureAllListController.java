package controller.literature;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Board;
import domain.Literature;
import service.facade.BoardService;
import service.facade.LiteratureService;
import service.logic.BoardServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/literature/allList.do")
public class LiteratureAllListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LiteratureService lService = new LiteratureServiceLogic();
		BoardService bService = new BoardServiceLogic();
		List<Board> bList = bService.findAllBoards();
		
		if (bList.size() == 0) {
			response.sendRedirect(request.getContextPath() +"/views/literatureAllList.jsp");
			return ;
		}
		
		List<Literature> newList = lService.findLiteraturesByGenreOrderById(bList.get(0).getTitle());
		request.setAttribute("literatures", newList);
		for (Board b : bList) {
			if (b.getTitle().equals("신고")) {
				bList.remove(b);
			}
		}
		request.setAttribute("genreList", bList);

		request.getRequestDispatcher("/views/literatureAllList.jsp").forward(request, response);
	}

}
