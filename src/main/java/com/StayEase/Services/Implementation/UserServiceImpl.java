package com.StayEase.Services.Implementation;

import com.StayEase.DTO.LoginRequest;
import com.StayEase.Models.User;
import com.StayEase.Repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String registerUser(@Valid User user) {
        String password = user.getPassword();
        if (password.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters long");
        }
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String loginUser(@Valid LoginRequest loginRequest) {
        User existingUser = userRepository.findByEmail(loginRequest.getEmail());
        if (existingUser == null) {
            throw new ValidationException("User not found");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword())) {
            throw new ValidationException("Invalid password");
        }
        return jwtService.generateToken(existingUser.getUsername());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public String updateUser(Long id, @Valid User user) {
        Optional<User> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isEmpty()) {
            throw new ValidationException("User not found");
        }
        User existingUser = existingUserOpt.get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        if (user.getPassword() != null && user.getPassword().length() >= 8) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(existingUser);
        return "User updated successfully";
    }

    public String deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ValidationException("User not found");
        }
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}
