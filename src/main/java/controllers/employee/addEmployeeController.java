package controllers.employee;

import com.jfoenix.controls.JFXTextField;
import dto.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import service.ServiceFactory;
import service.custom.UserService;
import utill.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class addEmployeeController implements Initializable {

    @FXML
    private ComboBox<String> cmbEmployeeType;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
        User user = new User(
                txtName.getText(),
                txtEmail.getText(),
                txtAge.getText(),
                cmbEmployeeType.getValue().toString()
        );
        System.out.println(user);
        if(userService.addUser(user)){
            new Alert(Alert.AlertType.ERROR,"Employee Not Added :").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Employee Added !!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("ADMIN");
        titles.add("USER");
        cmbEmployeeType.setItems(titles);
    }
}
