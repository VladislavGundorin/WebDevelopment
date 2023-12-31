package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.ModelDTO;
import com.example.webdevelopment.model.Model;
import com.example.webdevelopment.repositorie.ModelRepository;
import com.example.webdevelopment.service.ModelService;
import com.example.webdevelopment.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching

public class ModelServiceImpl implements ModelService {
    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public ModelServiceImpl(ModelMapper modelMepper, ModelRepository modelRepository, ValidationUtil validationUtil) {
        this.modelMapper = modelMepper;
        this.modelRepository = modelRepository;
        this.validationUtil = validationUtil;
    }

    @Override

    public ModelDTO createModel(ModelDTO modelDTO) {
        Set<ConstraintViolation<ModelDTO>> violations = validationUtil.violations(modelDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Model model = modelMapper.map(modelDTO, Model.class);
        Model saveModel = modelRepository.save(model);
        return modelMapper.map(saveModel, ModelDTO.class);
    }

    @Override
    @Cacheable(value = "modelCache", key = "'allModels'")
    public List<ModelDTO> getAllModels() {
        List<Model> models = modelRepository.findAll();
        return models.stream().map(model -> modelMapper.map(model, ModelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ModelDTO getModelById(UUID id) {
        Optional<Model> modelOptional = modelRepository.findById(id);
        if (modelOptional.isPresent()) {
            return modelMapper.map(modelOptional.get(), ModelDTO.class);
        }
        return null;
    }

    @Override
    public ModelDTO updateModelById(UUID id, ModelDTO modelDTO) {
        Set<ConstraintViolation<ModelDTO>> violations = validationUtil.violations(modelDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Optional<Model> optionalModel = modelRepository.findById(id);
        if (optionalModel.isPresent()) {
            Model model = optionalModel.get();
            model.setName(modelDTO.getName());
            model.setCategory(modelDTO.getCategory());
            model.setStartYear(modelDTO.getStartYear());
            model.setEndYear(modelDTO.getEndYear());
            model.setImageUrl(modelDTO.getImageUrl());
            Model updateModel = modelRepository.save(model);
            return modelMapper.map(updateModel, ModelDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteModelById(UUID id) {
        modelRepository.deleteById(id);
    }

    @Override
    public List<String> getModelsByBrandAndStartYear(String brandName, int yearstart) {
        return modelRepository.findModelsByBrandAndStartYear(brandName,yearstart);
    }

    @Override
    public List<ModelDTO> getModelsByBrandName(String brandName) {
        List<Model> models = modelRepository.findByBrandName(brandName);
        return models.stream()
                .map(model -> modelMapper.map(model, ModelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ModelDTO> getModelsByBrandAndName(String brandName, String modelName) {
        List<Model> models = modelRepository.findByBrandNameAndModelName(brandName,modelName);
        return models.stream().map(model -> modelMapper.map(model,ModelDTO.class))
                .collect(Collectors.toList());
    }
}