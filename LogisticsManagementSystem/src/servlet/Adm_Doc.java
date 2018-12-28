package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


import entity.UserOrder;
import utils.ConnectionUtils;

public class Adm_Doc extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
			String uid ="u1006";
			String oid =req.getParameter("oid");
			String data=req.getParameter("data");
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			ArrayList<UserOrder> list =selectDO(uid, oid, data);
//			if(list.isEmpty()){
//				resp.sendRedirect("Adm_DistributeOrd_ConFirmOrd.html");
//			}else {
			Object obj =JSON.toJSON(list);
			resp.getWriter().write(obj.toString());
//			}	
	}
	public ArrayList<UserOrder> selectDO(String uid,String oid,String data) {
		Connection conn;
		PreparedStatement pstm;
		String sql ="select oid, category,state,send_data from (select oid, category,state,send_data from user_odr "
				+ "where oid in (select oid from site_orders where sid ="
				+ "(select sid from users_wl where uid_wl=?))and state='КЬАн') a where oid like ? and send_data like ? ";
		ResultSet rs;
		UserOrder uo=new UserOrder();
		ArrayList<UserOrder> list = new ArrayList<UserOrder>();
		try {
			conn=ConnectionUtils.getConnection();
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, uid);
			pstm.setString(2, "%"+oid+"%");
			pstm.setString(3, "%"+data+"%");
			rs=pstm.executeQuery();
			while(rs.next()){
				uo.setOid(rs.getString(1));
				uo.setCategory(rs.getString(2));
				uo.setState(rs.getString(3));
				uo.setSenddata(rs.getString(4));
				list.add(uo);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
