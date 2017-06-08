package code.gui.controllers.directories.input_forms;

import code.expandings.Utils;
import code.gui.controllers.directories.IDirectoryController;
import code.hibernate.HibernateSessionFactory;
import code.hibernate.ManagementCompaniesEntity;
import code.hibernate.RoofingTypesEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 * Created by Пользователь on 26.04.2017.
 */
public class AddManagementCompanyController implements IDirectoryController {
    @FXML
    private TextField name;
    @FXML
    private TextField managerField;
    @FXML
    private TextField contact1;
    @FXML
    private TextField contact2;
    @FXML
    private TextField contact3;
    @FXML
    private TextField contact4;
    @FXML
    private TextField contact5;
    @FXML
    private TextField contact6;
    @FXML
    private TextField contact7;

    private ManagementCompaniesEntity company;

    @Override
    public void initialize() {

    }

    @Override
    public void setPopUpStage(Stage stage) {

    }


    public void onAddClick(ActionEvent event) {
        if(Utils.isNullOrEmpty(name.getText()) || Utils.isNullOrEmpty(managerField.getText())){
            new Alert(Alert.AlertType.ERROR, "Заполните название и руководителя").showAndWait();
            return;
        }
        boolean update = true;
        if(company == null) {
            update = false;
            company = new ManagementCompaniesEntity();
        }

        company.setManagementCompany(name.getText());
        company.setManager(managerField.getText());
        company.setPhone1(Utils.convertToDBText(contact1.getText()));
        company.setPhone2(Utils.convertToDBText(contact2.getText()));
        company.setPhone3(Utils.convertToDBText(contact3.getText()));
        company.setPhone4(Utils.convertToDBText(contact4.getText()));
        company.setPhone5(Utils.convertToDBText(contact5.getText()));
        company.setPhone6(Utils.convertToDBText(contact6.getText()));
        company.setPhone7(Utils.convertToDBText(contact7.getText()));

        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        if(update)
            session.update(company);
        else session.save(company);
        session.getTransaction().commit();
        session.close();
        ((Stage)name.getScene().getWindow()).close();
    }

    public void setCompany(ManagementCompaniesEntity company) {
        this.company = company;
        name.setText(company.getManagementCompany());
        managerField.setText(company.getManager());
        contact1.setText(Utils.convertToText(company.getPhone1()));
        contact2.setText(Utils.convertToText(company.getPhone2()));
        contact3.setText(Utils.convertToText(company.getPhone3()));
        contact4.setText(Utils.convertToText(company.getPhone4()));
        contact5.setText(Utils.convertToText(company.getPhone5()));
        contact6.setText(Utils.convertToText(company.getPhone6()));
        contact7.setText(Utils.convertToText(company.getPhone7()));
    }
}
