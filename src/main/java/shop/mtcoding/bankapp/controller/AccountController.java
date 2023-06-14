package shop.mtcoding.bankapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.bankapp.dto.account.AccountDetailRespDTO;
import shop.mtcoding.bankapp.dto.history.HistoryRespDTO;
import shop.mtcoding.bankapp.handler.ex.AuthException;
import shop.mtcoding.bankapp.handler.ex.CustomException;
import shop.mtcoding.bankapp.model.account.Account;
import shop.mtcoding.bankapp.model.account.AccountRepository;
import shop.mtcoding.bankapp.model.history.HistoryRepository;
import shop.mtcoding.bankapp.model.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AccountController {

    @Autowired
    private HttpSession session;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private HistoryRepository historyRepository;


    // 컨트롤러 매개변수에는 request정보와 IoC 컨테이너 정보를 전부 주입할 수 있다.
    @GetMapping({ "/", "/account" })
    public String main(Model model) {
        // 1. 인증 검사 (시큐리티, 인터셉터)
        User principal = (User) session.getAttribute("principal");
        if(principal == null){
            throw new AuthException("인증되지 않았습니다");
        }

        // 2. 레포지토리 - 조회
        List<Account> accountList = accountRepository.findByUserId(principal.getId());

        // 3. 조회된 결과를 - 가방에 담는다. (request)
        model.addAttribute("accountList", accountList);
        //request.setAttribute("accountList", accountList);
        //request.setAttribute("name", "gildong");

        return "account/main";
    }


    // localhost:8080/account/1?gubun=all
    @GetMapping("/account/{id}") // pk, uk, 그게 아닌 모든 것들은 QueryString으로 받기
    public String detail(@PathVariable int id, Model model, @RequestParam(name = "gubun", defaultValue = "all") String gubun) {
        // 1. 인증 검사 (시큐리티, 인터셉터)
        User principal = (User) session.getAttribute("principal");
        if(principal == null){
            throw new AuthException("인증되지 않았습니다");
        }

        AccountDetailRespDTO account = accountRepository.findByIdWithUser(id);
        List<HistoryRespDTO> historyList = historyRepository.findByGubun(gubun, id);
        model.addAttribute("account", account);
        model.addAttribute("historyList", historyList);

        return "account/detail";
    }

    @GetMapping("/account/saveForm")
    public String saveForm() {
        return "account/saveForm";
    }

    @GetMapping("/account/withdrawForm")
    public String withdrawForm() {
        return "account/withdrawForm";
    }

    @GetMapping("/account/depositForm")
    public String depositForm() {
        return "account/depositForm";
    }

    @GetMapping("/account/transferForm")
    public String transferForm() {
        return "account/transferForm";
    }
}
