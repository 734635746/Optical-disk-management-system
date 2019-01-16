package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.DBManager;
import po.Manager;
import po.personnel;

/**
 * 封装更新数据库的方法
 * @author liuyoubin
 *
 */
public class DBUtil {
	/**
	 * 输入sql语句和参数数组，更新数据库，返回影响的行数
	 * @param sql sql语句
	 * @param param 参数数组
	 * @return 数据库影响的行数
	 */
	public static boolean updateDB(String sql,Object[] param){
		//获取连接
		Connection conn = new DBManager().getConnection();
		//创建预处理语句
		PreparedStatement prep = null;
		//更新的行数
		int updateRow = 0;
		
		try {
			prep = conn.prepareStatement(sql);
			//调用工具类方法设置参数
			JDBCutil.setParameter(prep, param);
			//执行预处理语句，并获得更新的行数
			updateRow = prep.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接
			DBManager.close(conn, prep);
		}
		
		if(updateRow>0) {
			updateRow = 0;
			return true;
		}else {
			return false;
		}
		
	}

	
	
	
	
}
