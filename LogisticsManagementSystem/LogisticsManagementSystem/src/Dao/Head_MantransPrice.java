package Dao;

import java.util.ArrayList;

import entity.Transportation_Price;

public interface Head_MantransPrice {//����۸������ѯ���޸�����۸�
	//��ѯ����۸�
	public ArrayList<Transportation_Price> query_ranprice(int currentPage,int row);
	//�޸�����۸�
	public boolean modify_tranprice();

}
