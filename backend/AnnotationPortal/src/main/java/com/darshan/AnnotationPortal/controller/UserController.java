package com.darshan.AnnotationPortal.controller;

import com.darshan.AnnotationPortal.dto.ApiResponse;
import com.darshan.AnnotationPortal.dto.UserRequest;
import com.darshan.AnnotationPortal.dto.UserResponse;
import com.darshan.AnnotationPortal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // CREATE ANNOTATOR
    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(
            @Valid @RequestBody UserRequest request) {

        UserResponse response = userService.createUser(request);

        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Annotator created successfully",
                        response
                ),
                HttpStatus.CREATED
        );
    }

    // GET ALL ANNOTATORS
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllAnnotators() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotators fetched successfully",
                        userService.getAllAnnotators()
                )
        );
    }

    // GET ANNOTATOR BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotator fetched successfully",
                        userService.getUserById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotator updated successfully",
                        userService.updateUser(id, request)
                )
        );
    }

    // ENABLE ANNOTATOR
    @PutMapping("/{id}/enable")
    public ResponseEntity<ApiResponse<UserResponse>> enableUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotator enabled successfully",
                        userService.enableUser(id)
                )
        );
    }

    // DISABLE ANNOTATOR
    @PutMapping("/{id}/disable")
    public ResponseEntity<ApiResponse<UserResponse>> disableUser(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotator disabled successfully",
                        userService.disableUser(id)
                )
        );
    }

    // SEARCH ANNOTATOR
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserResponse>>> searchUser(
            @RequestParam String name) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search completed successfully",
                        userService.searchUser(name)
                )
        );
    }
}