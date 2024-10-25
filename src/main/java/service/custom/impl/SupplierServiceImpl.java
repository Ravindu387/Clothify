package service.custom.impl;

import dto.Supplier;
import entity.ItemEntity;
import entity.SupplierEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import repository.custom.SupplierDao;
import service.custom.SupplierService;
import utill.DaoType;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public boolean addSupplier(Supplier supplier) {
        System.out.println("Service Layer : " + supplier);
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
         SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        supplierDao.save(entity);
        return false;
    }

    @Override
    public ObservableList<SupplierEntity> getAll() {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        ObservableList<SupplierEntity> all = supplierDao.getAll();
        return all;
    }

    @Override
    public boolean deleteItem(String supId) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        supplierDao.delete(supId);
        return true;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        supplierDao.update(supplier);
        return true;
    }
}
