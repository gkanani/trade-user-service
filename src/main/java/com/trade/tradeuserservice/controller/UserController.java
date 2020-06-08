package com.trade.tradeuserservice.controller;

import com.trade.tradeuserservice.config.PasswordUtil;
import com.trade.tradeuserservice.model.User;
import com.trade.tradeuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/trade/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    public List<User> getAllUserList() {
        return userService.getAllUsers();
    }

    @PostMapping("/save")
    public User saveUser(@Valid @RequestBody User user) {
        String generatedPass = user.getPassword();
        String salt = PasswordUtil.generateSalt();
        String secretKey = PasswordUtil.generateSecretKey();
        String encryptedPassword = PasswordUtil.encrypt(generatedPass, secretKey, salt);
        user.setPassword(encryptedPassword);
        return userService.saveUser(user);
    }

    @GetMapping("/getUserById/{id}")
    public Optional<User> getuser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public User updateuser(@PathVariable Long id, @Valid @RequestBody User user1) {
        String generatedPass = user1.getPassword();
        String salt = PasswordUtil.generateSalt();
        String secretKey = PasswordUtil.generateSecretKey();
        String encryptedPassword = PasswordUtil.encrypt(generatedPass, secretKey, salt);
        System.out.println("encripted pass is " + encryptedPassword);
        user1.setPassword(encryptedPassword);
        return userService.updateUser(id, user1);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "user " + id + " deleted";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}