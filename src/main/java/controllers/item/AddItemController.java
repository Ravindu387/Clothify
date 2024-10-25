package controllers.item;

import com.jfoenix.controls.JFXTextField;
import dto.Item;
import dto.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import service.ServiceFactory;
import service.custom.ItemService;
import service.custom.UserService;
import utill.ServiceType;

public class AddItemController {

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
        Item item = new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );

        System.out.println(item);
        if(itemService.addItem(item)){
            new Alert(Alert.AlertType.ERROR,"Employee Not Added :").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Employee Added !!").show();
        }
        txtItemCode.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        txtUnitPrice.setText("");

    }

}
