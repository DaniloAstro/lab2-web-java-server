package com.example.webjavaserver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<User> users = new ArrayList<>();

    // GET /users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) Integer age,
                                                  @RequestParam(required = false) String country,
                                                  @RequestParam(required = false) String group) {
        List<User> filteredUsers = filterUsersByCriteria(users, age, country, group);
        return ResponseEntity.ok(filteredUsers);
    }

    // GET /users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    // PUT /users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User request) {
        User existingUser = findUserById(id);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        // Оновити всі поля користувача
        existingUser.setName(request.getName());
        existingUser.setEmail(request.getEmail());
        existingUser.setFriends(request.getFriends());
        existingUser.setAge(request.getAge());
        existingUser.setCountry(request.getCountry());
        existingUser.setGroups(request.getGroups());
        existingUser.setAddress(request.getAddress());
        existingUser.setDateOfBirth(request.getDateOfBirth());

        return ResponseEntity.ok(existingUser);
    }

    // DELETE /users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        User user = findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        users.remove(user);
        return ResponseEntity.noContent().build();
    }

    // POST /users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User request) {
        if (findUserById(request.getId()) != null) {
            return ResponseEntity.badRequest().build();
        }

        User newUser = new User(
                request.getId(),
                request.getName(),
                request.getEmail(),
                request.getFriends(),
                request.getAge(),
                request.getCountry(),
                request.getGroups(),
                request.getAddress(),
                request.getDateOfBirth()
        );

        users.add(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    // Additional method for filtering users by criteria
    private List<User> filterUsersByCriteria(List<User> users, Integer age, String country, String group) {
        return users.stream()
                .filter(user -> (age == null || age.equals(user.getAge())))
                .filter(user -> (country == null || country.equals(user.getCountry())))
                .filter(user -> (group == null || user.getGroups().contains(group)))
                .collect(Collectors.toList());
    }

    // Additional method to find user by ID
    private User findUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }
}