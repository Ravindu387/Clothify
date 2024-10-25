package service.custom.impl;

import dto.OrderDetails;
import entity.OrderDetailsEntity;
import entity.SupplierEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.OrderDetailsDao;
import repository.custom.SupplierDao;
import service.custom.OrderDetailsService;
import utill.DaoType;

public class OrderDetailsServiceImpl implements OrderDetailsService {


    @Override
    public boolean addOrderDetails(OrderDetails orderDetails) {
        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
        OrderDetailsEntity entity = new ModelMapper().map(orderDetails, OrderDetailsEntity.class);
        orderDetailsDao.save(entity);
        return true;
    }
}
