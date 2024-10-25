package controllers.item;

import com.jfoenix.controls.JFXTextField;
import dto.Item;
import dto.User;
import entity.ItemEntity;
import entity.UserEntity;
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
import service.custom.UserService;
import utill.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageItemController implements Initializable {

    @FXML
    private TableColumn colDescription;

    @FXML
    private TableColumn colItemCode;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn coleUnitPrice;

    @FXML
    private TableView<ItemEntity> tblItem;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtUnitPrice;

    @FXML
    void btnOnActionDelete(ActionEvent event) {
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
        if (itemService.deleteItem(txtItemCode.getText())){
            new Alert(Alert.AlertType.INFORMATION,"Item Deleted !!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Item Not Deleted !!").show();
        }
        txtItemCode.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        txtUnitPrice.setText("");
    }

    @FXML
    void btnOnActionReload(ActionEvent event) {
            loadTable();
    }

    @FXML
    void btnOnActionUpdate(ActionEvent event) {
        Item item=new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                Integer.parseInt(txtQty.getText()),
                Double.parseDouble(txtUnitPrice.getText())
        );
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
        if ( itemService.updateItem(item)){
            new Alert(Alert.AlertType.INFORMATION,"Item Updated !!").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Item Not Updated !!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qyt"));
        coleUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            setTextToValues(newValue);
        }));
        loadTable();
    }

    private void setTextToValues(ItemEntity newValue) {
        try {
            txtItemCode.setText(newValue.getItemCode());
            txtDescription.setText(newValue.getDescription());
            txtQty.setText(String.valueOf(newValue.getQyt()));
            txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
        }catch (NullPointerException e){
            System.out.println(e);
        }

    }

    private void loadTable() {
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
        ObservableList<ItemEntity> all = itemService.getAll();
        System.out.println(all);
        tblItem.setItems(all);


    }
}
