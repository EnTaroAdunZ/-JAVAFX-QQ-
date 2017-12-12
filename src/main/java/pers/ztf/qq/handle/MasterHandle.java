package main.java.pers.ztf.qq.handle;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

import main.java.pers.ztf.qq.bean.Msg;
import main.java.pers.ztf.qq.util.MsgFactory;
import main.java.pers.ztf.qq.util.PortDB;

/**
 * 
 * @Description: 服务端核心处理类，开启一个一个线程处理当前接受的一个请求
 * @author ZTF
 * @date 2017年12月12日 下午8:16:31
 * @version V1.0
 */
public class MasterHandle implements Runnable {
	private Socket socket;

	public MasterHandle(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

			String client ="【客户端端口"+socket.getPort()+"】：";
			Msg msg = (Msg) objectInputStream.readObject();
			System.out.println(client + "发来信息:" + msg);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

			String type = msg.getType();
			// 大部分操作模拟成功执行
			if ("register".equals(type)) {
				// 这里的注册没有任何作用，后台没有保留任何数据
				boolean isOK = true;
				if (isOK) {
					objectOutputStream.writeObject(MsgFactory.registSuccess());
				} else {
					objectOutputStream.writeObject(MsgFactory.registFail());
				}
				objectOutputStream.flush();

			} else if ("login".equals(type)) {
				boolean isOK = true;
				Integer port = PortDB.getPort(msg.getName());
				if (port != null)
					isOK = false;
				if (isOK) {
					objectOutputStream.writeObject(MsgFactory.loadSuccess());
					objectOutputStream.flush();
				} else {
					objectOutputStream.writeObject(MsgFactory.loadFail());
					objectOutputStream.flush();
				}
			} else if ("logout".equals(type)) {
				boolean isOK = true;
				if (isOK) {
					objectOutputStream.writeObject(MsgFactory.logoutSucces());
					objectOutputStream.flush();
				} else {
					objectOutputStream.writeObject(MsgFactory.logoutFail());
					objectOutputStream.flush();
				}
			} else if ("message".equals(type)) {
				String to = msg.getTo();
				// 用用户名寻找本地端口
				Integer port = PortDB.getPort(to);
				// 给发送端返回发送成功的信息
				objectOutputStream.writeObject(MsgFactory.sendMsgSuccess());
				objectOutputStream.flush();
				// 向该用户端口发送信息
				Socket sender = new Socket("127.0.0.1", port);
				ObjectOutputStream toClient = new ObjectOutputStream(sender.getOutputStream());
				toClient.writeObject(msg);
				toClient.flush();
				ObjectInputStream toService = new ObjectInputStream(sender.getInputStream());
				Msg toServiceMsg = (Msg) toService.readObject();
				// 关闭这个端口，每一次发送信息都开关一次
				sender.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
