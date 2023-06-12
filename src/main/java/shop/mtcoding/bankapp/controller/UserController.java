package shop.mtcoding.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.bankapp.dto.user.JoinReqDTO;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    // username=ssar&password=1234

    // 1. 클라이언트의 요청 데이터 받기 (request 객체) (x-www-form-urlencoded) (application/json)
    // 2. 유효성 검사
    // 3. 서비스 호출
    // 4. 서비스로 정상적인 처리가 끝나면, 응답 (MessageConverter, ViewResolover)
    @PostMapping("/join")
    public String join(JoinReqDTO joinReqDTO){
        if (joinReqDTO.getUsername() == null || joinReqDTO.getUsername().isEmpty()) {
            throw new CustomException("username을 입력해주세요");
        }
        if (joinReqDTO.getPassword() == null || joinReqDTO.getPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요");
        }
        if (joinReqDTO.getFullname() == null || joinReqDTO.getFullname().isEmpty()) {
            throw new CustomException("fullname을 입력해주세요");
        }

        userService.회원가입(joinReqDTO);

        return "redirect:/loginForm";
    }
}
