package DaoDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import entity.Abnormal_sheet;
import entity.User_Odr;
import Dao.Adm_AbnOrdAdd;
import utils.ConnectionUtils;

public class Adm_AddAbnOrd implements Adm_AbnOrdAdd {//异常订单登记
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	//获取异常订单号
	public String findmaxasid() {
		String maxasid=null;
		try {
			conn=ConnectionUtils.getConnection();
			String sql="select max(asid) from abnormal_sheet";
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()) {
				Abnormal_sheet abs=new Abnormal_sheet();
				abs.setAsid(rs.getString(1));
				maxasid=abs.getAsid();
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
		String newasid=null;
		String zm = maxasid.replaceAll("[^(a-zA-Z)]","" );  //取出字母
		String number = maxasid.replaceAll("[^(0-9)]", "");  //取出数字
		int number1=Integer.parseInt(number)+1;
		return zm+number1;
	}
	
	//获取日期
	public String date() {
		Date date=new Date();
		SimpleDateFormat ft =new SimpleDateFormat ("yyyyMMdd");
		return ft.format(date);
	}
	//异常订单登记
	@Override
	public boolean addAbnOrd(String oid, String reasons) {
		try {
			String asid=findmaxasid();
			String asdate=date();
			conn=ConnectionUtils.getConnection();
			//登记异常订单编号，订单编号，原因，时间
			String sql="insert into abnormal_sheet (asid,oid,resons,asdata) values (?,?,?,?)";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, asid);
			psmt.setString(2, oid);
			psmt.setString(3, reasons);
			psmt.setString(4, asdate);
			int a=psmt.executeUpdate();
			if(a==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}try {
			ConnectionUtils.close(conn, rs, psmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
