package com.darshan.AnnotationPortal.service;

import com.darshan.AnnotationPortal.dto.DatasetRequest;
import com.darshan.AnnotationPortal.dto.DatasetResponse;
import com.darshan.AnnotationPortal.entity.Dataset;
import com.darshan.AnnotationPortal.repository.DatasetRepository;
import org.springframework.stereotype.Service;
import com.darshan.AnnotationPortal.exception.DuplicateResourceException;
import com.darshan.AnnotationPortal.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DatasetService {

    private final DatasetRepository datasetRepository;

    public DatasetService(DatasetRepository datasetRepository) {
        this.datasetRepository = datasetRepository;
    }

    // CREATE
    public DatasetResponse createDataset(DatasetRequest request) {

        if (datasetRepository.existsByNameIgnoreCase(request.getName())) {
            throw new DuplicateResourceException("Dataset already exists");
        }

        Dataset dataset = mapToEntity(request);

        Dataset savedDataset = datasetRepository.save(dataset);

        return mapToResponse(savedDataset);
    }

    // GET ALL
    public List<DatasetResponse> getAllDatasets() {

        return datasetRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public DatasetResponse getDatasetById(Long id) {

        Dataset dataset = datasetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dataset not found"));
        return mapToResponse(dataset);
    }

    // UPDATE
    public DatasetResponse updateDataset(Long id, DatasetRequest request) {

        Dataset dataset = datasetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dataset not found"));
        dataset.setName(request.getName());
        dataset.setDescription(request.getDescription());
        dataset.setTotalImages(request.getTotalImages());
        dataset.setPriority(request.getPriority());
        dataset.setStatus(request.getStatus());

        Dataset updatedDataset = datasetRepository.save(dataset);

        return mapToResponse(updatedDataset);
    }

    // DELETE
    public void deleteDataset(Long id) {

        Dataset dataset = datasetRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dataset not found"));
        datasetRepository.delete(dataset);
    }

    // SEARCH
    public List<DatasetResponse> searchDataset(String name) {

        return datasetRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // DTO -> ENTITY
    private Dataset mapToEntity(DatasetRequest request) {

        return Dataset.builder()
                .name(request.getName())
                .description(request.getDescription())
                .totalImages(request.getTotalImages())
                .priority(request.getPriority())
                .status(request.getStatus())
                .build();
    }

    // ENTITY -> DTO
    private DatasetResponse mapToResponse(Dataset dataset) {

        return DatasetResponse.builder()
                .id(dataset.getId())
                .name(dataset.getName())
                .description(dataset.getDescription())
                .totalImages(dataset.getTotalImages())
                .priority(dataset.getPriority())
                .status(dataset.getStatus())
                .build();
    }
}