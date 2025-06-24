package Service.Interfaces;

import Entity.User;

import java.util.Optional;
public interface IUserService {
    User registerUser(String name, String email, String rawPassword);
    User loginUser(String email, String inputPassword);
    Optional<User> getUserById(Long id);
    Iterable<User> getAllUsers();
    User updateUser(Long id, String email, String name, String password);
    void deleteUserById(Long id);
}
