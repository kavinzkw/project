package Dao;

import java.util.Date;

public interface User_Addord {
	//写入订货单表(寄货省份，收货省份，寄件人姓名，电话，地址，收件人姓名，电话，地址，货物重量，体积，名称，数量，价格，是否保值，支付方式)
	public  boolean addord(String uid,String send_province,String rece_province,String send_name,String send_tel,
			String send_address,String rece_name,String rece_tel,String rece_address,String category,Double weight,
			Double volume,String goods_name,Integer goods_num,Integer goods_insured,
			String pay_method);
	//获取订单编号
	public String findmaxoid();
	//获取寄货站点编号
	public String send_siteid(String send_province);
	//获取收货站点编号
	public String rece_siteid(String rece_province);
	//获取寄货日期
	public String send_date();

}
