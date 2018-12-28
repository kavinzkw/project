package DaoDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Abnormal_sheet;
import entity.User_Odr;
import Dao.Adm_AbmOrdMan;
import Dao.Adm_AbnOrdAdd;
import utils.ConnectionUtils;

public class Adm_ManAbnOrd implements Adm_AbmOrdMan {
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	@Override
	//查询异常订单
	public ArrayList<Abnormal_sheet> queryAbnord(String oid) {
			ArrayList<Abnormal_sheet> abnormal_sheet=new ArrayList<Abnormal_sheet>();
			String sql="select * from Abnormal_sheet where oid like ?";
			try {
				 conn=ConnectionUtils.getConnection();
				 psmt=conn.prepareStatement(sql);
				 psmt.setString(1,"%"+oid+"%");
				 rs=psmt.executeQuery();
				 while(rs.next()){
					 Abnormal_sheet abs=new Abnormal_sheet();
					 abs.setAsid(rs.getString(1));
					 abs.setOid(rs.getString(2));
					 abs.setAsmoney(rs.getDouble(3));
					 abs.setAsdata(rs.getString(4));
					 abs.setPayee(rs.getString(5));
					 abs.setResons(rs.getString(6));
					 abs.setHandle(rs.getString(7));
					 abnormal_sheet.add(abs);
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					ConnectionUtils.close(conn, rs, psmt);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return abnormal_sheet;
		}
	
	@Override
	//异常订单处理:输入用户订单号，赔偿金额，收款人，处理人
	public boolean ManAbnOrd(String oid, double asmoney, String payee, String handle) {
		ArrayList<Abnormal_sheet> abnormal_sheet=new ArrayList<Abnormal_sheet>();
		String sql="update Abnormal_sheet set asmoney=?,payee=?,handle=? where oid =?";
		try {
			 conn=ConnectionUtils.getConnection();
			 psmt=conn.prepareStatement(sql);
			 psmt.setDouble(1, asmoney);
			 psmt.setString(2,payee);
			 psmt.setString(3, handle);
			 psmt.setString(4, oid);
			 int issuccess=psmt.executeUpdate();
			 if(issuccess==1) {
				 return true;
			 }
	}catch (SQLException e) {
		e.printStackTrace();
	}finally{
		try {
			ConnectionUtils.close(conn, rs, psmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
			return false;
		}
}
