package Service;
import Entity.User;
import Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Value;

import java.util.Optional;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

@Value("${jasypt.encryptor.password}")
private String encryptionKey;

    private static final String ENCRYPTION_ALGORITHM = "PBEWithMD5AndDES";
@Transactional
    public User registerUser(String name, String email, String rawPassword) {
        if (rawPassword == null || rawPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be set empty");
        }

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptionKey);
        encryptor.setAlgorithm(ENCRYPTION_ALGORITHM);
        String encryptedPassword = encryptor.encrypt(rawPassword);

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }
@Transactional
    public User loginUser(String email, String inputPassword) {
        User user = userRepository.findByEmail(email);


        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptionKey);
        encryptor.setAlgorithm(ENCRYPTION_ALGORITHM);

        String decryptedPassword = encryptor.decrypt(user.getPassword());

        if (!decryptedPassword.equals(inputPassword)) {
            throw new RuntimeException("Invalid credentials");
        }

        return user;
    }


    @Transactional
    public Optional<User>getUserById(Long id) {
        return userRepository.findById(id);
    }
@Transactional
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
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
@Transactional
public void deleteUserById(Long id) {
    if (!userRepository.existsById(id)) {
        throw new RuntimeException("Perdoruesi me ID " + id + " nuk u gjet.");
    }
        userRepository.deleteById(id);
}
}

