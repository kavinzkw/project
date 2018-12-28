package servlet;
//∞‡¥Œ≤È—Ø
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

import entity.Shifts;
import utils.ConnectionUtils;
@WebServlet(value="/shifts.do")
public class ShiftsServlet extends HttpServlet{    
	String rid;
	String cid;
  @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  req.setCharacterEncoding("utf-8");
 		resp.setContentType("text/html;charset=utf-8");
 		rid = req.getParameter("rid");
 		cid = req.getParameter("cid");
 		ArrayList<Shifts> list = new ArrayList<Shifts>();
 		list = getShifts(rid, cid);
 		 String data = JSON.toJSON(list).toString();
   		resp.getWriter().write(data);
 		
}
  public ArrayList<Shifts> getShifts(String rid,String cid){
	  ArrayList<Shifts> list = new ArrayList<Shifts>();
	  if(!rid.equals("")&&!cid.equals("")) {
		   try {
   		  Connection conn = ConnectionUtils.getConnection();
     		String sql = " select rid,cid,departure_time,arrival_time from shifts where rid = ? and cid = ?";
     		PreparedStatement psmt = conn.prepareStatement(sql);
     		psmt.setString(1, rid);
     		psmt.setString(2, cid);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {	
				Shifts shifts = new Shifts();
				shifts.setRid(rs.getString(1));
				shifts.setCid(rs.getString(2));
				shifts.setDtime(rs.getString(3));
				shifts.setAtime(rs.getString(4));
				list.add(shifts);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  else if(!rid.equals("")&&cid.equals("")) {
		  try {
	   		  Connection conn = ConnectionUtils.getConnection();
	     		String sql = " select rid,cid,departure_time,arrival_time from shifts where rid = ?";
	     		PreparedStatement psmt = conn.prepareStatement(sql);
	     		psmt.setString(1, rid);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {	
					Shifts shifts = new Shifts();
					shifts.setRid(rs.getString(1));
					shifts.setCid(rs.getString(2));
					shifts.setDtime(rs.getString(3));
					shifts.setAtime(rs.getString(4));
					list.add(shifts);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  else if(rid.equals("")&&!cid.equals("")) {
		  try {
	   		  Connection conn = ConnectionUtils.getConnection();
	     		String sql = " select rid,cid,departure_time,arrival_time from shifts where cid = ?";
	     		PreparedStatement psmt = conn.prepareStatement(sql);
	     		psmt.setString(1, cid);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {	
					Shifts shifts = new Shifts();
					shifts.setRid(rs.getString(1));
					shifts.setCid(rs.getString(2));
					shifts.setDtime(rs.getString(3));
					shifts.setAtime(rs.getString(4));
					list.add(shifts);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	 
	return list;
	  
  }
}
