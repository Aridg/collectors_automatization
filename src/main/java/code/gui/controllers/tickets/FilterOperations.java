package code.gui.controllers.tickets;

/**
 * Created by Алексей on 08.05.2017.
 */
public enum FilterOperations {
    MORE("больше",">"),
    LESS("меньше","<"),
    EQUALLY("равно","="),
    NOT_EQUAL("не равно","<>"),
    LIKE("содержит","like"),
    MORE_EQ("больше, либо равно",">="),
    LESS_EQ("меньше, либо равно","<=")
    ;

    private String displayName;
    private String sqlSymbol;

    FilterOperations(String displayName, String sqlSymbol) {
        this.displayName = displayName;
        this.sqlSymbol = sqlSymbol;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSqlSymbol() {
        return sqlSymbol;
    }


    @Override
    public String toString() {
        return displayName;
    }
}
