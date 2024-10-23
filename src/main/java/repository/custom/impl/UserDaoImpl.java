package repository.custom.impl;

import entity.UserEntity;
import org.hibernate.Session;
import repository.custom.UserDao;
import utill.HibernateUtil;

public class UserDaoImpl implements UserDao {
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
    public String cheackEmail(String email, String passwordE) {
        return "";
    }
}
