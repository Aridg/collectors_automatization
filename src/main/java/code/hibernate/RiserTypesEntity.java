package code.hibernate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

/**
 * Created by Алексей on 29.04.2017.
 */
@Entity
@Table(name = "riser_types", schema = "main", catalog = "")
public class RiserTypesEntity implements IHibernateEntiry{
    private int id;
    private String riserType;

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

    private IntegerProperty idP = new SimpleIntegerProperty();
    private StringProperty nameP = new SimpleStringProperty();

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
    @Column(name = "riser_type", nullable = false, length = 100)
    public String getRiserType() {
        return riserType;
    }

    public void setRiserType(String riserType) {
        this.riserType = riserType;
        setNameP(riserType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RiserTypesEntity that = (RiserTypesEntity) o;

        if (id != that.id) return false;
        if (riserType != null ? !riserType.equals(that.riserType) : that.riserType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (riserType != null ? riserType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return riserType;
    }
}
