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
//登录验证
@WebServlet(value = "/permission.do")
public class PermissionServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 初始化权限
		int limit = 0;

		// 获取登录账号
		String username = (String) req.getParameter("username");

		// 获取登录密码
		String password = (String) req.getParameter("password");

		try {
			// 得到权限
			Connection conn = ConnectionUtils.getConnection();
			String sql = "select permission from users_wl where username = ? and password = ?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, username);
			psmt.setString(2, password);
			ResultSet rs = psmt.executeQuery();
			// 权限存在则获得权限
			if (rs.next()) {
				limit = rs.getInt(1);
				// 利用session存储用户名
				HttpSession session = req.getSession();
				session.setAttribute("loginName", username);
			}
			// 如果不存在则设置权限为-1
			else {
				limit = -1;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 账号密码错误跳转到登录界面
		if (limit == -1) {
			req.getRequestDispatcher("login.html").forward(req, resp);

		} // 权限为0跳转到
		else if (limit == 0) {
			req.getRequestDispatcher("registy.html").forward(req, resp);
		} // 权限为1跳转到
		else if (limit == 1) {
			req.getRequestDispatcher("User_index.html").forward(req, resp);
		} // 权限为2跳转到
		else if (limit == 2) {
			req.getRequestDispatcher("regist.html").forward(req, resp);
		}

	}

}
