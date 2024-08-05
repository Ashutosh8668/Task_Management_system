package com.example.taskmanagement.TaskManager.Services;

import com.example.taskmanagement.TaskManager.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);


}
