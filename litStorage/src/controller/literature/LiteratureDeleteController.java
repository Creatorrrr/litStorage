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
import domain.LitStorage;
import domain.Literature;
import domain.MemberLitStorage;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/literature/delete.do")
public class LiteratureDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. recevice LiteratureId to episodeList.jsp
		// 2. delete literature

		LiteratureService Lservice = new LiteratureServiceLogic();
		LitStorageService LSservice = new LitStorageServiceLogic();

		String loginId = (String) request.getSession().getAttribute("loginId");
		String deleteLiteratureId = request.getParameter("deleteLiteratureId");

		if (loginId != null && deleteLiteratureId != null) {
			// 작품속 연재글들 삭제
			List<Episode> episodes = Lservice.findEpisodeByLiteratureId(deleteLiteratureId);
			for (Episode episode : episodes) {
				Lservice.removeEpisode(episode.getId());
			}
			// 선택한 작품 삭제
			Lservice.removeLiterature(deleteLiteratureId);

			// 전 작품저장소 화면 이동
			List<MemberLitStorage> memberLitstorage = LSservice.findMemberLitStoragesByMemberId(loginId);

			List<LitStorage> Litstorages = new ArrayList<>();
			LitStorage litstorage = new LitStorage();

			for (MemberLitStorage MLStorage : memberLitstorage) {
				litstorage = LSservice.findLitStorageById(MLStorage.getLitStorage().getId());
			}
			Litstorages.add(litstorage);
			request.setAttribute("litStorages", Litstorages);

			// 나의 작품저장소로 이동
			request.getRequestDispatcher("../views/litStorageMyStorageList.jsp").forward(request, response);

		}
	}
}
