package po;

import java.util.Date;

/**
 * 用户类
 * 用于封装用户信息
 * @author liuyoubin
 *
 */
public class User implements personnel{
	/**
	 * 用户账号（主键）
	 */
	private String uaccount = null;
	/**
	 * 用户密码
	 */
	private String upwd = null;
	/**
	 * 用户姓名
	 */
	private String uname = null;
	/**
	 * 用户年龄
	 */
	private int age = 0;
	/**
	 * 用户性别
	 */
	private String sex = null;
	/**
	 * 用户出生日期
	 */
	private Date birthday = null;
	
	public User() {
	}

	public User(String uaccount, String upwd, String uname, int age, String sex, Date birthday) {
		this.uaccount = uaccount;
		this.upwd = upwd;
		this.uname = uname;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
	}



	public String getUaccount() {
		return uaccount;
	}

	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
