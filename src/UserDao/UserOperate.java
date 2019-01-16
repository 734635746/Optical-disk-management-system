package UserDao;

import java.util.Date;
import java.util.List;

import core.DBManager;
import po.CD;
import po.Record;
import po.Root;
import po.User;
import util.DBUtil;
import util.QueryUtil;

/**
 * 存放用户操作的方法
 * @author liuyoubin
 *
 */
public class UserOperate implements userOperateInt{
	/**
	 * 修改用户信息
	 */
	@Override
	public boolean modifyPersonalInfo(User user) {
		//创建sql语句
		String sql = "update user SET upwd=?,uname=?,age=?,sex=?,birthday=? where uaccount=?";
		//创建参数数组
		Object[] param = new Object[]{user.getUpwd(),user.getUname(),user.getAge(),user.getSex(),user.getBirthday(),user.getUaccount()};
		//调用更新方法
		return  DBUtil.updateDB(sql, param);
		
	}
	/**
	 * 根据光盘编号查询一个光盘是否存在
	 */
	@Override
	public CD queryCD(String cid) {
		//创建sql语句
		String sql = "select * from cd where cid=?";
		//创建参数数组
		Object[] param = new Object[] {cid};
		//调用查询方法
		return QueryUtil.checkCdByID(sql, param);
		
		
	}
	/**
	 * 根据光盘名称查询一个光盘列表
	 */
	@Override
	public List<CD> queryCDs(String cname) {
		//创建sql语句
		String sql = "select * from cd where cname=?";
		//创建参数数组
		Object[] param = new Object[] {cname};
		//调用查询方法
		return (List<CD>) QueryUtil.checkCdByName(sql, param);
				
	}
	/**
	 * 根据光盘类型查询一组光盘
	 */
	@Override
	public List<CD> queryCDsWithType(String type) {
		//创建sql语句
		String sql = "select * from cd where type=?";
		//创建参数数组
		Object[] param = new Object[] {type};
		//调用查询方法
		return (List<CD>) QueryUtil.checkCdByName(sql, param);
	}
	/**
	 * 查看可借阅光盘
	 */
	@Override
	public List<CD> queryCDsCouldBeBorrowed() {
		//创建sql语句
		String sql = "select * from cd";
		//创建参数数组
		Object[] param = new Object[]{};
		//调用查询方法
		return (List<CD>) QueryUtil.checkCdCouldBeBorrowed(sql, param);
		
		
	}
	/**
	 * 查看已借阅的光盘
	 */
	@Override
	public List<CD> queryCDsHaveBeBorrowed(String uname) {
		//创建sql语句
		String sql = "select * from cd where borrowed=? ";
		//创建参数数组
		Object[] param = new Object[]{uname};
		//调用查询方法
		return (List<CD>) QueryUtil.checkCdHaveBeBorrowed(sql, param);
	}
	
	@Override
	/**
	 * 借阅光盘
	 */
	public boolean borrowCD(CD cd, User user) {
		//创建sql语句
		String sql = "update cd SET borrowed=?,btime=? where cid=?";
		//创建参数数组
		Object[] param = new Object[]{user.getUaccount(),new Date(),cd.getCid()};
		//调用更新方法
		return  DBUtil.updateDB(sql, param);
	}
	/**
	 * 归还光盘
	 */
	@Override
	public boolean returnCD(CD cd) {
		//创建sql语句
		String sql = "update cd SET borrowed=?,btime=? where cid=?";
		//创建参数数组
		Object[] param = new Object[]{null,null,cd.getCid()};
		//调用更新方法
		return  DBUtil.updateDB(sql, param);
	}
	/**
	 * 借阅成功，更新记录表
	 */
	@Override
	public boolean recordBorrowCD(User user, CD cd) {
		//创建sql语句
		String sql = "insert into record (useraccount,action,btime,cdid) values(?,?,?,?)";
		//创建参数数组
		Object[] param = new Object[]{user.getUaccount(),"借阅",new Date(),cd.getCid()};
		//调用插入方法
		return DBUtil.updateDB(sql, param);
	}
	/**
	 * 归还成功更新记录表
	 */
	@Override
	public boolean recordReturnCD(User user, CD cd) {
		//创建sql语句
		String sql = "insert into record (useraccount,action,btime,cdid) values(?,?,?,?)";
		//创建参数数组
		Object[] param = new Object[]{user.getUaccount(),"归还",new Date(),cd.getCid()};
		//调用插入方法
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
	 * 申请修改权限
	 */
	@Override
	public boolean applyModifyRoot(String uaccount) {
		//创建sql语句
		String sql = "update root SET applymodifyroot='申请' where useraccount=?";
		//创建参数数组
		Object[] param = new Object[]{uaccount};
		//调用查询方法
		return DBUtil.updateDB(sql, param);
	}
	/**
	 * 申请借阅权限
	 */
	@Override
	public boolean applyBorrowRoot(String uaccount) {
		//创建sql语句
		String sql = "update root SET applyborrowroot='申请' where useraccount=?";
		//创建参数数组
		Object[] param = new Object[]{uaccount};
		//调用查询方法
		return DBUtil.updateDB(sql, param);
	}
}
