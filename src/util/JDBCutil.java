package util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 封装常用的jdbc操作
 * @author liuyoubin
 *
 */
public class JDBCutil {
	/**
	 * 为预处理语句设置参数
	 * @param prep 预处理语句
	 * @param param 参数数组
	 * @throws SQLException
	 */
	public static void setParameter(PreparedStatement prep,Object[] param) throws SQLException {
		
		if(prep!=null) {
			for(int i=0;i<param.length;i++) {
				prep.setObject(i+1, param[i]);
			}
		}
		
	}

}
