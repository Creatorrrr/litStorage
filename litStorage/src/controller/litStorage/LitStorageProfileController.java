package controller.litStorage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.LitStorage;
import domain.Member;
import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

/**
 * Servlet implementation class LitStorageProfileController
 */
@WebServlet("/litStorage/profile.do")
public class LitStorageProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		LitStorageService service = new LitStorageServiceLogic();
		
		//add LitStorage to request and send to jsp
		LitStorage ls = service.findLitStorageById(id);
		
		String loginId = (String)request.getSession().getAttribute("loginId");
		
		/*search whether member already join that LitStorage or not.
		 * if already joined or member is creator, flag will set false.
		 * or not, flag will set true. if true is set to request, join request
		 * button will be appear on litStorageProfile.jsp*/
		boolean flag = true;
		if(!loginId.equals(ls.getCreator().getId())){
			for(Member m : ls.getParticipants()){
				if(m.getId().equals(loginId)){
					flag = false;
				}
			}
		}else{
			flag = false;
		}
		request.setAttribute("litStorage", ls);
		request.setAttribute("isNotJoined", flag);
		request.getRequestDispatcher("/views/litStorageProfile.jsp").forward(request, response);
	}

}
