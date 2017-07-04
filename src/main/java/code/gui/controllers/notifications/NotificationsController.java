package code.gui.controllers.notifications;

import code.expandings.GuiForm;
import code.gui.controllers.IController;
import code.gui.controllers.tickets.InformationAboutTicketController;
import code.gui.controllers.tickets.TicketsController;
import code.helper.TreeViewHelper;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.StatusesEntity;
import code.hibernate.TicketsVEntity;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    public TreeView<NotificationModel> treeView;


    private Stage popUpStage;

    @Override
    public void initialize() {
        LocalDate initDate = LocalDate.now();
        initDate = initDate.plusDays(2);
        datePicker.setValue(initDate);
        treeView.setOnMouseClicked(event -> {
            if (event.getClickCount() >= 2){
                TicketsVEntity ticket = treeView.getSelectionModel().getSelectedItem().getValue().getTicket();
                GuiForm<AnchorPane, TicketsController> form = new GuiForm<>(new String[]{"tickets", "Tickets.fxml"});
                AnchorPane parent = form.getParent();
                TicketsController controller = form.getController();
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
        ArrayList<TreeItem> itemArrayList = new ArrayList<>();
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

        TreeViewHelper helper = new TreeViewHelper();
        TreeItem rootItem = new TreeItem("Статусы");
        rootItem.getChildren().addAll(helper.getContent(models));
        treeView.setRoot(rootItem);
    }
    public void onDateChange(ActionEvent event) {
        getData();
    }
}
