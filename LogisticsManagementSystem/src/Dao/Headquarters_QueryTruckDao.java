package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.ConnectionUtils;
import entity.Car;
import entity.Routes;

public class Headquarters_QueryTruckDao {
	public static  ArrayList<Car> HeadQuerycar(int first,int end){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		Car s=new Car();
		int sum=QueryCountnum();
		s.setCid(String.valueOf(sum));
		ArrayList<Car> sites=new ArrayList<Car>();
		sites.add(s);
		String sql= "select c.cid,c.starts,c.big_traffic,c.big_volume,s.sname from car c,site s where c.place_now=s.sid limit ?,10";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 if(first!=1){
				 first=10*(first-1)+1;
			 }
			 prst.setInt(1, first-1);
			 rs=prst.executeQuery();
			 while(rs.next()){
				Car car=new Car();
				car.setCid(rs.getString(1));
				if(rs.getString(2).equals("0"))
				     car.setStarts("ø’œ–");
				else if(rs.getString(2).equals("1"))
				     car.setStarts("‘À ‰");
				else if(rs.getString(2).equals("2"))
				     car.setStarts("Œ¨ª§");
				car.setBig_traffic(rs.getString(3));
				car.setBig_volume(rs.getString(4));
				car.setPlace_now(rs.getString(5));
				sites.add(car);
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
	
	public static  boolean Deleteforrows(String cid){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql= "delete from car where cid=?";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1, cid);
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
		String sql= "select count(cid) from car";
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
