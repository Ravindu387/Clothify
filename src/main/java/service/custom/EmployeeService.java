package service.custom;

import dto.Employee;
import service.SuperService;

public interface EmployeeService extends SuperService {

    boolean addEmployee(Employee employee);
    String cheackEmail(String email,String password);

}
