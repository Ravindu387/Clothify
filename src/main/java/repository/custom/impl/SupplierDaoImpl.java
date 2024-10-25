package repository.custom.impl;

import db.DBConnection;
import dto.Item;
import dto.Supplier;
import dto.User;
import entity.ItemEntity;
import entity.SupplierEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.SupplierDao;
import utill.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        System.out.println("Repository : " + supplierEntity);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return false;
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        ObservableList<SupplierEntity> supplierObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM supplierentity";
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()) {
                SupplierEntity supplierEntity = new SupplierEntity(
                        resultSet.getString("supId"),
                        resultSet.getString("supName"),
                        resultSet.getString("itemName"),
                        resultSet.getString("pNumber")

                );
                supplierObservableList.add(supplierEntity);
                // System.out.println(user);
            }
            System.out.println(supplierObservableList);
            return supplierObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        String SQL = "DELETE FROM supplierentity WHERE supId='" + supId + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Supplier supplier) {
        String sql = "UPDATE supplierentity SET itemName=?, pNumber=?, supName=? WHERE supId=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, supplier.getItemName());
            statement.setString(2, supplier.getPNumber());
            statement.setString(3, supplier.getSupName());
            statement.setString(4, supplier.getSupId());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows were updated. Email not found: " + supplier.getSupId());
            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
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
