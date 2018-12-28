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

import entity.UserOdr;
import entity.price;
import utils.ConnectionUtils;
@WebServlet(value ="/coutprice.do")
public class User_QueryFeight extends HttpServlet{//计算运费
@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charset=utf-8");	
			String province=req.getParameter("select1");
			String province1=req.getParameter("select4");
			String provinces[][]= {{"11","北京市"},{"12","天津市"},{"13","河北省"},{"14","山西省"},{"15","内蒙古省"},{"21","辽宁省"},{"22","吉林省"},
					{"23","黑龙江省"},{"32","江苏省"},{"34","安徽省"},{"35","福建省"},{"36","江西省"},{"37","山东省"},{"41","河南省"},
					{"43","湖南省"},{"44","广东省"},{"46","海南省"},{"50","重庆市"},{"51","四川省"},{"61","陕西省"},{"62","甘肃省"}};		
			price p=new price();
			for(int i=0;i<21;i++) {
				if(provinces[i][0].equals(province)){
					province=provinces[i][1];
				}
				if(provinces[i][0].equals(province1)) {
					province1=provinces[i][1];
				}
			}
	
			float w=Float.parseFloat(req.getParameter("weight"));
			float v=Float.parseFloat(req.getParameter("volume"));
			try {
				double sumprice=coutprice(province,province1, w, v)+coutdeprice(province1, w, v);
				
				p.setFprice(sumprice);
				System.out.println(p.getFprice());
				resp.getWriter().write("msg"+p.getFprice());
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	}
	
	public double coutprice(String province,String province1,float w ,float v) throws SQLException{
		
		Connection conn =ConnectionUtils.getConnection();
		String sql= "select first_weight_price,first_volume_price ,first_weight,first_volume,"
				+ "second_serght_price,second_volume_price from transportation_price where "
				+ "send_id =(select sid from site where province =?) and " + 
				"receiving_id=(select sid from site where province = ?)";
		//查询运输价格表的首重，首重价；首立方，首立方价；次立方价，次重价；
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,province );
		pstm.setString(2,province1 );
		ResultSet rs= pstm.executeQuery();
		float fwp = 0;
		float fvp = 0;
		float fw = 0;
		float fv = 0;
		float ssp = 0;
		float svp = 0;
		float vp=0;//根据体积来算应该给的钱
		float wp=0;//根据重量来算应该给的钱
		float sumpri=0;
		
		while(rs.next()){
			fwp=rs.getFloat("first_weight_price");
			fvp=rs.getFloat("first_volume_price");
			fw=rs.getFloat("first_weight");
			fv =rs.getFloat("first_volume");
			ssp=rs.getFloat("second_serght_price");
			svp =rs.getFloat("second_volume_price");
		}
		//根据体积或重量来算价格；两者取其高
		if(w>fw) {
			wp=(w-fw)*ssp+fwp;
		}else {
			wp=fwp;
		};
		if(v>fv){
			vp=(v-fv)*svp+fvp;
		}else {
			vp=fvp;
		};
		
		if(vp>wp) {
			sumpri=vp;
		}else {
			sumpri=wp;
		};
		
		return sumpri;
	}
public double coutdeprice(String province1,float w ,float v) throws SQLException{
		
		Connection conn =ConnectionUtils.getConnection();
		String sql= "select first_weight_price,first_volume_price ,first_weight,first_volume,"
				+ "second_weight_price,second_volume_price from delivery_price  where "
				+  "site_id=(select sid from site where province = ?)";
		//查询配送价格表的首重，首重价；首立方，首立方价；次立方价，次重价；
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1,province1 );
		ResultSet rs= pstm.executeQuery();
		float fwp = 0;
		float fvp = 0;
		float fw = 0;
		float fv = 0;
		float ssp = 0;
		float svp = 0;
		float vp=0;//根据体积来算应该给的钱
		float wp=0;//根据重量来算应该给的钱
		float sumpri=0;
		
		while(rs.next()){
			fwp=rs.getFloat("first_weight_price");
			fvp=rs.getFloat("first_volume_price");
			fw=rs.getFloat("first_weight");
			fv =rs.getFloat("first_volume");
			ssp=rs.getFloat("second_weight_price");
			svp =rs.getFloat("second_volume_price");
		}
		//根据体积或重量来算价格；两者取其高
		if(w>fw) {
			wp=(w-fw)*ssp+fwp;
		}else {
			wp=fwp;
		};
		if(v>fv){
			vp=(v-fv)*svp+fvp;
		}else {
			vp=fvp;
		};
		
		if(vp>wp) {
			sumpri=vp;
		}else {
			sumpri=wp;
		};
		return sumpri;
	}
}
