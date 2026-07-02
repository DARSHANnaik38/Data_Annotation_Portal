package com.darshan.AnnotationPortal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {

    private long totalDatasets;

    private long totalAnnotators;

    private long totalAssignments;

    private long completedAssignments;

}