package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnectionUtils;

public class Headquarters_addDeliveryDao {
	public static  String Querysitesid(){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sid="";
		String sql="select max(sid) from site";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 rs=prst.executeQuery();
			 if(rs.next()){
				sid=rs.getString(1);
				String zm = sid.replaceAll("[^(a-zA-Z)]","" );  
				String number = sid.replaceAll("[^(0-9)]","");
				int number1=Integer.parseInt(number)+1;
				sid=zm+number1;
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
	public static  boolean Updatasite(String sid,String sname,String csite_range,String province){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="insert into site values(?,?,?,?)";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1, sid);
			 prst.setString(2, sname);
			 prst.setString(3, csite_range);
			 prst.setString(4, province);
			
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
	
	public static  boolean Updatadelivery_price(String site_id,String first_weight,String first_volume,String first_weight_price,String first_volume_price,String second_weight_price,String second_volume_price){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="insert into delivery_price values(?,?,?,?,?,?,?)";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1, site_id);
			 prst.setString(2, first_weight);
			 prst.setString(3, first_volume);
			 prst.setString(4, first_weight_price);
			 prst.setString(5, first_volume_price);
			 prst.setString(6, second_weight_price);
			 prst.setString(7, second_volume_price);
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
}
