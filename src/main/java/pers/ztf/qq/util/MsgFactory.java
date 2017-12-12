package main.java.pers.ztf.qq.util;

import main.java.pers.ztf.qq.bean.Msg;

/**
 * 
* @Description: 交互信息工厂，主要用于产生互相过程中所需要的对象
* @author ZTF
* @date 2017年12月12日 下午8:46:12 
* @version V1.0
 */
public class MsgFactory {
	public static Msg regist(String name) {
		Msg msg=new Msg();
		msg.setType("register");
		msg.setName(name);
		return msg;
	}

	
	public static Msg registSuccess() {
		Msg msg=new Msg();
		msg.setType("result");
		msg.setCommand("register");
		msg.setState("ok");
		return msg;
	}
	
	public static Msg registFail() {
		Msg msg=new Msg();
		msg.setType("result");
		msg.setCommand("register");
		msg.setState("error");
		return msg;
	}
	
	
	
	public static Msg load(String name) {
		Msg msg=new Msg();
		msg.setType("login");
		msg.setName(name);
		return msg;
	}
	
	
	public static Msg loadSuccess() {
		Msg msg=new Msg();
		msg.setType("result");
		msg.setCommand("message");
		msg.setState("ok");
		return msg;
	}
	
	public static Msg loadFail() {
		Msg msg=new Msg();
		msg.setType("result");
		msg.setCommand("message");
		msg.setState("fail");
		return msg;
	}
	
	public static Msg sendMsg(String message,String from,String to) {
		Msg msg=new Msg();
		msg.setType("message");
		msg.setFrom(from);
		msg.setTo(to);
		msg.setMessage(message);
		return msg;
	}
	
	public static Msg sendMsgSuccess() {
		Msg msg=new Msg();
		msg.setType("result");
		msg.setCommand("message");
		msg.setState("ok");
		return msg;
	}
	
	public static Msg sendMsgFail() {
		Msg msg=new Msg();
		msg.setType("result");
		msg.setCommand("message");
		msg.setState("fail");
		return msg;
	}
	
	public static Msg logout(String name) {
		Msg msg=new Msg();
		msg.setType("logout");
		msg.setName(name);
		return msg;
	}
	
	public static Msg logoutSucces() {
		Msg msg=new Msg();
		msg.setType("result");
		msg.setCommand("logout");
		msg.setState("ok");
		return msg;
	}
	
	public static Msg logoutFail() {
		Msg msg=new Msg();
		msg.setType("result");
		msg.setCommand("logout");
		msg.setState("error");
		return msg;
	}
	
}
