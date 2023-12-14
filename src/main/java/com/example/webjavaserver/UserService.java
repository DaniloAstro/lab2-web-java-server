package com.example.webjavaserver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public List<User> getAllUsers(Integer age, String country, String group) {
        return filterUsersByCriteria(users, age, country, group);
    }

    public User getUserById(int id) {
        return findUserById(id);
    }

    public User updateUser(int id, User request) {
        User existingUser = findUserById(id);
        if (existingUser != null) {
            existingUser.setName(request.getName());
            existingUser.setEmail(request.getEmail());
            existingUser.setFriends(request.getFriends());
            existingUser.setAge(request.getAge());
            existingUser.setCountry(request.getCountry());
            existingUser.setGroups(request.getGroups());
            existingUser.setAddress(request.getAddress());
            existingUser.setDateOfBirth(request.getDateOfBirth());
        }
        return existingUser;
    }

    public void deleteUser(int id) {
        User user = findUserById(id);
        if (user != null) {
            users.remove(user);
        }
    }

    public User createUser(User request) {
        if (findUserById(request.getId()) != null) {
            return null; // or throw an exception, handle the duplicate user case accordingly
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
        return newUser;
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