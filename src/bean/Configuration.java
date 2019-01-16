package bean;
/**
  *  用于封装将配置文件的配置信息，便于程序使用配置信息
 * @author liuyoubin
 *
 */
public class Configuration {
	
	/**
	 * 数据库驱动
	 */
	private String driver = null;
	/**
	 * 数据库url
	 */
	private String url = null;
	/**
	 * 数据库用户名
	 */
	private String ures = null;
	/**
	 * 数据库密码
	 */
	private String pwd = null;
	
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUres() {
		return ures;
	}
	public void setUres(String ures) {
		this.ures = ures;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
}
