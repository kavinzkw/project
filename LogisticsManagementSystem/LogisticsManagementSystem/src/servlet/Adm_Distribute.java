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

import DaoDemo.Adm_DistributeStock;
import entity.Abnormal_sheet;
import entity.User_Odr;

@WebServlet(value="/Distribute_QueryStock.do")
public class Adm_Distribute extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		Adm_DistributeStock ads=new Adm_DistributeStock();
		ArrayList<User_Odr> list=new ArrayList<User_Odr>();
		list=ads.QueryStock();
		Object obj=JSON.toJSON(list);
    	resp.getWriter().print(obj);
	}

}
