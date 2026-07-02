package com.darshan.AnnotationPortal.service;

//import com.darshan.AnnotationPortal.exception.DuplicateResourceException;
import com.darshan.AnnotationPortal.dto.AssignmentRequest;
import com.darshan.AnnotationPortal.dto.AssignmentResponse;
import com.darshan.AnnotationPortal.entity.*;
import com.darshan.AnnotationPortal.exception.DuplicateResourceException;
import com.darshan.AnnotationPortal.exception.ResourceNotFoundException;
import com.darshan.AnnotationPortal.repository.AssignmentRepository;
import com.darshan.AnnotationPortal.repository.DatasetRepository;
import com.darshan.AnnotationPortal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final DatasetRepository datasetRepository;
    private final UserRepository userRepository;

    public AssignmentService(AssignmentRepository assignmentRepository,
                             DatasetRepository datasetRepository,
                             UserRepository userRepository) {
        this.assignmentRepository = assignmentRepository;
        this.datasetRepository = datasetRepository;
        this.userRepository = userRepository;
    }




    // CREATE ASSIGNMENT
    public AssignmentResponse assignDataset(AssignmentRequest request) {


        if (assignmentRepository.existsByDatasetIdAndAnnotatorId(
                request.getDatasetId(),
                request.getAnnotatorId())) {

            throw new DuplicateResourceException(
                    "This dataset is already assigned to this annotator");
        }
        Dataset dataset = datasetRepository.findById(request.getDatasetId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dataset not found"));

        User annotator = userRepository.findById(request.getAnnotatorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Annotator not found"));

        if (annotator.getRole() != Role.ANNOTATOR) {
            throw new IllegalArgumentException("Selected user is not an annotator");
        }



        Assignment assignment = Assignment.builder()
                .dataset(dataset)
                .annotator(annotator)
                .assignedDate(LocalDate.now())
                .dueDate(request.getDueDate())
                .remarks(request.getRemarks())
                .status(AssignmentStatus.ASSIGNED)
                .build();

        Assignment savedAssignment = assignmentRepository.save(assignment);

        return mapToResponse(savedAssignment);
    }

    // GET ALL ASSIGNMENTS
    public List<AssignmentResponse> getAllAssignments() {

        return assignmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET ASSIGNMENT BY ID
    public AssignmentResponse getAssignmentById(Long id) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found"));

        return mapToResponse(assignment);
    }

    // GET ASSIGNMENTS OF AN ANNOTATOR
    public List<AssignmentResponse> getAssignmentsByAnnotator(Long annotatorId) {

        return assignmentRepository.findByAnnotatorId(annotatorId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET ASSIGNMENTS OF A DATASET
    public List<AssignmentResponse> getAssignmentsByDataset(Long datasetId) {

        return assignmentRepository.findByDatasetId(datasetId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE STATUS
    public AssignmentResponse updateStatus(Long id,
                                           AssignmentStatus status) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found"));

        assignment.setStatus(status);

        return mapToResponse(
                assignmentRepository.save(assignment)
        );
    }

    // DELETE ASSIGNMENT
    public void deleteAssignment(Long id) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found"));

        assignmentRepository.delete(assignment);
    }

    // ENTITY -> DTO
    private AssignmentResponse mapToResponse(Assignment assignment) {

        return AssignmentResponse.builder()
                .id(assignment.getId())
                .datasetId(assignment.getDataset().getId())
                .datasetName(assignment.getDataset().getName())
                .annotatorId(assignment.getAnnotator().getId())
                .annotatorName(assignment.getAnnotator().getName())
                .assignedDate(assignment.getAssignedDate())
                .dueDate(assignment.getDueDate())
                .status(assignment.getStatus())
                .remarks(assignment.getRemarks())
                .build();
    }
}