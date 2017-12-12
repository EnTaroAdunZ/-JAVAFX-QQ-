package main.java.pers.ztf.qq.server;

import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import main.java.pers.ztf.qq.handle.ChatHandle;
import main.java.pers.ztf.qq.util.PortDB;

/**
 * 
* @Description: 客户端接收服务，用于接收从其他客户端来的信息
* @author ZTF
* @date 2017年12月12日 下午8:23:21 
* @version V1.0
 */
public class ReceiverServer implements Runnable{
	
	private Integer port;
	private String name;
	private TableView tb_log;
	
	public ReceiverServer(String name,TableView tb_log) {
		this.name=name;
		this.tb_log=tb_log;
	}
	
	@Override
	public void run() {
		try {
			//得到一个没有使用过的端口
			port=PortDB.getNewPort();
			//反馈给端口用户名信息
			PortDB.setClient(name, port);
			ServerSocket serverSocket=new ServerSocket(port);
			while(true) {
				Socket socket=serverSocket.accept();
				ChatHandle handle=new ChatHandle(socket,tb_log);
				Thread thread=new Thread(handle);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
