package Dao;

import java.util.Date;

public interface User_Addord {
	//д�붩������(�Ļ�ʡ�ݣ��ջ�ʡ�ݣ��ļ����������绰����ַ���ռ����������绰����ַ��������������������ƣ��������۸��Ƿ�ֵ��֧����ʽ)
	public  boolean addord(String uid,String send_province,String rece_province,String send_name,String send_tel,
			String send_address,String rece_name,String rece_tel,String rece_address,String category,Double weight,
			Double volume,String goods_name,Integer goods_num,Integer goods_insured,
			String pay_method);
	//��ȡ�������
	public String findmaxoid();
	//��ȡ�Ļ�վ����
	public String send_siteid(String send_province);
	//��ȡ�ջ�վ����
	public String rece_siteid(String rece_province);
	//��ȡ�Ļ�����
	public String send_date();

}
