package service.custom.impl;

import dto.Item;
import entity.ItemEntity;
import entity.UserEntity;
import javafx.collections.ObservableList;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ItemDao;
import repository.custom.UserDao;
import service.custom.ItemService;
import utill.DaoType;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean addItem(Item item) {
        System.out.println("Service Layer : " + item);
        ItemEntity entity = new ModelMapper().map(item, ItemEntity.class);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        itemDao.save(entity);

        return false;
    }

    @Override
    public ObservableList<ItemEntity> getAll() {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        ObservableList<ItemEntity> all = itemDao.getAll();
        return all;
    }

    @Override
    public boolean deleteItem(String code) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        itemDao.deleteItem(code);
        return true;
    }

    @Override
    public boolean updateItem(Item item) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        itemDao.updateItem(item);
        return true;
    }
}
