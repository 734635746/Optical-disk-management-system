package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.DBManager;
import po.CD;
import po.Manager;
import po.Record;
import po.Root;
import po.User;
import po.personnel;
/**
 * 封装查询数据库的操作
 * @author liuyoubin
 *
 */
public class QueryUtil {
	
	/**
	 * 输入sql语句和参数数组，查询数据库是否存在查询的管理员对象
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return personal对象
	 */
	public static personnel checkManager(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//管理员对象
		Manager manager = null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到Manager对象里
			while(rs.next()) {
				manager = new Manager();
				manager.setMid(rs.getString("mid"));
				manager.setMaccount(rs.getString("maccount"));
				manager.setMpwd(rs.getString("mpwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return manager;
		
	}
	
	/**
	 * 输入sql语句和参数数组，查询数据库是否存在查询的用户对象
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return personal对象
	 */
	public static personnel checkUser(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//用户对象
		User user = null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到User对象里
			while(rs.next()) {
				user = new User();
				user.setAge(rs.getInt("age"));
				user.setUaccount(rs.getString("uaccount"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
				user.setBirthday(rs.getDate("birthday"));
				user.setSex(rs.getString("sex"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return user;
		
	}
	
	/**
	 * 输入sql语句和参数数组，查询数据库是否存在CD对象
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return CD对象
	 */
	public static CD checkCdByID(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//CD对象
		CD cd = null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到User对象里
			while(rs.next()) {
				cd = new CD();
				cd.setCid(rs.getString("cid"));
				cd.setCname(rs.getString("cname"));
				cd.setType(rs.getString("type"));
				cd.setBorrowed(rs.getString("borrowed"));
				cd.setBtime(rs.getDate("btime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return cd;
		
	}
	
	/**
	 * 输入sql语句和参数数组，查询数据库是否存在CD对象
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return CD列表
	 */
	public static List<CD> checkCdByName(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//CD数组
		List<CD> cdList = new ArrayList<CD>();
		CD c = null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到User对象里
			while(rs.next()) {
				c = new CD();
				c.setCid(rs.getString("cid"));
				c.setCname(rs.getString("cname"));
				c.setType(rs.getString("type"));
				c.setBorrowed(rs.getString("borrowed"));
				c.setBtime(rs.getDate("btime"));
				cdList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return cdList;
		
	}
	/**
	 * 输入sql语句和参数数组，查询数据库是否可借阅光盘
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return CD列表
	 */
	public static List<CD> checkCdCouldBeBorrowed(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//CD数组
		List<CD> cdList = new ArrayList<CD>();
		CD c = null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到User对象里
			while(rs.next()) {
				if(rs.getString("borrowed")==null) {
					c = new CD();
					c.setCid(rs.getString("cid"));
					c.setCname(rs.getString("cname"));
					c.setType(rs.getString("type"));
					c.setBorrowed(rs.getString("borrowed"));
					c.setBtime(rs.getDate("btime"));
				cdList.add(c);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return cdList;
		
	}
	
	/**
	 * 输入sql语句和参数数组，查询数据库是否可借阅光盘
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return CD列表
	 */
	public static List<CD> checkCdHaveBeBorrowed(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//CD数组
		List<CD> cdList = new ArrayList<CD>();
		CD c = null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到User对象里
			while(rs.next()) {
				
					c = new CD();
					c.setCid(rs.getString("cid"));
					c.setCname(rs.getString("cname"));
					c.setType(rs.getString("type"));
					c.setBorrowed(rs.getString("borrowed"));
					c.setBtime(rs.getDate("btime"));
					cdList.add(c);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return cdList;
		
	}
	
	
	/**
	 * 输入sql语句和参数数组，查询数据库的用户借阅信息
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return 用户借阅信息列表
	 */
	public static List<Record> queryUserRecord(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//用户借阅信息列表
		List<Record> recordList = new ArrayList<Record>();
		//记录表对象
		Record record = null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到Record对象里
			while(rs.next()) {
				record = new Record();
				record.setId(rs.getInt("id"));
				record.setUserAccount(rs.getString("useraccount"));
				record.setAction(rs.getString("action"));
				record.setCdId(rs.getString("cdid"));
				record.setBtime(rs.getTimestamp("btime"));
				recordList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return recordList;
		
	}
	
	
	/**
	 * 输入sql语句和参数数组，新建光盘时查询数据库判断光盘编号是否存在
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return 是否存在
	 */
	public static boolean queryCDIdExist(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		boolean bool = false;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			//bool = prep.execute();
			rs = prep.executeQuery();
			if(rs.next()) {
				bool = true;
			}
			
			//System.out.println(bool);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return bool;
		
	}
	
	/**
	 * 输入sql语句和参数数组，查询数据库所有的用户对象
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return CD列表
	 */
	public static List<User> queryAllUser(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//User列表
		List<User> userList = new ArrayList<User>();
		User user = null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到User对象里
			while(rs.next()) {
				user = new User();
				user.setAge(rs.getInt("age"));
				user.setUaccount(rs.getString("uaccount"));
				user.setUname(rs.getString("uname"));
				user.setUpwd(rs.getString("upwd"));
				user.setBirthday(rs.getDate("birthday"));
				user.setSex(rs.getString("sex"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return userList;
		
	}
	/**
	 * 输入sql语句和参数数组，查询所指定用户的权限信息
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return CD列表
	 */
	public static Root queryRoot(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//Root对象
		Root root= null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到User对象里
			while(rs.next()) {
				root = new Root();
				root.setModifyRoot(rs.getString("modifyroot"));
				root.setBorrowRoot(rs.getString("borrowRoot"));
				root.setUserAccount(rs.getString("useraccount"));
				root.setApplyModifyRoot(rs.getString("applymodifyroot"));
				root.setApplyBorrowRoot(rs.getString("applyborrowRoot"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return root;
		
	}
	
	/**
	 * 查看所有用户申请权限
	 */
	public static List<Root> queryUserApplyRoot(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		List<Root> list = new ArrayList<>();
		//Root对象
		Root root= null;
		//创建预处理语句
		PreparedStatement prep = null;
		//结果集
		ResultSet rs = null;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得结果集
			rs = prep.executeQuery();
			//将查询信息存放到User对象里
			while(rs.next()) {
				root = new Root();
				root.setModifyRoot(rs.getString("modifyroot"));
				root.setBorrowRoot(rs.getString("borrowRoot"));
				root.setUserAccount(rs.getString("useraccount"));
				root.setApplyModifyRoot(rs.getString("applymodifyroot"));
				root.setApplyBorrowRoot(rs.getString("applyborrowRoot"));
				list.add(root);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep, rs);
		}
		
		return list;
		
	}
	
}
