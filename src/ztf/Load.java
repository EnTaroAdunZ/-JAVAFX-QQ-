package ztf;

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

public class Load extends Application{
	public static void main(String[] args) {
        Application.launch(args);
        System.exit(0);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
        	 Parent root = FXMLLoader.load(getClass().getResource("/ztf/MainWin.fxml"));
             Scene scene = new Scene(root, 800, 400);
             primaryStage.setTitle("实验四 网络编程");
             primaryStage.setScene(scene);
             primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
