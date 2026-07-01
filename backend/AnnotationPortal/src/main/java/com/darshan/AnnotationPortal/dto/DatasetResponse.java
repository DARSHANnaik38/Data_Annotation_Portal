package com.darshan.AnnotationPortal.dto;

import com.darshan.AnnotationPortal.entity.Priority;
import com.darshan.AnnotationPortal.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class DatasetResponse {

    private Long id;

    private String name;

    private String description;

    private Integer totalImages;

    private Priority priority;

    private Status status;
}