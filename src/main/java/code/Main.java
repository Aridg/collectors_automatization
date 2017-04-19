package code;

import com.sun.istack.internal.NotNull;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getGuiForm("TestPath.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private URL getGuiForm(String fileName){
        URL url = this.getClass().getClassLoader().getResource(String.format("gui/%s", fileName));
        if(url == null)
            throw new IllegalArgumentException("FXML file not found!");
        return url;
    }
}
