 package DaoDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.ChangeShifts;
import entity.Head_Shifts;
import entity.Order;
import entity.Shifts;
import utils.ConnectionUtils;

public class Adm_ChcekOrd {
	
	

	//插入班次
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
	//查询该车是不是处于空闲状态
		public boolean getCarState(String rid,String cid){
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
	 //根据站点ID查找该站点的所有审核订单
	       public ArrayList<Order>  getChangeSite(String sid){
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
	//根据用户名得到站点编号
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
	    				}
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
			        return sid;
	    	   
	       }
	//得到权限值
	        
	    		   public int getPermission(String username,String password) {
	    			   int permission = -1;
	    			   try {
	    					// 得到权限
	    					Connection conn = ConnectionUtils.getConnection();
	    					String sql = "select permission from users_wl where username = ? and password = ?";
	    					PreparedStatement psmt = conn.prepareStatement(sql);
	    					psmt.setString(1, username);
	    					psmt.setString(2, password);
	    					ResultSet rs = psmt.executeQuery();
	    					// 权限存在则获得权限
	    					if (rs.next()) {
	    						permission = rs.getInt(1);
	    						// 利用session存储用户名
	    					}
	    					
	    				} catch (SQLException e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    				}
					return permission;
		   
			       } 
	    		   //更新订单状态
	public boolean upDateState(String state,String sid) {
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
	//通过订单编号得到订单
	public ArrayList<Order>  getSite(String sid){
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
	//详细班次查询
	public ArrayList<Head_Shifts> getMoreShifts(String rid,String cid){
		ArrayList<Head_Shifts> list = new ArrayList<Head_Shifts>();
		  if(!rid.equals("")&&!cid.equals("")) {
			   try {
	   		  Connection conn = ConnectionUtils.getConnection();
	     		String sql = " select site.sname,s.sname,sh.departure_time,sh.arrival_time from (select departure_time,arrival_time,rid from shifts where rid = ? and cid =?)sh,(select rid,start_sid,end_sid from route  )r,site s,(select sname,sid from site )site  where s.sid = r.end_sid and site.sid=r.start_sid and sh.rid = r.rid";
	     		PreparedStatement psmt = conn.prepareStatement(sql);
	     		psmt.setString(1, rid);
	     		psmt.setString(2, cid);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {	
					Head_Shifts hs = new Head_Shifts();
					hs.setDePlace(rs.getString(1));
					hs.setArPlace(rs.getString(2));
					hs.setDeTime(rs.getString(3));
					hs.setArTime(rs.getString(4));
					list.add(hs);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  else if(!rid.equals("")&&cid.equals("")) {
			  try {
		   		  Connection conn = ConnectionUtils.getConnection();
		     		String sql = " select site.sname,s.sname,sh.departure_time,sh.arrival_time from (select departure_time,arrival_time,rid from shifts)sh,(select rid,start_sid,end_sid from route where rid = ?)r,site s,(select sname,sid from site )site  where s.sid = r.end_sid and site.sid=r.start_sid and sh.rid = r.rid";
		     		PreparedStatement psmt = conn.prepareStatement(sql);
		     		psmt.setString(1, rid);
					ResultSet rs = psmt.executeQuery();
					while(rs.next()) {	
						Head_Shifts hs = new Head_Shifts();
						hs.setDePlace(rs.getString(1));
						hs.setArPlace(rs.getString(2));
						hs.setDeTime(rs.getString(3));
						hs.setArTime(rs.getString(4));
						list.add(hs);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  else if(rid.equals("")&&!cid.equals("")) {
			  try {
		   		  Connection conn = ConnectionUtils.getConnection();
		     		String sql = " select site.sname,s.sname,sh.departure_time,sh.arrival_time from (select departure_time,arrival_time,rid from shifts where  cid =?)sh,(select rid,start_sid,end_sid from route  )r,site s,(select sname,sid from site )site  where s.sid = r.end_sid and site.sid=r.start_sid and sh.rid = r.rid";
		     		PreparedStatement psmt = conn.prepareStatement(sql);
		     		psmt.setString(1, cid);
					ResultSet rs = psmt.executeQuery();
					while(rs.next()) {	
						Head_Shifts hs = new Head_Shifts();
						hs.setDePlace(rs.getString(1));
						hs.setArPlace(rs.getString(2));
						hs.setDeTime(rs.getString(3));
						hs.setArTime(rs.getString(4));
						list.add(hs);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		return list;
		 
		
		  
	  }
	//查询没有到达时间的班次
	public ArrayList<ChangeShifts> getNoArTimeShifts(String rid,String cid){
		ArrayList<ChangeShifts> list = new ArrayList<ChangeShifts>();
		  if(!rid.equals("")&&!cid.equals("")) {
			   try {
	   		  Connection conn = ConnectionUtils.getConnection();
	     		String sql = " select sh.rid,sh.cid,site.sname,s.sname,sh.departure_time,sh.arrival_time from (select departure_time,arrival_time,rid,cid from shifts where rid = ? and cid =? and arrival_time = '')sh,(select rid,start_sid,end_sid from route  )r,site s,(select sname,sid from site )site  where s.sid = r.end_sid and site.sid=r.start_sid and sh.rid = r.rid";
	     		PreparedStatement psmt = conn.prepareStatement(sql);
	     		psmt.setString(1, rid);
	     		psmt.setString(2, cid);
				ResultSet rs = psmt.executeQuery();
				while(rs.next()) {	
					ChangeShifts hs = new ChangeShifts();
					hs.setRid(rs.getString(1));
					hs.setCid(rs.getString(2));
					hs.setDplace(rs.getString(3));
					hs.setAplace(rs.getString(4));
					hs.setDtime(rs.getString(5));
					hs.setAtime(rs.getString(6));
					list.add(hs);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
		  else if(!rid.equals("")&&cid.equals("")) {
			  try {
		   		  Connection conn = ConnectionUtils.getConnection();
		     		String sql = " select sh.rid,sh.cid,site.sname,s.sname,sh.departure_time,sh.arrival_time from (select departure_time,arrival_time,rid,cid from shifts where rid = ? and arrival_time = '')sh,(select rid,start_sid,end_sid from route  )r,site s,(select sname,sid from site )site  where s.sid = r.end_sid and site.sid=r.start_sid and sh.rid = r.rid";
		     		PreparedStatement psmt = conn.prepareStatement(sql);
		     		psmt.setString(1, rid);
					ResultSet rs = psmt.executeQuery();
					while(rs.next()) {	
						ChangeShifts hs = new ChangeShifts();
						hs.setRid(rs.getString(1));
						hs.setCid(rs.getString(2));
						hs.setDplace(rs.getString(3));
						hs.setAplace(rs.getString(4));
						hs.setDtime(rs.getString(5));
						hs.setAtime(rs.getString(6));
						list.add(hs);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		  else if(rid.equals("")&&!cid.equals("")) {
			  try {
		   		  Connection conn = ConnectionUtils.getConnection();
		     		String sql = " select sh.rid,sh.cid, site.sname,s.sname,sh.departure_time,sh.arrival_time from (select departure_time,arrival_time,rid,cid from shifts where  cid = ? and arrival_time = '')sh,(select rid,start_sid,end_sid from route  )r,site s,(select sname,sid from site )site  where s.sid = r.end_sid and site.sid=r.start_sid and sh.rid = r.rid";
		     		PreparedStatement psmt = conn.prepareStatement(sql);
		     		psmt.setString(1, cid);
					ResultSet rs = psmt.executeQuery();
					while(rs.next()) {	
						ChangeShifts hs = new ChangeShifts();
						hs.setRid(rs.getString(1));
						hs.setCid(rs.getString(2));
						hs.setDplace(rs.getString(3));
						hs.setAplace(rs.getString(4));
						hs.setDtime(rs.getString(5));
						hs.setAtime(rs.getString(6));
						list.add(hs);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  }
		return list;
		 
		
		  
	  }
	//班次查询
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
