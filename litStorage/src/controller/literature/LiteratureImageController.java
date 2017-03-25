package controller.literature;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Literature;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;
import utils.AutoCloser;

@WebServlet("/literature/image.do")
public class LiteratureImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String literatureId = request.getParameter("literatureId");
		
		LiteratureService service = new LiteratureServiceLogic();
		
		Literature literature = service.findLiteratureById(literatureId);
		
		File image = new File(literature.getImagePath());
		
		if(!image.exists()) {
			throw new RuntimeException("no literature image");
		}
		
		InputStream in = new BufferedInputStream(new FileInputStream(image));
		OutputStream out = response.getOutputStream();
		
		byte[] buf = new byte[8096];
		int readByte = 0;
		while((readByte = in.read(buf)) > -1) {
			out.write(buf, 0, readByte);
		}
		
		AutoCloser.close(in,out);
	}
}
