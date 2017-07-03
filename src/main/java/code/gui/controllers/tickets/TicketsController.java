package code.gui.controllers.tickets;

import code.expandings.GuiForm;
import code.gui.controllers.IController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.TicketsEntity;
import code.hibernate.TicketsVEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.hibernate.Session;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Пользователь on 01.05.2017.
 */
public class TicketsController implements IController, Initializable{
    public ComboBox<FilteredColumn> fieldCombo;
    public ComboBox<FilterOperations> operationCombo;
    public TextField filterValueCombo;
    public Button clearFilter;
    public Button applyFilter;
    public TextArea filterArea;
    @FXML private TableView<TicketsVEntity> table;
    @FXML private TableColumn<TicketsVEntity, String> microDistrictColumn;
    @FXML private TableColumn<TicketsVEntity, String> addressColumn;
    @FXML private TableColumn<TicketsVEntity, Integer> flatColumn;
    @FXML private TableColumn<TicketsVEntity, Double> debtColumn;
    @FXML private TableColumn<TicketsVEntity, LocalDate> createDateColumn;
    @FXML private TableColumn<TicketsVEntity, String> statusColumn;

    private ObservableList<TicketsVEntity> ticketsData = FXCollections.observableArrayList();
    private Stage popUpStage;

    private String filterString = "";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configTable();
        configFilters();
        getData();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void setPopUpStage(Stage stage) {
        popUpStage = stage;
    }

    private void configTable(){
        microDistrictColumn.setCellValueFactory(param -> param.getValue().microDistinctPProperty());
        addressColumn.setCellValueFactory(param -> param.getValue().addressPProperty());
        flatColumn.setCellValueFactory(param -> param.getValue().flatPProperty().asObject());
        debtColumn.setCellValueFactory(param -> param.getValue().debtPProperty().asObject());
        createDateColumn.setCellValueFactory(param -> param.getValue().datePProperty());
        statusColumn.setCellValueFactory(param -> param.getValue().statePProperty());
        statusColumn.setCellFactory(getLineBreakForStringColumn());
        table.setItems(ticketsData);
        table.setColumnResizePolicy(param -> false);
        table.setRowFactory(param -> {
                TableRow<TicketsVEntity> row = new TableRow<>();
                row.setContextMenu(getContextBar(row));
                return row;
        });
    }
    private void getData(){
        Session session = HibernateSessionFactory.getSession();
        List<TicketsVEntity> list = session.createQuery("from TicketsVEntity ", TicketsVEntity.class)
                .getResultList();
        ticketsData.clear();
        ticketsData.addAll(list);
        session.close();

    }
    private void getData(String filterString){
        if (filterString.equals("") || filterString == null) {
            getData();
            return;
        }
        Session session = HibernateSessionFactory.getSession();
        List<TicketsVEntity> list = session.createQuery("from TicketsVEntity where " + filterString , TicketsVEntity.class)
                .getResultList();
        ticketsData.clear();
        ticketsData.addAll(list);
        session.close();
    }
    private void configFilters(){
        fieldCombo.setItems(FXCollections.observableArrayList(FilteredColumn.values()));
        operationCombo.setItems(FXCollections.observableArrayList(FilterOperations.values()));
    }

    private Callback<TableColumn<TicketsVEntity, String>, TableCell<TicketsVEntity, String>> getLineBreakForStringColumn(){
        return param -> {
            TableCell<TicketsVEntity, String> cellName = new TableCell<>();
            Text styleText = new Text();
            styleText.setStyle("");

            cellName.setGraphic(styleText);
            cellName.setPrefHeight(Control.USE_COMPUTED_SIZE);
            styleText.textProperty().bind(cellName.itemProperty());
            //styleText.wrappingWidthProperty().bind(cellName.widthProperty());
            cellName.setAlignment(Pos.BASELINE_CENTER);
            styleText.wrappingWidthProperty().bind(param.widthProperty());
            return cellName;
        };
    }

    public void onApplyClick(ActionEvent event) {
        getData(filterString);
    }

    public void onClearClick(ActionEvent event) {
        filterValueCombo.setText("");
        filterArea.setText("");
        filterString = "";
        getData();
    }

    public void onAndParameterClick(ActionEvent event) {
        addNewFilter(Condition.AND);
    }

    private void addNewFilter(Condition condition){
        String field = fieldCombo.getSelectionModel().getSelectedItem() != null ?
                fieldCombo.getSelectionModel().getSelectedItem().getColumnName() : null;
        String operation = operationCombo.getSelectionModel().getSelectedItem() != null ?
                operationCombo.getSelectionModel().getSelectedItem().getSqlSymbol() : null;
        String value = filterValueCombo.getText().length() == 0 ?
                null : filterValueCombo.getText();
        if(value == null || field == null || operation == null)
            return;

        if(filterString.length() == 0) {
            if(operationCombo.getSelectionModel().getSelectedItem() != FilterOperations.LIKE)
                filterString += String.format("%s %s '%s' ", field, operation, firstUpperCase(value));
            else filterString += String.format("%s %s '%%%s%%' ", field, operation, firstUpperCase(value));
        }
        else {
            if(operationCombo.getSelectionModel().getSelectedItem() != FilterOperations.LIKE)
                filterString += String.format("%s %s %s '%s' ", condition.getSqlCondition(), field, operation, firstUpperCase(value));
            else filterString += String.format("%s %s %s '%%%s%%' ", condition.getSqlCondition(), field, operation, firstUpperCase(value));
        }
        if(filterArea.getText().length() == 0)
            filterArea.setText(String.format("'%s' %s '%s'",
                    fieldCombo.getSelectionModel().getSelectedItem().getDisplayName(),
                    operationCombo.getSelectionModel().getSelectedItem().getDisplayName(),
                    filterValueCombo.getText()
                    ));
        else filterArea.setText(String.format("%s %s '%s' %s '%s'",
                filterArea.getText(),
                condition.getDisplayCondition(),
                fieldCombo.getSelectionModel().getSelectedItem().getDisplayName(),
                operationCombo.getSelectionModel().getSelectedItem().getDisplayName(),
                filterValueCombo.getText()
        ));
    }

    private ContextMenu getContextBar(TableRow<TicketsVEntity> row){
        ContextMenu toolBar = new ContextMenu();
        MenuItem info = new MenuItem("Подробнее");
        MenuItem edit = new MenuItem("Редактировать");
        toolBar.getItems().add(info);
        toolBar.getItems().add(edit);

        info.setOnAction(event -> {
            if(row.getIndex() >= row.getTableView().getItems().size())
                return;
            TicketsVEntity ticket = row.getTableView().getItems().get(row.getIndex());
            GuiForm<AnchorPane, InformationAboutTicketController> form =
                    new GuiForm<>(new String[]{"tickets", "InformationAboutTicket.fxml"});
            AnchorPane parent = form.getParent();
            InformationAboutTicketController controller = form.getController();
            controller.setTicket(ticket);
            Scene scene = new Scene(parent);
            popUpStage.setScene(scene);
            popUpStage.showAndWait();
            getData(filterString);
        });
        edit.setOnAction(event -> {
            if(row.getIndex() >= row.getTableView().getItems().size())
                return;
            TicketsVEntity ticket = row.getTableView().getItems().get(row.getIndex());
            GuiForm<AnchorPane, TicketEditingController> form = new GuiForm<>(new String[]{"tickets", "TicketEditing.fxml"});
            AnchorPane parent = form.getParent();
            TicketEditingController controller = form.getController();
            controller.setTicket(ticket);
            Scene scene = new Scene(parent);
            popUpStage.setScene(scene);
            popUpStage.centerOnScreen();
            popUpStage.showAndWait();
            getData(filterString);
        });
        return toolBar;
    }


    public String firstUpperCase(String word){
        if(word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    @FXML
    private void onAddClick(ActionEvent event) {
        GuiForm<AnchorPane, TicketEditingController> form = new GuiForm<>(new String[]{"tickets", "TicketEditing.fxml"});
        AnchorPane parent = form.getParent();
        TicketEditingController controller = form.getController();
        Scene scene = new Scene(parent);
        popUpStage.setScene(scene);
        popUpStage.centerOnScreen();
        popUpStage.showAndWait();
        getData(filterString);
    }
}
