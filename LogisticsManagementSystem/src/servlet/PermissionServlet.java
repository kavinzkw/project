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

		try {
			// �õ�Ȩ��
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select permission from users_wl where username = ? and password = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			ResultSet rs = psmt.executeQuery();
			// Ȩ�޴�������Ȩ��
			if (rs.next()) {
				limit = rs.getInt(1);
				// ����session�洢�û���
				HttpSession session = req.getSession();
				session.setAttribute("loginName", username);
			}
			// ���������������Ȩ��Ϊ-1
			else {
				limit = -1;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �˺����������ת����¼����
		if (limit == -1) {
			req.getRequestDispatcher("login.html").forward(req, resp);

		} // Ȩ��Ϊ0��ת��
		else if (limit == 0) {
			req.getRequestDispatcher("registy.html").forward(req, resp);
		} // Ȩ��Ϊ1��ת��
		else if (limit == 1) {
			req.getRequestDispatcher("User_index.html").forward(req, resp);
		} // Ȩ��Ϊ2��ת��
		else if (limit == 2) {
			req.getRequestDispatcher("regist.html").forward(req, resp);
		}

	}

}
