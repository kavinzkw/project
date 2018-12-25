package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


/**
 * 
 * @author 汪洋 数据库连接工具类
 * 
 */
public class ConnectionUtils {
	static String username;
	static String password;
	static String url;
	static String driver;

	static {
		try {
			getParam();
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void getParam() throws IOException {
		ClassLoader cl= ConnectionUtils.class.getClassLoader();
		InputStream is = cl.getResourceAsStream("db_oracle.properties");
		Properties pro = new Properties();
		pro.load(is);
		username = pro.getProperty("username");
		password = pro.getProperty("password");
		url = pro.getProperty("url");
		driver = pro.getProperty("driver");
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	public static void close(Connection conn, ResultSet rs,
			PreparedStatement... psmts) throws SQLException {
		if (conn != null) {
			conn.close();

		}
		if (psmts != null) {
			for(PreparedStatement psmt:psmts){
				if(psmt!=null){
					psmt.close();
				}
			}
		}
		if (rs != null) {
			rs.close();
		}
	}
	
	public static void close(Connection conn,
			PreparedStatement... psmts) throws SQLException {
		if (conn != null) {
			conn.close();

		}
		if (psmts != null) {
			for(PreparedStatement psmt:psmts){
				if(psmt!=null){
					psmt.close();
				}
			}
		}
	}

	/**
	 * 该方法是用来获取到当前时间之后，然后格式化成一个适合作为主键的日期格式 比如，201508221640
	 * 
	 * @return
	 */
	public static String getNowTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.format(date);
	}
}
