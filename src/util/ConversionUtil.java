package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ManagerDao.ManagerOperation;
import po.CD;
import po.Record;
import po.Root;
import po.User;

/**
 * 封装了转换数据形式的方法
 * @author liuyoubin
 *
 */
public class ConversionUtil {
	/**
	 * 将光盘列表里的光盘对象转换成【用户】表格所需要的Object[][]数组
	 * @param cd CD数组
	 * @return Object[][]
	 */
	public static Object[][] cDToArray(List<CD> cdList){
		//
		Object[][] obj = new Object[cdList.size()][4];
		/**
		 * 遍历CD数组
		 */
		int i = 0;
		for(CD c: cdList) {
			obj[i][0] = c.getCid();//获取光盘编号
			obj[i][1] = c.getCname();//获取光盘名称
			obj[i][2] = c.getType();//获取光盘类型
			//判断是否已借阅
			if(c.getBorrowed()!=null) {
				obj[i][3] = "已借阅";
			}else {
				obj[i][3] = "可借阅";
			}
			i++;
		}
		
		
		return obj;
	}
	/**
	 * 将光盘列表里的光盘对象转换成【管理员】表格所需要的Object[][]数组
	 * @param cd CD数组
	 * @return Object[][]
	 */
	public static Object[][] cDToArray_Manager(List<CD> cdList){
		//
		Object[][] obj = new Object[cdList.size()][4];
		/**
		 * 遍历CD数组
		 */
		int i = 0;
		for(CD c: cdList) {
			obj[i][0] = c.getCid();//获取光盘编号
			obj[i][1] = c.getCname();//获取光盘名称
			obj[i][2] = c.getType();//获取光盘类型
			if(c.getBorrowed()==null) {
				obj[i][3] = "当前无人借阅";
			}else if(c.getBorrowed().equals("")) {
				obj[i][3] = "当前无人借阅";
			}else {
				obj[i][3] = c.getBorrowed();
			}
			i++;
		}
		
		
		return obj;
	}
	
	/**
	 * 将用户列表里的用户对象转换成【管理员】表格所需要的Object[][]数组
	 * @param cd CD数组
	 * @return Object[][]
	 */
	public static Object[][] userToArray_Manager(List<User> userList){
		//
		Object[][] obj = new Object[userList.size()][6];
		/**
		 * 遍历用户列表
		 */
		int i = 0;
		for(User user: userList) {
			obj[i][0] = user.getUaccount();
			obj[i][1] = user.getUpwd();
			obj[i][2] = user.getUname();
			obj[i][3] = Integer.toString(user.getAge());
			obj[i][4] = user.getSex();
			obj[i][5] = StringUtil.DataToString(user.getBirthday());
			i++;
		}
		
		
		return obj;
	}
	/**
	 * 将光盘数组转换成统计表所需的Map
	 * @param cdList
	 * @return
	 */
	public static Map<String, Integer> statistcsCDs(List<Record> recordList) {
		//创建map
		Map<String, Integer> recordMap = new HashMap<String, Integer>();
		ManagerOperation manaop = new ManagerOperation();
		
		String [] cDType = {"文学","娱乐","音乐","教育","历史","科普","惊悚"}; 

		for(String key:cDType) {
			recordMap.put(key, 0);
		}
		
		for(Record record:recordList) {
			if(record.getAction().equals("借阅")) {
			CD cd = manaop.queryCDByCid(record.getCdId());
			recordMap.put(cd.getType(), recordMap.get(cd.getType())+1);
			}
		}

		return recordMap;
	}
	
}
