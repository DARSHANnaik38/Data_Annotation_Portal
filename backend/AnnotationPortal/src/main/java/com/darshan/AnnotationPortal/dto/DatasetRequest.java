package com.darshan.AnnotationPortal.dto;

import com.darshan.AnnotationPortal.entity.Priority;
import com.darshan.AnnotationPortal.entity.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatasetRequest {

    @NotBlank(message = "Dataset name is required")
    private String name;

    private String description;

    @NotNull(message = "Total images is required")
    @Positive(message = "Total images must be greater than zero")
    private Integer totalImages;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Status is required")
    private Status status;
}