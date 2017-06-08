package code.gui.controllers.notifications;

import code.expandings.GuiForm;
import code.gui.controllers.IController;
import code.gui.controllers.tickets.InformationAboutTicketController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.StatusesEntity;
import code.hibernate.TicketsVEntity;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 12.05.2017.
 */
public class NotificationsController implements IController {

    public DatePicker datePicker;
    public ListView<NotificationModel> list;

    private Stage popUpStage;

    @Override
    public void initialize() {
        LocalDate initDate = LocalDate.now();
        initDate = initDate.plusDays(2);
        datePicker.setValue(initDate);
        list.setOnMouseClicked(event -> {
            if (event.getClickCount() >= 2){
                TicketsVEntity ticket = list.getSelectionModel().getSelectedItem().getTicket();
                GuiForm<AnchorPane, InformationAboutTicketController> form = new GuiForm<>(new String[]{"tickets", "InformationAboutTicket.fxml"});
                AnchorPane parent = form.getParent();
                InformationAboutTicketController controller = form.getController();
                controller.setTicket(ticket);
                Scene scene = new Scene(parent);
                popUpStage.setScene(scene);
                popUpStage.showAndWait();
                getData();
            }
        });
        getData();
    }

    @Override
    public void setPopUpStage(Stage stage) {
        popUpStage = stage;
    }

    private void getData() {
        Session session = HibernateSessionFactory.getSession();
        List<NotificationModel> models = new ArrayList<>();
        session.createQuery("from TicketsVEntity t " +
                "left join StatusesEntity s on t.statusId = s.id " +
                "where date(t.lastStatusSetDate, '+' || s.delay || ' days') <= date(:dateIn) " +
                "order by (julianday(:dateIn) - julianday(t.lastStatusSetDate, '+' || s.delay || ' days')) desc", Object[].class)
                .setParameter("dateIn", datePicker.getValue().toString())
                .getResultList()
                .forEach(objects ->
                        models.add(new NotificationModel((TicketsVEntity) objects[0], ((StatusesEntity) objects[1]).getNotificationText()))
                );
        session.close();
        list.getItems().clear();
        list.getItems().addAll(models);
    }

    public void onDateChange(ActionEvent event) {
        getData();
    }
}
