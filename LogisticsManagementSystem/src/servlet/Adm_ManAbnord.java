package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import DaoDemo.Adm_ManAbnOrd;
import entity.Abnormal_sheet;

@WebServlet(value="/Adm_ManAbnOrd.do")
public class Adm_ManAbnord extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw=resp.getWriter();
			//ʵ����������
			Adm_ManAbnOrd amao=new Adm_ManAbnOrd();
			String queryoid=req.getParameter("queryoid");
			//���������д������Ϊ����ִ�д�ǰ̨��������
			if(req.getParameter("oid")!=null&&req.getParameter("oid")!=""){
			String oid =req.getParameter("oid");
			String asmoney1=req.getParameter("asmoney");//double��
			String payee=req.getParameter("payee");
			String handle=req.getParameter("handle");
			double asmoney=Double.parseDouble(asmoney1);
			//��ǰ̨��������
			amao.ManAbnOrd(oid, asmoney, payee, handle);
			resp.getWriter().write("msg");
			}
			//����ѯ���������ǰ̨
			ArrayList<Abnormal_sheet> list=new ArrayList<Abnormal_sheet>();
			list=amao.queryAbnord(queryoid);
			Object obj=JSON.toJSON(list);
	    	resp.getWriter().print(obj);
	    	
	    	
	    	
	}
}
