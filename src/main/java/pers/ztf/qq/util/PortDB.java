package main.java.pers.ztf.qq.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @Description: 端口管理类
* @author ZTF
* @date 2017年12月12日 下午8:46:54 
* @version V1.0
 */
public class PortDB {
	
	private static int nowPort=889;
	private static Map<String, Integer> portMap;
	
	static{
		portMap=new HashMap<>();
	}
	
	/**
	 * 
	* @Title: getNewPort 
	* @Description: 获得一个未使用的端口
	* @return
	* @return int
	* @throws
	 */
	public static int getNewPort() {
		return nowPort++;
	}
	
	/**
	 * 
	* @Title: setClient 
	* @Description:注册一个新端口 ,名字必须唯一
	* @param name
	* @param port
	* @return void
	* @throws
	 */
	public static void setClient(String name,Integer port) {
		if(portMap.containsKey(name)) {
			throw new RuntimeException("用户名不能重复！！！");
		}
		portMap.put(name, port);
	}
	
	/**
	 * 
	* @Title: getPort 
	* @Description: 根据用户名返回端口
	* @param name
	* @return
	* @return Integer
	* @throws
	 */
	public static Integer getPort(String name) {
		return portMap.get(name);
	}

}
