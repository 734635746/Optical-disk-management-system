package core;
/**
 * 1 根据配置文件信息生成配置文件封装对象，管理维护数据库连接
 * @author liuyoubin
 *
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import bean.Configuration;

public class DBManager {
	/**
	  *  封装配置文件信息
	 */
	private static Configuration conf = null;
	
	/**
	 * 1 类加载时读取配置文件信息并封装
	 */
	static {
		
		Properties pos = new Properties();
		conf = new Configuration();
		try {
			/**
			 *  1 加载配置文件
			 */
			pos.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("po.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/**
		 * 2 封装配置信息到conf
		 */
		conf.setDriver(pos.getProperty("driver"));
		conf.setUrl(pos.getProperty("url"));
		conf.setPwd(pos.getProperty("pwd"));
		conf.setUres(pos.getProperty("ures"));
	
	}
	
	/**
	 * 2  获取数据库连接
	 * @return Connection对象
	 */
	public Connection getConnection() {
		
		Connection conn = null ;
		
		try {
			
			/**
			 *  1 加载驱动
			 */
			Class.forName(conf.getDriver());
			/**
			 *  2 连接数据库，获取连接
			 */
		    conn = DriverManager.getConnection(conf.getUrl(), conf.getUres(), conf.getPwd());
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	//1 关闭数据库连接的几个常用重载方法
	
	public static void close(Connection conn) {
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void close(Connection conn,Statement sta) {
		
		if(sta!=null) {
			try {
				sta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void close(Connection conn,PreparedStatement prep) {
		
		
		if(prep!=null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
public static void close(Connection conn,PreparedStatement prep,ResultSet rs) {	
		
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(prep!=null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
	
	
}
