package controllers.account;

import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import service.ServiceFactory;
import service.SuperService;
import service.custom.EmployeeService;
import utill.ServiceType;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Connection;
import java.util.Base64;

public class CreateAccountController {

    private static final String SECRET_KEY = "MySecretKey12345"; // 16 characters for AES
    private static final String ALGORITHM = "AES";

    public static String encrypt(String password) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedPassword) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private Label txtError;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtId;

    @FXML
    void btnOnActionCreateAccount(ActionEvent event) throws Exception {
        EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);
        String password = txtPassword.getText();
        String encrypted = encrypt(password);
        Employee employee = new Employee(
                    txtId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                   encrypted
        );

        if(employeeService.addEmployee(employee)){
            new Alert(Alert.AlertType.ERROR,"Employee Not Added :").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Employee Added !!").show();
        }
    }

}
