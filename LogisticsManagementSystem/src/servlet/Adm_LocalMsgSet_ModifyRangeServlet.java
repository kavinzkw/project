package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Adm_LocalMsgSet_ModifyRangeDao;
@WebServlet(value="/Adm_LocalMsgSet_ModifyRange.do")
public class Adm_LocalMsgSet_ModifyRangeServlet extends HttpServlet{
          @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp)
        		throws ServletException, IOException {
        	  req.setCharacterEncoding("utf-8");
  			  resp.setContentType("text/html;charset=utf-8");
  			  String site_range=req.getParameter("site_range");
  			  if(site_range.equals("null")){
  			  String site_ranges=Adm_LocalMsgSet_ModifyRangeDao.ReturnFormsite_range("u1001");
  			  resp.getWriter().write("msg"+site_ranges);
  			  }
  			  else{
  				String str=Adm_LocalMsgSet_ModifyRangeDao.updatesite("u1001", site_range);
  				resp.getWriter().write("msg"+str);
  			  }
        }
}
