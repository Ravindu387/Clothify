package controllers.supplier;

import com.jfoenix.controls.JFXTextField;
import dto.Supplier;
import entity.ItemEntity;
import entity.SupplierEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.ItemService;
import service.custom.SupplierService;
import utill.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageSupplierController implements Initializable {

    @FXML
    private TableColumn colItemName;

    @FXML
    private TableColumn colPnumber;

    @FXML
    private TableColumn colSupId;

    @FXML
    private TableColumn colSupName;

    @FXML
    private TableView<SupplierEntity> tblSupplier;

    @FXML
    private JFXTextField txtItemName;

    @FXML
    private JFXTextField txtPnumber;

    @FXML
    private JFXTextField txtSupId;

    @FXML
    private JFXTextField txtSupName;

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        if (supplierService.deleteItem(txtSupId.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted !!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Item Not Deleted !!").show();
        }
        txtSupId.setText("");
        txtSupName.setText("");
        txtItemName.setText("");
        txtPnumber.setText("");

    }

    @FXML
    void btnOnActionReload(ActionEvent event) {
        loadTable();
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtSupId.getText(),
                txtSupName.getText(),
                txtItemName.getText(),
                txtPnumber.getText()
        );
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        if ( supplierService.updateSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION,"Item Updated !!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Item Not Updated !!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        colItemName.setCellValueFactory(new  PropertyValueFactory<>("itemName"));
        colPnumber.setCellValueFactory(new  PropertyValueFactory<>("pNumber"));
        tblSupplier.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }

    private void setTextToValues(SupplierEntity newValue) {
        try {
            txtSupId.setText(newValue.getSupId());
            txtSupName.setText(newValue.getSupName());
            txtItemName.setText(newValue.getItemName());
            txtPnumber.setText(newValue.getPNumber());
        }catch (NullPointerException e){
            System.out.println(e);
        }

    }

    private void loadTable() {
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);
        ObservableList<SupplierEntity> all = supplierService.getAll();
        System.out.println(all);
        tblSupplier.setItems(all);


    }
}
