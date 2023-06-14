package shop.mtcoding.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.bankapp.dto.user.JoinReqDTO;
import shop.mtcoding.bankapp.dto.user.LoginReqDTO;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.user.User;
import shop.mtcoding.bankapp.model.user.UserRepository;

import java.lang.reflect.Parameter;

@Service // IoC
public class UserService {

    @Autowired // DI
    private UserRepository userRepository;

    @Transactional // RuntimeException 발동하면 rollback 한다.
    public void 회원가입(JoinReqDTO joinReqDTO) {
        // 1. 아이디 중복체크 (필터링)
        User user = userRepository.findByUsername(joinReqDTO.getUsername());
        if(user != null){
            throw new CustomException("username이 중복되었습니다");
        }

        // 2. 핵심로직 -1(내부에서 터짐), 0, 1
        try {
            // delete, update, insert 무조건 서비스단에 try catch 걸어서 제어권을 뺏자.
            userRepository.insert(joinReqDTO.toEntity()); // JPA, MyBatis (DAO 오류나면 내부에 터진다.)
        }catch (RuntimeException e){
            throw new CustomException("디비 에러 : "+e.getMessage());
        }
    }

    public User 로그인(LoginReqDTO loginReqDTO) {
        User user = userRepository.findByUsernameAndPassword(loginReqDTO.getUsername(), loginReqDTO.getPassword());
        if(user == null){
            throw new CustomException("아이디 혹은 패스워드가 틀렸습니다");
        }
        return user;
    }
}
