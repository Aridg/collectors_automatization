package code.hibernate;

import javafx.beans.property.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Алексей on 29.04.2017.
 */
@Entity
@Table(name = "house_materials", schema = "main")
public class HouseMaterialsEntity implements IHibernateEntiry{
    private int id;
    private String houseMaterial;

    private IntegerProperty idP = new SimpleIntegerProperty();
    private StringProperty nameP = new SimpleStringProperty();

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
    public int getIdP() {
        return idP.get();
    }

    public IntegerProperty idPProperty() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP.set(idP);
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
    @Column(name = "house_material", nullable = false, length = 100)
    public String getHouseMaterial() {
        return houseMaterial;
    }

    public void setHouseMaterial(String houseMaterial) {
        this.houseMaterial = houseMaterial;
        setNameP(houseMaterial);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HouseMaterialsEntity that = (HouseMaterialsEntity) o;

        if (id != that.id) return false;
        if (houseMaterial != null ? !houseMaterial.equals(that.houseMaterial) : that.houseMaterial != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (houseMaterial != null ? houseMaterial.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return houseMaterial;
    }
}
