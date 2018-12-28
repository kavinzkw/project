package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import entity.UserOrder;
import utils.ConnectionUtils;
@WebServlet(value="/changtab.do")
public class Adm_DistributeOdr_F extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			
		//	resp.setContentType("text/html;chaarser=utf-8");
			String uid="u1001";
			String oid =req.getParameter("oid");
			String msg;
			boolean flag=false;
			flag=istrueOid(uid,oid);
			if(flag) {
				change(oid);
				
				resp.getWriter().write("msg:确认完成！请刷新网页后再操作！");
			}else {
				
				 resp.getWriter().write("msg:订单号有误！请确认后再输入！");
			}
			
			
	}
	public void change(String oid) {
		Connection conn;
		PreparedStatement pstm;
		String sql="update user_odr set state='完成'  ,receiving_data=? where oid=?";
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
		String thedate =sdf.format(date);
		try {
			conn=ConnectionUtils.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, thedate);
			pstm.setString(2, oid);
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean istrueOid(String uid,String oid) {
		Connection conn;
		PreparedStatement pstm;
		boolean flag=false;
		String sql ="select oid from user_odr "
				+ "where oid in (select oid from site_orders where sid ="
				+ "(select sid from users_wl where uid_wl=?))and state='受理'  ";
		ResultSet rs;
		try {
			conn=ConnectionUtils.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, uid);
			rs=pstm.executeQuery();
			while(rs.next()){
			if(oid.equals(rs.getString(1))) {
				
					flag=true;
				}else {
					
					flag=false;}
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
		
	}
}
