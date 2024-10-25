package repository.custom.impl;

import db.DBConnection;
import dto.Item;
import dto.User;
import entity.ItemEntity;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.ItemDao;
import utill.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity itemEntity) {
        System.out.println("Repository : " + itemEntity);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(itemEntity);
        session.getTransaction().commit();
        session.close();
        return false;

    }

    @Override
    public ObservableList<ItemEntity> getAll() {
        ObservableList<ItemEntity> itemObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM itementity";
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()) {
                ItemEntity item = new ItemEntity(
                        resultSet.getString("itemCode"),
                        resultSet.getString("description"),
                        resultSet.getInt("qyt"),
                        resultSet.getDouble("unitPrice")

                );
                itemObservableList.add(item);
                // System.out.println(user);
            }
            System.out.println(itemObservableList);
            return itemObservableList;
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
        String SQL = "DELETE FROM itementity WHERE itemCode='" + code + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItem(Item item) {
        String sql = "UPDATE itementity SET description=?, qyt=?, unitPrice=? WHERE itemCode=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, item.getDescription());
            statement.setString(2, String.valueOf(item.getQyt()));
            statement.setString(3, String.valueOf(item.getUnitPrice()));
            statement.setString(4, item.getItemCode());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows were updated. Email not found: " + item.getItemCode());
            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }


}
