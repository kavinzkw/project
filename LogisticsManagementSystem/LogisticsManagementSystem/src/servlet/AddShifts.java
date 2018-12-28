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

import entity.Shifts;
import utils.ConnectionUtils;
@WebServlet(value="/add.do")
public class AddShifts extends HttpServlet {
	String rid;
	String cid;
	String date;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		rid = req.getParameter("rid");
		cid = req.getParameter("cid");
		date = req.getParameter("date");
		System.out.println(rid+" "+cid+" "+date);
		if(getCarState(cid)) {
			insertShifts(rid, cid, date);
			System.out.println("添加成功");
		}
		else {
			System.out.println("添加失败");
			PrintWriter pw = resp.getWriter();
			pw.write("添加有误");
		}
	}

	public void insertShifts(String rid, String cid,String date) {

		if (!rid.equals("") && !cid.equals("")) {
			try {
				Connection conn = ConnectionUtils.getConnection();
				String sql = " insert into shifts values(?,?,?,'','') ";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, rid);
				psmt.setString(2, cid);
				psmt.setString(3, date);
				psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	public boolean getCarState(String cid){
		  ArrayList<Shifts> list = new ArrayList<Shifts>();
		  if(!rid.equals("")&&!cid.equals("")) {
			   try {
	   		  Connection conn = ConnectionUtils.getConnection();
	     		String sql = " select * from car where cid = ? and starts = '0' ";
	     		PreparedStatement psmt = conn.prepareStatement(sql);
	     		psmt.setString(1, cid);
				ResultSet rs = psmt.executeQuery();
				if(rs.next()) {	
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		return false;
	  }
	

}
