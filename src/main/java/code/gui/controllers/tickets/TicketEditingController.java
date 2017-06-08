package code.gui.controllers.tickets;

import code.expandings.Utils;
import code.gui.controllers.IController;
import code.hibernate.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

import javax.rmi.CORBA.Util;

/**
 * Created by Пользователь on 01.05.2017.
 */
public class TicketEditingController implements IController {
    @FXML
    private TextField dept;
    @FXML
    private ComboBox<MicroDistrictsEntity> microDistrict;
    @FXML
    private TextField flat;
    @FXML
    private TextField riserCount;
    @FXML
    private TextField street;
    @FXML
    private TextField house;
    @FXML
    private ComboBox<HouseMaterialsEntity> houseMaterial;
    @FXML
    private ComboBox<StatusesEntity> status;
    @FXML
    private ComboBox<RiserTypesEntity> riserType;
    @FXML
    private ComboBox<RoofingTypesEntity> roofingType;
    @FXML
    private ComboBox<ManagementCompaniesEntity> managementCompany;
    @FXML
    private TextField installer;
    @FXML
    private TextField flexField1;
    @FXML
    private TextField flexField2;
    @FXML
    private TextField flexField3;
    @FXML
    private TextField flexField4;
    @FXML
    private TextField flexField5;
    @FXML
    private TextField floor;
    @FXML
    private TextField floorCount;

    private TicketsEntity ticket;

    public void setTicket(TicketsVEntity ticketV) {
        Session session = HibernateSessionFactory.getSession();
        this.ticket = session.createQuery("from TicketsEntity where id = :id", TicketsEntity.class)
                .setParameter("id", ticketV.getId())
                .getSingleResult();
        microDistrict.getSelectionModel().select(
                session.createQuery("from MicroDistrictsEntity where id = :id", MicroDistrictsEntity.class)
                        .setParameter("id", ticket.getMicroDistrict())
                        .getSingleResult()
        );
        if (ticket.getHouseMaterialId() != null)
            houseMaterial.getSelectionModel().select(
                    session.createQuery("from HouseMaterialsEntity where id = :id", HouseMaterialsEntity.class)
                            .setParameter("id", ticket.getHouseMaterialId())
                            .getSingleResult()
            );
        if (ticket.getRiserTypeId() != null)
            riserType.getSelectionModel().select(
                    session.createQuery("from RiserTypesEntity where id = :id", RiserTypesEntity.class)
                            .setParameter("id", ticket.getRiserTypeId())
                            .getSingleResult()
            );
        if (ticket.getRoofingTypeId() != null)
            roofingType.getSelectionModel().select(
                    session.createQuery("from RoofingTypesEntity where id = :id", RoofingTypesEntity.class)
                            .setParameter("id", ticket.getRoofingTypeId())
                            .getSingleResult()
            );
        managementCompany.getSelectionModel().select(
                session.createQuery("from ManagementCompaniesEntity where id = :id", ManagementCompaniesEntity.class)
                        .setParameter("id", ticket.getManagementCompany())
                        .getSingleResult()
        );
        status.getSelectionModel().select(
                session.createQuery("from StatusesEntity where id = :id", StatusesEntity.class)
                        .setParameter("id", ticket.getStatusId())
                        .getSingleResult()
        );


        dept.setText(Utils.convertToText(ticket.getDebt()));
        flat.setText(Utils.convertToText(ticket.getFlat()));
        riserCount.setText(Utils.convertToText(ticket.getRiserCount()));
        String[] address = ticket.getAddress().split(",");
        street.setText(Utils.convertToText(address[0]));
        house.setText(Utils.convertToText(address[1]));
        installer.setText(Utils.convertToText(ticket.getInstaller()));
        floorCount.setText(Utils.convertToText(ticket.getFloorsNumber()));
        floor.setText(Utils.convertToText(ticket.getFloor()));
    }

    @Override
    public void initialize() {
        getData();
    }

    private void getData() {
        Session session = HibernateSessionFactory.getSession();
        microDistrict.setItems(
                FXCollections.observableArrayList(
                        session.createQuery("from MicroDistrictsEntity ", MicroDistrictsEntity.class)
                                .getResultList()
                )
        );
        houseMaterial.setItems(
                FXCollections.observableArrayList(
                        session.createQuery("from HouseMaterialsEntity ", HouseMaterialsEntity.class)
                                .getResultList()
                )
        );
        status.setItems(
                FXCollections.observableArrayList(
                        session.createQuery("from StatusesEntity ", StatusesEntity.class)
                                .getResultList()
                )
        );
        riserType.setItems(
                FXCollections.observableArrayList(
                        session.createQuery("from RiserTypesEntity ", RiserTypesEntity.class)
                                .getResultList()
                )
        );
        roofingType.setItems(
                FXCollections.observableArrayList(
                        session.createQuery("from RoofingTypesEntity ", RoofingTypesEntity.class)
                                .getResultList()
                )
        );
        managementCompany.setItems(
                FXCollections.observableArrayList(
                        session.createQuery("from ManagementCompaniesEntity ", ManagementCompaniesEntity.class)
                                .getResultList()
                )
        );
    }

    @Override
    public void setPopUpStage(Stage stage) {

    }

    @FXML
    private void onSaveClick(ActionEvent event) {
        if (checkNecessary()) {
            boolean update = true;
            if(ticket == null) {
                ticket = new TicketsEntity();
                update = false;
            }
            Session session = HibernateSessionFactory.getSession();
            try {
                ticket.setManagementCompany(Utils.getSelectedId(managementCompany));
                ticket.setMicroDistrict(Utils.getSelectedId(microDistrict));
                ticket.setAddress(String.format("%s,%s", street.getText(), house.getText()));
                ticket.setFlat(Integer.parseInt(flat.getText()));
                ticket.setDebt(Double.parseDouble(dept.getText()));
                ticket.setFloor(Utils.convertToDBInteger(floor.getText()));
                ticket.setFloorsNumber(Utils.convertToDBInteger(floorCount.getText()));
                ticket.setHouseMaterialId(Utils.getSelectedId(houseMaterial));
                ticket.setStatusId(Utils.getSelectedId(status));
                ticket.setRiserTypeId(Utils.getSelectedId(riserType));
                ticket.setRiserCount(Utils.convertToDBInteger(riserCount.getText()));
                ticket.setRoofingTypeId(Utils.getSelectedId(roofingType));
                ticket.setInstaller(Utils.convertToDBText(installer.getText()));
                ticket.setFlexibleField1(Utils.convertToDBText(flexField1.getText()));
                ticket.setFlexibleField2(Utils.convertToDBText(flexField2.getText()));
                ticket.setFlexibleField3(Utils.convertToDBText(flexField3.getText()));
                ticket.setFlexibleField4(Utils.convertToDBText(flexField4.getText()));
                ticket.setFlexibleField5(Utils.convertToDBText(flexField5.getText()));

                session.beginTransaction();
                if(update)
                    session.update(ticket);
                else session.save(ticket);
                session.getTransaction().commit();
                session.close();
                ((Stage) floorCount.getScene().getWindow()).close();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Ошибка при заполнении полей: " + e.getLocalizedMessage()).showAndWait();
                return;
            } finally {
                session.close();
            }
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR, "Заполните все обязательные поля:\n" +
                    "Статус\n" +
                    "УК\n" +
                    "Микрорайон\n" +
                    "Улица\n" +
                    "Дом\n" +
                    "Квартира\n" +
                    "Сумма долга");
            error.showAndWait();
        }
    }

    @FXML
    private void onExitClick(ActionEvent event) {
        ((Stage) floorCount.getScene().getWindow()).close();
    }


    private boolean checkNecessary() {
        if (
                managementCompany.getSelectionModel().getSelectedItem() == null ||
                        microDistrict.getSelectionModel().getSelectedItem() == null ||
                        Utils.isNullOrEmpty(street.getText()) ||
                        Utils.isNullOrEmpty(house.getText()) ||
                        Utils.isNullOrEmpty(flat.getText()) ||
                        status.getSelectionModel().getSelectedItem() == null
                )
            return false;
        else return true;
    }
}
