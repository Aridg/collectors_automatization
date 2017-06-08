package code.gui.controllers;

import code.expandings.GuiForm;
import code.gui.controllers.directories.*;
import code.gui.controllers.notifications.NotificationsController;
import code.gui.controllers.tickets.TicketsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Пользователь on 23.04.2017.
 */
public class MainFormDirectoryController implements IDirectoryController{
    @FXML
    private AnchorPane contentPane;
    private Stage addingStage;
    private Stage primaryStage;

    @FXML
    public void initialize() {
        addingStage = new Stage();
        addingStage.setResizable(false);
        addingStage.initModality(Modality.APPLICATION_MODAL);
        addingStage.initOwner(primaryStage);
    }

    @Override
    public void setPopUpStage(Stage stage) {
        primaryStage = stage;
    }



    public void onStatusClick(ActionEvent event) {
        GuiForm<AnchorPane, StatusDirectoryController> form = new GuiForm<>(new String[]{"directories", "StatusDirectory.fxml"});
        AnchorPane parent = form.getParent();
        StatusDirectoryController controller = form.getController();
        controller.setPopUpStage(addingStage);
        setContentPaneContent(parent);
    }

    public void onMicroDistrictClick(ActionEvent event) {
        GuiForm<AnchorPane, MicroDistrictDirectoryController> form = new GuiForm<>(new String[]{"directories", "MicroDistrictDirectory.fxml"});
        AnchorPane parent = form.getParent();
        MicroDistrictDirectoryController controller = form.getController();
        controller.setPopUpStage(addingStage);
        setContentPaneContent(parent);
    }

    public void onBuildingMaterialClick(ActionEvent event) {
        GuiForm<AnchorPane, BuildingMaterialDirectoryController> form = new GuiForm<>(new String[]{"directories", "BuildingMaterialDirectory.fxml"});
        AnchorPane parent = form.getParent();
        BuildingMaterialDirectoryController controller = form.getController();
        controller.setPopUpStage(addingStage);
        setContentPaneContent(parent);
    }

    public void onRiserMaterialClick(ActionEvent event) {
        GuiForm<AnchorPane, RiserMaterialDirectoryController> form = new GuiForm<>(new String[]{"directories", "RiserTypeDirectory.fxml"});
        AnchorPane parent = form.getParent();
        RiserMaterialDirectoryController controller = form.getController();
        controller.setPopUpStage(addingStage);
        setContentPaneContent(parent);
    }

    public void onRoofingTypeClick(ActionEvent event) {
        GuiForm<AnchorPane, RoofingTypeDirectoryController> form = new GuiForm<>(new String[]{"directories", "RoofingTypeDirectory.fxml"});
        AnchorPane parent = form.getParent();
        RoofingTypeDirectoryController controller = form.getController();
        controller.setPopUpStage(addingStage);
        setContentPaneContent(parent);
    }

    public void onManagmentCompanyClick(ActionEvent event) {
        GuiForm<AnchorPane, ManagementCompanyDirectoryController> form = new GuiForm<>(new String[]{"directories", "ManagementCompanyDirectory.fxml"});
        AnchorPane parent = form.getParent();
        ManagementCompanyDirectoryController controller = form.getController();
        controller.setPopUpStage(addingStage);
        setContentPaneContent(parent);
    }

    private void setContentPaneContent(Node content){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(content);
    }

    public void onTicketsClick(ActionEvent event) {
        GuiForm<AnchorPane, TicketsController> form = new GuiForm<>(new String[]{"tickets", "Tickets.fxml"});
        AnchorPane parent = form.getParent();
        TicketsController controller = form.getController();
        controller.setPopUpStage(addingStage);
        setContentPaneContent(parent);
    }

    public void onNotificationsClick(ActionEvent event) {
        GuiForm<AnchorPane, NotificationsController> form = new GuiForm<>(new String[]{"notifications", "Notifications.fxml"});
        AnchorPane parent = form.getParent();
        NotificationsController controller = form.getController();
        controller.setPopUpStage(addingStage);
        setContentPaneContent(parent);
    }
}
