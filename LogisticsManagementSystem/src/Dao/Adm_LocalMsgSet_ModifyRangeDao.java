package Dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import utils.ConnectionUtils;
import entity.User_Ord_User;

public class Adm_LocalMsgSet_ModifyRangeDao {
    public static String ReturnFormsite_range(String uid){
    	
    	Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		String site_range="";
		String sql="select site_range from site where sid=(select sid from users_wl where uid_wl=?)";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1,uid);
			 rs=prst.executeQuery();
			 if(rs.next()){
				 site_range=rs.getString(1);
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
		return site_range;
    	
    }
    
public static String updatesite(String uid,String site_range){
    	
    	Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		
		String sql="updata site set site_range =? and sid =(select sid from users_wl where uid_wl=?)";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1,site_range);
			 prst.setString(2,uid);
			 
			 if(prst.executeUpdate()==1){
				 return "成功";
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
		return "成功";
    	
    }
}
