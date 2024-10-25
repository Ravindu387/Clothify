package controllers.supplier;

import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import service.ServiceFactory;
import service.custom.ItemService;
import service.custom.SupplierService;
import utill.ServiceType;

public class AddSupllierController {

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtPNumber;

    @FXML
    private JFXTextField txtSupId;

    @FXML
    private JFXTextField txtSupName;

    @FXML
    void btnOnActionAdd(ActionEvent event) {
        SupplierService service = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        Supplier supplier = new Supplier(
                txtSupId.getText(),
                txtSupName.getText(),
                txtItemName.getText(),
                txtPNumber.getText()
        );
        System.out.println(supplier);
        if(service.addSupplier(supplier)){
            new Alert(Alert.AlertType.ERROR,"Supplier Not Added :").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Supplier Added !!").show();
        }
        txtSupId.setText("");
        txtSupName.setText("");
        txtItemName.setText("");
        txtPNumber.setText("");
    }

}
