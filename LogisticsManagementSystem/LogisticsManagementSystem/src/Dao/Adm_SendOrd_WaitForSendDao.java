package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import utils.ConnectionUtils;
import entity.WaitForSend;

public class Adm_SendOrd_WaitForSendDao {
    //查询出对应站点的所有未发出订单：订单状态为受理
	public static ArrayList WaitForSendQueryAll(){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		ArrayList array=new ArrayList();
		ArrayList<WaitForSend> wfss=new ArrayList<WaitForSend>();
		ArrayList<WaitForSend> wfss_receiving_siteid=new ArrayList<WaitForSend>();
		long days=0L;
		String sql="select u.oid,s.sname,u.category,u.send_data from user_odr u,site s where s.sid=u.receiving_siteid and oid in"
				+ "(select oid from site_orders where sid = (select sid from users_wl where uid_wl='u1001'))and u.state='受理'";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 rs=prst.executeQuery();
			 while(rs.next()){
				 WaitForSend wfs=new WaitForSend();
				 if(wfss_receiving_siteid.size()==0){
					 WaitForSend wfs1=new WaitForSend();
					 wfs1.setSname(rs.getString(2));
					 wfss_receiving_siteid.add(wfs1);
				 }
				 else{
					 boolean s=true;
					 for(int i=0;i<wfss_receiving_siteid.size();i++){
						 if(wfss_receiving_siteid.get(i).getSname().equals( rs.getString(2))){
							 s=false;
							 break;
						 }
					 }
					 if(s){
						 WaitForSend wfs1=new WaitForSend();
						 wfs1.setSname(rs.getString(2));
						 wfss_receiving_siteid.add(wfs1);
					 }
				 }
				 wfs.setOid(rs.getString(1));
				 wfs.setSname(rs.getString(2));
				 wfs.setCategory(rs.getString(3));
				 String time=rs.getString(4);
				 Date date = null;
				 date = new SimpleDateFormat("yyyyMMddhhmm").parse(time);
				 Calendar c= Calendar.getInstance();
				 c.setTime(date);
				 if("普通".equals(wfs.getCategory())){
					 c.add(Calendar.DATE,5);
					 long begin = c.getTimeInMillis();
					 Calendar cl= Calendar.getInstance();
					 long end = cl.getTimeInMillis();
					  days = ( begin-end) / (1000 * 3600 * 24);
				 }
				 else{
					 c.add(Calendar.DATE,5);
					 long begin = c.getTimeInMillis();
					 Calendar cl= Calendar.getInstance();
					 long end = cl.getTimeInMillis();
					 days = ( begin-end) / (1000 * 3600 * 24);
				 }
				 wfs.setSend_data(date.toLocaleString());
				 wfs.setSurplus_data(String.valueOf(days));
				 wfss.add(wfs);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}finally{
			try {
				ConnectionUtils.close(conn, rs, prst);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		array.add(wfss_receiving_siteid);
		array.add(wfss);
		return array;
	   }
	//查询送到对应站点的订单
	public static ArrayList WaitForSendQuery(String sname){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		ArrayList array=new ArrayList();
		ArrayList<WaitForSend> wfss=new ArrayList<WaitForSend>();
		ArrayList<WaitForSend> wfss_receiving_siteid=new ArrayList<WaitForSend>();
		long days=0L;
		String sql="select u.oid,s.sname,u.category,u.send_data from user_odr u,site s where s.sid=u.receiving_siteid and oid in"
				+ "(select oid from site_orders where sid = (select sid from users_wl where uid_wl='u1001'))and u.state='受理' and s.sname=?";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1, sname);
			 rs=prst.executeQuery();
			 while(rs.next()){
				 WaitForSend wfs=new WaitForSend();
				 if(wfss_receiving_siteid.size()==0){
					 WaitForSend wfs1=new WaitForSend();
					 wfs1.setSname(rs.getString(2));
					 wfss_receiving_siteid.add(wfs1);
				 }
				 else{
					 boolean s=true;
					 for(int i=0;i<wfss_receiving_siteid.size();i++){
						 if(wfss_receiving_siteid.get(i).getSname().equals( rs.getString(2))){
							 s=false;
							 break;
						 }
					 }
					 if(s){
						 WaitForSend wfs1=new WaitForSend();
						 wfs1.setSname(rs.getString(2));
						 wfss_receiving_siteid.add(wfs1);
					 }
				 }
				 wfs.setOid(rs.getString(1));
				 wfs.setSname(rs.getString(2));
				 wfs.setCategory(rs.getString(3));
				 String time=rs.getString(4);
				 Date date = null;
				 date = new SimpleDateFormat("yyyyMMddhhmm").parse(time);
				 Calendar c= Calendar.getInstance();
				 c.setTime(date);
				 if("普通".equals(wfs.getCategory())){
					 c.add(Calendar.DATE,5);
					 long begin = c.getTimeInMillis();
					 Calendar cl= Calendar.getInstance();
					 long end = cl.getTimeInMillis();
					  days = ( begin-end) / (1000 * 3600 * 24);
				 }
				 else{
					 c.add(Calendar.DATE,3);
					 long begin = c.getTimeInMillis();
					 Calendar cl= Calendar.getInstance();
					 long end = cl.getTimeInMillis();
					 days = ( begin-end) / (1000 * 3600 * 24);
				 }
				 wfs.setSend_data(date.toLocaleString());
				 wfs.setSurplus_data(String.valueOf(days));
				 wfss.add(wfs);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}finally{
			try {
				ConnectionUtils.close(conn, rs, prst);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		array.add(wfss_receiving_siteid);
		array.add(wfss);
		return array;
	   }
	
	//生成封单，减除站点订单表中订单，生成运输表或加入封单号
	//生成封单
	public static void WaitForSendinsert(String oid,String uid,String yssid){
		Connection conn=null;
		PreparedStatement prst=null;
		String ssid=WaitForSendMAXssid();
		String[] oids=oid.split(",");
		String oids_oid = "";
		 for (int i = 0; i < oids.length-1; i++) {
			 oids_oid+=oids[i]+"-";
		}
		 oids_oid+=oids[oids.length-1];
		 Calendar c= Calendar.getInstance();
		 Date date=c.getTime();
		 String data = new SimpleDateFormat("yyyyMMddhhmm").format(date);
		 String sql="insert into sealing_sheet (ssid,ssdata,oid,ss_uid) values (?,?,?,?)";
		   try {
			conn=ConnectionUtils.getConnection();
			prst=conn.prepareStatement(sql);
			prst.setString(1,ssid);
			prst.setString(2,data);
			prst.setString(3,oids_oid);
			prst.setString(4,uid);
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ConnectionUtils.close(conn,prst);
				String sid=WaitForSendzid(uid);
				WaitForSendupdate("ys10002",ssid);
				WaitForSenddelete(oid,sid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	//返回最大封单号+1
	public static String WaitForSendMAXssid(){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String ssid="";
		String sql="select max(ssid) from sealing_sheet";
		try {
			conn=ConnectionUtils.getConnection();
			prst=conn.prepareStatement(sql);
			rs=prst.executeQuery();
			if(rs.next()){
				ssid=rs.getString(1);
				String zm = ssid.replaceAll("[^(a-zA-Z)]","" );  //取出字母
				String number = ssid.replaceAll("[^(0-9)]","");  //取出数字
				int number1=Integer.parseInt(number)+1;
				ssid=zm+number1;
			}
			else{
				ssid="fd10001";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ConnectionUtils.close(conn, rs, prst);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ssid;
	}
	    //返回站点编号
		public static String WaitForSendzid(String uid){
			Connection conn=null;
			PreparedStatement prst=null;
			ResultSet rs=null;
			String sid="";
			String sql="select sid from users_wl where uid_wl=?";
			try {
				conn=ConnectionUtils.getConnection();
				prst=conn.prepareStatement(sql);
				prst.setString(1, uid);
				rs=prst.executeQuery();
				if(rs.next()){
					sid=rs.getString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					ConnectionUtils.close(conn, rs, prst);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return sid;
		}
	//减除站点订单表中订单
	public static void WaitForSenddelete(String oid,String sid){
		Connection conn=null;
		PreparedStatement prst=null;
		String[] oids=oid.split(",");
		 String sql="delete from site_orders where oid=? and sid=?";
		   try {
			conn=ConnectionUtils.getConnection();
			for(int i=0;i<oids.length;i++){
			prst=conn.prepareStatement(sql);
			prst.setString(1, oids[i]);
			prst.setString(2, sid);
			prst.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ConnectionUtils.close(conn,prst);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	//寻找封单号
	public static void WaitForSendupdate(String yssid,String fdid){
		  Connection conn=null;
		  PreparedStatement prst=null;
		  ResultSet rs=null;
		  String fd=null;
		  String sql="select ssid from transport where tsid=?";
		   try {
			conn=ConnectionUtils.getConnection();
			prst=conn.prepareStatement(sql);
			prst.setString(1, yssid);
			rs=prst.executeQuery();
			if(rs.next()){
				fd=rs.getString(1);
				if(fd!=""&&fd!=null)
				   fd=fd+"-"+fdid;
				else
				   fd=fdid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ConnectionUtils.close(conn,prst);
				WaitForSendToupdate(yssid,fd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
	//插入封单号
	public static void WaitForSendToupdate(String yssid,String fdid){
		  Connection conn=null;
		  PreparedStatement prst=null;
		  String sql="update transport set ssid =? where tsid=?";
		   try {
			conn=ConnectionUtils.getConnection();
			prst=conn.prepareStatement(sql);
			prst.setString(1, fdid);
			prst.setString(2,yssid);
			prst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ConnectionUtils.close(conn,prst);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
