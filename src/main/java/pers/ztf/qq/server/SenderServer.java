package main.java.pers.ztf.qq.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import main.java.pers.ztf.qq.bean.Msg;
import main.java.pers.ztf.qq.common.Global;
import main.java.pers.ztf.qq.controller.ChatWinController;
import main.java.pers.ztf.qq.util.MsgFactory;

/**
 * 
* @Description: 客户端接收端，用户操作客户端交由此类处理
* @author ZTF
* @date 2017年12月12日 下午8:32:24 
* @version V1.0
 */
public class SenderServer implements Callable{
	
	private Socket socket;
	private int op;
	private String context;
	private ChatWinController chatWinControlle;
	private String from;
	private String to;
	private TableView tb_log;
	
	public SenderServer(Socket socket,int op,String context) {
		this.socket=socket;
		this.op=op;
		this.context=context;
	}
	
	
	public SenderServer(Socket socket,int op,String context,String from,String to,TableView tb_log) {
		this.socket=socket;
		this.op=op;
		this.context=context;
		this.from=from;
		this.to=to;
		this.tb_log=tb_log;
	}
	
	public SenderServer(Socket socket,int op) {
		this.socket=socket;
		this.op=op;
	}
	
	/**
	 * 
	* @Title: send 
	* @Description: 发送信息
	* @param msg
	* @return
	* @throws IOException
	* @throws ClassNotFoundException
	* @return Msg
	* @throws
	 */
	public Msg send(Msg msg) throws IOException, ClassNotFoundException {
		ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
		outputStream.writeObject(msg);
		outputStream.flush();
		ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
		Msg result = (Msg)objectInputStream.readObject();
		String back="【服务器"+socket.getPort()+"】返回消息：";
		System.out.println(back+result);
		outputStream.close();
		return result;
	}

	@Override
	public Object call() throws Exception {
		
		try {
			if(op==Global.REGIST) {
				send(MsgFactory.regist(context));
			}else if(op==Global.LOAD){
				Msg result = send(MsgFactory.load(context));
				if("ok".equals(result.getState())) {
					//服务器允许登录，其他操作该步奏忽略
					return true;
				}else {
					return false;
				}
			}else if(op==Global.LOGOUT) {
				send(MsgFactory.logout(context));
			}else if(op==Global.SENDMSG) {
				send(MsgFactory.sendMsg(context, from,to));
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
