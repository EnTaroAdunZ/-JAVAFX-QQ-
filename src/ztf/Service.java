package ztf;

import java.net.ServerSocket;
import java.net.Socket;

public class Service implements Runnable{
	
	private static boolean isStart=false;

	@Override
	public void run() {
    	try {
    		if(!isStart) {
        		ServerSocket serverSocket=new ServerSocket(888);
        		System.out.println("服务器启动");
        		isStart=true;
        		while(true) {
        			Socket socket=serverSocket.accept();
        			SocketHandle handle=new SocketHandle(socket);
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
