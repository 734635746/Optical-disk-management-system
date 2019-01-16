package ManagerDao;

import java.util.List;
import java.util.Map;

import po.CD;
import po.Record;
import po.Root;
import po.User;

/**
 *   管理员操作接口
 *   规范管理员的操作
 * @author liuyoubin
 *
 */
public interface managerOperateInt {
	/**
	 * 查询数据库，返回用户借阅/归还信息（将用户借阅信息放在List中）
	 * @return 用户借阅/归还信息
	 */
	public List<Record> queryAllRecord();
	/**
	 * 查询数据库，返回指定用户借阅/归还信息（将用户借阅信息放在List中）
	 * @param username 用户账号
	 * @return 用户借阅/归还信息
	 */
	public List<Record> queryRecordByUesrAccount(String uaccount);
	
	/**
	 * 新建光盘时，查询光盘编号是否被占用
	 */
	public boolean checkCDId(CD cd);
	/**
	 * 光盘编号没有占用时,新建光盘
	 */
	public boolean insertCD(CD cd);
	/**
	 * 查看所有光盘
	 */
	public List<CD> queryAllCD();
	/**
	 * 查看指定用户的光盘
	 */
	public List<CD> queryCDByUserAccount(String uaccount);
	/**
	 * 查询指定光盘编号的光盘
	 */
	public CD queryCDByCid(String cid);
	/**
	 * 修改光盘信息
	 */
	public boolean updateCD(CD cd);
	/**
	 * 删除光盘
	 */
	public boolean deleteCD(CD cd);
	/**
	 * 新建用户
	 */
	public boolean addUser(User user);
	/**
	 * 查询用户，返回用户对象
	 */
	public User queryUser(String uaccount);
	/**
	 * 删除用户
	 */
	public boolean deleteUser(String uaccount);
	/**
	 * 查看所有用户
	 */
	public List<User> queryAllUser();
	/**
	 * 统计光盘借阅情况
	 */
	public Map<String,Integer> statistcsCDs(List<Record> recordList);
	/**
	 * 新建用户时，创建权限信息
	 */
	public boolean buildRoot(User user);
	/**
	 * 查看权限信息
	 */
	public Root queryRoot(String userAccount);
	/**
	 * 修改用户权限
	 */
	public boolean modifyRoot(String userAccount,Root root);
	/**
	 * 查看用户申请权限
	 */
	public List<Root> queryUserApplyRoot();
	/**
	 * 置空申请内容
	 */
	public boolean setNULLApplyRoot(String uaccount,int flag);
}
