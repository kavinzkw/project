package DaoDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import Dao.Adm_Distribute;
import entity.Abnormal_sheet;
import entity.User_Odr;
import entity.site_Orders;
import utils.ConnectionUtils;

public class Adm_DistributeStock implements Adm_Distribute{
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	//计算出剩余时间
	public long getresttime(String send_data,String category) throws ParseException {
	Date date = null;
	long days=0;
	date = new SimpleDateFormat("yyyyMMddhhmm").parse(send_data);
	Calendar c= Calendar.getInstance();
	c.setTime(date);
	 if("普通".equals(category)){
		 //慢件5天
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
	 return days;
	}
	@Override
	public ArrayList<User_Odr> QueryStock() {
			ArrayList<User_Odr> list=new ArrayList<User_Odr>();
			try {
				conn=ConnectionUtils.getConnection();
				//查找订单号，收货地点，货物类别，货物寄送时间
				String sql="select oid,receiving_ars,category,send_data from user_odr where oid in (select oid from site_orders)";
				psmt=conn.prepareStatement(sql);
				rs=psmt.executeQuery();
				while(rs.next()) {
					User_Odr uo=new User_Odr();
					uo.setOid(rs.getString(1));
					uo.setReceiving_ars(rs.getString(2));
					uo.setCategory(rs.getString(3));
					uo.setSend_data(rs.getString(4));
					//获取剩余时间
					long resttime=getresttime(uo.getSend_data(),uo.getCategory());
					uo.setResttime(resttime);
					list.add(uo);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ConnectionUtils.close(conn, rs, psmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return list;
	}
	
}
