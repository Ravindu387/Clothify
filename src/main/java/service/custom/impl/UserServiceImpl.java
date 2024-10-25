package service.custom.impl;

import dto.User;
import entity.UserEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserDao;
import service.custom.UserService;
import utill.DaoType;

public class UserServiceImpl implements UserService {
    private String enterEmail;
    @Override
    public boolean addUser(User user) {
        System.out.println("Service Layer : " + user);
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        userDao.save(entity);
        System.out.println("Service Layer : " + user);

        return false;
    }

    @Override
    public ObservableList<UserEntity> getAll() {
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        ObservableList<UserEntity> all = userDao.getAll();
        return all;
    }

    @Override
    public boolean deleteEmployee(String email) {
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        userDao.deleteEmployee(email);
        return true;
    }

    @Override
    public boolean updateEmployee(User user) {
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        userDao.updateEmployee(user);
        return true;
    }

    @Override
    public UserEntity setUserDetails() {
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        UserEntity userEntity = userDao.setUserDetails();
        System.out.println(userEntity);
        return  userEntity;
    }

    private static UserServiceImpl instance;
    public static UserServiceImpl getInstance() {
        return null==instance?instance=new UserServiceImpl():instance;
    }

    public void setEmail(String email) {
        this.enterEmail=email;
    }
}
