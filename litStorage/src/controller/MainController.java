package controller;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LiteratureService lService = new LiteratureServiceLogic();
		BoardService bService = new BoardServiceLogic();
		List<Board> bList = bService.findTitles();
		// 게시판 제목(장르) 중 첫번째 항목 장르별로 조회수순, 신작순으로 가져옴.
		// 만약 생성된 게시판이 없으면 바로 index.jsp로 리다이렉트
		if (bList.size() == 0) {
			response.sendRedirect(request.getContextPath() +"/views/index.jsp");
			return ;
		}
		List<Literature> recoList = lService.findLiteraturesByGenreOrderByHits(bList.get(0).getTitle());
		List<Literature> newList = lService.findLiteraturesByGenreOrderById(bList.get(0).getTitle());

		List<Literature> recoCutList = new ArrayList<>();
		List<Literature> newCutList = new ArrayList<>();
		/*
		 * 메인페이지에 작품 6개만 보낼거라 6개만 짜름. 갯수 조정이 필요하면 for문 조건문을 조정하면 됩니다.
		 */
		for (int i = 0; i < 6; i++) {
			try{
				recoCutList.add(recoList.get(i));
			}catch(Exception e){}
			try{
				newCutList.add(newList.get(i));
			}catch(Exception e){}
		}
		
		request.setAttribute("recoLiteratures", recoCutList);
		request.setAttribute("newLiteratures", newCutList);
		
		List<Board> bListRemoved = new ArrayList<>();

		/* 장르 dropdownlist를 만들기 위해 게시판 이름을 싹 보내줌(신고 빼고) */
		for (Board b : bList) {
			if (!b.getTitle().equals("신고")) {
				bListRemoved.add(b);
			}
		}

		request.setAttribute("genreList", bListRemoved);

		request.getRequestDispatcher("/views/index.jsp").forward(request, response);
	}

}
