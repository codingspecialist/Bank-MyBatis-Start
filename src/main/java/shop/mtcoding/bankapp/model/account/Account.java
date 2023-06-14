package shop.mtcoding.bankapp.model.account;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@ToString
@Setter
@Getter
public class Account {
    private Integer id;
    private String number;
    private String password;
    private Long balance;
    private Integer userId;
    private Timestamp createdAt;
}
