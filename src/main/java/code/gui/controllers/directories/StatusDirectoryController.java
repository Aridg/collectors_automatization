package code.gui.controllers.directories;

import code.expandings.GuiForm;
import code.expandings.Utils;
import code.gui.controllers.directories.input_forms.AddStatusController;
import code.gui.controllers.tickets.InformationAboutTicketController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.MicroDistrictsEntity;
import code.hibernate.StatusesEntity;
import code.hibernate.TicketsVEntity;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Пользователь on 23.04.2017.
 */
public class StatusDirectoryController implements IDirectoryController {
    @FXML
    private TableView<StatusesEntity> table;
    @FXML
    private TableColumn<StatusesEntity, String> nameCol;
    @FXML
    private TableColumn<StatusesEntity, Boolean> notifCol;
    @FXML
    private TableColumn<StatusesEntity, Integer> notifDelayCol;
    @FXML
    private TableColumn<StatusesEntity, String> notifTextCol;
    @FXML
    private Button delButton;
    @FXML
    private Button addButton;

    private Stage addingStage;

    private ObservableList<StatusesEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize() {
        getData(false);
        tableConfiguration();
    }

    public void onDelClick(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if(selectedIndex < 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Выберите строку для удаления");
            alert.showAndWait();
            return;
        }
        Session session = HibernateSessionFactory.getSession();
        long count = session.createQuery("select count(*) from TicketsEntity where statusId = :status", Long.class)
                .setParameter("status", table.getSelectionModel().getSelectedItem().getId())
                .getSingleResult();
        if(count > 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Статус используется!");
            alert.show();
            session.close();
            return;
        }
        session.beginTransaction();
        StatusesEntity group = session.createQuery("from StatusesEntity where id = :id", StatusesEntity.class)
                .setParameter("id", table.getSelectionModel().getSelectedItem().getId())
                .getSingleResult();
        session.delete(group);
        session.getTransaction().commit();
        data.remove(table.getSelectionModel().getSelectedItem());
        session.close();
    }

    public void onAddClick(ActionEvent event) {
        GuiForm<AnchorPane, AddStatusController> form = new GuiForm<>(new String[]{"directories", "input_forms", "AddStatus.fxml"});
        Scene scene = new Scene(form.getParent());
        addingStage.setScene(scene);
        addingStage.showAndWait();
        getData(true);
    }

    private void getData(boolean need_clear){
        Session session = HibernateSessionFactory.getSession();
        List<StatusesEntity> list = session.createQuery("from StatusesEntity ", StatusesEntity.class)
                .getResultList();
        if(need_clear)
            data.clear();
        data.addAll(list);
        session.close();
    }
    private void tableConfiguration(){
        table.setColumnResizePolicy(param -> false);
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() >= 2){
                StatusesEntity status = table.getSelectionModel().getSelectedItem();
                GuiForm<AnchorPane, AddStatusController> form = new GuiForm<>(new String[]{"directories", "input_forms", "AddStatus.fxml"});
                form.getController().setStatus(status);
                Scene scene = new Scene(form.getParent());
                addingStage.setScene(scene);
                addingStage.showAndWait();
                getData(true);
            }
        });
        nameCol.setCellValueFactory(param -> param.getValue().namePProperty());
        nameCol.setCellFactory(getLineBreakForStringColumn());

        notifCol.setCellValueFactory(param -> {
            StatusesEntity status = param.getValue();
            SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(status.isEnableNotificationP());
            booleanProperty.addListener((observable, oldValue, newValue) -> {
                status.setEnableNotificationP(newValue);
                status.setEnableNotification(newValue ? 1 : 0);
            });
            return booleanProperty;
        });
        notifCol.setCellFactory(param -> {
            CheckBoxTableCell<StatusesEntity, Boolean> cell = new CheckBoxTableCell<>();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        notifDelayCol.setCellValueFactory(param -> param.getValue().delayPProperty().asObject());
        notifTextCol.setCellValueFactory(param -> param.getValue().notificationTextPProperty());
        notifTextCol.setCellFactory(getLineBreakForStringColumn());
        table.setItems(data);
    }

    public void setPopUpStage(Stage addingStage) {
        this.addingStage = addingStage;
    }

    private Callback<TableColumn<StatusesEntity, String>, TableCell<StatusesEntity, String>> getLineBreakForStringColumn(){
        return param -> {
            TableCell<StatusesEntity, String> cellName = new TableCell<>();
            Text styleText = new Text();
            styleText.setStyle("");
            cellName.setGraphic(styleText);
            cellName.setPrefHeight(Control.USE_COMPUTED_SIZE);
            styleText.textProperty().bind(cellName.itemProperty());
            styleText.wrappingWidthProperty().bind(cellName.widthProperty());
            cellName.setAlignment(Pos.BASELINE_CENTER);
            return cellName;
        };
    }
}
