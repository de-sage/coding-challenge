package com.dev.codingchallenge.userportal.controller;

import com.dev.codingchallenge.userportal.entity.ApplicationUser;
import com.dev.codingchallenge.userportal.exception.UserPortalException;
import com.dev.codingchallenge.userportal.payload.request.LoginRequest;
import com.dev.codingchallenge.userportal.payload.request.SignupRequest;
import com.dev.codingchallenge.userportal.payload.response.JwtResponse;
import com.dev.codingchallenge.userportal.repository.UserRepository;
import com.dev.codingchallenge.userportal.security.jwt.JwtUtils;
import com.dev.codingchallenge.userportal.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws UserPortalException {
        Optional<ApplicationUser> userOptional = userRepository.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            throw new UserPortalException("user with email "+ loginRequest.getEmail() +" not found" );
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ApplicationUser user = userRepository.getById(userDetails.getId());


        return ResponseEntity.ok(new JwtResponse(jwt, user));
    }
}
