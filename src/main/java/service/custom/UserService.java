package service.custom;

import dto.Employee;
import dto.User;
import service.SuperService;

public interface UserService extends SuperService {
    boolean addUser(User user);
}
