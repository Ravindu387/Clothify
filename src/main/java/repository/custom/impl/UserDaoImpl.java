package repository.custom.impl;

import db.DBConnection;
import dto.Item;
import dto.User;
import entity.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import repository.custom.UserDao;
import utill.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private String enterEmail;
    @Override
    public boolean save(UserEntity userEntity) {
        System.out.println("Repository : " + userEntity);
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(userEntity);
        session.getTransaction().commit();
        session.close();
        return false;
    }

    @Override
    public ObservableList<UserEntity> getAll() {
        ObservableList<UserEntity> userObservableList = FXCollections.observableArrayList();
        try {
            String SQL = "SELECT * FROM userentity";
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            while (resultSet.next()) {
                UserEntity user = new UserEntity(
                        resultSet.getString("email"),
                        resultSet.getString("fName"),
                        resultSet.getString("age"),
                        resultSet.getString("etype")

                );
               userObservableList.add(user);
               // System.out.println(user);
            }
            System.out.println(userObservableList);
            return userObservableList;
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
        String SQL = "DELETE FROM userentity WHERE email='" + email + "'";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            return connection.createStatement().executeUpdate(SQL) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateEmployee(User user) {
        String sql = "UPDATE userentity SET fname=?, age=?, etype=? WHERE email=?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user.getFname());
            statement.setString(2, user.getAge());
            statement.setString(3, user.getEType());
            statement.setString(4, user.getEmail());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows were updated. Email not found: " + user.getEmail());
            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public UserEntity setUserDetails() {
        String email;
        try {
            String sql = "SELECT * FROM useremail";
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);
            PreparedStatement psTm = connection.prepareStatement(sql);
            ResultSet resultSet = psTm.executeQuery();

            // Check if resultSet has data and move cursor to first row
            while (resultSet.next()) {
                email=resultSet.getString("email");
                System.out.println("dao " + email);
                this.enterEmail=email;

            }

            // Close resources
            resultSet.close();
            psTm.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

            }



        try {
            String SQL = "SELECT * FROM userentity WHERE email=?";
            Connection connection = DBConnection.getInstance().getConnection();
            System.out.println(connection);
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setString(1,this.enterEmail);
            ResultSet resultSet = psTm.executeQuery();

            // Check if resultSet has data
            if (resultSet.next()) {
                UserEntity user = new UserEntity(
                        resultSet.getString("email"),
                        resultSet.getString("fName"),
                        resultSet.getString("age"),
                        resultSet.getString("etype")
                );
                System.out.println(user);
                return user;
            } else {
                // Handle case when no user is found
                return null; // or throw new UserNotFoundException()
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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


}
