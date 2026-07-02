package com.darshan.AnnotationPortal.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssignmentRequest {

    @NotNull(message = "Dataset id is required")
    private Long datasetId;

    @NotNull(message = "Annotator id is required")
    private Long annotatorId;

    @NotNull(message = "Due date is required")
    @Future(message = "Due date must be in the future")
    private LocalDate dueDate;

    private String remarks;

}