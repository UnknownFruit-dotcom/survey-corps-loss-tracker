package com.fruit.scouts.service;

import com.fruit.scouts.dto.request.UserRegistrationRequest;
import com.fruit.scouts.dto.request.UserUpdateRequest;
import com.fruit.scouts.dto.response.UserResponse;
import com.fruit.scouts.exception.ResourceNotFoundException;
import com.fruit.scouts.exception.UserExistsException;
import com.fruit.scouts.mapper.UserMapper;
import com.fruit.scouts.model.User;
import com.fruit.scouts.repository.UserRepository;
import com.fruit.scouts.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public UserResponse createUser(UserRegistrationRequest request) {
        var user = userMapper.toEntity(request);

        if (userRepository.existsByUsername(request.username()))
            throw new UserExistsException("User already exists with username: " +  request.username());

        String hashedPassword = passwordEncoder.encode(request.password());
        user.setPasswordHash(hashedPassword);

        return UserResponse.from(userRepository.save(user));
    }

    public List<UserResponse> getAllUsers() { return UserResponse.from(userRepository.findAll()); }

    public UserResponse getUserById(Long id) { return UserResponse.from(userRepository.getReferenceById(id)); }

    @Transactional
    public void deleteUser(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userRepository.delete(user);
    }

    @Transactional
    public UserResponse updateUser(Long id, UserUpdateRequest request) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        userMapper.updateUserFromDto(request, user);

        return UserResponse.from(userRepository.save(user));
    }

    @Transactional
    public UserResponse registerUser(UserRegistrationRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UserExistsException("User already exists with username: " +  request.username());
        }

        User user = userMapper.toEntity(request);
        user.setCreatedAt(LocalDateTime.now());

        String hashedPassword = passwordEncoder.encode(request.password());
        user.setPasswordHash(hashedPassword);

        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Неверный логин или пароль"));

        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BadCredentialsException("Неверный логин или пароль");
        }

        return jwtService.generateToken(user);
    }
}
