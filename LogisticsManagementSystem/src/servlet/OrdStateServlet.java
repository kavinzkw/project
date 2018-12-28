package servlet;
//∂©µ•…Û∫À
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import DaoDemo.Adm_ChcekOrd;
import entity.Order;
import utils.ConnectionUtils;
@WebServlet(value="/OrdState.do")
public class OrdStateServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 req.setCharacterEncoding("utf-8");
	   		resp.setContentType("text/html;charset=utf-8");
	   		String sid = req.getParameter("OrdState_id");
	   		 ArrayList<Order> list = new ArrayList<Order>();
	   		Adm_ChcekOrd ac = new Adm_ChcekOrd();
	   		 list = ac.getSite(sid);
	   		 String data = JSON.toJSON(list).toString();
	   		resp.getWriter().write(data);
	    }
	
	        
	}


