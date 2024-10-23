package service.custom.impl;

import controllers.account.LoginPageController;
import db.DBConnection;
import dto.Employee;
import entity.EmployeeEntity;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import utill.DaoType;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;

public class EmployeeServiceImpl implements EmployeeService {

    private String email;
    private static EmployeeServiceImpl instance;
    private static final String SECRET_KEY = "MySecretKey12345"; // 16 characters for AES
    private static final String ALGORITHM = "AES";

    public static String decrypt(String encryptedPassword) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    public static String encrypt(String password) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    @Override
    public boolean addEmployee(Employee employee) {

        EmployeeEntity entity = new ModelMapper().map(employee, EmployeeEntity.class);

        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

        employeeDao.save(entity);

        System.out.println("Service Layer : " + employee);
        return false;
    }

    @Override
    public String cheackEmail(String email,String password) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);

        employeeDao.cheackEmail(email,password);
        return email;

    }

    public String validate(String validPassword, String passwordE) throws Exception {
        System.out.println(validPassword+"  "+passwordE);
        String decrypt = decrypt(validPassword);
        String encrypt = encrypt(passwordE);
        System.out.println(decrypt);
        if (validPassword.equals(encrypt)){
            System.out.println("correct");
            Stage stage = new Stage();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../../view/dash_board.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Password is incorrect..!").show();
            System.out.println("incorrect");
        }
        return validPassword;
    }

    public static EmployeeServiceImpl getInstance() {
        return null==instance?instance=new EmployeeServiceImpl():instance;
    }

}
