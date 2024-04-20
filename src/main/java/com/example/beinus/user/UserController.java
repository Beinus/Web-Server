package com.example.beinus.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5174")
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/get-all")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId) {
        return service.getUser(userId);
    }

    @PostMapping("/save")
    public User saveUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User user) {
        return service.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId) {
        service.deleteUser(service.getUser(userId));
    }
}
