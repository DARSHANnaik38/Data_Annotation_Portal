package com.darshan.AnnotationPortal.service;

import com.darshan.AnnotationPortal.dto.UserRequest;
import com.darshan.AnnotationPortal.dto.UserResponse;
import com.darshan.AnnotationPortal.entity.Role;
import com.darshan.AnnotationPortal.entity.User;
import com.darshan.AnnotationPortal.exception.DuplicateResourceException;
import com.darshan.AnnotationPortal.exception.ResourceNotFoundException;
import com.darshan.AnnotationPortal.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // CREATE ANNOTATOR
    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        User user = mapToEntity(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    // GET ALL ANNOTATORS
    public List<UserResponse> getAllAnnotators() {

        return userRepository.findByRole(Role.ANNOTATOR)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET USER BY ID
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return mapToResponse(user);
    }

    // ENABLE USER
    public UserResponse enableUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setEnabled(true);

        return mapToResponse(userRepository.save(user));
    }

    // DISABLE USER
    public UserResponse disableUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setEnabled(false);

        return mapToResponse(userRepository.save(user));
    }

    // SEARCH USER
    public List<UserResponse> searchUser(String keyword) {

        List<User> users = userRepository.findByNameContainingIgnoreCase(keyword);

        if (users.isEmpty()) {
            users = userRepository.findByEmailContainingIgnoreCase(keyword);
        }

        return users.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // DTO -> ENTITY
    private User mapToEntity(UserRequest request) {

        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .enabled(true)
                .build();
    }

    public UserResponse updateUser(Long id, UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        user.setRole(request.getRole());

        return mapToResponse(userRepository.save(user));
    }

    // ENTITY -> DTO
    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .build();
    }
}