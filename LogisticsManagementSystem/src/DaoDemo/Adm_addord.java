package DaoDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Dao.User_Addord;
import entity.User_Odr;
import utils.ConnectionUtils;

public class Adm_addord implements User_Addord{
	Connection conn=null;
	PreparedStatement psmt=null;
	ResultSet rs=null;
	
	//获取订单编号
	public String findmaxoid() {
		String maxoid=null;
		try {
			conn=ConnectionUtils.getConnection();
			String sql="select max(oid) from user_odr";
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				User_Odr u=new User_Odr();
				u.setOid(rs.getString(1));
				maxoid=u.getOid();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ConnectionUtils.close(conn, rs, psmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String newoid=null;
		String zm = maxoid.replaceAll("[^(a-zA-Z)]","" );  //取出字母
		String number = maxoid.replaceAll("[^(0-9)]", "");  //取出数字
		int number1=Integer.parseInt(number)+1;
		return zm+number1;
	}
	@Override
	//获取寄货站点编号
	public String send_siteid(String send_province) {
		String siteid = null;
		try {
			conn=ConnectionUtils.getConnection();
			String sql="select sid from site where province=?";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, send_province);
			rs=psmt.executeQuery();
			if(rs.next()) {
				siteid=rs.getString(1);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			ConnectionUtils.close(conn, rs, psmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return siteid;
	}
	@Override
	//获取收货站点编号
	public String rece_siteid(String rece_province) {
		String receid = null;
		try {
			conn=ConnectionUtils.getConnection();
			String sql="select sid from site where province=?";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, rece_province);
			rs=psmt.executeQuery();
			if(rs.next()) {
				receid=rs.getString(1);	
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			ConnectionUtils.close(conn, rs, psmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return receid;
	}
	@Override
	//获取日期
	public String send_date() {
		Date date=new Date();
		SimpleDateFormat ft =new SimpleDateFormat ("yyyyMMddhhmm");
		return ft.format(date);
	}
	
	@Override
	//插入订单
	public boolean addord(String send_province,String rece_province,String send_name,String send_tel,String send_address,
			String rece_name,String rece_tel,String rece_address,String category,Double weight,Double volume,
			String goods_name,Integer goods_num,Integer goods_insured,String pay_method) {
		String oid=findmaxoid();//订单编号
		String send_siteid=send_siteid(send_province);//寄件站点编号
		String rece_siteid=rece_siteid(rece_province);//收货站点
		System.out.println(send_siteid);
		System.out.println(rece_siteid);
		String date=send_date();//寄货日期
		try {
			conn=ConnectionUtils.getConnection();
			String sql="insert into user_odr "
					+ "(oid,send_siteid,receiving_siteid,send_data,category,state,"
					+ "send_name,send_tel,send_address,receiving_name,receiving_tel,"
					+ "receiving_ars,weigth,volume,goods_name,goods_num,goods_price,goods_insured,pay_method)"
					+ "values (?,?,?,?,?,'受理',?,?,?,?,?,?,?,?,?,?,?,?,?)";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, oid);
			psmt.setString(2, send_siteid);
			psmt.setString(3, rece_siteid);
			psmt.setString(4, date);
			psmt.setString(5, category);
			psmt.setString(6, send_name);
			psmt.setString(7, send_tel);
			psmt.setString(8, send_address);
			psmt.setString(9, rece_name);
			psmt.setString(10, rece_tel);
			psmt.setString(11, rece_address);
			psmt.setDouble(12, weight);
			psmt.setDouble(13, volume);
			psmt.setString(14, goods_name);
			psmt.setInt(15,goods_num);
			psmt.setInt(16, 0);//运输价格
			psmt.setInt(17,goods_insured);
			psmt.setString(18,pay_method);
			int issuccess=psmt.executeUpdate();
			if(issuccess==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			ConnectionUtils.close(conn, rs, psmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}

//	public static void main(String[] args) {
//	Addord a=new Addord();
//	System.out.println(a.send_date());
//	}


