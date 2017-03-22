package controller;

import java.io.IOException;
import java.io.OutputStream;
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

import domain.Member;
import domain.wrapper.Wrapper;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;

@WebServlet("/member/search.do")
public class MemberSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OutputStream out = response.getOutputStream();
		
		JAXBContext context;
		
		MemberService service = new MemberServiceLogic();
		//must check selected option(id, name) but not implemented yet.
		//will develop tomorrow
		//id is testCase
		String id = request.getParameter("id");
		Member member = service.findMemberById(id);
		//List<Member> list = service.findMemberByName(name);
		
		//this is wrong.
		try {
			context = JAXBContext.newInstance(Wrapper.class,Member.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			QName qname = new QName("members");
			Wrapper<Member> wrapper = new Wrapper<>(member);
			JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname,Wrapper.class,wrapper);
			m.marshal(element, out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
