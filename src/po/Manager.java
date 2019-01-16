package po;
/**
 * 管理员类
 * 用于封装管理员信息
 * @author liuyoubin
 *
 */
public class Manager implements personnel{
	/**
	 * 1 主键
	 */
	private String mid = null;
	/**
	 * 2 管理员账号
	 */
	private String maccount = null;
	/**
	 *  3 管理员账号密码
	 */
	private String mpwd = null;
	
	public Manager() {
		
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMaccount() {
		return maccount;
	}
	public void setMaccount(String maccount) {
		this.maccount = maccount;
	}
	public String getMpwd() {
		return mpwd;
	}
	public void setMpwd(String mpwd) {
		this.mpwd = mpwd;
	}
	
	
	

}
