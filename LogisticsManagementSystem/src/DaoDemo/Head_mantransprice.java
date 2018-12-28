package DaoDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.Head_MantransPrice;
import entity.Transportation_Price;
import utils.ConnectionUtils;

public class Head_mantransprice implements Head_MantransPrice{
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	@Override
	//查询运输费用
	public ArrayList<Transportation_Price> query_ranprice(int currentPage,int row) {
		int startNum=(currentPage-1)*row;
		int endNum=row;
		ArrayList<Transportation_Price> list=new ArrayList<Transportation_Price>();
		try {
			conn=ConnectionUtils.getConnection();
			//连表查询，将站点id缓冲名称
			String sql="select a.sname,b.sname,a.first_weight,a.first_weight_price,"
					+ "a.second_serght_price,a.first_volume,a.first_volume_price,"
					+ "a.second_volume_price from(select b.sname,a.receiving_id,a.first_weight,"
					+ "a.first_weight_price,a.second_serght_price,a.first_volume,a.first_volume_price,"
					+ "a.second_volume_price from transportation_price a,site b where a.send_id=b.sid) "
					+ "a,site b where a.receiving_id=b.sid limit ?,?";
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, startNum);
			psmt.setInt(2, endNum);
			rs=psmt.executeQuery();
			while(rs.next()) {
				Transportation_Price t=new Transportation_Price();
				t.setSend_name(rs.getString(1));
				t.setRece_name(rs.getString(2));
				t.setFirst_weight(rs.getDouble(3));
				t.setFirst_weight_price(rs.getDouble(4));
				t.setSeconds_serght_price(rs.getDouble(5));
				t.setFirst_volume(rs.getDouble(6));
				t.setFirst_volume_price(rs.getDouble(7));
				t.setSeconds_volume_price(rs.getDouble(8));
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	@Override
	//修改运输费用
	public boolean modify_tranprice() {
		try {
			conn=ConnectionUtils.getConnection();
			String sql="";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
