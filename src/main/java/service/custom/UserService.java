package service.custom;

import dto.Employee;
import dto.User;
import entity.UserEntity;
import javafx.collections.ObservableList;
import service.SuperService;

public interface UserService extends SuperService {
    boolean addUser(User user);
    ObservableList<UserEntity> getAll();
    boolean deleteEmployee(String email);
    boolean updateEmployee(User user);
    UserEntity setUserDetails();
}
