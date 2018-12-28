package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.crypto.Data;

import entity.Route;
import entity.User_Ord_User;
import utils.ConnectionUtils;

public class User_ScheduleQueryDao {
    //查询单号是否存在
	public static boolean QueryOrderOid(String Oid){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="select * from user_odr where oid=?";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1,Oid);
			 rs=prst.executeQuery();
			 if(rs.next()){
				 return true;
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
		return false;
	   }
	    //查询对应用户的所有订单
		public static ArrayList<String> QueryOrderAllOid(String uid){
			Connection conn=null;
			PreparedStatement prst=null;
			ResultSet rs=null;
			ArrayList<String> user_oid=new ArrayList<String>();
			String sql="select oid from userdi where uid=?";
			try {
				 conn=ConnectionUtils.getConnection();
				 prst=conn.prepareStatement(sql);
				 prst.setString(1,uid);
				 rs=prst.executeQuery();
				 while(rs.next()){
					 user_oid.add(rs.getString(1));
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
			return user_oid;
		}
		  //查询订单的状态和基本信息
				public static User_Ord_User QueryOrderInformation(String oid){
					Connection conn=null;
					PreparedStatement prst=null;
					ResultSet rs=null;
					User_Ord_User usu=new User_Ord_User();
					String sql="select send_name,send_data,receiving_data,category,state,receiving_name from user_odr where oid=?";
					try {
						 conn=ConnectionUtils.getConnection();
						 prst=conn.prepareStatement(sql);
						 prst.setString(1,oid);
						 rs=prst.executeQuery();
						 while(rs.next()){
							 usu.setOid(oid);
							 usu.setSend_name(rs.getString(1));
							 Date data=null;
							 Date data1=null;
							try {
								data = new SimpleDateFormat("yyyyMMddhhmm").parse(rs.getString(2));
								data1 = new SimpleDateFormat("yyyyMMddhhmm").parse(rs.getString(3));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							if(data!=null){
						        usu.setReceiving_data(data.toLocaleString());
							 }
							  else
								 usu.setReceiving_data(""); 
							  if(data1!=null){
						        usu.setReceiving_data(data1.toLocaleString());
							 }
							  else
								 usu.setReceiving_data(""); 
							 usu.setCategory(rs.getString(4));
							 usu.setState(rs.getString(5));
							 usu.setReceiving_name(rs.getString(6));
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
					return usu;
				}
				//模糊查询收货人为*当前用户订单
				public static ArrayList<User_Ord_User> QueryOrderByname(String uid,String name){
					Connection conn=null;
					PreparedStatement prst=null;
					ResultSet rs=null;
					ArrayList<User_Ord_User> uou=new ArrayList<User_Ord_User>();
					String sql="select oid,send_name,send_data,receiving_data,category,state,receiving_name from user_odr where receiving_name like ? and oid in (select oid from userdi where uid=?)";
					try {
						 conn=ConnectionUtils.getConnection();
						 prst=conn.prepareStatement(sql);
						 prst.setString(2,uid);
						 prst.setString(1,"%"+name+"%");
						 rs=prst.executeQuery();
						 while(rs.next()){
							 User_Ord_User usu=new User_Ord_User();
							 usu.setOid(rs.getString(1));
							 usu.setSend_name(rs.getString(2));
							 Date data=null;
							 Date data1=null;
							try {
								data = new SimpleDateFormat("yyyyMMddhhmm").parse(rs.getString(3));
								data1 = new SimpleDateFormat("yyyyMMddhhmm").parse(rs.getString(4));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							   usu.setSend_data(data.toLocaleString());
							  if(data1!=null){
						        usu.setReceiving_data(data1.toLocaleString());
							 }
							  else
								 usu.setReceiving_data("");  
							 usu.setCategory(rs.getString(5));
							 usu.setState(rs.getString(6));
							 usu.setReceiving_name(rs.getString(7));
							 uou.add(usu);
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
					return uou;
				}
				//查询订单的路线图
				public static  ArrayList<Route> QueryOrderRoute(String oid){
					Connection conn=null;
					PreparedStatement prst=null;
					ResultSet rs=null;
					ArrayList<Route> route=new ArrayList<Route>();
					String sql="select sname, tt_data from transport_time,site where "
							+ "transport_time.tt_sid=site.sid and tsid = (select tsid from "
							+ "transport where INSTR(ssid,(select ssid from sealing_sheet where oid=?))) order by tt_data ";
					try {
						 conn=ConnectionUtils.getConnection();
						 prst=conn.prepareStatement(sql);
						 prst.setString(1,oid);
						 rs=prst.executeQuery();
						 while(rs.next()){
							 Route rt=new Route();
							 rt.setSname(rs.getString(1));
							 String time=rs.getString(2);
							 Date date = null;
							try {
								date = new SimpleDateFormat("yyyyMMddhhmm").parse(time);
							} catch (ParseException e) {
								e.printStackTrace();
							} 
							 rt.setData(date.toLocaleString());
							 route.add(rt);
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
					return route;
				}		
				
}
