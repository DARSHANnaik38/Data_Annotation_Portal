package com.darshan.AnnotationPortal.dto;

import com.darshan.AnnotationPortal.entity.AssignmentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AssignmentResponse {

    private Long id;

    private Long datasetId;

    private String datasetName;

    private Long annotatorId;

    private String annotatorName;

    private LocalDate assignedDate;

    private LocalDate dueDate;

    private AssignmentStatus status;

    private String remarks;

}