package com.vgtstptlk.gramrestapi.controllers;

import com.vgtstptlk.gramrestapi.domains.Auth;
import com.vgtstptlk.gramrestapi.domains.User;
import com.vgtstptlk.gramrestapi.exception.UserExistException;
import com.vgtstptlk.gramrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class UserController {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    @PostMapping("auth/register")
    ResponseEntity<?> createUser(@RequestParam String login, @RequestParam String password){
        User tempUser = new User(login, bCryptPasswordEncoder.encode(password));
        if (userRepository.findByLogin(login).isPresent()){
            throw new UserExistException(login);
        }
        userRepository.save(tempUser);
        return ResponseEntity.ok(tempUser);
    }

    @GetMapping("auth/login")
    Auth emulateAuth(@RequestParam String login, @RequestParam String password){
        Optional<User> optionalUser = userRepository.findByLogin(login);
        if (optionalUser.isEmpty()){
            return new Auth(login, false);
        }
        User user = optionalUser.get();
        return bCryptPasswordEncoder.matches(password, user.getPassword()) ? new Auth(login, true) : new Auth(login, false);
    }

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
