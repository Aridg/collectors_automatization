package code.gui.controllers.directories.input_forms;

import code.expandings.Utils;
import code.gui.controllers.directories.IDirectoryController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.MicroDistrictsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 * Created by Пользователь on 26.04.2017.
 */
public class AddMicroDistrictController implements IDirectoryController {
    @FXML
    private TextField name;

    @Override
    public void initialize() {

    }

    @Override
    public void setPopUpStage(Stage stage) {

    }


    public void onAddClick(ActionEvent event) {
        if(Utils.isNullOrEmpty(name.getText())){
            new Alert(Alert.AlertType.ERROR, "Заполните название").showAndWait();
            return;
        }
        MicroDistrictsEntity element = new MicroDistrictsEntity();
        element.setMicroDistrict(name.getText());
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        session.save(element);
        session.getTransaction().commit();
        session.close();
        ((Stage)name.getScene().getWindow()).close();
    }
}
