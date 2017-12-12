package main.java.pers.ztf.qq.controller;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

/**
 * 
* @Description: 主要用于启动应用
* @author ZTF
* @date 2017年12月12日 下午8:01:55 
* @version V1.0
 */
public class Load extends Application{
	public static void main(String[] args) {
        Application.launch(args);
        System.exit(0);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
        	 Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/MainWin.fxml"));
             Scene scene = new Scene(root, 800, 400);
             primaryStage.setTitle("实验四 网络编程 by 201525010627");
             primaryStage.setScene(scene);
             primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
