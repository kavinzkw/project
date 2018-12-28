package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.ConnectionUtils;
import entity.Car;
import entity.Routes;

public class Headquarters_ManRouteDao {
	public static  ArrayList<Routes> HeadQuerySit(int first,int end){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		Routes s=new Routes();
		int sum=QueryCountnum();
		s.setRid(String.valueOf(sum));
		ArrayList<Routes> sites=new ArrayList<Routes>();
		sites.add(s);
		String sql= "select r.rid,s.sname,q.sname,after_sid from route r,site s,site q where r.start_sid=s.sid and r.end_sid=q.sid limit ?,10 ";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 if(first!=1){
				 first=10*(first-1)+1;
			 }
			 prst.setInt(1, first-1);
			 rs=prst.executeQuery();
			 while(rs.next()){
				Routes route=new Routes();
				route.setRid(rs.getString(1));
				route.setStart_sname(rs.getString(2));
				route.setEnd_sname(rs.getString(3));
				route.setAfter_sid(rs.getString(4));
				sites.add(route);
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
		return sites;
	}
	
	public static  String HeadQuerysname(String sid){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql= "select sname from site where sid=?";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1, sid);
			 rs=prst.executeQuery();
			 if(rs.next()){
				return rs.getString(1);
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
		return null;
	}
	
	public static  boolean Deleteforrows(String rid){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql= "delete from route where rid=?";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1, rid);
			 if(prst.executeUpdate()!=0){
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
	
	public static  int QueryCountnum(){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql= "select count(rid) from route";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 rs=prst.executeQuery();
			 if(rs.next()){
				return rs.getInt(1);
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
		return 0;
	}
}
