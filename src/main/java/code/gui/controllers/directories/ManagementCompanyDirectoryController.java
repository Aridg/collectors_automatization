package code.gui.controllers.directories;

import code.expandings.GuiForm;
import code.gui.controllers.directories.input_forms.AddManagementCompanyController;
import code.gui.controllers.directories.input_forms.AddRoofingTypeController;
import code.gui.controllers.tickets.InformationAboutTicketController;
import code.gui.controllers.tickets.TicketEditingController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.ManagementCompaniesEntity;
import code.hibernate.RoofingTypesEntity;
import code.hibernate.TicketsVEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Пользователь on 26.04.2017.
 */
public class ManagementCompanyDirectoryController implements IDirectoryController {
    @FXML
    private TableView<ManagementCompaniesEntity> table;
    @FXML
    private TableColumn<ManagementCompaniesEntity, String> name;
    @FXML
    private TableColumn<ManagementCompaniesEntity, String> manager;
    @FXML
    private TableColumn<ManagementCompaniesEntity, String> phone1;

    private Stage addingStage;

    private ObservableList<ManagementCompaniesEntity> data = FXCollections.observableArrayList();

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
        GuiForm<AnchorPane, AddManagementCompanyController> form = new GuiForm<>(new String[]{"directories", "input_forms", "AddManagementCompany.fxml"});
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
        ManagementCompaniesEntity elem = session.createQuery("from ManagementCompaniesEntity where id = :id", ManagementCompaniesEntity.class)
                .setParameter("id", table.getSelectionModel().getSelectedItem().getId())
                .getSingleResult();
        session.delete(elem);
        session.getTransaction().commit();
        data.remove(table.getSelectionModel().getSelectedItem());
        session.close();
    }

    private void getData(boolean need_clear){
        Session session = HibernateSessionFactory.getSession();
        List<ManagementCompaniesEntity> list = session.createQuery("from ManagementCompaniesEntity ", ManagementCompaniesEntity.class)
                .getResultList();
        if(need_clear)
            data.clear();
        data.addAll(list);
        session.close();
    }

    private void tableConfiguration(){
        table.setColumnResizePolicy(param -> false);
        table.setRowFactory(param -> {
            TableRow<ManagementCompaniesEntity> row = new TableRow<>();
            row.setContextMenu(getContextBar(row));
            return row;
        });
        name.setCellValueFactory(param -> param.getValue().namePProperty());
        manager.setCellValueFactory(param -> param.getValue().managerPProperty());
        phone1.setCellValueFactory(param -> param.getValue().phoneProperty());

        table.setItems(data);
    }

    private ContextMenu getContextBar(TableRow<ManagementCompaniesEntity> row){
        ContextMenu toolBar = new ContextMenu();
        MenuItem edit = new MenuItem("Редактировать");
        toolBar.getItems().add(edit);
        edit.setOnAction(event -> {
            if(row.getIndex() >= row.getTableView().getItems().size())
                return;
            ManagementCompaniesEntity company = row.getTableView().getItems().get(row.getIndex());
            GuiForm<AnchorPane, AddManagementCompanyController> form = new GuiForm<>(new String[]{"directories", "input_forms", "AddManagementCompany.fxml"});
            AnchorPane parent = form.getParent();
            AddManagementCompanyController controller = form.getController();
            controller.setCompany(company);
            Scene scene = new Scene(parent);
            addingStage.setScene(scene);
            addingStage.centerOnScreen();
            addingStage.showAndWait();
            getData(true);
        });
        return toolBar;
    }
}
