package service;

import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.UserServiceImpl;
import utill.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;
    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeServiceImpl();
            case USER:return (T) new UserServiceImpl();
        }
        return null;
    }
}
