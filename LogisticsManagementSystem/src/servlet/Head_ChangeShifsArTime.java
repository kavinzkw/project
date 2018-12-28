package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import DaoDemo.Adm_ChcekOrd;
import entity.ChangeShifts;
import entity.Shifts;
@WebServlet(value="/head_changeshifts.do")
public class Head_ChangeShifsArTime extends HttpServlet{
                  @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                	  req.setCharacterEncoding("utf-8");
               		resp.setContentType("text/html;charset=utf-8");
               		String rid = req.getParameter("rid");
               		String cid = req.getParameter("cid");
               		ArrayList<ChangeShifts> list = new ArrayList<ChangeShifts>();
               		Adm_ChcekOrd ac = new Adm_ChcekOrd();
               		list =ac.getNoArTimeShifts(rid,cid);
               		 String data = JSON.toJSON(list).toString();
               		 System.out.println(data);
                 		resp.getWriter().write(data);
                 		
                }
}
