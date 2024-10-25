package service.custom;

import dto.OrderDetails;
import service.SuperService;

import java.util.function.Supplier;

public interface OrderDetailsService extends SuperService {
    boolean addOrderDetails(OrderDetails orderDetails);
}
