package main.java.pers.ztf.qq.server;

import java.net.ServerSocket;
import java.net.Socket;

import main.java.pers.ztf.qq.handle.MasterHandle;

/**
 * 
* @Description: 服务端服务类
* @author ZTF
* @date 2017年12月12日 下午8:42:42 
* @version V1.0
 */
public class MasterService implements Runnable{
	
	private static boolean isStart=false;

	@Override
	public void run() {
    	try {
    		if(!isStart) {
        		ServerSocket serverSocket=new ServerSocket(888);
        		isStart=true;
        		while(true) {
        			Socket socket=serverSocket.accept();
        			//交由SocketHandle处理
        			MasterHandle handle=new MasterHandle(socket);
        			Thread thread=new Thread(handle);
        			thread.start();
        		}
    		}else {
    			System.out.println("服务器已经启动了！");
    		}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
