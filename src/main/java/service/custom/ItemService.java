package service.custom;

import dto.Item;
import entity.ItemEntity;
import javafx.collections.ObservableList;
import service.SuperService;

public interface ItemService extends SuperService {
    boolean addItem(Item item);
    ObservableList<ItemEntity> getAll();
    boolean deleteItem(String code);
    boolean updateItem(Item item);
}
