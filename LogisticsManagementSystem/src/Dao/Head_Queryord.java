package Dao;

import java.util.ArrayList;

import DaoDemo.User_addord;
import entity.User_Odr;

public interface Head_Queryord {//总公司查询所有订单
	//查询全部所有订单，展示订单编号，寄货站点，收货站点，寄件时间，运输价格
	public ArrayList<User_Odr> queryord(int currentPage,int row);

}
