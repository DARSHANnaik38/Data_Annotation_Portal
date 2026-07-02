package com.darshan.AnnotationPortal.dto;

import com.darshan.AnnotationPortal.entity.AnnotationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnotationResponse {

    private Long id;

    private Long assignmentId;

    private String imageName;

    private String label;

    private Double confidence;

    private String comments;

    private AnnotationStatus status;

    private LocalDateTime annotatedAt;
}