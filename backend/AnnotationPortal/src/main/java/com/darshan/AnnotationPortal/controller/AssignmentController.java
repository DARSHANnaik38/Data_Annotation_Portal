package com.darshan.AnnotationPortal.controller;

import com.darshan.AnnotationPortal.dto.ApiResponse;
import com.darshan.AnnotationPortal.dto.AssignmentRequest;
import com.darshan.AnnotationPortal.dto.AssignmentResponse;
import com.darshan.AnnotationPortal.entity.AssignmentStatus;
import com.darshan.AnnotationPortal.service.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    // CREATE ASSIGNMENT
    @PostMapping
    public ResponseEntity<ApiResponse<AssignmentResponse>> assignDataset(
            @Valid @RequestBody AssignmentRequest request) {

        AssignmentResponse response = assignmentService.assignDataset(request);

        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Dataset assigned successfully",
                        response
                ),
                HttpStatus.CREATED
        );
    }

    // GET ALL ASSIGNMENTS
    @GetMapping
    public ResponseEntity<ApiResponse<List<AssignmentResponse>>> getAllAssignments() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Assignments fetched successfully",
                        assignmentService.getAllAssignments()
                )
        );
    }

    // GET ASSIGNMENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssignmentResponse>> getAssignmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Assignment fetched successfully",
                        assignmentService.getAssignmentById(id)
                )
        );
    }

    // GET ASSIGNMENTS OF AN ANNOTATOR
    @GetMapping("/annotator/{annotatorId}")
    public ResponseEntity<ApiResponse<List<AssignmentResponse>>> getAssignmentsByAnnotator(
            @PathVariable Long annotatorId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Annotator assignments fetched successfully",
                        assignmentService.getAssignmentsByAnnotator(annotatorId)
                )
        );
    }

    // GET ASSIGNMENTS OF A DATASET
    @GetMapping("/dataset/{datasetId}")
    public ResponseEntity<ApiResponse<List<AssignmentResponse>>> getAssignmentsByDataset(
            @PathVariable Long datasetId) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dataset assignments fetched successfully",
                        assignmentService.getAssignmentsByDataset(datasetId)
                )
        );
    }

    // UPDATE STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<AssignmentResponse>> updateStatus(
            @PathVariable Long id,
            @RequestParam AssignmentStatus status) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Assignment status updated successfully",
                        assignmentService.updateStatus(id, status)
                )
        );
    }

    // DELETE ASSIGNMENT
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAssignment(
            @PathVariable Long id) {

        assignmentService.deleteAssignment(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Assignment deleted successfully",
                        null
                )
        );
    }
}