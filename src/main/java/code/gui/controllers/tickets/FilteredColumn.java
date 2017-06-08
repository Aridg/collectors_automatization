package code.gui.controllers.tickets;

/**
 * Created by Алексей on 08.05.2017.
 */
public enum FilteredColumn {
    MANAGEMENT_COMPANY("managementCompany", "УК"),
    MICRO_DISTRICT("microDistrict", "Микрорайон"),
    ADDRESS("address", "Адрес"),
    FLAT("flat", "Квартира"),
    FLOOR("floor", "Этаж"),
    FLOORS_NUMBER("floorsNumber", "Этажность дома"),
    HOUSE_MATERIAL("houseMaterial", "Материал дома"),
    STATUS("statusName", "Статус"),
    RISER_TYPE("riserType", "Материал стояка"),
    RISER_COUNT("riserCount", "Кол-во стояков"),
    ROOFING_TYPE("roofingType", "Тип кровли"),
    CREATION_DATE("creationDate", "Дата создания"),
    STATUS_DATE("lastStatusSetDate", "Дата изменения статуса"),
    INSTALLER("installer", "Монтажник"),
    DEBT("debt", "Сумма долга"),
    FLEX_FIELD1("flexibleField1", "Доп. поле 1"),
    FLEX_FIELD2("flexibleField2", "Доп. поле 2"),
    FLEX_FIELD3("flexibleField3", "Доп. поле 3"),
    FLEX_FIELD4("flexibleField4", "Доп. поле 4"),
    FLEX_FIELD5("flexibleField5", "Доп. поле 5"),
    ;

    private String columnName;
    private String displayName;

    FilteredColumn(String columnName, String displayName){
        this.columnName = columnName;
        this.displayName = displayName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getDisplayName() {
        return displayName;
    }


    @Override
    public String toString() {
        return displayName;
    }
}
