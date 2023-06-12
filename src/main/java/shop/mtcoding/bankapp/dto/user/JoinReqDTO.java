package shop.mtcoding.bankapp.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.bankapp.model.user.User;

@ToString
@Getter @Setter
public class JoinReqDTO {
    private String username;
    private String password;
    private String fullname;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .fullname(fullname)
                .build();
    }
}
