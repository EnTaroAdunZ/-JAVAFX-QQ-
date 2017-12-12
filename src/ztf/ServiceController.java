package ztf;

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

public class ServiceController implements Initializable{
	
	private Socket socket;
	
	@FXML
	TextField client_name;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void regist() throws UnknownHostException, IOException {
		socket=new Socket("127.0.0.1",888);
		FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Client(socket,Global.REGIST,client_name.getText()));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(futureTask);
		 
	}
	
	@FXML
	public void loadOn() throws Exception {
		socket=new Socket("127.0.0.1",888);
		String user_name = client_name.getText();
		FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Client(socket,Global.LOAD,user_name));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(futureTask);
		Boolean isOk = futureTask.get();
		if(isOk) {
			ChatWinController chatWinController=(ChatWinController) LoadViewUtil
					.replaceSceneContent("/ztf/ChatWin.fxml", (Stage)client_name.getScene().getWindow());
			chatWinController.setName(user_name);

		}else {
			System.out.println("登录失败");
		}
	}
	


}
