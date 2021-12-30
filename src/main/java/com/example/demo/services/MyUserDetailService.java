package com.example.demo.services;

import com.example.demo.dto.SignupRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new UsernameNotFoundException("User not found");
        });


        return UserPrincipal.create(user);

//        return new User("foo", "foo", new ArrayList<>());
    }

    public void save(SignupRequest signupRequest){
        userRepository.save(
                new User(
                        signupRequest.getUsername(),
                        signupRequest.getPassword(),
                        signupRequest.getName(),
                        signupRequest.getEmail(),
                        signupRequest.getRole()
                )
        );
    }
}
