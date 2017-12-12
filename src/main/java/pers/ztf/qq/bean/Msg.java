package main.java.pers.ztf.qq.bean;

import java.io.Serializable;

/**
* @Description: 客户端、服务端信息封装类
* @author ZTF
* @date 2017年12月12日 下午7:58:30 
* @version V1.0
 */
public class Msg implements Serializable{
	private String from;
	private String to;
	private String message;
	private String type;
	private String name;
	private String state;
	private String command;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder=new StringBuilder();
		stringBuilder.append("<");
		stringBuilder.append(type);
		if (command!=null&&!"".equals(command)) {
			stringBuilder.append(" command=\""+command+"\" ");
		}
		if (from!=null&&!"".equals(from)) {
			stringBuilder.append(" from=\""+from+"\" ");
		}
		if (to!=null&&!"".equals(to)) {
			stringBuilder.append(" to=\""+to+"\" ");
		}
		if (message!=null&&!"".equals(message)) {
			stringBuilder.append(" message=\""+message+"\" ");
		}
		if (name!=null&&!"".equals(name)) {
			stringBuilder.append(" name=\""+name+"\" ");
		}
		if (state!=null&&!"".equals(state)) {
			stringBuilder.append(" state=\""+state+"\" ");
		}
		
		stringBuilder.append(" />");
		return stringBuilder.toString();
	}
	
	
	
	
	
}
