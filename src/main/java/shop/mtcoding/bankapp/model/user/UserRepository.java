package shop.mtcoding.bankapp.model.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {

    User findByUsername(String username);

    int insert(User user);

    int updateById(User user);

    int deleteById(int id);

    List<User> findAll();

    User findById(int id);

    User findByUsernameAndPassword(String username, String password);
}
