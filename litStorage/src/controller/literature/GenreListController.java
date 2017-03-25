package controller.literature;

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

import domain.Literature;
import domain.wrapper.Wrapper;
import service.facade.LiteratureService;
import service.logic.LiteratureServiceLogic;

@WebServlet("/genreList.do")
public class GenreListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		OutputStream out = response.getOutputStream();
		
		JAXBContext context;

		String type = request.getParameter("type");
		String genre = request.getParameter("genre");

		LiteratureService service = new LiteratureServiceLogic();

		List<Literature> list = new ArrayList<>();
		if (type.equals("newGenre")) {
			list = service.findLiteraturesByGenreOrderById(genre);
		} else {
			list = service.findLiteraturesByGenreOrderByHits(genre);
		}

		if (request.getParameter("from").equals("main")) {
			List<Literature> cutList = new ArrayList<>();
			for (int i = 0; i < 6; i++) {
				try {
					cutList.add(list.get(i));
				} catch (Exception e) {}
				
			}
			list.clear();
			list = cutList;
		}

		try {
			context = JAXBContext.newInstance(Wrapper.class, Literature.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			QName qname = new QName("literatures");
			Wrapper<Literature> wrapper = new Wrapper<>(list);
			JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname, Wrapper.class, wrapper);
			m.marshal(element, out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
