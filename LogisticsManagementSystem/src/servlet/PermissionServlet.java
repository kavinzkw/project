package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DaoDemo.Adm_ChcekOrd;
import utils.ConnectionUtils;
//��¼��֤
@WebServlet(value = "/permission.do")
public class PermissionServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// ��ʼ��Ȩ��
		int limit = 0;
		// ��ȡ��¼�˺�
		String username = (String) req.getParameter("username");
		// ��ȡ��¼����
		String password = (String) req.getParameter("password");
		Adm_ChcekOrd ac = new Adm_ChcekOrd();
		 limit = ac.getPermission(username, password);
		HttpSession session = req.getSession();
		session.setAttribute("loginName", username);
		session.setAttribute("getPermission", limit);
		// �˺����������ת����¼����
		if (limit == -1) {
			req.getRequestDispatcher("login.html").forward(req, resp);

		} // Ȩ��Ϊ0��ת��
		else if (limit == 0) {
			req.getRequestDispatcher("User_index.html").forward(req, resp);
		} // Ȩ��Ϊ1��ת��
		else if (limit == 1) {
			req.getRequestDispatcher("Administrators_index.html").forward(req, resp);
		} // Ȩ��Ϊ2��ת��
		else if (limit == 2) {
			req.getRequestDispatcher("Headquarters_index.html").forward(req, resp);
		}

	}

}
