package com.darshan.AnnotationPortal.controller;

import com.darshan.AnnotationPortal.dto.AnnotationRequest;
import com.darshan.AnnotationPortal.dto.AnnotationResponse;
import com.darshan.AnnotationPortal.dto.ApiResponse;
import com.darshan.AnnotationPortal.entity.AnnotationStatus;
import com.darshan.AnnotationPortal.service.AnnotationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annotations")
public class AnnotationController {

    private final AnnotationService annotationService;

    public AnnotationController(AnnotationService annotationService) {
        this.annotationService = annotationService;
    }

    // CREATE ANNOTATION
    @PostMapping
    public ResponseEntity<ApiResponse<AnnotationResponse>> createAnnotation(
            @Valid @RequestBody AnnotationRequest request) {

        AnnotationResponse response =
                annotationService.createAnnotation(request);

        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Annotation created successfully",
                        response
                ),
                HttpStatus.CREATED
        );
    }

    // GET ALL ANNOTATIONS
    @GetMapping
    public ResponseEntity<ApiResponse<List<AnnotationResponse>>> getAllAnnotations() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotations fetched successfully",
                        annotationService.getAllAnnotations()
                )
        );
    }

    // GET ANNOTATION BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnnotationResponse>> getAnnotationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotation fetched successfully",
                        annotationService.getAnnotationById(id)
                )
        );
    }

    // GET BY ASSIGNMENT
    @GetMapping("/assignment/{assignmentId}")
    public ResponseEntity<ApiResponse<List<AnnotationResponse>>> getByAssignment(
            @PathVariable Long assignmentId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Assignment annotations fetched successfully",
                        annotationService.getAnnotationsByAssignment(assignmentId)
                )
        );
    }

    // UPDATE ANNOTATION
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AnnotationResponse>> updateAnnotation(
            @PathVariable Long id,
            @Valid @RequestBody AnnotationRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotation updated successfully",
                        annotationService.updateAnnotation(id, request)
                )
        );
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AnnotationResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam AnnotationStatus status) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotation status updated successfully",
                        annotationService.updateStatus(id, status)
                )
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAnnotation(
            @PathVariable Long id) {

        annotationService.deleteAnnotation(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotation deleted successfully",
                        null
                )
        );
    }
}