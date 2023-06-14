package shop.mtcoding.bankapp.model.account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import shop.mtcoding.bankapp.dto.account.AccountDetailRespDTO;

@Mapper
public interface AccountRepository {

    AccountDetailRespDTO findByIdWithUser(int id);

    List<Account> findByUserId(int principalId);

    int insert(Account account);

    int updateById(Account account);

    int deleteById(int id);

    List<Account> findAll();

    Account findById(int id);
}
