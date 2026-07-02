package com.darshan.AnnotationPortal.service;

import com.darshan.AnnotationPortal.dto.AnnotationRequest;
import com.darshan.AnnotationPortal.dto.AnnotationResponse;
import com.darshan.AnnotationPortal.entity.Annotation;
import com.darshan.AnnotationPortal.entity.AnnotationStatus;
import com.darshan.AnnotationPortal.entity.Assignment;
import com.darshan.AnnotationPortal.exception.DuplicateResourceException;
import com.darshan.AnnotationPortal.exception.ResourceNotFoundException;
import com.darshan.AnnotationPortal.repository.AnnotationRepository;
import com.darshan.AnnotationPortal.repository.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnotationService {

    private final AnnotationRepository annotationRepository;
    private final AssignmentRepository assignmentRepository;

    public AnnotationService(AnnotationRepository annotationRepository,
                             AssignmentRepository assignmentRepository) {
        this.annotationRepository = annotationRepository;
        this.assignmentRepository = assignmentRepository;
    }

    // CREATE ANNOTATION
    public AnnotationResponse createAnnotation(AnnotationRequest request) {

        Assignment assignment = assignmentRepository.findById(request.getAssignmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Assignment not found"));

        if (annotationRepository.existsByAssignmentIdAndImageName(
                request.getAssignmentId(),
                request.getImageName())) {

            throw new DuplicateResourceException(
                    "Annotation already exists for this image");
        }

        Annotation annotation = Annotation.builder()
                .assignment(assignment)
                .imageName(request.getImageName())
                .label(request.getLabel())
                .confidence(request.getConfidence())
                .comments(request.getComments())
                .status(AnnotationStatus.DRAFT)
                .annotatedAt(LocalDateTime.now())
                .build();

        return mapToResponse(
                annotationRepository.save(annotation)
        );
    }

    // GET ALL
    public List<AnnotationResponse> getAllAnnotations() {

        return annotationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET BY ID
    public AnnotationResponse getAnnotationById(Long id) {

        Annotation annotation = annotationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Annotation not found"));

        return mapToResponse(annotation);
    }

    // GET BY ASSIGNMENT
    public List<AnnotationResponse> getAnnotationsByAssignment(Long assignmentId) {

        return annotationRepository.findByAssignmentId(assignmentId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // UPDATE
    public AnnotationResponse updateAnnotation(Long id,
                                               AnnotationRequest request) {

        Annotation annotation = annotationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Annotation not found"));

        annotation.setImageName(request.getImageName());
        annotation.setLabel(request.getLabel());
        annotation.setConfidence(request.getConfidence());
        annotation.setComments(request.getComments());

        return mapToResponse(
                annotationRepository.save(annotation)
        );
    }

    // UPDATE STATUS
    public AnnotationResponse updateStatus(Long id,
                                           AnnotationStatus status) {

        Annotation annotation = annotationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Annotation not found"));

        annotation.setStatus(status);

        return mapToResponse(
                annotationRepository.save(annotation)
        );
    }

    // DELETE
    public void deleteAnnotation(Long id) {

        Annotation annotation = annotationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Annotation not found"));

        annotationRepository.delete(annotation);
    }

    // ENTITY -> DTO
    private AnnotationResponse mapToResponse(Annotation annotation) {

        return AnnotationResponse.builder()
                .id(annotation.getId())
                .assignmentId(annotation.getAssignment().getId())
                .imageName(annotation.getImageName())
                .label(annotation.getLabel())
                .confidence(annotation.getConfidence())
                .comments(annotation.getComments())
                .status(annotation.getStatus())
                .annotatedAt(annotation.getAnnotatedAt())
                .build();
    }
}