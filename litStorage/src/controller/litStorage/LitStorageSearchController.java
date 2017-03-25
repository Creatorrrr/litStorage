package controller.litStorage;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import domain.LitStorage;
import domain.wrapper.Wrapper;
import service.facade.LitStorageService;
import service.logic.LitStorageServiceLogic;

@WebServlet("/litStorage/search.do")
public class LitStorageSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/views/litStorageSearch.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		OutputStream out = response.getOutputStream();

		JAXBContext context;

		LitStorageService service = new LitStorageServiceLogic();
		
		// check option whether selected condition is id or name
		String type = request.getParameter("type");
		// get search keyword
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		List<LitStorage> list = new ArrayList<>();
		if (type.equals("id")) {
			list = service.findLitStoragesByMemberId(keyword);
		} else {
			list = service.findLitStoragesByName(keyword);
		}

		// send memberSearchResult as list to memberInviteSearch.jsp using AJAX
		try {
			context = JAXBContext.newInstance(Wrapper.class, LitStorage.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			QName qname = new QName("litStorages");
			Wrapper<LitStorage> wrapper = new Wrapper<>(list);
			JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname, Wrapper.class, wrapper);
			m.marshal(element, out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
