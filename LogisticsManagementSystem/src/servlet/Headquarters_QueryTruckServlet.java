package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Headquarters_ManRouteDao;
import Dao.Headquarters_QueryTruckDao;

import com.alibaba.fastjson.JSON;

import entity.Car;
import entity.Routes;
@WebServlet(value="/Headquarters_QueryTruck.do")
public class Headquarters_QueryTruckServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		  resp.setContentType("text/html;charset=utf-8");
		  String page=req.getParameter("page");
		  String deletefor=req.getParameter("deletefor");
		  String add=req.getParameter("add");
		  if(deletefor!=null&&!"null".equals(deletefor)){
			  Headquarters_QueryTruckDao.Deleteforrows(deletefor);
			  resp.getWriter().print("msgsuccess");
		  }
		  else if(add!=null&&!"null".equals(add)){
			
		  }
		  else{
		  int page1=Integer.parseInt(page);
		  ArrayList<Car> Car=Headquarters_QueryTruckDao.HeadQuerycar(page1, page1*10);
		  Object object=JSON.toJSON(Car);
		  resp.getWriter().print(object);
		  }
      }
	}

