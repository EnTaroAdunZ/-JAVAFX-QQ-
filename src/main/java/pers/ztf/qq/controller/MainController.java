package main.java.pers.ztf.qq.controller;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import main.java.pers.ztf.qq.server.MasterService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;


/**
 * 
* @Description: 
* @author ZTF
* @date 2017年12月12日 下午8:02:06 
* @version V1.0
 */
public class MainController implements Initializable{
	private Load application; 
	
	/**
	 * 
	* @Title: startService 
	* @Description: 服务端，启动一个进程去监听接受端口
	* @param event
	* @return void
	* @throws
	 */
    @FXML  
    public void startService(ActionEvent event) {  
    	Thread thread = new Thread(new MasterService());
    	thread.start();
    }

    /**
     * 
    * @Title: startClient 
    * @Description: 为一个客户端打开新舞台
    * @param event
    * @return void
    * @throws
     */
    @FXML  
    public void startClient(ActionEvent event) { 
    	try {
        	Stage stage=new Stage();
    	   	Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/ClientService.fxml"));
    	    Scene scene = new Scene(root, 500, 350);
    	    stage.setTitle("客户端");
    	    stage.setScene(scene);
    	    stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
