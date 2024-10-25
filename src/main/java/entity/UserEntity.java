package entity;

import controllers.account.LoginPageController;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class UserEntity {
    @Id
    private String  email;
    private String fname;
    private String  age;
    private String  etype;

    private static UserEntity instance;
    public static UserEntity getInstance()  {
        return null==instance?instance=new UserEntity():instance;
    }
}
