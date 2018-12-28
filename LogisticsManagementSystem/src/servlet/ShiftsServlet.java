package servlet;
//∞‡¥Œ≤È—Ø


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import DaoDemo.Adm_ChcekOrd;
import entity.Shifts;
import utils.ConnectionUtils;
@WebServlet(value="/shifts.do")
public class ShiftsServlet extends HttpServlet{    
	
  @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	  req.setCharacterEncoding("utf-8");
 		resp.setContentType("text/html;charset=utf-8");
 		String rid = req.getParameter("rid");
 		String cid = req.getParameter("cid");
 		ArrayList<Shifts> list = new ArrayList<Shifts>();
 		Adm_ChcekOrd ac = new Adm_ChcekOrd();
 		list =ac.getShifts(rid,cid);
 		 String data = JSON.toJSON(list).toString();
   		resp.getWriter().write(data);
 		
}


 
}
