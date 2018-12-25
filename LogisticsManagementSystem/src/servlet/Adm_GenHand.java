package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			String ssid =req.getParameter("ssid");
			String hoid =findmaxhoid();
			String uid="u1004";
			add(ssid, uid, hoid);
			resp.sendRedirect("Adm_handOrd_genHandOrdsucessful.html");
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
		}
				return zm+number1;
	}
	public void add(String ssid,String uid,String hoid){
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改   
		int year = c.get(Calendar.YEAR);  
		int month = c.get(Calendar.MONTH)+1;   
		int date = c.get(Calendar.DATE);    
		int hour = c.get(Calendar.HOUR_OF_DAY);   
		int minute = c.get(Calendar.MINUTE);  
		String thedate = year+":"+month+":"+date+":"+hour+":"+minute;
		
		Connection conn;
		PreparedStatement pstm;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
