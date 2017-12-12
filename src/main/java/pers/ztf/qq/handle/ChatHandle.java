package main.java.pers.ztf.qq.handle;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import main.java.pers.ztf.qq.bean.LogText;
import main.java.pers.ztf.qq.bean.Msg;
import main.java.pers.ztf.qq.util.MsgFactory;

/**
 * 
* @Description: 客户端接收信息后处理类
* @author ZTF
* @date 2017年12月12日 下午8:57:58 
* @version V1.0
 */
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
			String server="【服务器端口"+socket.getPort()+"】：";
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
