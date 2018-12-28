package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ConnectionUtils;

public class User_RetrunDao {
	public static  boolean QueryOrderoid(String oid,String uid){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="select * from user_odr u,userdi s where u.oid=s.oid and u.oid=? and s.uid=?";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1,oid);
			 prst.setString(2,uid);
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
	
	public static  boolean updateuser_retrue(String oid,String uid,String reason){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String sql="insert into user_retrun values(?,?,?)";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1,oid);
			 prst.setString(2,uid);
			 prst.setString(3,reason);
			 if(prst.executeUpdate()>=0){
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
