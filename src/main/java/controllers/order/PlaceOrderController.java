package controllers.order;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Item;
import dto.OrderCart;
import dto.OrderDetails;
import entity.ItemEntity;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import service.ServiceFactory;
import service.custom.ItemService;
import service.custom.OrderDetailsService;
import service.custom.SupplierService;
import utill.ServiceType;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbItem;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<OrderCart> tblCart;

    @FXML
    private JFXTextField txtCusName;

    @FXML
    private JFXTextField txtDate;

    @FXML
    private JFXTextField txtDescription;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtStrock;

    @FXML
    private JFXTextField txtTime;

    @FXML
    private JFXTextField txtUnitPrice;

    ObservableList<OrderCart> cartTMS = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        String itemCode = cmbItem.getValue();
        String description = txtDescription.getText();
        Integer qty = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        Double total = unitPrice*qty;

        if (Integer.parseInt(txtStrock.getText())<qty){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
        }else{
            cartTMS.add(new OrderCart(itemCode,description,qty,unitPrice,total));
            calcNetTotal();
        }


        tblCart.setItems(cartTMS);



    }

    @FXML
    void btnOnActionPlaceOrder(ActionEvent event) {
         OrderDetailsService orderDetailsService = ServiceFactory.getInstance().getServiceType(ServiceType.ORDERDETAILS);
         OrderDetails orderDetails = new OrderDetails(
                txtId.getText(),
                txtCusName.getText(),
                 txtTime.getText(),
                 txtTime.getText()

        );

        System.out.println(orderDetails);
        if(orderDetailsService.addOrderDetails(orderDetails)){
            new Alert(Alert.AlertType.INFORMATION,"Place Order !! :").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION,"Place order Unsuccessful !!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadItemCodes();

        cmbItem.getSelectionModel().selectedItemProperty().addListener((observableValue, s, newVal) -> {
            if (newVal!=null){
                searchItem(newVal);
            }
        });
    }
    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);

        txtDate.setText(dateNow);

//-------------------------------------------------------------------------------

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            txtTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }

    private void loadItemCodes(){
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
        cmbItem.setItems(itemService.getItemCode());
    }

    private void searchItem(String newVal) {
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
        ItemEntity entity = itemService.searchItem(newVal);
        txtDescription.setText(entity.getDescription());
        txtStrock.setText(String.valueOf(entity.getQyt()));
        txtUnitPrice.setText(String.valueOf(entity.getUnitPrice()));
    }
    private void calcNetTotal(){
        Double total=0.0;

        for (OrderCart cartTM: cartTMS){
            total+=cartTM.getTotal();
        }

        lblNetTotal.setText(total.toString()+"/=");

    }
}
