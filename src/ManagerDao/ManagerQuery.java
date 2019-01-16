package ManagerDao;


import po.Manager;
import po.personnel;
import util.QueryUtil;

/**
 * 账号密码验证模块
 *  存放查询管理员是否存在，账号密码是否有误
 * @author liuyoubin
 *
 */
public class ManagerQuery implements checkAccountAndPwd{
	
	/**
	 *  查询管理员账号密码是否正确
	 */
	@Override
	public personnel checkVaild(String account, String pwd) {
		//创建sql语句
		String sql = "select * from manager where maccount=? and mpwd=?";
		//参数数组，用于存放参数
		Object[] param = new Object[]{account,pwd};
		//调用查询工具的查询方法
		Manager manager = (Manager) QueryUtil.checkManager(sql, param);
		//返回管理员对象
		return manager;
		
	}
	/**
	 * 查询管理员账号是否存在
	 */
	@Override
	public boolean checkAccount(String account) {
		//创建sql语句
		String sql = "select * from manager where maccount=?";
		//参数数组，用于存放参数
		Object[] param = new Object[]{account};
		//调用查询工具的查询方法
		Manager manager = (Manager) QueryUtil.checkManager(sql, param);
		//返回判断结果
		if(manager!=null) {
			return true;
		}else {
			return false;
		}
	}
	

}
