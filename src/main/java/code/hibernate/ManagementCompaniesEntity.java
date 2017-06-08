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
@Table(name = "management_companies", schema = "main")
public class ManagementCompaniesEntity implements IHibernateEntiry{
    private int id;
    private String managementCompany;
    private String manager;
    private String phone1;
    private String phone2;
    private String phone3;
    private String phone4;
    private String phone5;
    private String phone6;
    private String phone7;

    private IntegerProperty idP = new SimpleIntegerProperty();
    private StringProperty nameP = new SimpleStringProperty();
    private SimpleStringProperty managerP = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();

    @Transient
    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
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

    @Basic
    @Column(name = "manager_name", nullable = false)
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
        setManagerP(manager);
    }

    @Transient
    public String getManagerP() {
        return managerP.get();
    }

    public SimpleStringProperty managerPProperty() {
        return managerP;
    }

    public void setManagerP(String managerP) {
        this.managerP.set(managerP);
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
    @Column(name = "management_company", nullable = false, length = 100)
    public String getManagementCompany() {
        return managementCompany;
    }

    public void setManagementCompany(String managementCompany) {
        this.managementCompany = managementCompany;
        setNameP(managementCompany);
    }

    @Basic
    @Column(name = "phone1")
    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
        setPhone(phone1);
    }

    @Basic
    @Column(name = "phone2")
    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Basic
    @Column(name = "phone3")
    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    @Basic
    @Column(name = "phone4")
    public String getPhone4() {
        return phone4;
    }

    public void setPhone4(String phone4) {
        this.phone4 = phone4;
    }

    @Basic
    @Column(name = "phone5")
    public String getPhone5() {
        return phone5;
    }

    public void setPhone5(String phone5) {
        this.phone5 = phone5;
    }

    @Basic
    @Column(name = "phone6")
    public String getPhone6() {
        return phone6;
    }

    public void setPhone6(String phone6) {
        this.phone6 = phone6;
    }

    @Basic
    @Column(name = "phone7")
    public String getPhone7() {
        return phone7;
    }

    public void setPhone7(String phone7) {
        this.phone7 = phone7;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagementCompaniesEntity that = (ManagementCompaniesEntity) o;

        if (id != that.id) return false;
        if (managementCompany != null ? !managementCompany.equals(that.managementCompany) : that.managementCompany != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (managementCompany != null ? managementCompany.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return managementCompany;
    }
}
