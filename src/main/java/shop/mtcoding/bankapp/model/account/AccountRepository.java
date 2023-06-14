package shop.mtcoding.bankapp.model.account;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountRepository {

    List<Account> findByUserId(int principalId);

    int insert(Account account);

    int updateById(Account account);

    int deleteById(int id);

    List<Account> findAll();

    Account findById(int id);
}
