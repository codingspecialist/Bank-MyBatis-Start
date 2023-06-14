package shop.mtcoding.bankapp.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import shop.mtcoding.bankapp.model.user.User;


@Getter @Setter
public class LoginReqDTO {
    private String username;
    private String password;
}
