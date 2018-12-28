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
		Adm_ChcekOrd ac = new Adm_ChcekOrd();
		 limit = ac.getPermission(username, password);
		HttpSession session = req.getSession();
		session.setAttribute("loginName", username);
		session.setAttribute("getPermission", limit);
		// 账号密码错误跳转到登录界面
		if (limit == -1) {
			req.getRequestDispatcher("login.html").forward(req, resp);

		} // 权限为0跳转到
		else if (limit == 0) {
			req.getRequestDispatcher("User_index.html").forward(req, resp);
		} // 权限为1跳转到
		else if (limit == 1) {
			req.getRequestDispatcher("Administrators_index.html").forward(req, resp);
		} // 权限为2跳转到
		else if (limit == 2) {
			req.getRequestDispatcher("Headquarters_index.html").forward(req, resp);
		}

	}

}
