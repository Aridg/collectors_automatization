package code.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * Created by Алексей on 08.05.2017.
 */
public interface IController{
    @FXML
    void initialize();

    void setPopUpStage(Stage stage);
}
