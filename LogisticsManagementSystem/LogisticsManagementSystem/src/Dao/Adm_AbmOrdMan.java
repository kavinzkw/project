package Dao;

import java.util.ArrayList;

import entity.Abnormal_sheet;

public interface Adm_AbmOrdMan {//异常订单处理
	//查询异常订单
	public ArrayList<Abnormal_sheet> queryAbnord(String oid);
	//处理异常订单
	public boolean ManAbnOrd(String oid, double asmoney, String payee,String handle);
}
