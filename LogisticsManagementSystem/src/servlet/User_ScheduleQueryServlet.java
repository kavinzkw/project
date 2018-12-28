package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Dao.User_ScheduleQueryDao;
import entity.Route;
import entity.User_Ord_User;


@WebServlet(value="/User_ScheduleQuery.do")
public class User_ScheduleQueryServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		    req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			String Oid=req.getParameter("Orders");
			String Oid_id=req.getParameter("Orders_id");
		    if(Oid.equals("null")&&Oid_id.equals("null")){
		    	ArrayList<String> a=User_ScheduleQueryDao.QueryOrderAllOid("u1022");
		    	ArrayList<User_Ord_User> usu=new ArrayList<User_Ord_User>();
		    	for (String string : a) {
					User_Ord_User uor=User_ScheduleQueryDao.QueryOrderInformation(string);
					usu.add(uor);
				}
		    	Object obj1=JSON.toJSON(usu);
				resp.getWriter().print(obj1);
		    }
		    else if(Oid.equals("null")&&!Oid_id.equals("null")){
		    	 ArrayList<Route> route=User_ScheduleQueryDao.QueryOrderRoute(Oid_id);
		    	 Object obj1=JSON.toJSON(route);
		    	 resp.getWriter().print(obj1);
		    }
		    else if(!Oid.equals("null")){
		    	    Pattern p = Pattern.compile("[0-9]");
		            Matcher m = p.matcher(Oid);
		            User_Ord_User uor=null;
		            if(m.find()){
		    	      uor=User_ScheduleQueryDao.QueryOrderInformation(Oid);
		    	        if(uor.getOid()!=null){
		  		    	ArrayList<User_Ord_User> usu=new ArrayList<User_Ord_User>();
		  		    	 usu.add(uor);
		  		    	 Object obj1=JSON.toJSON(usu);
		  		    	 resp.getWriter().print(obj1);
		  		        }
		  		    	else 
		  		    		 resp.getWriter().print("msg");
		  		        } 
		            else{
		            	ArrayList<User_Ord_User> usu=User_ScheduleQueryDao.QueryOrderByname("u1022", Oid);
		            	if(usu.size()<=0){
		            		 resp.getWriter().print("msg");
		            	}
		            	else{
		            		Object obj1=JSON.toJSON(usu);
			  		    	resp.getWriter().print(obj1);
		            	}
		            }
		    }
	}
 
}
