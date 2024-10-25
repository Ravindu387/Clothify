package repository.custom.impl;


import db.DBConnection;
import dto.Item;
import dto.User;
import entity.EmployeeEntity;
import entity.UserEntity;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.EmployeeDao;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.UserServiceImpl;
import utill.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class EmployeeDaoImpl implements EmployeeDao {
    public String validPassword;
    public boolean save(EmployeeEntity employeeEntity) {
        System.out.println("Repository : " + employeeEntity);

        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(employeeEntity);
        session.getTransaction().commit();
        session.close();
        return false;
    }

    @Override
    public ObservableList<EmployeeEntity> getAll() {
        return null;
    }

    @Override
    public String cheackEmail(String email,String passwordE) {
        String password = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            String HQL = "SELECT e.password FROM EmployeeEntity e WHERE e.email = :email";
            Query<String> query = session.createQuery(HQL, String.class);
            query.setParameter("email", email);
            password = query.uniqueResult();
            System.out.println(password+"  "+passwordE);
            this.validPassword=password;
            EmployeeServiceImpl.getInstance().validate(password,passwordE);
            UserServiceImpl.getInstance().setEmail(email);
            transaction.commit();
            session.close();
            return password;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        String sql = "UPDATE useremail SET email=? ";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, email);


            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows were updated. Email not found: ");
            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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






