package Dao;

import java.util.ArrayList;

import entity.Abnormal_sheet;

public interface Adm_AbmOrdMan {//�쳣��������
	//��ѯ�쳣����
	public ArrayList<Abnormal_sheet> queryAbnord(String oid);
	//�����쳣����
	public boolean ManAbnOrd(String oid, double asmoney, String payee,String handle);
}
