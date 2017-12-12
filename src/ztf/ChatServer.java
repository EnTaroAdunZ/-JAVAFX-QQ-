package ztf;

import java.net.ServerSocket;
import java.net.Socket;

import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class ChatServer implements Runnable{
	
	private Integer port;
	private String name;
	private TableView tb_log;
	
	public ChatServer(String name,TableView tb_log) {
		this.name=name;
		this.tb_log=tb_log;
	}
	
	@Override
	public void run() {
		try {
			port=PortDB.getNewPort();
			PortDB.setClient(name, port);
			ServerSocket serverSocket=new ServerSocket(port);
			
			System.out.println("端口"+port+"，用户"+name+"启动");
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
