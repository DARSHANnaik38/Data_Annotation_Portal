package com.darshan.AnnotationPortal.repository;

import com.darshan.AnnotationPortal.entity.Assignment;
import com.darshan.AnnotationPortal.entity.AssignmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    List<Assignment> findByAnnotatorId(Long annotatorId);

    List<Assignment> findByDatasetId(Long datasetId);

    List<Assignment> findByStatus(AssignmentStatus status);

    boolean existsByDatasetIdAndAnnotatorId(Long datasetId, Long annotatorId);

    long countByStatus(AssignmentStatus status);

}