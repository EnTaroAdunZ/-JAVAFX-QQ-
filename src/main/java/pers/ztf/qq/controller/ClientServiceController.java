package main.java.pers.ztf.qq.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import main.java.pers.ztf.qq.common.Global;
import main.java.pers.ztf.qq.server.SenderServer;
import main.java.pers.ztf.qq.util.LoadViewUtil;

/**
 * 
* @Description: 登录界面控制器
* @author ZTF
* @date 2017年12月12日 下午8:13:01 
* @version V1.0
 */
public class ClientServiceController implements Initializable{
	
	private Socket socket;
	
	@FXML
	TextField client_name;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	/**
	 * 
	* @Title: regist 
	* @Description: 用一个新进程去注册
	* @throws UnknownHostException
	* @throws IOException
	* @return void
	* @throws
	 */
	@FXML
	public void regist() throws UnknownHostException, IOException {
		socket=new Socket("127.0.0.1",888);
		FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new SenderServer(socket,Global.REGIST,client_name.getText()));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(futureTask);
		 
	}
	
	/**
	 * 
	* @Title: loadOn 
	* @Description: 用一个新进程去发送登录信息，如果返回登录成功，则打开新界面
	* @throws Exception
	* @return void
	* @throws
	 */
	@FXML
	public void loadOn() throws Exception {
		socket=new Socket("127.0.0.1",888);
		String user_name = client_name.getText();
		FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new SenderServer(socket,Global.LOAD,user_name));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(futureTask);
		Boolean isOk = futureTask.get();
		if(isOk) {
			ChatWinController chatWinController=(ChatWinController) LoadViewUtil
					.replaceSceneContent("/resource/fxml/ChatWin.fxml", (Stage)client_name.getScene().getWindow());
			chatWinController.setName(user_name);

		}else {
			System.out.println("登录失败");
		}
	}
	


}
