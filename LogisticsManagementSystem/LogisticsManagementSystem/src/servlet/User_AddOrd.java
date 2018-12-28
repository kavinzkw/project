package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoDemo.User_addord;
@WebServlet(value="/User_Addord.do")
public class User_AddOrd extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		//传一个uid
		String uid="kavin";
		String send_name=req.getParameter("send_name");
		String send_tel=req.getParameter("send_tel");
		String send_address=req.getParameter("send_address");
		String rece_name=req.getParameter("rece_name");
		String rece_tel=req.getParameter("rece_tel");
		String rece_address=req.getParameter("rece_address");
		String send_province=req.getParameter("province");
		String rece_province=req.getParameter("province1");
		String category=req.getParameter("category");//订单类别，普通 / 快件 
		String ord_name=req.getParameter("ord_name");
		String ord_volume1=req.getParameter("ord_volume");//float
		String ord_num1=req.getParameter("ord_num");//int
		String ord_weight1=req.getParameter("ord_weight");//float
		String insured1=req.getParameter("insured");//int
		String pay_method=req.getParameter("pay_method");
		User_addord addord=new User_addord();
		Double ord_volume=Double.parseDouble(ord_volume1);
		int ord_num=Integer.parseInt(ord_num1);
		double ord_weight=Double.parseDouble(ord_weight1);
		int insured=Integer.parseInt(insured1);

//		System.out.println(ord_volume);
//		System.out.println(ord_num);		
//		String send_province=addord.send_siteid(province);
//		String rece_province=addord.rece_siteid(province1);
		boolean flag=addord.addord(uid,send_province, rece_province, send_name, 
				send_tel, send_address, rece_name, rece_tel, rece_address,
				category, ord_weight, ord_volume, ord_name, ord_num, insured, pay_method);
		if(flag) {
			System.out.println("下单成功");
		}else {
			System.out.println("下单失败");
		}
	}
}
