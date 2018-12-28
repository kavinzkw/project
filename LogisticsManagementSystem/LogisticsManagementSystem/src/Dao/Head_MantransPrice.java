package Dao;

import java.util.ArrayList;

import entity.Transportation_Price;

public interface Head_MantransPrice {//运输价格管理，查询和修改运输价格
	//查询运输价格
	public ArrayList<Transportation_Price> query_ranprice(int currentPage,int row);
	//修改运输价格
	public boolean modify_tranprice();

}
