package code.hibernate;

import javafx.beans.property.*;
import org.hibernate.Session;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Алексей on 08.05.2017.
 */
@Entity
@Table(name = "tickets", schema = "main")
public class TicketsEntity implements IHibernateEntiry{
    private int id = -1;
    private int managementCompany;
    private int microDistrict = -1;
    private String address = null;
    private int flat = -1;
    private double debt = -1.0;
    private Integer floor;
    private Integer floorsNumber;
    private Integer houseMaterialId;
    private int statusId = -1;
    private Integer riserTypeId;
    private Integer riserCount;
    private Integer roofingTypeId;
    private String creationDate;
    private String lastStatusSetDate;
    private String installer;
    private String flexibleField1;
    private String flexibleField2;
    private String flexibleField3;
    private String flexibleField4;
    private String flexibleField5;
    private String phone1;
    private String phone2;
    private String phone3;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "management_company", nullable = false)
    public int getManagementCompany() {
        return managementCompany;
    }

    public void setManagementCompany(int managementCompany) {
        this.managementCompany = managementCompany;
    }

    @Basic
    @Column(name = "micro_district", nullable = false, length = 150)
    public int getMicroDistrict() {
        return microDistrict;
    }

    public void setMicroDistrict(int microDistrict) {
        this.microDistrict = microDistrict;
    }

    @Basic
    @Column(name = "address", nullable = false, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "flat", nullable = false)
    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    @Basic
    @Column(name = "floor", nullable = true)
    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "floors_number", nullable = true)
    public Integer getFloorsNumber() {
        return floorsNumber;
    }

    public void setFloorsNumber(Integer floorsNumber) {
        this.floorsNumber = floorsNumber;
    }

    @Basic
    @Column(name = "house_material_id", nullable = true)
    public Integer getHouseMaterialId() {
        return houseMaterialId;
    }

    public void setHouseMaterialId(Integer houseMaterialId) {
        this.houseMaterialId = houseMaterialId;
    }

    @Basic
    @Column(name = "status_id", nullable = false)
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @Basic
    @Column(name = "riser_type_id", nullable = true)
    public Integer getRiserTypeId() {
        return riserTypeId;
    }

    public void setRiserTypeId(Integer riserTypeId) {
        this.riserTypeId = riserTypeId;
    }

    @Basic
    @Column(name = "riser_count", nullable = true)
    public Integer getRiserCount() {
        return riserCount;
    }

    public void setRiserCount(Integer riserCount) {
        this.riserCount = riserCount;
    }

    @Basic
    @Column(name = "roofing_type_id", nullable = true)
    public Integer getRoofingTypeId() {
        return roofingTypeId;
    }

    public void setRoofingTypeId(Integer roofingTypeId) {
        this.roofingTypeId = roofingTypeId;
    }

    @Basic
    @Column(name = "creation_date", nullable = true)
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "last_status_set_date", nullable = true)
    public String getLastStatusSetDate() {
        return lastStatusSetDate;
    }

    public void setLastStatusSetDate(String lastStatusSetDate) {
        this.lastStatusSetDate = lastStatusSetDate;
    }

    @Basic
    @Column(name = "installer", nullable = true, length = 150)
    public String getInstaller() {
        return installer;
    }

    public void setInstaller(String installer) {
        this.installer = installer;
    }

    @Basic
    @Column(name = "flexible_field_1", nullable = true, length = 500)
    public String getFlexibleField1() {
        return flexibleField1;
    }

    public void setFlexibleField1(String flexibleField1) {
        this.flexibleField1 = flexibleField1;
    }

    @Basic
    @Column(name = "flexible_field_2", nullable = true, length = 500)
    public String getFlexibleField2() {
        return flexibleField2;
    }

    public void setFlexibleField2(String flexibleField2) {
        this.flexibleField2 = flexibleField2;
    }

    @Basic
    @Column(name = "flexible_field_3", nullable = true, length = 500)
    public String getFlexibleField3() {
        return flexibleField3;
    }

    public void setFlexibleField3(String flexibleField3) {
        this.flexibleField3 = flexibleField3;
    }

    @Basic
    @Column(name = "flexible_field_4", nullable = true, length = 500)
    public String getFlexibleField4() {
        return flexibleField4;
    }

    public void setFlexibleField4(String flexibleField4) {
        this.flexibleField4 = flexibleField4;
    }

    @Basic
    @Column(name = "flexible_field_5", nullable = true, length = 500)
    public String getFlexibleField5() {
        return flexibleField5;
    }

    public void setFlexibleField5(String flexibleField5) {
        this.flexibleField5 = flexibleField5;
    }

    @Basic
    @Column(name = "debt", nullable = false)
    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }

    @Basic
    @Column(name = "phone1", length = 100)
    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    @Basic
    @Column(name = "phone2", length = 100)
    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @Basic
    @Column(name = "phone3", length = 100)
    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketsEntity that = (TicketsEntity) o;

        if (id != that.id) return false;
        if (managementCompany != that.managementCompany) return false;
        if (microDistrict != that.microDistrict) return false;
        if (flat != that.flat) return false;
        if (debt != that.debt) return false;
        if (statusId != that.statusId) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (floorsNumber != null ? !floorsNumber.equals(that.floorsNumber) : that.floorsNumber != null) return false;
        if (houseMaterialId != null ? !houseMaterialId.equals(that.houseMaterialId) : that.houseMaterialId != null)
            return false;
        if (riserTypeId != null ? !riserTypeId.equals(that.riserTypeId) : that.riserTypeId != null) return false;
        if (riserCount != null ? !riserCount.equals(that.riserCount) : that.riserCount != null) return false;
        if (roofingTypeId != null ? !roofingTypeId.equals(that.roofingTypeId) : that.roofingTypeId != null)
            return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        if (lastStatusSetDate != null ? !lastStatusSetDate.equals(that.lastStatusSetDate) : that.lastStatusSetDate != null)
            return false;
        if (installer != null ? !installer.equals(that.installer) : that.installer != null) return false;
        if (flexibleField1 != null ? !flexibleField1.equals(that.flexibleField1) : that.flexibleField1 != null)
            return false;
        if (flexibleField2 != null ? !flexibleField2.equals(that.flexibleField2) : that.flexibleField2 != null)
            return false;
        if (flexibleField3 != null ? !flexibleField3.equals(that.flexibleField3) : that.flexibleField3 != null)
            return false;
        if (flexibleField4 != null ? !flexibleField4.equals(that.flexibleField4) : that.flexibleField4 != null)
            return false;
        if (flexibleField5 != null ? !flexibleField5.equals(that.flexibleField5) : that.flexibleField5 != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + managementCompany;
        result = 31 * result + microDistrict;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + flat;
        result = 31 * result + Double.hashCode(debt);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (floorsNumber != null ? floorsNumber.hashCode() : 0);
        result = 31 * result + (houseMaterialId != null ? houseMaterialId.hashCode() : 0);
        result = 31 * result + statusId;
        result = 31 * result + (riserTypeId != null ? riserTypeId.hashCode() : 0);
        result = 31 * result + (riserCount != null ? riserCount.hashCode() : 0);
        result = 31 * result + (roofingTypeId != null ? roofingTypeId.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastStatusSetDate != null ? lastStatusSetDate.hashCode() : 0);
        result = 31 * result + (installer != null ? installer.hashCode() : 0);
        result = 31 * result + (flexibleField1 != null ? flexibleField1.hashCode() : 0);
        result = 31 * result + (flexibleField2 != null ? flexibleField2.hashCode() : 0);
        result = 31 * result + (flexibleField3 != null ? flexibleField3.hashCode() : 0);
        result = 31 * result + (flexibleField4 != null ? flexibleField4.hashCode() : 0);
        result = 31 * result + (flexibleField5 != null ? flexibleField5.hashCode() : 0);
        return result;
    }

}
