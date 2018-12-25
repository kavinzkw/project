package servlet;
//站点查询
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import entity.Order;
import utils.ConnectionUtils;
@WebServlet(value="/audit.do")
public class AdmCheckOrdServlet extends HttpServlet{
	 
       @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException{
    	   req.setCharacterEncoding("utf-8");
   		resp.setContentType("text/html;charset=utf-8");
   		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("loginName");
   		String sid = getSid(username);
   		 ArrayList<Order> list = new ArrayList<Order>();
   		 list = getSite(sid);
   		 String data = JSON.toJSON(list).toString();
   		resp.getWriter().write(data);
   	    String changid = req.getParameter("changsid");
   	    if(changid!=null) {
   		  if(upDateState(changid)) {
   			resp.getWriter().write("msg审核完成");
   		  }else {
   			resp.getWriter().write("msg审核失败");
   		  }
   	  }
   	     
    }
       //根据站点ID查找该站点的所有审核订单
       public ArrayList<Order>  getSite(String sid){
    	   ArrayList<Order> list = new ArrayList<Order>();
   		try {
   		  Connection conn = ConnectionUtils.getConnection();
     		String sql = "select w.oid,y.sname,s.sname,w.send_name,w.send_tel,w.send_data,w.state from site s, (select oid,send_siteid,receiving_siteid,send_name,send_tel,send_data,state from user_odr where state = '审核')w ,(select sname,sid from site where sid = ?)y  where s.sid = w.receiving_siteid and y.sid = w.send_siteid;";
     		PreparedStatement psmt = conn.prepareStatement(sql);
     		psmt.setString(1,sid);
			ResultSet rs = psmt.executeQuery();
			while(rs.next()) {
				Order ord = new Order();
				ord.setoId(rs.getString(1));
				ord.setSePlace(rs.getString(2));
				ord.setRePlace(rs.getString(3));
				ord.setSeName(rs.getString(4));
				ord.setSetel(rs.getString(5));
				ord.setDate(rs.getString(6));
				ord.setState(rs.getString(7));
				list.add(ord);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
    	   
       } 
       //更改订单状态为受理
       public boolean upDateState(String changid) {
    	   boolean flag = false;
    	   Connection conn = null;
    	   PreparedStatement psmt = null;
		try {
			conn = ConnectionUtils.getConnection();
			if(changid!=null) {
      	    	String [] oid = changid.split(",");
      	    	for(int i = 0;i<oid.length;i++) {
      	   	     		String sql = "update user_odr set state = '受理'  where oid = ?";
      	   	     	    psmt = conn.prepareStatement(sql);
      	   	     		psmt.setString(1,oid[i]);
      	   	     		if(psmt.executeUpdate()>0) {
      	   	     		flag = true;
      	   	     		}
      	    	}
      	    	}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}finally {
			try {
				ConnectionUtils.close(conn, psmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}          
    	          return flag;
      	    		
      	    }
       public String getSid(String username) {
    	       String sid =null;
    	   try {
    	   		  Connection conn = ConnectionUtils.getConnection();
    	     		String sql = "select Sid from users_wl where username = ? ";
    	     		PreparedStatement psmt = conn.prepareStatement(sql);
    	     		psmt.setString(1,username);
    				ResultSet rs = psmt.executeQuery();
    				if(rs.next()) {
    				  sid =  rs.getString(1);
    				  System.out.println(sid);
    				}
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
		        return sid;
    	   
       }
      	      
   		 
       
}
