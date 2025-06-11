package Service;
import Entity.User;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(String name,String email,String password) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email Already Exists");
        }
        if (userRepository.existsByEmail(name)) {
            throw new RuntimeException("Email Already Exists");
        }
        User user = User.builder().name(name).email(email).password(password).build();
        return userRepository.save(user);

}
public User loginUser(String email, String password) {
//to do hash password
        User user = userRepository.findByEmail(email);
        if (user.getPassword().equals(password)) {
            return user;
        }
        return user;

}
    public Optional<User>getUserById(Long id) {
        return userRepository.findById(id);
    }
public User updateUser(Long id,String email, String name, String password) {
    Optional<User> existingUser = userRepository.findById(id);
    if (!existingUser.isPresent()) {
        throw new RuntimeException("Perdoruesi me ID " + id + " nuk u gjet.");
    }
    if (userRepository.existsByEmail(email) && !existingUser.get().getEmail().equals(email)) {
        throw new RuntimeException("Email Already Exists");
    }
    if (userRepository.existsByName(name)&& !existingUser.get().getName().equals(name)) {

    throw new RuntimeException("Name Already Exists");
    }
    User user = existingUser.get();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    return userRepository.save(user);
}
public void deleteUserById(Long id) {
    if (!userRepository.existsById(id)) {
        throw new RuntimeException("Perdoruesi me ID " + id + " nuk u gjet.");
    }
        userRepository.deleteById(id);
}

    }