package service.custom.impl;

import dto.User;
import entity.UserEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserDao;
import service.custom.UserService;
import utill.DaoType;

public class UserServiceImpl implements UserService {
    @Override
    public boolean addUser(User user) {
        System.out.println("Service Layer : " + user);
        UserEntity entity = new ModelMapper().map(user, UserEntity.class);
        UserDao userDao = DaoFactory.getInstance().getDaoType(DaoType.USER);
        userDao.save(entity);
        System.out.println("Service Layer : " + user);

        return false;
    }
}
