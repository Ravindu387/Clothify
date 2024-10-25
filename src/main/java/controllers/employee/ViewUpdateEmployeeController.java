package controllers.employee;

import com.jfoenix.controls.JFXTextField;
import dto.User;
import entity.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import repository.custom.impl.UserDaoImpl;
import service.ServiceFactory;
import service.custom.UserService;
import utill.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewUpdateEmployeeController implements Initializable {

    @FXML
    private TableColumn colAge;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn coleType;

    @FXML
    private TableView<UserEntity> tblEmployee;

    @FXML
    private JFXTextField txtAge;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtEmployeeType;

    @FXML
    private JFXTextField txtName;

    UserDaoImpl userDao;
    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        User user=new User(
                txtEmail.getText(),
                txtName.getText(),
                txtAge.getText(),
                txtEmployeeType.getText()
        );
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
        if ( userService.updateEmployee(user)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Updated !!").show();
        }else{
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fname"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        coleType.setCellValueFactory(new PropertyValueFactory<>("etype"));
        tblEmployee.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }
    private void setTextToValues(UserEntity newValue) {
        try {
            txtEmail.setText(newValue.getEmail());
            txtName.setText(newValue.getFname());
            txtAge.setText(newValue.getAge());
            txtEmployeeType.setText(newValue.getEtype());
        }catch (NullPointerException e){
            System.out.println(e);
        }

    }
    private void loadTable() {
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
        ObservableList<UserEntity> all = userService.getAll();
        System.out.println(all);
        tblEmployee.setItems(all);


    }

    public void btnOnActionDelete(ActionEvent actionEvent) {
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
        if (userService.deleteEmployee(txtEmail.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Employee Deleted !!").show();
        }else{
            new Alert(Alert.AlertType.ERROR).show();
        }
        txtEmail.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtEmployeeType.setText("");
    }

    public void btnOnActionReload(ActionEvent actionEvent) {
        loadTable();
    }
}
