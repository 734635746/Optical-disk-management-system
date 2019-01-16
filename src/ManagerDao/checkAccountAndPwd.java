package ManagerDao;

import po.personnel;

/**
  *             验证接口
 * 1 验证账号和密码是否正确
 * @author liuyoubin
 *
 */
public interface checkAccountAndPwd {
	
	/**
	 * 1 检验账号密码是否正确
	 * @param account 账号
	 * @param pwd 密码
	 * @return 查询封装的personnel对象
	 */
	public personnel checkVaild(String account,String pwd);
	/**
	 *  2 查询账号是否存在
	 * @param account 账号
	 * @return 判断结果
	 */
	public boolean checkAccount(String account);

}
