package code.gui.controllers.tickets;

/**
 * Created by Алексей on 10.05.2017.
 */
public enum Condition {
    AND("AND", "+"),
    //OR("OR", "ИЛИ")
    ;

    private String sqlCondition;
    private String displayCondition;

    Condition(String sqlCondition, String displayCondition) {
        this.sqlCondition = sqlCondition;
        this.displayCondition = displayCondition;
    }

    public String getSqlCondition() {
        return sqlCondition;
    }

    public String getDisplayCondition() {
        return displayCondition;
    }
}
