package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Headquarters_addDeliveryDao;
@WebServlet(value="/Headquarters_addDelivery.do")
public class Headquarters_addDeliveryservlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	      req.setCharacterEncoding("utf-8");
			  resp.setContentType("text/html;charset=utf-8");
			  String bool=req.getParameter("data");
			  if(bool!=null){
			  String sid=Headquarters_addDeliveryDao.Querysitesid();
			  resp.getWriter().write("msg"+sid);
			  }
			  else{
				  String sid=req.getParameter("sid");
				  String sname=req.getParameter("sname");
				  String site_range=req.getParameter("site_range");
				  String province=req.getParameter("province");
				  String first_weight=req.getParameter("first_weight");
				  String first_weight_price=req.getParameter("first_weight_price");
				  String first_volume=req.getParameter("first_volume");
				  String first_volume_price=req.getParameter("first_volume_price");
				  String second_weight_price=req.getParameter("second_weight_price");
				  String second_volume_price=req.getParameter("second_volume_price");
				  boolean b1= Headquarters_addDeliveryDao.Updatasite(sid, sname, site_range, province);
				  boolean b2=Headquarters_addDeliveryDao.Updatadelivery_price(sid, first_weight, first_volume, first_weight_price, first_volume_price, second_weight_price, second_volume_price);
				  if(b1&&b2){
					  resp.getWriter().write("msgtrue");
				  }
				  else{
					  resp.getWriter().write("msgfalse");
				  }
			  }
    }
}
