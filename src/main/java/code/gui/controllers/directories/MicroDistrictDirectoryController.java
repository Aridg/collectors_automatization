package code.gui.controllers.directories;

import code.expandings.GuiForm;
import code.gui.controllers.directories.input_forms.AddMicroDistrictController;
import code.gui.controllers.directories.input_forms.AddStatusController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.MicroDistrictsEntity;
import code.hibernate.StatusesEntity;
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
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Пользователь on 26.04.2017.
 */
public class MicroDistrictDirectoryController implements IDirectoryController {
    @FXML private TableView<MicroDistrictsEntity> table;
    @FXML private TableColumn<MicroDistrictsEntity, String> name;


    private Stage addingStage;

    private ObservableList<MicroDistrictsEntity> data = FXCollections.observableArrayList();

    @Override
    public void initialize() {
        getData(false);
        tableConfiguration();
    }

    @Override
    public void setPopUpStage(Stage stage) {
        this.addingStage = stage;
    }


    public void onAddClick(ActionEvent event) {
        GuiForm<AnchorPane, AddMicroDistrictController> form = new GuiForm<>(new String[]{"directories", "input_forms", "AddMicroDistrict.fxml"});
        Scene scene = new Scene(form.getParent());
        addingStage.setScene(scene);
        addingStage.showAndWait();
        getData(true);
    }

    public void onDelClick(ActionEvent event) {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if(selectedIndex < 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Выберите строку для удаления");
            alert.showAndWait();
            return;
        }
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        MicroDistrictsEntity elem = session.createQuery("from MicroDistrictsEntity where id = :id", MicroDistrictsEntity.class)
                .setParameter("id", table.getSelectionModel().getSelectedItem().getId())
                .getSingleResult();
        session.delete(elem);
        session.getTransaction().commit();
        data.remove(table.getSelectionModel().getSelectedItem());
        session.close();
    }

    private void getData(boolean need_clear){
        Session session = HibernateSessionFactory.getSession();
        List<MicroDistrictsEntity> list = session.createQuery("from MicroDistrictsEntity ", MicroDistrictsEntity.class)
                .getResultList();
        if(need_clear)
            data.clear();
        data.addAll(list);
        session.close();
    }

    private void tableConfiguration(){
        table.setColumnResizePolicy(param -> false);
        name.setCellValueFactory(param -> param.getValue().namePProperty());
        table.setItems(data);
    }

}
