package controllers.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controllers.account.LoginPageController;
import entity.UserEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.EmployeeService;
import service.custom.UserService;
import service.custom.impl.UserServiceImpl;
import utill.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class EmployeeDashboard  implements Initializable{

    public Label txtAge;
    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label txtEmail;

    @FXML
    private Label txtEtype;

    @FXML
    private Label txtId;

    @FXML
    private Label txtName;

    @FXML
    private JFXTextField txtSearch;

    private String enterEmail;

    @FXML
    private JFXButton btnEmployee;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        setUserDetails();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);

        lblDate.setText(dateNow);

//-------------------------------------------------------------------------------

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }



    public void btnOnActionEmployee(javafx.event.ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/employee_home.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUserDetails() {
            UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.USER);
            UserEntity userEntity = userService.setUserDetails();
             System.out.println(userEntity.getAge());
             String age = userEntity.getAge();
              txtEmail.setText(userEntity.getEmail());
              txtName.setText(userEntity.getFname());
              txtAge.setText(userEntity.getAge());
              txtEtype.setText(userEntity.getEtype());
        if ("USER".equalsIgnoreCase(txtEtype.getText().trim())) {
            btnEmployee.setVisible(false);  // This will hide the button
        } else {
            btnEmployee.setVisible(true);   // This will show the button
        }

    }
    private static EmployeeDashboard instance;
    public static EmployeeDashboard getInstance()  {
        return null==instance?instance=new EmployeeDashboard():instance;
    }


    public void btnOnActionItem(javafx.event.ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/item_home.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnOnActionSupplier(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/supplier_home.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnOnActionOrder(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/placeOrder.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
