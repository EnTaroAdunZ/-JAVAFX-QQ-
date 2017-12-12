package ztf;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Window;

public class ChatWinController implements Initializable{
	
	@FXML
	private TextField tf_addressee;
	
	@FXML
	private TableView tb_log;
	@FXML
	private TableColumn col_log;
	@FXML
	private TextArea ta_msg;
	
	@FXML
	private Text text_cur_user;
	
	private Socket socket;
	//模拟标记当前客户端 
	private String name;
	
	//这个方法只能使用一次
	public void setName(String name) {
		this.name=name;
		text_cur_user.setText(name);
    	Thread thread = new Thread(new ChatServer(name,tb_log));
    	thread.start();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		col_log.setCellValueFactory(new PropertyValueFactory("text"));// 映射
	}
	
	@FXML
	public void logout() throws UnknownHostException, IOException {
		socket=new Socket("127.0.0.1",888);
		FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Client(socket,Global.LOGOUT,name));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(futureTask);
		Window window = ta_msg.getScene().getWindow();
		window.hide();
	}
	
	@FXML
	public void sendMsg() throws UnknownHostException, IOException {
		socket=new Socket("127.0.0.1",888);
		FutureTask<Boolean> futureTask = new FutureTask<Boolean>(new Client(socket,Global.SENDMSG,ta_msg.getText(),name,tf_addressee.getText(),tb_log));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		executor.execute(futureTask);
	}
	
}
