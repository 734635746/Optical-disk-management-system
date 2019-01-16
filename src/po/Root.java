package po;
/**
 * 权限类，用于封装权限信息
 * @author liuyoubin
 *
 */
public class Root {
	/**
	 * 权限编号（主键）
	 */
	private int id;
	/**
	 * 权限用户对象账号（外键）
	 */
	private String userAccount;
	/**
	 * 修改个人信息权限
	 */
	private String modifyRoot;
	/**
	 * 借阅光盘权限
	 */
	private String borrowRoot;
	/**
	 * 申请修改个人信息权限
	 */
	private String applyModifyRoot;
	/**
	 * 申请借阅个人信息权限
	 */
	private String applyBorrowRoot;
	public Root() {
		
	}
	

	
	public String getApplyModifyRoot() {
		return applyModifyRoot;
	}

	public void setApplyModifyRoot(String applyModifyRoot) {
		this.applyModifyRoot = applyModifyRoot;
	}

	public String getApplyBorrowRoot() {
		return applyBorrowRoot;
	}

	public void setApplyBorrowRoot(String applyBorrowRoot) {
		this.applyBorrowRoot = applyBorrowRoot;
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
	public String getModifyRoot() {
		return modifyRoot;
	}
	public void setModifyRoot(String modifyRoot) {
		this.modifyRoot = modifyRoot;
	}
	public String getBorrowRoot() {
		return borrowRoot;
	}
	public void setBorrowRoot(String borrowRoot) {
		this.borrowRoot = borrowRoot;
	}
	
	
	
}
