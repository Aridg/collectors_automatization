package code.hibernate;

import javafx.beans.property.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Алексей on 08.05.2017.
 */
@Entity
@Table(name = "ticketsV", schema = "main")
public class TicketsVEntity {
    private int id;
    private String managementCompany;
    private String managerName;
    private String microDistrict;
    private String address;
    private int flat;
    private Integer floor;
    private Integer floorsNumber;
    private String houseMaterial;
    private String statusName;
    private String riserType;
    private Integer riserCount;
    private String roofingType;
    private String creationDate;
    private String lastStatusSetDate;
    private String installer;
    private String flexibleField1;
    private String flexibleField2;
    private String flexibleField3;
    private String flexibleField4;
    private String flexibleField5;
    private double debt;
    private int managementCompanyId;
    private int microDistrictId;
    private Integer houseMaterialId;
    private int statusId;
    private Integer riserTypeId;
    private Integer roofingTypeId;
    private String phone1;
    private String phone2;
    private String phone3;

    private IntegerProperty idP = new SimpleIntegerProperty();
    private StringProperty microDistinctP = new SimpleStringProperty();
    private StringProperty addressP = new SimpleStringProperty();
    private IntegerProperty flatP = new SimpleIntegerProperty();
    private DoubleProperty debtP = new SimpleDoubleProperty();
    private ObjectProperty<LocalDate> dateP = new SimpleObjectProperty<>();
    private StringProperty stateP = new SimpleStringProperty();

    @Id
    @Column(name = "id", nullable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        setIdP(id);
    }

    @Basic
    @Column(name = "micro_district", nullable = true, length = 100)
    public String getMicroDistrict() {
        return microDistrict;
    }

    public void setMicroDistrict(String microDistrict) {
        this.microDistrict = microDistrict;
        setMicroDistinctP(microDistrict);
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        setAddressP(address);
    }

    @Basic
    @Column(name = "flat", nullable = true)
    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
        setFlatP(flat);
    }

    @Basic
    @Column(name = "debt", nullable = true, precision = 0)
    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
        setDebtP(debt);
    }

    @Basic
    @Column(name = "creation_date", nullable = true)
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        setDateP(Date.valueOf(creationDate).toLocalDate());
    }

    @Basic
    @Column(name = "status_name", nullable = true, length = 100)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String name) {
        this.statusName = name;
        setStateP(name);
    }


    @Basic
    @Column(name = "management_company", nullable = true, length = 100)
    public String getManagementCompany() {
        return managementCompany;
    }

    public void setManagementCompany(String managementCompany) {
        this.managementCompany = managementCompany;
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
    @Column(name = "house_material", nullable = true, length = 100)
    public String getHouseMaterial() {
        return houseMaterial;
    }

    public void setHouseMaterial(String houseMaterial) {
        this.houseMaterial = houseMaterial;
    }


    @Basic
    @Column(name = "riser_type", nullable = true, length = 100)
    public String getRiserType() {
        return riserType;
    }

    public void setRiserType(String riserType) {
        this.riserType = riserType;
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
    @Column(name = "roofing_type", nullable = true, length = 100)
    public String getRoofingType() {
        return roofingType;
    }

    public void setRoofingType(String roofingType) {
        this.roofingType = roofingType;
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
    @Column(name = "management_company_id", nullable = false)
    public int getManagementCompanyId() {
        return managementCompanyId;
    }

    public void setManagementCompanyId(int managementCompanyId) {
        this.managementCompanyId = managementCompanyId;
    }

    @Basic
    @Column(name = "micro_district_id", nullable = false)
    public int getMicroDistrictId() {
        return microDistrictId;
    }

    public void setMicroDistrictId(int microDistrictId) {
        this.microDistrictId = microDistrictId;
    }

    @Basic
    @Column(name = "house_material_id")
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
    @Column(name = "riser_type_id")
    public Integer getRiserTypeId() {
        return riserTypeId;
    }

    public void setRiserTypeId(Integer riserTypeId) {
        this.riserTypeId = riserTypeId;
    }

    @Basic
    @Column(name = "roofing_type_id")
    public Integer getRoofingTypeId() {
        return roofingTypeId;
    }

    public void setRoofingTypeId(Integer roofingTypeId) {
        this.roofingTypeId = roofingTypeId;
    }

    @Basic
    @Column(name = "manager_name")
    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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

        TicketsVEntity that = (TicketsVEntity) o;

        if (id != that.id) return false;
        if (flat != that.flat) return false;
        if (Double.compare(that.debt, debt) != 0) return false;
        if (managementCompany != null ? !managementCompany.equals(that.managementCompany) : that.managementCompany != null)
            return false;
        if (microDistrict != null ? !microDistrict.equals(that.microDistrict) : that.microDistrict != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (floor != null ? !floor.equals(that.floor) : that.floor != null) return false;
        if (floorsNumber != null ? !floorsNumber.equals(that.floorsNumber) : that.floorsNumber != null) return false;
        if (houseMaterial != null ? !houseMaterial.equals(that.houseMaterial) : that.houseMaterial != null)
            return false;
        if (statusName != null ? !statusName.equals(that.statusName) : that.statusName != null) return false;
        if (riserType != null ? !riserType.equals(that.riserType) : that.riserType != null) return false;
        if (riserCount != null ? !riserCount.equals(that.riserCount) : that.riserCount != null) return false;
        if (roofingType != null ? !roofingType.equals(that.roofingType) : that.roofingType != null) return false;
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
        int result;
        long temp;
        result = id;
        result = 31 * result + (managementCompany != null ? managementCompany.hashCode() : 0);
        result = 31 * result + (microDistrict != null ? microDistrict.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + flat;
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (floorsNumber != null ? floorsNumber.hashCode() : 0);
        result = 31 * result + (houseMaterial != null ? houseMaterial.hashCode() : 0);
        result = 31 * result + (statusName != null ? statusName.hashCode() : 0);
        result = 31 * result + (riserType != null ? riserType.hashCode() : 0);
        result = 31 * result + (riserCount != null ? riserCount.hashCode() : 0);
        result = 31 * result + (roofingType != null ? roofingType.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastStatusSetDate != null ? lastStatusSetDate.hashCode() : 0);
        result = 31 * result + (installer != null ? installer.hashCode() : 0);
        result = 31 * result + (flexibleField1 != null ? flexibleField1.hashCode() : 0);
        result = 31 * result + (flexibleField2 != null ? flexibleField2.hashCode() : 0);
        result = 31 * result + (flexibleField3 != null ? flexibleField3.hashCode() : 0);
        result = 31 * result + (flexibleField4 != null ? flexibleField4.hashCode() : 0);
        result = 31 * result + (flexibleField5 != null ? flexibleField5.hashCode() : 0);
        temp = Double.doubleToLongBits(debt);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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
    public String getMicroDistinctP() {
        return microDistinctP.get();
    }

    public StringProperty microDistinctPProperty() {
        return microDistinctP;
    }

    public void setMicroDistinctP(String microDistinctP) {
        this.microDistinctP.set(microDistinctP);
    }
    @Transient
    public String getAddressP() {
        return addressP.get();
    }

    public StringProperty addressPProperty() {
        return addressP;
    }

    public void setAddressP(String addressP) {
        this.addressP.set(addressP);
    }
    @Transient
    public int getFlatP() {
        return flatP.get();
    }

    public IntegerProperty flatPProperty() {
        return flatP;
    }

    public void setFlatP(int flatP) {
        this.flatP.set(flatP);
    }
    @Transient
    public double getDebtP() {
        return debtP.get();
    }

    public DoubleProperty debtPProperty() {
        return debtP;
    }

    public void setDebtP(double debtP) {
        this.debtP.set(debtP);
    }
    @Transient
    public LocalDate getDateP() {
        return dateP.get();
    }

    public ObjectProperty<LocalDate> datePProperty() {
        return dateP;
    }

    public void setDateP(LocalDate dateP) {
        this.dateP.set(dateP);
    }
    @Transient
    public String getStateP() {
        return stateP.get();
    }

    public StringProperty statePProperty() {
        return stateP;
    }

    public void setStateP(String stateP) {
        this.stateP.set(stateP);
    }
}
