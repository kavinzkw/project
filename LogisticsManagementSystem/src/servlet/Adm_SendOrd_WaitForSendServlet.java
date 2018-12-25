package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Dao.Adm_SendOrd_WaitForSendDao;


@WebServlet(value="/Adm_SendOrd_WaitForSend.do")
public class Adm_SendOrd_WaitForSendServlet extends HttpServlet{
  @Override
protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	    req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String fxsite=req.getParameter("fxsite");
		String yssid=req.getParameter("yssid");
		String oids=req.getParameter("oids");
		 if(fxsite.equals("null")&&oids.equals("null")){
	      ArrayList arr=Adm_SendOrd_WaitForSendDao.WaitForSendQueryAll();
	      Object object=JSON.toJSON(arr);
	      resp.getWriter().print(object);
	     }
		 else if(!fxsite.equals("null")&&oids.equals("null")){
			  ArrayList arr=Adm_SendOrd_WaitForSendDao.WaitForSendQuery(fxsite);
		      Object object=JSON.toJSON(arr);
		      resp.getWriter().print(object);
		 }
		 else{
			  Adm_SendOrd_WaitForSendDao.WaitForSendinsert(oids,"u1001",yssid);
			  ArrayList arr=Adm_SendOrd_WaitForSendDao.WaitForSendQueryAll();
		      Object object=JSON.toJSON(arr);
		      resp.getWriter().print(object);
		 }
}
}
