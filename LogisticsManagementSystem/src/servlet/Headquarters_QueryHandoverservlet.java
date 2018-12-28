package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Dao.Headquarters_QueryHandoverDao;
import entity.Handover;
@WebServlet(value="/Headquarters_QueryHandover.do")
public class Headquarters_QueryHandoverservlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	  req.setCharacterEncoding("utf-8");
		  resp.setContentType("text/html;charset=utf-8");
		  String query=req.getParameter("query");
		  if(query==null||"null".equals(query)){
		  ArrayList<Handover> hand=Headquarters_QueryHandoverDao.Querysitesid();
		  Object object=JSON.toJSON(hand);
		  resp.getWriter().print(object);
		  }
		  else{
			   Pattern p = Pattern.compile("[0-9]");
               Matcher m = p.matcher(query);
               if(m.find()){
            	   ArrayList<Handover> hand=Headquarters_QueryHandoverDao.Querysitebysid(query);
         		  Object object=JSON.toJSON(hand);
         		  resp.getWriter().print(object);
               }
               else{
            	  ArrayList<Handover> hand=Headquarters_QueryHandoverDao.Querysitebyuname(query);
          		  Object object=JSON.toJSON(hand);
          		  resp.getWriter().print(object);
               }
		  }
		 
          
		 
    }
}
