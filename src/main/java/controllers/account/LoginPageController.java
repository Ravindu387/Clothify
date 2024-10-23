package controllers.account;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.EmployeeService;
import utill.ServiceType;

import java.io.IOException;
import java.sql.SQLException;

public class LoginPageController {

    @FXML
    private JFXTextField txtEmail;

    @FXML
     Label txtError;

    @FXML
    private JFXTextField txtPassword;


    @FXML
    void btnOnActionCreateAccount(MouseEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/create_Account.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnOnActionLogin(ActionEvent event) {
        EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        employeeService.cheackEmail(email,password);




    }
    private static LoginPageController instance;
    public static LoginPageController getInstance()  {
        return null==instance?instance=new LoginPageController():instance;
    }

}
