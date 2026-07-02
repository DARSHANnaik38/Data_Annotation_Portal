package com.darshan.AnnotationPortal.repository;

import com.darshan.AnnotationPortal.entity.Annotation;
import com.darshan.AnnotationPortal.entity.AnnotationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnotationRepository extends JpaRepository<Annotation, Long> {

    List<Annotation> findByAssignmentId(Long assignmentId);

    List<Annotation> findByStatus(AnnotationStatus status);

    boolean existsByAssignmentIdAndImageName(Long assignmentId,
                                             String imageName);
}