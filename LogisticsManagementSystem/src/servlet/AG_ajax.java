package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import utils.ConnectionUtils;

@WebServlet(value="/ajax.do")
public class AG_ajax extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String ssid= req.getParameter("ssid");
		PrintWriter pw = resp.getWriter();
		String ss[]=getssid(ssid);
		boolean flag =false;
		for(int i=0;i<ss.length;i++) {
			if(ss[i].equals(ssid)) {
				flag=true;
				break;
			}
		}
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
		if(istruessid(ssid)) {
			pw.write("单号可交接!");
			
		}else {
			
			pw.write("单号错误");
		}
		}else {
			pw.write("单号不存在");
		}
	}

	public boolean istruessid(String ssid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		String sql= "select ssid from handover";
		ResultSet rs = null;
		try {
			conn=ConnectionUtils.getConnection();
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()){
				if(ssid.equals(rs.getString(1))){
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return true;	
	}
	public String[] getssid(String ssid) {
		Connection conn = null;
		PreparedStatement pstm = null;
		String sql="select ssid from transport";
		ResultSet rs;
		String [] ss = null;
		try {
			conn=ConnectionUtils.getConnection();
			pstm=conn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while (rs.next()) {
				String s =rs.getString(1);
					ss=s.split("-");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ss;
	}
}
