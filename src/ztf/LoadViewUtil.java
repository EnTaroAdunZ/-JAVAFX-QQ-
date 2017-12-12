package ztf;

import java.io.InputStream;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoadViewUtil {
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
