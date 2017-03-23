package controller.member;

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

import domain.Member;
import domain.wrapper.Wrapper;
import service.facade.MemberService;
import service.logic.MemberServiceLogic;

@WebServlet("/member/search.do")
public class MemberSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/views/memberInviteSearch.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		OutputStream out = response.getOutputStream();
		
		JAXBContext context;
		
		MemberService service = new MemberServiceLogic();
		
//		 check option whether selected condition is id or name
		String type = request.getParameter("type");
//		 get search keyword
		String keyword = request.getParameter("keyword");
		System.out.println(keyword);
		List<Member> list = new ArrayList<>();
		if(type.equals("id")){
			Member member = service.findMemberById(keyword);
			list.add(member);
		}else{
			list = service.findMemberByName(keyword);
		}
		
		// send memberSearchResult as list to memberInviteSearch.jsp using AJAX 
		try {
			context = JAXBContext.newInstance(Wrapper.class,Member.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			QName qname = new QName("members");
			Wrapper<Member> wrapper = new Wrapper<>(list);
			JAXBElement<Wrapper> element = new JAXBElement<Wrapper>(qname,Wrapper.class,wrapper);
			m.marshal(element, out);
			System.out.println("¼º°ø!");
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
