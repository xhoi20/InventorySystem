package Repository;

import Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);


    boolean existsByEmail(String email);


    boolean existsByName(String name);


    User findByName(String name);
}