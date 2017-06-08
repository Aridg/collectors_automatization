package code.hibernate;

import javafx.beans.property.IntegerProperty;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Алексей on 08.05.2017.
 */
@Entity
@Table(name = "tickets_comments", schema = "main")
public class TicketsCommentsEntity implements IHibernateEntiry{
    private int id;
    private int ticketId;
    private String commentDate;
    private String commentText;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ticket_id", nullable = true)
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "comment_date", nullable = true)
    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Basic
    @Column(name = "comment_text", nullable = true, length = 2000)
    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketsCommentsEntity that = (TicketsCommentsEntity) o;

        if (id != that.id) return false;
        if (ticketId != that.ticketId) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;
        if (commentText != null ? !commentText.equals(that.commentText) : that.commentText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(id);
        result = 31 * result + Integer.hashCode(ticketId);
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
        return result;
    }
}
