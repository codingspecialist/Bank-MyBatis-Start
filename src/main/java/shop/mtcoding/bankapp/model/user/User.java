package shop.mtcoding.bankapp.model.user;

import java.sql.Timestamp;

import lombok.*;

@Setter
@Getter
public class User {
    private Integer id;
    private String username;
    private String password;
    private String fullname;
    private Timestamp createdAt;

    @Builder
    public User(Integer id, String username, String password, String fullname, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.createdAt = createdAt;
    }
}
