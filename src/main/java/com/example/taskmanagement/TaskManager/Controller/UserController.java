package com.example.taskmanagement.TaskManager.Controller;

import com.example.taskmanagement.TaskManager.Entity.User;
import com.example.taskmanagement.TaskManager.Repository.UserRepository;
import com.example.taskmanagement.TaskManager.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

//    @GetMapping("/edit/{id}")
//    public String editUser(@PathVariable Long id, Model model) {
//        User user = userService.getUserById(id);
//        model.addAttribute("user", user);
//        return "editUser";
//    }
//
//    @PostMapping("/{id}")
//    public String updateUser(@PathVariable Long id, @ModelAttribute User userDetails) {
//        User user = userService.updateUser(id, userDetails);
//        return "redirect:/users";
//    }

    @Autowired
    private UserRepository userRepository;
@GetMapping("/edit/{id}")
public String editUserForm(@PathVariable Long id, Model model) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    model.addAttribute("user", user);
    return "editUser";
}

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
