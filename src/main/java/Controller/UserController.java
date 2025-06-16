package Controller;

import Entity.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/user1")
public class UserController {
   @Autowired
   private UserService userService;
   @GetMapping
   public ResponseEntity<Iterable<User>> getAllUsers() {
      Iterable <User> users = userService.getAllUsers();
      return ResponseEntity.ok(users);
   }
   @PostMapping("register")
   public ResponseEntity<User> registerUser(@RequestBody User user) {
      User createdUser = userService.registerUser(user.getName(), user.getEmail(), user.getPassword());
      return ResponseEntity.ok(createdUser);
   }

   @PostMapping("login")
   public ResponseEntity<User> loginUser(@RequestBody String email, @RequestBody String password) {
      userService.loginUser(email, password);
      return ResponseEntity.ok().build();

   }

   @GetMapping("id")
   public ResponseEntity<User> getUserById(@RequestParam Long id) {
      Optional<User> user = userService.getUserById(id);
      return user.map(ResponseEntity::ok)
              .orElseGet(() -> ResponseEntity.notFound().build());

   }

   @PutMapping("{id}")
   public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
      try {
         User updatedUser = userService.updateUser(id, user.getName(), user.getEmail(), user.getPassword());
         return ResponseEntity.ok(updatedUser);
      } catch (RuntimeException e) {
         return ResponseEntity.badRequest().body(null);
      }
   }

   @DeleteMapping("{id}")
   public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
      try {
         userService.deleteUserById(id);
         return ResponseEntity.ok().build();
      } catch (RuntimeException e) {

         return ResponseEntity.notFound().build();

      }
   }
   @ExceptionHandler(IllegalArgumentException.class)
   public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   }


   @ExceptionHandler(ResourceNotFoundExceptionU.class)
   public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundExceptionU ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }
}


class ResourceNotFoundExceptionU extends RuntimeException {
   public ResourceNotFoundExceptionU(String message) {
      super(message);
   }

}