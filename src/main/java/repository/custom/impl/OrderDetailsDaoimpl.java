package repository.custom.impl;

import dto.Item;
import dto.Supplier;
import dto.User;
import entity.ItemEntity;
import entity.OrderDetailsEntity;
import entity.UserEntity;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.OrderDetailsDao;
import utill.HibernateUtil;

public class OrderDetailsDaoimpl implements OrderDetailsDao {
    @Override
    public boolean save(OrderDetailsEntity orderDetailsEntity) {
        System.out.println("Repository : " + orderDetailsEntity);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(orderDetailsEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ObservableList<OrderDetailsEntity> getAll() {
        return null;
    }

    @Override
    public String cheackEmail(String email, String passwordE) {
        return "";
    }

    @Override
    public boolean deleteEmployee(String email) {
        return false;
    }

    @Override
    public boolean updateEmployee(User user) {
        return false;
    }

    @Override
    public UserEntity setUserDetails() {
        return null;
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public boolean deleteItem(String code) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public boolean delete(String supId) {
        return false;
    }

    @Override
    public boolean update(Supplier supplier) {
        return false;
    }

    @Override
    public ObservableList<ItemEntity> getItemCode() {
        return null;
    }

    @Override
    public ItemEntity searchItem(String itemCode) {
        return null;
    }
}
