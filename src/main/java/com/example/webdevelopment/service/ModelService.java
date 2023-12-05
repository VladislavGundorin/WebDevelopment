package com.example.webdevelopment.service;

import com.example.webdevelopment.dto.ModelDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModelService {
    ModelDTO createModel(ModelDTO modelDTO);

    List<ModelDTO> getAllModels();

    ModelDTO getModelById(UUID id);

    ModelDTO updateModelById(UUID id, ModelDTO modelDTO);

    void deleteModelById(UUID id);

    List<String> getModelsByBrandAndStartYear(String brandName,int yearstart);

    List<ModelDTO> getModelsByBrandName(String brandName);

    List<ModelDTO> getModelsByBrandAndName(String brandName, String modelName);
}
