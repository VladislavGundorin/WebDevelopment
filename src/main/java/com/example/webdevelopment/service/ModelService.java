package com.example.webdevelopment.service;

import com.example.webdevelopment.dto.ModelDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelService {
    ModelDTO createModel(ModelDTO modelDTO);

    List<ModelDTO> getAllModels();

    Optional<ModelDTO> getModelById(UUID id);

    ModelDTO updateModelById(UUID id, ModelDTO modelDTO);

    void deleteModelById(UUID id);
}
