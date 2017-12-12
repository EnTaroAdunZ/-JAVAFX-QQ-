package ztf;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class MainController implements Initializable{
	private Load application; 
    @FXML  
    public void startService(ActionEvent event) {  
    	Thread thread = new Thread(new Service());
    	thread.start();
    }

    
    @FXML  
    public void startClient(ActionEvent event) { 
    	try {
        	Stage stage=new Stage();
    	   	Parent root = FXMLLoader.load(getClass().getResource("/ztf/ClientService.fxml"));
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
