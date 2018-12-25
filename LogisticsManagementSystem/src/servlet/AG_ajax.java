package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import utils.ConnectionUtils;

@WebServlet(value="/ajax.do")
public class AG_ajax extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String ssid= req.getParameter("ssid");
		PrintWriter pw = resp.getWriter();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(istruessid(ssid)) {
			pw.write("单号可交接！");
		}else {
			pw.write("单号错误！");
		}
	}

	public boolean istruessid(String ssid) {
		Connection conn;
		PreparedStatement pstm;
		String sql= "select ssid from handover";
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.getConnection();
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				if(ssid.equals(rs.getString(1))){

					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;	
	}
}
