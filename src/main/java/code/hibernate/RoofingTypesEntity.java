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
@Table(name = "roofing_types", schema = "main")
public class RoofingTypesEntity implements IHibernateEntiry{
    private int id;
    private String roofingType;

    private IntegerProperty idP = new SimpleIntegerProperty();
    private StringProperty nameP = new SimpleStringProperty();

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
    @Column(name = "roofing_type", nullable = false, length = 100)
    public String getRoofingType() {
        return roofingType;
    }

    public void setRoofingType(String roofingType) {
        this.roofingType = roofingType;
        setNameP(roofingType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoofingTypesEntity that = (RoofingTypesEntity) o;

        if (id != that.id) return false;
        if (roofingType != null ? !roofingType.equals(that.roofingType) : that.roofingType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roofingType != null ? roofingType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return roofingType;
    }
}
