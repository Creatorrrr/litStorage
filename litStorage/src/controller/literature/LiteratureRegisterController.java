package controller.literature;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import constants.Constants;
import domain.Board;
import domain.LitStorage;
import domain.Literature;
import domain.Member;
import service.facade.BoardService;
import service.facade.LitStorageService;
import service.facade.LiteratureService;
import service.logic.BoardServiceLogic;
import service.logic.LitStorageServiceLogic;
import service.logic.LiteratureServiceLogic;

@WebServlet("/literature/register.do")
public class LiteratureRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService service = new BoardServiceLogic();
		
		List<Board> boardList = service.findAllBoards();
		String litStorageId = request.getParameter("litStorageId");

		request.setAttribute("boards", boardList);
		request.setAttribute("litStorageId", litStorageId);

		request.getRequestDispatcher("/views/literatureRegister.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LitStorageService lsService = new LitStorageServiceLogic();
		LiteratureService lService = new LiteratureServiceLogic();
		
		String loginId = (String)request.getSession().getAttribute("loginId");
		
		String imagePath = Constants.IMAGE_PATH;

		File dir = new File(imagePath);
		if (!dir.exists()) {
			// 폴더가 존재하지 않으면 폴더 생성
			dir.mkdirs();
		}

		// MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩케릭터셋, 동일한 파일명 보호 여부])
		MultipartRequest mr = new MultipartRequest(request, imagePath, 5 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
		
		String litStorageId = mr.getParameter("litStorageId");
		String name = mr.getParameter("name");
		String genre = mr.getParameter("selectGenre");
		File image = mr.getFile("image");
		String introduce = mr.getParameter("introduce");
		
		Literature literature = new Literature();
		
		Member creator = new Member();
		creator.setId(loginId);
		
		LitStorage litStorage = new LitStorage();
		litStorage.setId(litStorageId);
		
		literature.setName(name);
		literature.setGenre(genre);
		literature.setImagePath(image.getCanonicalPath());
		literature.setIntroduce(introduce);
		literature.setCreator(creator);
		literature.setHits(0);
		literature.setLitStorage(litStorage);
		
		if(!lService.registerLiterature(literature)) {
			throw new RuntimeException("literature register failed");
		}
		
		litStorage = lsService.findLitStorageById(litStorageId);
		
		request.setAttribute("litStorage", litStorage);
		
		request.getRequestDispatcher("/views/literatureList.jsp").forward(request, response);
	}
}
