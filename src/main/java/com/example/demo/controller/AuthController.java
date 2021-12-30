package com.example.demo.controller;

import com.example.demo.dto.SigninRequest;
import com.example.demo.dto.SigninResponse;
import com.example.demo.dto.SignupRequest;
import com.example.demo.security.JwtUtils;
import com.example.demo.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService userDetailService;



    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(path = "/signin")
    public ResponseEntity<?> signIn(@RequestBody SigninRequest authRequest) throws Exception{

        try{
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(),
                authRequest.getPassword()
        ));
        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new SigninResponse(jwt));
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) throws Exception{
        try {
            userDetailService.save(signupRequest);
        }catch (Exception exception){
            throw new Exception(exception);
        }

        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
