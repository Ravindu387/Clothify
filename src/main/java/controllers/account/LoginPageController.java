package controllers.account;

import com.jfoenix.controls.JFXTextField;
import controllers.dashboard.EmployeeDashboard;
import db.DBConnection;
import dto.User;
import entity.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.SuperService;
import service.custom.EmployeeService;
import service.custom.UserService;
import utill.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPageController{

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
        employeeService.setEmail(email);
        employeeService.cheackEmail(email,password);
        txtEmail.setText("");
        txtPassword.setText("");


    }

    private static LoginPageController instance;
    public static LoginPageController getInstance()  {
        return null==instance?instance=new LoginPageController():instance;
    }



}
