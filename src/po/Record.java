package po;

import java.util.Date;

/**
 * 借阅记录类
 * 用于封装借阅记录
 * @author liuyoubin
 *
 */
public class Record {
	/**
	 * 记录序号（主键）
	 */
	private int id;
	/**
	 * 借阅/归还者账号
	 */
	private String userAccount;
	/**
	 * 行为方式（借阅/归还）
	 */
	private String action;
	/**
	 * 光盘编号
	 */
	private String cdId;
	/**
	 * 借阅/归还时间
	 */
	private Date btime;
	
	
	public Record() {
	}
	
	
	public Record(int id, String userAccount, String action, String cdId, Date btime) {
		super();
		this.id = id;
		this.userAccount = userAccount;
		this.action = action;
		this.cdId = cdId;
		this.btime = btime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getCdId() {
		return cdId;
	}
	public void setCdId(String cdId) {
		this.cdId = cdId;
	}
	public Date getBtime() {
		return btime;
	}
	public void setBtime(Date btime) {
		this.btime = btime;
	}
	
	
	

}
