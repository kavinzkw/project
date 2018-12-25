package Dao;

import java.util.ArrayList;

import entity.User_Odr;
import entity.site_Orders;

public interface Adm_Distribute {//库存订单查询
	
	//查询库存表,返回一个订单的
	public ArrayList<User_Odr> QueryStock();
}
