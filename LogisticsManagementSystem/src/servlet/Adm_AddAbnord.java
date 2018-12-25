package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DaoDemo.Adm_AddAbnOrd;

@WebServlet(value="/Adm_AddAbnOrd.do")
public class Adm_AddAbnord extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter pw=resp.getWriter();
			String abn_oid=req.getParameter("oid");
			String reasons=req.getParameter("reasons");
			Adm_AddAbnOrd addabnord=new Adm_AddAbnOrd();
			boolean flag=addabnord.addAbnOrd(abn_oid,reasons);
			if (flag) {
				System.out.println("插入成功");
			}else {
				System.out.println("插入失败");
			}
			resp.getWriter().write("msg");
	}

}
