package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import entity.Handover;
import utils.ConnectionUtils;

public class Headquarters_QueryHandoverDao {
	public static  ArrayList<Handover> Querysitesid(){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		ArrayList<Handover> hands=new ArrayList<Handover>();
		String sql="select s.hoid,s.ssid,s.oid,s.username,u.username,s.ho_data from "
				+ "(select h.hoid,h.ssid,s.oid,u.username,s.ss_uid,h.ho_data from handover h,sealing_sheet s,users_wl u where u.uid_wl=h.uid and h.ssid=s.ssid)"
				+ " s,users_wl u where s.ss_uid=u.uid_wl";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 rs=prst.executeQuery();
			 while(rs.next()){
				Handover hand =new Handover();
				hand.setHoid(rs.getString(1));
				hand.setSsid(rs.getString(2));
				hand.setOid(rs.getString(3));
				hand.setUid(rs.getString(4));
				hand.setFuid(rs.getString(5));
				String date=rs.getString(6);
				Date time=null;
				try {
					time = new SimpleDateFormat("yyyyMMddmmhh").parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hand.setHo_data(time.toLocaleString());
				hands.add(hand);
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
		return hands;
	}
	
	public static  ArrayList<Handover> Querysitebysid(String oid){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		ArrayList<Handover> hands=new ArrayList<Handover>();
		String sql="select s.hoid,s.ssid,s.oid,s.username,u.username,s.ho_data from "
				+ "(select h.hoid,h.ssid,s.oid,u.username,s.ss_uid,h.ho_data from handover h,sealing_sheet s,users_wl u where u.uid_wl=h.uid and h.ssid=s.ssid)"
				+ " s,users_wl u where s.ss_uid=u.uid_wl and INSTR(s.oid,?)";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1, oid);
			 rs=prst.executeQuery();
			 while(rs.next()){
				Handover hand =new Handover();
				hand.setHoid(rs.getString(1));
				hand.setSsid(rs.getString(2));
				hand.setOid(rs.getString(3));
				hand.setUid(rs.getString(4));
				hand.setFuid(rs.getString(5));
				String date=rs.getString(6);
				Date time=null;
				try {
					time = new SimpleDateFormat("yyyyMMddmmhh").parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hand.setHo_data(time.toLocaleString());
				hands.add(hand);
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
		return hands;
	}
	
	public static  ArrayList<Handover> Querysitebyuname(String uname){
		Connection conn=null;
		PreparedStatement prst=null;
		ResultSet rs=null;
		ArrayList<Handover> hands=new ArrayList<Handover>();
		String sql="select s.hoid,s.ssid,s.oid,s.username,u.username,s.ho_data from "
				+ "(select h.hoid,h.ssid,s.oid,u.username,s.ss_uid,h.ho_data from handover h,sealing_sheet s,users_wl u where u.uid_wl=h.uid and h.ssid=s.ssid and INSTR(u.username,?))"
				+ " s,users_wl u where s.ss_uid=u.uid_wl";
		try {
			 conn=ConnectionUtils.getConnection();
			 prst=conn.prepareStatement(sql);
			 prst.setString(1, uname);
			 rs=prst.executeQuery();
			 while(rs.next()){
				Handover hand =new Handover();
				hand.setHoid(rs.getString(1));
				hand.setSsid(rs.getString(2));
				hand.setOid(rs.getString(3));
				hand.setUid(rs.getString(4));
				hand.setFuid(rs.getString(5));
				String date=rs.getString(6);
				Date time=null;
				try {
					time = new SimpleDateFormat("yyyyMMddmmhh").parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hand.setHo_data(time.toLocaleString());
				hands.add(hand);
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
		return hands;
	}
}
