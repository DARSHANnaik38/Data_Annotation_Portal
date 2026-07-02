package com.darshan.AnnotationPortal.service;

import com.darshan.AnnotationPortal.dto.DashboardResponse;
import com.darshan.AnnotationPortal.entity.AssignmentStatus;
import com.darshan.AnnotationPortal.entity.Role;
import com.darshan.AnnotationPortal.repository.AssignmentRepository;
import com.darshan.AnnotationPortal.repository.DatasetRepository;
import com.darshan.AnnotationPortal.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DatasetRepository datasetRepository;
    private final UserRepository userRepository;
    private final AssignmentRepository assignmentRepository;

    public DashboardService(DatasetRepository datasetRepository,
                            UserRepository userRepository,
                            AssignmentRepository assignmentRepository) {

        this.datasetRepository = datasetRepository;
        this.userRepository = userRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public DashboardResponse getDashboard() {

        return DashboardResponse.builder()
                .totalDatasets(datasetRepository.count())
                .totalAnnotators(userRepository.findByRole(Role.ANNOTATOR).size())
                .totalAssignments(assignmentRepository.count())
                .completedAssignments(
                        assignmentRepository.countByStatus(AssignmentStatus.COMPLETED)
                )
                .build();
    }
}