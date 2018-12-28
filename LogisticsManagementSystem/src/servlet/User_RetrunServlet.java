package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.User_RetrunDao;
@WebServlet(value="/User_Retrun.do")
public class User_RetrunServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String oid=req.getParameter("oid");
		String returns=req.getParameter("returns");
		if("null".equals(returns)){
			if(User_RetrunDao.QueryOrderoid(oid,"u1022")){
				resp.getWriter().print("true");
			}
			else{
				resp.getWriter().print("false");
			}	
		}
		else{
			if(User_RetrunDao.QueryOrderoid(oid,"u1022")){
				if(User_RetrunDao.updateuser_retrue(oid, "u1022", returns))
					resp.getWriter().print("true");
				else
					resp.getWriter().print("false");
			}
			else{
				resp.getWriter().print("false");
			}	
		}
    }
}
