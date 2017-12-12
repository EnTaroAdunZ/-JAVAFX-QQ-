package ztf;

import java.util.HashMap;
import java.util.Map;

//存放客户端信息以及对应端口
public class PortDB {
	
	private static int nowPort=889;
	private static Map<String, Integer> portMap;
	
	static{
		portMap=new HashMap<>();
	}
	
	//获得一个未使用的端口
	public static int getNewPort() {
		return nowPort++;
	}
	
	//名字必须唯一
	public static void setClient(String name,Integer port) {
		if(portMap.containsKey(name)) {
			throw new RuntimeException("用户名不能重复！！！");
		}
		portMap.put(name, port);
	}
	
	public static Integer getPort(String name) {
		return portMap.get(name);
	}

}
