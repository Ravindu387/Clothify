package controllers.employee;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeHomeController {

    public void btnOnActionAddEmployee(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../../view/addEmployee.fxml"))));
            stage.show();
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
