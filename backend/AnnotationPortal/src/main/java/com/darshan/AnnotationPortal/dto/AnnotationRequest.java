package com.darshan.AnnotationPortal.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnnotationRequest {

    @NotNull(message = "Assignment Id is required")
    private Long assignmentId;

    @NotBlank(message = "Image name is required")
    private String imageName;

    @NotBlank(message = "Label is required")
    private String label;

    @DecimalMin(value = "0.0", message = "Confidence must be at least 0")
    @DecimalMax(value = "100.0", message = "Confidence cannot exceed 100")
    private Double confidence;

    private String comments;
}