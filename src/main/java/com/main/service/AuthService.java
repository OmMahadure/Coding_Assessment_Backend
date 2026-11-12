
package com.main.service;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.main.dto.LoginRequest;
import com.main.dto.LoginResponse;
import com.main.dto.RegisterRequest;
import com.main.dto.RegisterResponse;
import com.main.entity.User;
import com.main.repository.UserRepo;

@Service
public class AuthService {

    private  UserRepo userRepository;
    private  PasswordEncoder passwordEncoder;
private JwtService jwtService;
    public AuthService(
            UserRepo userRepository,
            PasswordEncoder passwordEncoder,JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService=jwtService;
    }

    public RegisterResponse register(RegisterRequest request) {

        String email = request.email().trim().toLowerCase(Locale.ROOT);

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Email is already registered"
            );
        }

        User user = new User();

        user.setName(request.name().trim());
        user.setEmail(email);
        user.setPassword(
            passwordEncoder.encode(request.password())
        );

        User savedUser = userRepository.save(user);

        return new RegisterResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getRole().name(),
            savedUser.getCreatedAt()
        );
    }
    
    public LoginResponse login(LoginRequest request) {
    	
    	String email=request.email().trim().toLowerCase(Locale.ROOT);
    	
    	  User user = userRepository.findByEmail(email)
    		        .orElseThrow(() -> new ResponseStatusException(
    		            HttpStatus.UNAUTHORIZED,
    		            "Invalid email"
    		        ));

    		    boolean passwordMatches = passwordEncoder.matches(
    		        request.password(),
    		        user.getPassword()
    		    );

    		    if (!passwordMatches) {
    		        throw new ResponseStatusException(
    		            HttpStatus.UNAUTHORIZED,
    		            "Invalid password"
    		        );
    		    }
    		    	
    		    String token= jwtService.generateToken(user.getEmail());
    		    
    		    return new LoginResponse(
    		        user.getId(),
    		        user.getName(),
    		        user.getEmail(),
    		        user.getRole().name(),
    		        "Login successful",
    		        token,
    		        "Bearer"
    		    );
    }
}
