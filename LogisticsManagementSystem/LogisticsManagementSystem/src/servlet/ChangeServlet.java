package servlet;

import java.io.IOException;
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

import entity.Order;
import utils.ConnectionUtils;
@WebServlet(value="/change.do")
public class ChangeServlet extends HttpServlet{
	String sid;
	String state;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("utf-8");
	   		resp.setContentType("text/html;charset=utf-8");
	   		sid = req.getParameter("OrdState_id");
	   		state = req.getParameter("statename");
	   		if(upDateState()) {
	   		resp.getWriter().write("msg修改成功");                                                                                              
	}else {
		resp.getWriter().write("msg修改失败");       
	}
	   		}
	public boolean upDateState() {
		try {
	   		  Connection conn = ConnectionUtils.getConnection();
	     		String sql = "update user_odr set state = ? where oid = ?";
	     		PreparedStatement psmt = conn.prepareStatement(sql);
	     		psmt.setString(1, state);
	     		psmt.setString(2,sid);
	     		if(psmt.executeUpdate()!=0) {
	     			return true;
	     		}
			    
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
   
	       } 
	}


