package code.expandings;

import code.hibernate.IHibernateEntiry;
import javafx.scene.control.ComboBox;

/**
 * Created by Алексей on 27.04.2017.
 */
public class Utils {
    public static boolean isNullOrEmpty(String str){
        return str == null || str.trim().length() == 0;
    }

    public static String convertToText(String input){
        return input == null ? "" : input;
    }
    public static String convertToText(Integer input){
        return input == null ? "" : String.valueOf(input);
    }
    public static String convertToText(Double input){
        return input == null ? "" : String.valueOf(input);
    }

    public static String convertToDBText(String input){
        return input.equals("") ? null : input;
    }
    public static Integer convertToDBInteger(String input) {
        String text = convertToDBText(input);
        return text == null ? null : Integer.parseInt(text);
    }

    public static <T extends IHibernateEntiry> T getSelectedModelFromCombo(ComboBox<T> comboBox){
        if(comboBox.getSelectionModel().getSelectedItem() != null)
            return comboBox.getSelectionModel().getSelectedItem();
        else return null;
    }

    public static <T extends IHibernateEntiry> Integer getSelectedId(ComboBox<T> comboBox){
        if(getSelectedModelFromCombo(comboBox) != null)
            return getSelectedModelFromCombo(comboBox).getId();
        else return null;
    }
}
