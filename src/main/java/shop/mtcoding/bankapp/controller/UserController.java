package shop.mtcoding.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.bankapp.dto.user.JoinReqDTO;
import shop.mtcoding.bankapp.dto.user.LoginReqDTO;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.user.User;
import shop.mtcoding.bankapp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired // 세션 객체 IoC 컨테이너 저장
    private HttpSession session;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }

    // 로그인은 select지만, post 요청
    @PostMapping("/login")
    public String login(LoginReqDTO loginReqDTO){
        if (loginReqDTO.getUsername() == null || loginReqDTO.getUsername().isEmpty()) {
            throw new CustomException("username을 입력해주세요");
        }
        if (loginReqDTO.getPassword() == null || loginReqDTO.getPassword().isEmpty()) {
            throw new CustomException("password를 입력해주세요");
        }

        // 서비스 호출
        User principal = userService.로그인(loginReqDTO);

        // 세션에 저장 (로그인 인증 처리)
        session.setAttribute("principal", principal);

        return "redirect:/";
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
