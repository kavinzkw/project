package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Site;
import utils.ConnectionUtils;

public class Headquarters_QueryDeliverDao {
	
	public static  ArrayList<Site> HeadQuerySit(int first,int end){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		Site s=new Site();
		int sum=QuerysidSum();
		s.setSum(sum);
		ArrayList<Site> sites=new ArrayList<Site>();
		sites.add(s);
		String sql= "select s.sid,s.sname,s.site_range,s.province,u.username from site s,users_wl u where s.sid=u.sid order by s.sid  limit ?,10 ";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 if(first!=1){
				 first=10*(first-1)+1;
			 }
			 prst.setInt(1, first-1);
			 rs=prst.executeQuery();
			 while(rs.next()){
				Site site=new Site();
				site.setSid(rs.getString(1));
				site.setSanme(rs.getString(2));
				site.setSite_range(rs.getString(3));
				site.setProvince(rs.getString(4));
				site.setUidname(rs.getString(5));
				sites.add(site);
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
	
	public static  int  QuerysidSum(){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		int sum=0;
		String sql="select count(sid) from site";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 rs=prst.executeQuery();
			 if(rs.next()){
				sum=rs.getInt(1);
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
		return sum;
	}	

}
