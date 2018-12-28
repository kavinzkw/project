package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.UserOdr;
import utils.ConnectionUtils;
@WebServlet(value="/adm-g.do")
public class Adm_GenHand extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw = resp.getWriter();
			String ssid =req.getParameter("ssid");
			String hoid =findmaxhoid();
			String uid="u1004";
			add(ssid, uid, hoid);
			updatetime(uid,ssid);
			resp.sendRedirect("Adm_HandOrd_GenHandOrd.html");
	}
	private void updatetime(String uid, String ssid) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		String thedate =sdf.format(date);
		
		Connection conn = null;
		PreparedStatement pstm = null;
		String sql="insert into transport_time values((select tsid from transport where ssid like ?),"
				+ "(select sid from users_wl where uid_wl=?),?)";
		try {
			conn=ConnectionUtils.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(3, thedate);
			pstm.setString(2,uid);
			pstm.setString(1, "%"+ssid+"%");
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public String findmaxhoid() {
		String maxhoid=null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String zm = null;
		String number;
		int number1 = 0;
		try {
			conn=ConnectionUtils.getConnection();
			String sql="select max(hoid) from handover";
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				maxhoid=rs.getString(1);
				zm = maxhoid.replaceAll("[^(a-zA-Z)]","" );  //取出字母
			    number = maxhoid.replaceAll("[^(0-9)]", "");  //取出数字
			    number1=Integer.parseInt(number)+1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			ConnectionUtils.close(conn, rs, psmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ConnectionUtils.close(conn, psmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}          
				return zm+number1;
	}

	public void add(String ssid,String uid,String hoid){  
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		String thedate =sdf.format(date);
		
		Connection conn = null;
		PreparedStatement pstm = null;
		String sql="insert into handover value(?,?,?,?)";
		try {
			conn=ConnectionUtils.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, hoid);
			pstm.setString(2, ssid);
			pstm.setString(3, uid);
			pstm.setString(4, thedate);
			pstm.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			try {
				ConnectionUtils.close(conn, pstm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}      
	}
	
	
}
