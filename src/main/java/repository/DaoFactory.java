package repository;

import repository.custom.impl.*;
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
            case ITEM:return (T) new ItemDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case ORDERDETAILS:return (T) new OrderDetailsDaoimpl();
        }
        return null;

    }
}
