package DaoDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.Head_Queryord;
import entity.Abnormal_sheet;
import entity.User_Odr;
import utils.ConnectionUtils;


public class Head_QueryOrd implements Head_Queryord{

	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	@Override
	//��ѯȫ�����ж�����չʾ������ţ��Ļ�վ�㣬�ջ�վ�㣬�ļ�ʱ�䣬����۸�
	public ArrayList<User_Odr> queryord(int currentPage,int row) {
		//���ÿ�ʼ�����ͽ�������
		int startNum=(currentPage-1)*row;
		int endNum=row;
		ArrayList<User_Odr> list=new ArrayList<User_Odr>();
		try {
		 conn=ConnectionUtils.getConnection();
		 //�����ѯ
		 String sql="select a.oid,a.sname,b.sname,a.send_data,a.goods_price from"
		 		+ "(select a.oid,b.sname,a.receiving_siteid,a.send_data,a.goods_price from user_odr a,site b "
		 		+ "where a.send_siteid=b.sid) a,site b where a.receiving_siteid=b.sid order by a.oid limit ?,?";
		 psmt=conn.prepareStatement(sql);
		 psmt.setInt(1, startNum);
		 psmt.setInt(2, endNum);
		 rs=psmt.executeQuery();
		 while(rs.next()){
			 User_Odr uo=new User_Odr();
			 uo.setOid(rs.getString(1));
			 uo.setSend_sitename(rs.getString(2));
			 uo.setRece_sitename(rs.getString(3));
			 uo.setSend_data(rs.getString(4));
			 uo.setPrice(rs.getDouble(5));
			 list.add(uo);
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
	return list;
	}
}
