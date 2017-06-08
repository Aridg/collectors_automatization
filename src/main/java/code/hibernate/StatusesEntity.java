package code.hibernate;

import javafx.beans.property.*;

import javax.persistence.*;

/**
 * Created by Алексей on 25.04.2017.
 */
@Entity
@Table(name = "statuses", schema = "main")
public class StatusesEntity implements IHibernateEntiry{
    private int id;
    private String name;
    private int enableNotification;
    private Integer delay;
    private String notificationText;

    //данные модели
    private IntegerProperty idP = new SimpleIntegerProperty();
    private StringProperty nameP = new SimpleStringProperty();
    private BooleanProperty enableNotificationP = new SimpleBooleanProperty();
    private IntegerProperty delayP = new SimpleIntegerProperty();
    private StringProperty notificationTextP = new SimpleStringProperty();

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        setIdP(id);
    }

    @Basic
    @Column(name = "name", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setNameP(name);
    }

    @Basic
    @Column(name = "enable_notification", nullable = false)
    public int getEnableNotification() {
        return enableNotification;
    }

    public void setEnableNotification(int enableNotification) {
        this.enableNotification = enableNotification;
        boolean en;
        switch (enableNotification){
            case 0 : en = false;break;
            case 1 : en = true;break;
            default: en = false;
        }
        setEnableNotificationP(en);
    }

    @Basic
    @Column(name = "delay", nullable = true)
    public Integer getDelay() {
        return delay;
    }

    public void setDelay(Integer delay) {
        this.delay = delay;
        setDelayP(delay);
    }

    @Basic
    @Column(name = "notification_text", nullable = true, length = 4000)
    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
        setNotificationTextP(notificationText);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatusesEntity that = (StatusesEntity) o;

        if (id != that.id) return false;
        if (enableNotification != that.enableNotification) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (delay != null ? !delay.equals(that.delay) : that.delay != null) return false;
        if (notificationText != null ? !notificationText.equals(that.notificationText) : that.notificationText != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (enableNotification ^ (enableNotification >>> 32));
        result = 31 * result + (delay != null ? delay.hashCode() : 0);
        result = 31 * result + (notificationText != null ? notificationText.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }

    //Методы для модели
    @Transient
    public int getIdP() {
        return idP.get();
    }

    public IntegerProperty idPProperty() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP.set(idP);
    }

    @Transient
    public String getNameP() {
        return nameP.get();
    }

    public StringProperty namePProperty() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP.set(nameP);
    }

    @Transient
    public boolean isEnableNotificationP() {
        return enableNotificationP.get();
    }

    public BooleanProperty enableNotificationPProperty() {
        return enableNotificationP;
    }

    public void setEnableNotificationP(boolean enableNotificationP) {
        this.enableNotificationP.set(enableNotificationP);
    }

    @Transient
    public int getDelayP() {
        return delayP.get();
    }

    public IntegerProperty delayPProperty() {
        return delayP;
    }

    public void setDelayP(Integer delayP) {
        if(delayP == null)
            this.delayP.set(0);
        else this.delayP.set(delayP);
    }

    @Transient
    public String getNotificationTextP() {
        return notificationTextP.get();
    }

    public StringProperty notificationTextPProperty() {
        return notificationTextP;
    }

    public void setNotificationTextP(String notificationTextP) {
        this.notificationTextP.set(notificationTextP);
    }
}
