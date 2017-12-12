package ztf;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;


public class SocketHandle implements Runnable{
	private Socket socket;

		
	public SocketHandle(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream objectInputStream=new ObjectInputStream(socket.getInputStream());
		
			String client="<"+socket.getInetAddress().toString()+":"+socket.getPort()+">";
			Msg msg=(Msg)objectInputStream.readObject();
			System.out.println(client+"发来信息:"+msg);
			
			//这里模拟可否注册，默认可以注册
			ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
			
			String type = msg.getType();
			
			if("register".equals(type)) {
				boolean isOK=true;
				if(isOK) {
					objectOutputStream.writeObject(MsgFactory.registSuccess());
					
				}else {
					objectOutputStream.writeObject(MsgFactory.registFail());
				}
				objectOutputStream.flush();
				
			}else if("login".equals(type)) {
				boolean isOK=true;
				if(isOK) {
					objectOutputStream.writeObject(MsgFactory.loadSuccess());
					objectOutputStream.flush();
				}
			}else if("logout".equals(type)) {
				boolean isOK=true;
				if(isOK) {
					objectOutputStream.writeObject(MsgFactory.logoutSucces());
					objectOutputStream.flush();
				}else {
					objectOutputStream.writeObject(MsgFactory.logoutFail());
					objectOutputStream.flush();
				}
			}else if("message".equals(type)) {
				String to = msg.getTo();
				Integer port = PortDB.getPort(to);
				objectOutputStream.writeObject(MsgFactory.sendMsgSuccess());
				objectOutputStream.flush();
				//向该用户端口发送信息
				Socket sender=new Socket("127.0.0.1",port);
				ObjectOutputStream toClient = new ObjectOutputStream(sender.getOutputStream());
				toClient.writeObject(msg);
				toClient.flush();
				ObjectInputStream toService = new ObjectInputStream(sender.getInputStream());
				Msg toServiceMsg=(Msg)toService.readObject();
				System.out.println(port+":"+toServiceMsg);
				sender.close();
			}
			
			
			
			
			} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
