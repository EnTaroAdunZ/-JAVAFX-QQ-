package main.java.pers.ztf.qq.util;

import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.java.pers.ztf.qq.controller.Load;

/**
 * 
* @Description: 与View有关的工具方法
* @author ZTF
* @date 2017年12月12日 下午8:45:27 
* @version V1.0
 */
public class LoadViewUtil {
	/**
	 * 
	* @Title: replaceSceneContent 
	* @Description: 替换舞台，主要用于登录界面与聊天界面
	* @param fxml
	* @param stage
	* @return
	* @throws Exception
	* @return Initializable
	* @throws
	 */
    public static Initializable replaceSceneContent(String fxml,Stage stage) throws Exception {  
        FXMLLoader loader = new FXMLLoader();  
        InputStream in = Load.class.getResourceAsStream(fxml);  
        loader.setBuilderFactory(new JavaFXBuilderFactory());  
        loader.setLocation(Load.class.getResource(fxml));  
        AnchorPane page;  
        try {  
            page = (AnchorPane) loader.load(in);  
        } finally {  
            in.close();  
        }   
        Scene scene = new Scene(page);  
        stage.setScene(scene);  
        stage.sizeToScene();  
        return (Initializable) loader.getController();  
    }  
}
