package controller.literature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Episode;
import domain.Literature;
import domain.MemberLitStorage;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/episode/delete.do")
public class EpisodeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. recevice episodeId to episodeDetail.jsp
		// 2. delete episode

		LiteratureService Lservice = new LiteratureServiceLogic();
		String episodeId = request.getParameter("episodeId");
		String loginId = (String) request.getSession().getAttribute("loginId");
		if (episodeId != null && loginId != null) {
			Episode episode = Lservice.findEpisodeById(episodeId);
			String LiteratureId = episode.getLiterature().getId();
			// 선택한 연재글 삭제
			Lservice.removeEpisode(episodeId);
			// 전 연재글 목록으로 이동
			Literature literature = Lservice.findLiteratureById(LiteratureId);
			List<Literature> literatures = new ArrayList<>();
			literatures.add(literature);
			request.setAttribute("literatures", literatures);


			request.getRequestDispatcher("/views/episodeList.jsp").forward(request, response);
		}
	}

}
