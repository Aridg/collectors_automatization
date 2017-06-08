package code.gui.controllers.notifications;

import code.hibernate.HibernateSessionFactory;
import code.hibernate.TicketsVEntity;
import org.hibernate.Session;

/**
 * Created by Алексей on 13.05.2017.
 */
public class NotificationModel {
    private TicketsVEntity ticket;
    private String notificationText;

    public NotificationModel(TicketsVEntity ticket, String notificationText) {
        this.ticket = ticket;
        this.notificationText = notificationText;
    }

    public TicketsVEntity getTicket() {
        return ticket;
    }

    @Override
    public String toString() {
        return String.format("%s, кв. %s: %s", ticket.getAddress(), ticket.getFlat(), notificationText);
    }
}
