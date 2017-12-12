package ztf;

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

public class Client implements Callable{
	
	private Socket socket;
	private int op;
	private String context;
	private ChatWinController chatWinControlle;
	private String from;
	private String to;
	private TableView tb_log;
	
	public Client(Socket socket,int op,String context) {
		this.socket=socket;
		this.op=op;
		this.context=context;
	}
	
	
	public Client(Socket socket,int op,String context,String from,String to,TableView tb_log) {
		this.socket=socket;
		this.op=op;
		this.context=context;
		this.from=from;
		this.to=to;
		this.tb_log=tb_log;
	}
	
	public Client(Socket socket,int op) {
		this.socket=socket;
		this.op=op;
	}
	

	public Msg send(Msg msg) throws IOException, ClassNotFoundException {
		
		ObjectOutputStream outputStream=new ObjectOutputStream(socket.getOutputStream());
		outputStream.writeObject(msg);
		outputStream.flush();
		ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
		Msg result = (Msg)objectInputStream.readObject();
		System.out.println("服务器返回的信息："+result);
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
					return true;
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
