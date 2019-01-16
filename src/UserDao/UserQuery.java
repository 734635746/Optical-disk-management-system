package UserDao;

import ManagerDao.checkAccountAndPwd;
import po.Manager;
import po.User;
import po.personnel;
import util.QueryUtil;
/**
 * 存放查询用户是否存在，账号密码是否有误
 * @author liuyoubin
 *
 */
public class UserQuery implements checkAccountAndPwd{
	/**
	 *  查询用户账号密码是否正确
	 */
	@Override
	public personnel checkVaild(String account, String pwd) {
		//创建sql语句
		String sql = "select * from user where uaccount=? and upwd=?";
		//参数数组，用于存放参数
		Object[] param = new Object[]{account,pwd};
		//调用查询工具的查询方法
		User user = (User) QueryUtil.checkUser(sql, param);
		
		if(user!=null) {
			return user;
		}else {
			return null;
		}
	}
	/**
	 * 查询用户账号是否存在
	 */
	@Override
	public boolean checkAccount(String account) {
		//创建sql语句
		String sql = "select * from user where uaccount=?";
		//参数数组，用于存放参数
		Object[] param = new Object[]{account};
		//调用查询工具的查询方法
		User user = (User) QueryUtil.checkUser(sql, param);
				
		if(user!=null) {
			return true;
		}else {
			return false;
		}
	}


}
