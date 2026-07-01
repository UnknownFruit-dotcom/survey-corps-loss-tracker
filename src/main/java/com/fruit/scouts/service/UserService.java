package com.fruit.scouts.service;

import com.fruit.scouts.dto.request.UserCreationRequest;
import com.fruit.scouts.dto.request.UserUpdateRequest;
import com.fruit.scouts.dto.response.UserResponse;
import com.fruit.scouts.exception.ResourceNotFoundException;
import com.fruit.scouts.exception.UserExistsException;
import com.fruit.scouts.mapper.UserMapper;
import com.fruit.scouts.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(UserCreationRequest request) {
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
}
