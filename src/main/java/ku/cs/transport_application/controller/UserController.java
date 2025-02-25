package ku.cs.transport_application.controller;

import ku.cs.transport_application.common.UserRole;
import ku.cs.transport_application.entity.User;
import ku.cs.transport_application.service.FileService;
import ku.cs.transport_application.service.OrderService;
import ku.cs.transport_application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    FileService fileService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/users/{userId}/order")
    public ResponseEntity<?> getUserDetail(@PathVariable UUID userId) {
        return ResponseEntity.ok((orderService.getOrdersByUser(userId)));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @PutMapping("/users/update-profile/{userId}")
    public ResponseEntity<?> updateUserProfile(@PathVariable("userId") UUID userId,
                                               @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
                                               @RequestParam("name") String name,
                                               @RequestParam("email") String email,
                                               @RequestParam("phoneNumber") String phoneNumber) {
        User record = userService.findById(userId);
        record.setName(name);
        record.setEmail(email);
        record.setPhoneNumber(phoneNumber);
        userService.setUser(record);

        if (profilePicture != null) {
            try {
                fileService.uploadProfilePicture(userId, profilePicture, record.getRole() == UserRole.USER ? UserRole.ADMIN : UserRole.USER);
            } catch (IOException e) {
                return new ResponseEntity<>(Map.of("error", "Failed to upload profile picture"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return ResponseEntity.ok(record);
    }

    @DeleteMapping("/users/{userId}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
        User record = userService.findById(userId);
        if (record == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userService.deleteUser(record);
        return ResponseEntity.ok("User deleted successfully");
    }

}
