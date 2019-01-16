 package UserDao;

import java.util.List;

import po.CD;
import po.Record;
import po.Root;
import po.User;

/**
 * 用户操作接口
 * @author liuyoubin
 *
 */
public interface userOperateInt {
	/**
	 * 修改个人信息
	 * @param 返回布尔值
	 */
	public boolean modifyPersonalInfo(User user);
	/**
	 * 根据光盘编号查询一个光盘是否存在
	 */
	public CD queryCD(String cid);
	/**
	 * 根据光盘名称查询一组光盘
	 */
	public List<CD> queryCDs(String cname);
	/**
	 * 根据光盘类型查询一组光盘
	 */
	public List<CD> queryCDsWithType(String type);
	/**
	 * 查看可借阅光盘
	 */
	public List<CD> queryCDsCouldBeBorrowed();
	/**
	 * 查看已借阅的光盘
	 */
	public List<CD> queryCDsHaveBeBorrowed(String uname);
	/**
	 * 借阅光盘
	 */
	public boolean borrowCD(CD cd,User user);
	/**
	 * 归还光盘
	 */
	public boolean returnCD(CD cd);
	/**
	 * 借阅成功，更新记录表
	 */
	public boolean recordBorrowCD(User user,CD cd);
	/**
	 * 归还成功更新记录表
	 */
	public boolean recordReturnCD(User user,CD cd);
	/**
	 * 查看权限信息
	 */
	public Root queryRoot(String userAccount);
	/**
	 * 查询数据库，返回用户借阅/归还信息（将用户借阅信息放在List中）
	 * @param username 用户账号
	 * @return 用户借阅/归还信息
	 */
	public List<Record> queryRecordByUesrAccount(String uaccount);
	/**
	 * 申请修改权限
	 */
	public boolean applyModifyRoot(String uaccount);
	/**
	 * 申请借阅权限
	 */
	public boolean applyBorrowRoot(String uaccount);
	
}
