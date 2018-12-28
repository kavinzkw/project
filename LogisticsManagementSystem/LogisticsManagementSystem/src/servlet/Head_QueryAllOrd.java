package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import DaoDemo.Adm_DistributeStock;
import DaoDemo.Head_QueryOrd;
import entity.User_Odr;

@WebServlet(value="/Head_queryord.do")
public class Head_QueryAllOrd extends HttpServlet{

		@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("application/json;charset=UTF-8");
			
			
			//获取当前码索引和个数每页展示条数
			String currentPage1=req.getParameter("pageIndex");
			String row1=req.getParameter("pageSize");
			int currentPage=Integer.parseInt(currentPage1);
			int row=Integer.parseInt(row1);
			if(currentPage1.equals("")||currentPage1.equals(null)){
				currentPage=1;
			}
			if(row1.equals("")||row1.equals(null)){
				row=5;
			}
			ArrayList<User_Odr> list = null;
			//实例化dao层对象
			Head_QueryOrd hqo=new Head_QueryOrd(); 
			list =hqo.queryord(currentPage,row);
			resp.getWriter().write(JSON.toJSON(list).toString());
		}
}
