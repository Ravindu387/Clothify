package repository;

import dto.Employee;
import dto.User;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface CrudRepository<T> extends SuperDao{
    boolean save(T t);
    String cheackEmail(String email,String passwordE);

}
