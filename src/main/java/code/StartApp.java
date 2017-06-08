package code;

import code.expandings.GuiForm;
import code.gui.controllers.MainFormDirectoryController;
import code.hibernate.HibernateSessionFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class StartApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Запуск в фоновом потоке инициализацию подключения к базе
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Файл базы SQLite (*.db)", "*.db");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file == null){
            new Alert(Alert.AlertType.ERROR, "Не выбрана база данных!").showAndWait();
            return;
        }
        new Thread(() -> {
            HibernateSessionFactory.init(file.getAbsolutePath());
        }).start();


        GuiForm<AnchorPane, MainFormDirectoryController> mainForm = new GuiForm<>("MainForm.fxml");
        AnchorPane root = mainForm.getParent();
        MainFormDirectoryController controller = mainForm.getController();
        controller.setPopUpStage(primaryStage);

        primaryStage.setTitle("Управление заявками");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            HibernateSessionFactory.shutdown();
            System.exit(0);
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
