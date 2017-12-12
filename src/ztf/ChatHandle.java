package ztf;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

//聊天窗口信息接收后的处理
public class ChatHandle implements Runnable{
	private Socket socket;
	private TableView tb_log;
	public ChatHandle(Socket socket,TableView tb_log) {
		this.socket = socket;
		this.tb_log=tb_log;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
			String server="<"+socket.getInetAddress().toString()+":"+socket.getPort()+">";
			Msg msg=(Msg)objectInputStream.readObject();
			System.out.println(server+"发来信息:"+msg);
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
			if("message".equals(msg.getType())) {
				ObservableList list = tb_log.getItems();
				list.addAll(new LogText(msg.getFrom()+":"+msg.getMessage()));
				tb_log.setItems(list);
			}
			objectOutputStream.writeObject(MsgFactory.sendMsgSuccess());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
