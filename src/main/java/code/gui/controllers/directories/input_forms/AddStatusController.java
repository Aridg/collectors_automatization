package code.gui.controllers.directories.input_forms;

import code.expandings.Utils;
import code.gui.controllers.directories.IDirectoryController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.StatusesEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 * Created by Пользователь on 24.04.2017.
 */
public class AddStatusController implements IDirectoryController {

    @FXML private TextField name;
    @FXML private TextField date;
    @FXML private CheckBox notif;
    @FXML private TextField notifText;

    private StatusesEntity status;

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
        else if(!date.getText().matches("[0-9]*")){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Задержкой могут быть только цифры");
            alert.showAndWait();
            return;
        }
        else if(notif.isSelected() && (Utils.isNullOrEmpty(date.getText()) || Utils.isNullOrEmpty(notifText.getText()))){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Введите задержку и текст уведомления");
            alert.showAndWait();
            return;
        }

        boolean update = true;
        if(status == null){
            update = false;
            status = new StatusesEntity();
        }
        status.setName(name.getText());
        status.setDelay(Utils.isNullOrEmpty(date.getText()) ? null : Integer.valueOf(date.getText()));
        status.setEnableNotification(notif.isSelected() ? 1 : 0);
        status.setNotificationText(notifText.getText());
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        if(update)
            session.update(status);
        else session.save(status);
        session.getTransaction().commit();
        session.close();
        ((Stage)name.getScene().getWindow()).close();
    }

    public void setStatus(StatusesEntity status) {
        this.status = status;
        name.setText(status.getName());
        date.setText(Utils.convertToText(status.getDelay()));
        notif.setSelected(status.getEnableNotification() == 1 ? true : false);
        notifText.setText(Utils.convertToText(status.getNotificationText()));
    }
}
