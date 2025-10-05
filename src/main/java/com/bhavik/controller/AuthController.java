package com.bhavik.controller;

import com.bhavik.config.JwtProvider;
import com.bhavik.models.User;
import com.bhavik.repository.UserRepository;
import com.bhavik.response.AuthResponse;
import com.bhavik.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userservice;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public AuthResponse createuser(@RequestBody User user) throws Exception {
        User isExist = userRepository.findByEmail(user.getEmail());

        if(isExist!=null) {
            throw new Exception("this email already used with another account ");
        }

        User newuser = new User();
        newuser.setEmail(user.getEmail());
        newuser.setFirstName(user.getFirstName());
        newuser.setLastName(user.getLastName());
        newuser.setPassword(passwordEncoder.encode(user.getPassword()));

        User saveduser = userRepository.save(newuser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveduser.getEmail(),saveduser.getPassword());

        String token = JwtProvider.generateToken(authentication);

        AuthResponse res = new AuthResponse(token,"Register Success");
        return res;
    }



}
