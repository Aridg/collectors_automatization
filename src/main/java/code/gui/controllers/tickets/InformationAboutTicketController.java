package code.gui.controllers.tickets;

import code.expandings.Utils;
import code.gui.controllers.IController;
import code.hibernate.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 04.05.2017.
 */
public class InformationAboutTicketController implements IController {
    @FXML
    private Label floorsCountLabel;
    @FXML
    private Label houseMaterialLabel;
    @FXML
    private Label roofTypeLabel;
    @FXML
    private Label riserCountLabel;
    @FXML
    private Label creationDateLabel;
    @FXML
    private Label phone1Label;
    @FXML
    private Label phone2Label;
    @FXML
    private Label phone3Label;
    @FXML
    private Label riserMaterialLabel;
    @FXML
    private Label contact1Label;
    @FXML
    private Label contact2Label;
    @FXML
    private Label contact3Label;
    @FXML
    private Label contact4Label;
    @FXML
    private Label contact5Label;
    @FXML
    private Label contact6Label;
    @FXML
    private Label contact7Label;
    @FXML
    private ComboBox<StatusesEntity> statusCombo;
    @FXML
    private Label ticketId;
    @FXML
    private Label statusLabel;
    @FXML
    private Label mcLabel;
    @FXML
    private Label managerLabel;
    @FXML
    private Label mdLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label houseLabel;
    @FXML
    private Label flatLabel;
    @FXML
    private Label floorLabel;
    @FXML
    private Label installerLabel;
    @FXML
    private Label ff1Label;
    @FXML
    private Label ff2Label;
    @FXML
    private Label ff3Label;
    @FXML
    private Label ff4Label;
    @FXML
    private Label ff5Label;
    @FXML
    private TextArea commentArea;
    @FXML
    private ListView<String> commentList;

    private TicketsVEntity ticket;
    private ObservableList<StatusesEntity> dataStatuses = FXCollections.observableArrayList();
    private ObservableList<String> dataComments = FXCollections.observableArrayList();

    @Override
    public void initialize() {
        getStatuses();
        commentList.setItems(dataComments);
    }

    private void fillInfo() {
        ticketId.setText(Utils.convertToText(ticket.getId()));
        statusLabel.setText(Utils.convertToText(ticket.getStatusName()));
        mcLabel.setText(Utils.convertToText(ticket.getManagementCompany()));
        managerLabel.setText(Utils.convertToText(ticket.getManagerName()));
        mdLabel.setText(Utils.convertToText(ticket.getMicroDistrict()));
        String[] address = ticket.getAddress().split(",");
        streetLabel.setText(Utils.convertToText(address[0].trim()));
        houseLabel.setText(Utils.convertToText(address[1].trim()));
        flatLabel.setText(Utils.convertToText(ticket.getFlat()));
        floorLabel.setText(Utils.convertToText(ticket.getFloor()));
        floorsCountLabel.setText(Utils.convertToText(ticket.getFloorsNumber()));
        houseMaterialLabel.setText(Utils.convertToText(ticket.getHouseMaterial()));
        roofTypeLabel.setText(Utils.convertToText(ticket.getRoofingType()));
        riserCountLabel.setText(Utils.convertToText(ticket.getRiserCount()));
        riserMaterialLabel.setText(Utils.convertToText(ticket.getRiserType()));
        installerLabel.setText(Utils.convertToText(ticket.getInstaller()));
        creationDateLabel.setText(Utils.convertToText(ticket.getCreationDate()));
        ff1Label.setText(Utils.convertToText(ticket.getFlexibleField1()));
        ff2Label.setText(Utils.convertToText(ticket.getFlexibleField2()));
        ff3Label.setText(Utils.convertToText(ticket.getFlexibleField3()));
        ff4Label.setText(Utils.convertToText(ticket.getFlexibleField4()));
        ff5Label.setText(Utils.convertToText(ticket.getFlexibleField5()));
        phone1Label.setText(Utils.convertToText(ticket.getPhone1()));
        phone2Label.setText(Utils.convertToText(ticket.getPhone2()));
        phone3Label.setText(Utils.convertToText(ticket.getPhone3()));

        Session session = HibernateSessionFactory.getSession();
        ManagementCompaniesEntity mc = session.createQuery("from ManagementCompaniesEntity where id = :id", ManagementCompaniesEntity.class)
                .setParameter("id", ticket.getManagementCompanyId())
                .getSingleResult();
        contact1Label.setText(Utils.convertToText(mc.getPhone1()));
        contact2Label.setText(Utils.convertToText(mc.getPhone2()));
        contact3Label.setText(Utils.convertToText(mc.getPhone3()));
        contact4Label.setText(Utils.convertToText(mc.getPhone4()));
        contact5Label.setText(Utils.convertToText(mc.getPhone5()));
        contact6Label.setText(Utils.convertToText(mc.getPhone6()));
        contact7Label.setText(Utils.convertToText(mc.getPhone7()));


        StatusesEntity current = session.createQuery("from StatusesEntity where id = :id", StatusesEntity.class)
                .setParameter("id", ticket.getStatusId())
                .getSingleResult();
        session.close();
        statusCombo.getSelectionModel().select(current);
    }
    private void getStatuses(){
        Session session = HibernateSessionFactory.getSession();
        List<StatusesEntity> list = session.createQuery("from StatusesEntity ", StatusesEntity.class)
                .getResultList();
        dataStatuses.addAll(list);
        statusCombo.setItems(dataStatuses);
        session.close();
    }
    private void getComments() {
        Session session = HibernateSessionFactory.getSession();
        List<String> comments = new ArrayList<>();
        session.createQuery("from TicketsCommentsEntity where ticketId = :ticketId order by commentDate desc ",
                TicketsCommentsEntity.class)
                .setParameter("ticketId", ticket.getId())
                .getResultList()
                .forEach(elem -> comments.add(String.format("%s : %s", elem.getCommentDate(), elem.getCommentText())));
        dataComments.clear();
        dataComments.addAll(comments);
        session.close();
    }


    @Override
    public void setPopUpStage(Stage stage) {

    }

    public void setTicket(TicketsVEntity ticket) {
        this.ticket = ticket;
        fillInfo();
        getComments();
    }

    @FXML
    private void onAddCommentClick(ActionEvent event) {
        String text = commentArea.getText();
        if (Utils.isNullOrEmpty(text))
            return;
        Session session = HibernateSessionFactory.getSession();
        TicketsCommentsEntity comment = new TicketsCommentsEntity();
        comment.setTicketId(ticket.getId());
        comment.setCommentText(text);
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
        session.close();
        commentArea.clear();
        getComments();
    }

    @FXML
    private void onSaveClick(ActionEvent event) {
        Session session = HibernateSessionFactory.getSession();
        TicketsEntity ticket = session.createQuery("from TicketsEntity where id = :id", TicketsEntity.class)
                .setParameter("id", this.ticket.getId())
                .getSingleResult();
        ticket.setStatusId(statusCombo.getSelectionModel().getSelectedItem().getId());
        session.beginTransaction();
        session.update(ticket);
        session.getTransaction().commit();
        session.close();
        ((Stage) statusCombo.getScene().getWindow()).close();
    }

    @FXML
    private void onExitClick(ActionEvent event) {
        ((Stage) statusCombo.getScene().getWindow()).close();
    }

    public void onDelClick(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Вы уверены, что хотите удалить заявку?");
        confirm.showAndWait();
        if(confirm.getResult().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)
            return;
        Session session = HibernateSessionFactory.getSession();
        TicketsEntity ticket = session.createQuery("from TicketsEntity where id = :id", TicketsEntity.class)
                .setParameter("id", this.ticket.getId())
                .getSingleResult();
        session.beginTransaction();
        session.delete(ticket);
        session.getTransaction().commit();
        session.close();
        ((Stage) statusCombo.getScene().getWindow()).close();
    }
}
