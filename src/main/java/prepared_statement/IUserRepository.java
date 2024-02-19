package prepared_statement;

import entity.User;

import java.util.List;

public interface IUserRepository {
    List<User> findAllV1();
    List<User> findAll();
    List<User> findById(Long id);
}
