package ManagerDao;

import java.util.List;
import java.util.Map;

import org.eclipse.ui.internal.dialogs.NewContentTypeDialog;

import po.CD;
import po.Record;
import po.Root;
import po.User;
import util.ConversionUtil;
import util.DBUtil;
import util.QueryUtil;

/**
 * 1 存放管理员的操作方法
 * 2 界面交互行为的实现
 * @author liuyoubin
 *
 */
public class ManagerOperation implements managerOperateInt{
	/**
	 * 查询数据库，返回用户借阅/归还信息（将用户借阅信息放在List中）
	 * @return 用户借阅信息
	 */
	@Override
	public List<Record> queryAllRecord() {
		//创建sql语句
		String sql = "select * from record";
		//创建参数数组
		Object[] param = new Object[]{};
		//调用查询方法
		return QueryUtil.queryUserRecord(sql, param);
	}
	/**
	 * 查询数据库，返回指定用户借阅/归还信息（将用户借阅信息放在List中）
	 * @param username 用户账号
	 * @return 用户借阅/归还信息
	 */
	@Override
	public List<Record> queryRecordByUesrAccount(String uaccount) {
		//创建sql语句
		String sql = "select * from record where useraccount=?";
		//创建参数数组
		Object[] param = new Object[]{uaccount};
		//调用查询方法
		return QueryUtil.queryUserRecord(sql, param);
	}
	/**
	 * 新建光盘时，查询光盘编号是否被占用
	 */
	@Override
	public boolean checkCDId(CD cd) {
		//创建sql语句
		String sql = "select * from cd where cid = ?";
		//创建参数数组
		Object[] param = new Object[]{cd.getCid()};
		//调用查询方法
		return QueryUtil.queryCDIdExist(sql, param);
	}
	/**
	 * 光盘编号没有占用时,新建光盘
	 */
	@Override
	public boolean insertCD(CD cd) {
		//创建sql语句
		String sql = "insert into cd values(?,?,?,?,?) ";
		//创建参数数组
		Object[] param = new Object[]{cd.getCid(),cd.getCname(),cd.getBorrowed(),cd.getBtime(),cd.getType()};
		//调用查询方法
		return DBUtil.updateDB(sql, param);
	}
	/**
	 * 查看所有光盘
	 */
	@Override
	public List<CD> queryAllCD() {
		//创建sql语句
		String sql = "select * from cd";
		//创建参数数组
		Object[] param = new Object[]{};
		//调用查询方法
		return QueryUtil.checkCdByName(sql, param);
	}
	/**
	 * 查看指定用户的光盘
	 */
	@Override
	public List<CD> queryCDByUserAccount(String uaccount) {
		//创建sql语句
		String sql = "select * from cd where borrowed=? ";
		//创建参数数组
		Object[] param = new Object[]{uaccount};
		//调用查询方法
		return QueryUtil.checkCdByName(sql, param);
	}
	/**
	 * 查询指定光盘编号的光盘
	 */
	@Override
	public CD queryCDByCid(String cid) {
		//创建sql语句
		String sql = "select * from cd where cid=? ";
		//创建参数数组
		Object[] param = new Object[]{cid};
		//调用查询方法
		return QueryUtil.checkCdByID(sql, param);
	}
	/**
	 * 修改光盘信息
	 */
	@Override
	public boolean updateCD(CD cd) {
		//创建sql语句
		String sql = "update cd SET cname=?,type=? where cid=?";
		//创建参数数组
		Object[] param = new Object[]{cd.getCname(),cd.getType(),cd.getCid()};
		//调用查询方法
		return DBUtil.updateDB(sql, param);
	}
	/**
	 * 删除光盘
	 */
	@Override
	public boolean deleteCD(CD cd) {
		//创建sql语句
		String sql = "delete from cd where cid=? ";
		//创建参数数组
		Object[] param = new Object[]{cd.getCid()};
		//调用更新方法
		return DBUtil.updateDB(sql, param);
	}
	/**
	 * 新建用户
	 */
	@Override
	public boolean addUser(User user) {
		//创建sql语句
		String sql = "insert into user values(?,?,?,?,?,?)";
		//创建参数数组
		Object[] param = new Object[]{user.getUaccount(),user.getUpwd(),user.getUname(),user.getAge(),user.getSex(),user.getBirthday()};
		//调用更新方法
		return DBUtil.updateDB(sql, param);
	}
	/**
	 * 查询用户，返回用户对象
	 */
	@Override
	public User queryUser(String uaccount) {
		//创建sql语句
		String sql = "select * from user where uaccount=?";
		//创建参数数组
		Object[] param = new Object[]{uaccount};
		//调用查询方法
		return (User) QueryUtil.checkUser(sql, param);
	}
	/**
	 * 删除用户
	 */
	@Override
	public boolean deleteUser(String uaccount) {
		//创建sql语句
		String sql = "delete from user where uaccount=?";
		//创建参数数组
		Object[] param = new Object[]{uaccount};
		//调用更新方法
		return DBUtil.updateDB(sql, param);
	}
	/**
	 * 查看所有用户
	 */
	@Override
	public List<User> queryAllUser() {
		//创建sql语句
		String sql = "select * from user";
		//创建参数数组
		Object[] param = new Object[]{};
		//调用查询方法
		return QueryUtil.queryAllUser(sql, param);
	}
	/**
	 * 统计光盘借阅情况
	 */
	@Override
	public Map<String, Integer> statistcsCDs(List<Record> recordList) {
		
		
		return ConversionUtil.statistcsCDs(recordList);
	}
	/**
	 * 新建用户时，创建权限信息
	 */
	@Override
	public boolean buildRoot(User user) {
		//创建sql语句
		String sql = "insert into root (useraccount,modifyroot,borrowroot) values(?,?,?)";
		//创建参数数组
		Object[] param = new Object[]{user.getUaccount(),"启用","启用"};
		//调用更新方法
		return DBUtil.updateDB(sql, param);
	}
	
	/**
	 * 查看权限信息
	 */		
	@Override
	public Root queryRoot(String userAccount) {
		//创建sql语句
		String sql = "select * from root where useraccount=?";
		//创建参数数组
		Object[] param = new Object[]{userAccount};
		
		return QueryUtil.queryRoot(sql, param);
	}
	/**
	 * 修改用户权限
	 */
	@Override
	public boolean modifyRoot(String userAccount, Root root) {
		//创建sql语句
		String sql = "update root SET modifyroot=?,borrowroot=? where useraccount=?";
		//创建参数数组
		Object[] param = new Object[]{root.getModifyRoot(),root.getBorrowRoot(),userAccount};
		
		return DBUtil.updateDB(sql, param);
	}
	/**
	 * 查看所有用户申请权限
	 */
	@Override
	public List<Root> queryUserApplyRoot() {
		//创建sql语句
		String sql = "select * from root";
		//创建参数数组
		Object[] param = new Object[]{};
		return QueryUtil.queryUserApplyRoot(sql, param);
	}
	/**
	 * 置空申请内容
	 */
	@Override
	public boolean setNULLApplyRoot(String uaccount, int flag) {
		String sql=null;
		Object[] param =null;
		if(flag==0) {
			 sql = "update root SET applymodifyroot=? where useraccount=?";
			 param = new Object[]{null,uaccount};
			 return DBUtil.updateDB(sql, param);
		}
		if(flag==1) {
			sql = "update root SET applyborrowroot=? where useraccount=?";
			 param = new Object[]{null,uaccount};
			 return DBUtil.updateDB(sql, param);
		}
	
		return false;
	}
	
}
