package shop.mtcoding.bankapp.model.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import shop.mtcoding.bankapp.model.user.User;
import shop.mtcoding.bankapp.model.user.UserRepository;

import java.util.List;

@MybatisTest // (F - DS - C - S) X - (R - MyBatis) O
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void findByUserId_test(){
        // given
        int principalId = 1;

        // when
        List<Account> accountList = accountRepository.findByUserId(principalId);

        // then
        System.out.println(accountList);
        Assertions.assertThat(accountList.get(0).getNumber()).isEqualTo("1111");
    }



}
