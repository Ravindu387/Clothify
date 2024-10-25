package repository;

import dto.Item;
import dto.OrderDetails;
import dto.Supplier;
import dto.User;
import entity.ItemEntity;
import entity.UserEntity;
import javafx.collections.ObservableList;

public interface CrudRepository<T> extends SuperDao{
    boolean save(T t);
    ObservableList<T> getAll();
    String cheackEmail(String email,String passwordE);
    boolean deleteEmployee(String email);
    boolean updateEmployee(User user);
    UserEntity setUserDetails();
    void setEmail(String email);
    boolean deleteItem(String code);
    boolean updateItem(Item item);
    boolean delete(String supId);
    boolean update(Supplier supplier);
    ObservableList<ItemEntity> getItemCode();
    ItemEntity searchItem(String itemCode);

}
