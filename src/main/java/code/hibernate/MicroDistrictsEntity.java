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
@Table(name = "micro_districts", schema = "main")
public class MicroDistrictsEntity implements IHibernateEntiry{
    private int id;
    private String microDistrict;

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
    @Column(name = "micro_district", nullable = false, length = 100)
    public String getMicroDistrict() {
        return microDistrict;
    }

    public void setMicroDistrict(String microDistrict) {
        this.microDistrict = microDistrict;
        setNameP(microDistrict);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MicroDistrictsEntity that = (MicroDistrictsEntity) o;

        if (id != that.id) return false;
        if (microDistrict != null ? !microDistrict.equals(that.microDistrict) : that.microDistrict != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (microDistrict != null ? microDistrict.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return this.getMicroDistrict();
    }
}
