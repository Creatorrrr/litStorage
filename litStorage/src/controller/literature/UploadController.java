package controller.literature;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import domain.LitStorage;
import domain.Literature;
import domain.Member;
import service.facade.LiteratureService;
import service.facade.MemberService;
import service.logic.LiteratureServiceLogic;
import service.logic.MemberServiceLogic;

@WebServlet("/literature/registerImg.do")
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		LiteratureService Lservice = new LiteratureServiceLogic();
		MemberService Mservice = new MemberServiceLogic();
		String root = req.getSession().getServletContext().getRealPath("/");

		String pathname = root + "saveImg";

		File f = new File(pathname);
		if (!f.exists()) {
			// 폴더가 존재하지 않으면 폴더 생성
			f.mkdirs();
		}

		String encType = "UTF-8";
		int maxFilesize = 5 * 1024 * 1024;

		// MultipartRequest(request, 저장경로[, 최대허용크기, 인코딩케릭터셋, 동일한 파일명 보호 여부])
		MultipartRequest mr = new MultipartRequest(req, pathname, maxFilesize, encType, new DefaultFileRenamePolicy());

		File file1 = mr.getFile("filename1");
//		String litStorageId = req.getParameter("litStorageId");
//		String loginId = (String)req.getSession().getAttribute("loginId");
		
		req.setAttribute("imagePath", file1.getPath());
		
		req.getRequestDispatcher("../views/literatureRegister.jsp").forward(req, resp);
//		Literature literature = new Literature();
//		LitStorage litStorage = new LitStorage();
//		Member member = new Member();
//		literature.setImagePath(file1.getPath());
//		
//		member =Mservice.findMemberById(loginId);
//		
//		literature.setCreator(member);
//		litStorage.setId(litStorageId);
//		literature.setLitStorage(litStorage);
//		Lservice.registerLiterature(literature);

		// System.out.println(file1.getPath());
		// System.out.println(file1); // 첨부된 파일의 전체경로
		//
		// System.out.println(req.getParameter("title")); // null
		// System.out.println(mr.getParameter("title")); // 입력된 문자

	}

}
