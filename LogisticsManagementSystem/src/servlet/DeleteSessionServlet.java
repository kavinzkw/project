package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet(value="/deletesession.do")
public class DeleteSessionServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		session.removeAttribute("loginName");
		session.removeAttribute("getPermission");
		System.out.println(session.getAttribute("loginName"));
		System.out.println(session.getAttribute("getPermission"));
		session.invalidate();
		

	}

}
