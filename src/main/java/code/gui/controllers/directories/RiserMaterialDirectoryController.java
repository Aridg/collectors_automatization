package code.gui.controllers.directories;

import code.expandings.GuiForm;
import code.gui.controllers.directories.input_forms.AddMicroDistrictController;
import code.gui.controllers.directories.input_forms.AddRiserTypeController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.MicroDistrictsEntity;
import code.hibernate.RiserTypesEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Пользователь on 26.04.2017.
 */
public class RiserMaterialDirectoryController implements IDirectoryController {
    @FXML
    private TableView<RiserTypesEntity> table;
    @FXML
    private TableColumn<RiserTypesEntity, String> name;


    private Stage addingStage;

    private ObservableList<RiserTypesEntity> data = FXCollections.observableArrayList();

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
        GuiForm<AnchorPane, AddRiserTypeController> form = new GuiForm<>(new String[]{"directories", "input_forms", "AddRiserType.fxml"});
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
        RiserTypesEntity elem = session.createQuery("from RiserTypesEntity where id = :id", RiserTypesEntity.class)
                .setParameter("id", table.getSelectionModel().getSelectedItem().getId())
                .getSingleResult();
        session.delete(elem);
        session.getTransaction().commit();
        data.remove(table.getSelectionModel().getSelectedItem());
        session.close();
    }

    private void getData(boolean need_clear){
        Session session = HibernateSessionFactory.getSession();
        List<RiserTypesEntity> list = session.createQuery("from RiserTypesEntity ", RiserTypesEntity.class)
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
