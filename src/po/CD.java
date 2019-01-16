package po;

import java.util.Date;

/**
 * 光盘类
 * 用于封装光盘信息
 * @author liuyoubin
 *
 */
public class CD {
	/**
	 * 光盘编号（主键）
	 */
	private String cid = null;
	/**
	 * 光盘名称
	 */
	private String cname = null;
	/**
	 * 借阅者账号（外键）
	 */
	private String borrowed = null;
	/**
	 * 借阅时间
	 */
	private Date btime = null;
	/**
	 * 光盘类型
	 */
	private String type = null;
	
	public CD() {
		
	}
	

	public CD(String cid, String cname, String borrowed, Date btime, String type) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.borrowed = borrowed;
		this.btime = btime;
		this.type = type;
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getBorrowed() {
		return borrowed;
	}
	public void setBorrowed(String borrowed) {
		this.borrowed = borrowed;
	}
	public Date getBtime() {
		return btime;
	}
	public void setBtime(Date btime) {
		this.btime = btime;
	}
	
	
	
}
