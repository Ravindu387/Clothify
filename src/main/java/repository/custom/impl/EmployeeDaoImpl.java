package repository.custom.impl;


import controllers.account.LoginPageController;
import db.DBConnection;
import dto.Employee;
import dto.User;
import entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import service.custom.impl.EmployeeServiceImpl;
import utill.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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
            transaction.commit();
            session.close();
            return password;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





}






