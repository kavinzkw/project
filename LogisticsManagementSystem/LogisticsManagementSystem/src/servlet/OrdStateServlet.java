package servlet;
//∂©µ•…Û∫À
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
@WebServlet(value="/OrdState.do")
public class OrdStateServlet extends HttpServlet{
	String sid ;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("utf-8");
	   		resp.setContentType("text/html;charset=utf-8");
	   		 sid = req.getParameter("OrdState_id");
	   		 ArrayList<Order> list = new ArrayList<Order>();
	   		 list = getSite();
	   		 String data = JSON.toJSON(list).toString();
	   		resp.getWriter().write(data);
	    }
	public ArrayList<Order>  getSite(){
 	   ArrayList<Order> list = new ArrayList<Order>();
		try {
		  Connection conn = ConnectionUtils.getConnection();
  		String sql = "select w.oid,y.sname,s.sname,w.send_name,w.send_data,w.state from site s,(select oid,send_siteid,receiving_siteid,send_name,send_tel,send_data,state from user_odr where oid = ?)w ,(select sname,sid from site)y  where s.sid = w.receiving_siteid and y.sid = w.send_siteid;";
  		PreparedStatement psmt = conn.prepareStatement(sql);
  		psmt.setString(1,sid);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Order ord = new Order();
				ord.setoId(rs.getString(1));
				ord.setSePlace(rs.getString(2));
				ord.setRePlace(rs.getString(3));
				ord.setSeName(rs.getString(4));
				ord.setDate(rs.getString(5));
				ord.setState(rs.getString(6));
				list.add(ord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
 	   
    } 
	        
	}


