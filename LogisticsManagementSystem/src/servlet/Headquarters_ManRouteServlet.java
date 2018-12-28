package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Dao.Headquarters_ManRouteDao;
import entity.Routes;
@WebServlet(value="/Headquarters_ManRoute.do")
public class Headquarters_ManRouteServlet extends HttpServlet{
   @Override
protected void service(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	      req.setCharacterEncoding("utf-8");
		  resp.setContentType("text/html;charset=utf-8");
		  String page=req.getParameter("page");
		  String deletefor=req.getParameter("deletefor");
		  String add=req.getParameter("add");
		  if(deletefor!=null&&!"null".equals(deletefor)){
			  Headquarters_ManRouteDao.Deleteforrows(deletefor);
			  resp.getWriter().print("msgsuccess");
		  }
		  else if(add!=null&&!"null".equals(add)){
			
		  }
		  else{
		  int page1=Integer.parseInt(page);
		  ArrayList<Routes> routes=Headquarters_ManRouteDao.HeadQuerySit(page1, page1*10);
		  for(int i=0;i<routes.size();i++){
			  if(routes.get(i).getAfter_sid()!=null&routes.get(i).getAfter_sid()!=""){
			  String [] sids=routes.get(i).getAfter_sid().split("-");
			  String after_name="";
			  for(int j=0;j<sids.length;j++){
				  after_name=after_name+Headquarters_ManRouteDao.HeadQuerysname(sids[j])+"-";
			  }
			  routes.get(i).setAfter_sname(after_name);
			  }
		  }
		  Object object=JSON.toJSON(routes);
		  resp.getWriter().print(object);
		  }
}
}
