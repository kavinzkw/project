package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import Dao.Headquarters_QueryDeliverDao;
import entity.Site;
@WebServlet(value="/Headquarters_QueryDeliver.do")
public class Headquarters_QueryDeliverservlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    	    req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
    	    String page=req.getParameter("page");
    	    int page1=Integer.parseInt(page);
    	    ArrayList<Site> sites=Headquarters_QueryDeliverDao.HeadQuerySit(page1,page1*10);
    	    Object object=JSON.toJSON(sites);
    	    resp.getWriter().print(object);
    }
}
