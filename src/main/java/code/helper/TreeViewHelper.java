package code.helper;

import code.gui.controllers.notifications.NotificationModel;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.StatusesEntity;
import javafx.scene.control.TreeItem;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavri on 04.07.2017.
 */
public class TreeViewHelper {

    private List<StatusesEntity> statusesEntityList = new ArrayList<>();

    public TreeViewHelper() {
        getStatuses();
    }

    public ArrayList<TreeItem> getContent(List<NotificationModel> models)
    {
        ArrayList<TreeItem> content = new ArrayList<TreeItem>();
        for(StatusesEntity statusesEntity:statusesEntityList) {
            TreeItem treeItem = new TreeItem(statusesEntity.getName());
            for (NotificationModel obj : models) {
                if(statusesEntity.getName().equals(obj.getTicket().getStatusName())) {
                    treeItem.getChildren().add(new TreeItem(obj));
                }
            }
            content.add(treeItem);
        }
        return  content;
    }

    private void getStatuses(){
        Session session = HibernateSessionFactory.getSession();
        statusesEntityList = session.createQuery("from  StatusesEntity",StatusesEntity.class).getResultList();
        session.close();
    }
}
