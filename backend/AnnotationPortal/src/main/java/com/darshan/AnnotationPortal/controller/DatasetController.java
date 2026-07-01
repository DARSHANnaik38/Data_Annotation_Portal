package com.darshan.AnnotationPortal.controller;

import com.darshan.AnnotationPortal.dto.ApiResponse;
import com.darshan.AnnotationPortal.dto.DatasetRequest;
import com.darshan.AnnotationPortal.dto.DatasetResponse;
import com.darshan.AnnotationPortal.service.DatasetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/datasets")
public class DatasetController {

    private final DatasetService datasetService;

    public DatasetController(DatasetService datasetService) {
        this.datasetService = datasetService;
    }

    // CREATE DATASET
    @PostMapping
    public ResponseEntity<ApiResponse<DatasetResponse>> createDataset(
            @Valid @RequestBody DatasetRequest request) {

        DatasetResponse response = datasetService.createDataset(request);

        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Dataset created successfully",
                        response
                ),
                HttpStatus.CREATED
        );
    }

    // GET ALL DATASETS
    @GetMapping
    public ResponseEntity<ApiResponse<List<DatasetResponse>>> getAllDatasets() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Datasets fetched successfully",
                        datasetService.getAllDatasets()
                )
        );
    }

    // GET DATASET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DatasetResponse>> getDatasetById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dataset fetched successfully",
                        datasetService.getDatasetById(id)
                )
        );
    }

    // UPDATE DATASET
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DatasetResponse>> updateDataset(
            @PathVariable Long id,
            @Valid @RequestBody DatasetRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dataset updated successfully",
                        datasetService.updateDataset(id, request)
                )
        );
    }

    // DELETE DATASET
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDataset(
            @PathVariable Long id) {

        datasetService.deleteDataset(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Dataset deleted successfully",
                        null
                )
        );
    }

    // SEARCH DATASET
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DatasetResponse>>> searchDataset(
            @RequestParam String name) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search completed successfully",
                        datasetService.searchDataset(name)
                )
        );
    }
}