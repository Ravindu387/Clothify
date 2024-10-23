package repository;

import repository.custom.impl.EmployeeDaoImpl;
import repository.custom.impl.UserDaoImpl;
import utill.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}

    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case USER:return (T) new UserDaoImpl();
        }
        return null;

    }
}
