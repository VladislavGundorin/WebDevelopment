package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.ModelDTO;
import com.example.webdevelopment.model.Model;
import com.example.webdevelopment.repositorie.ModelRepository;
import com.example.webdevelopment.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    @Autowired
    public ModelServiceImpl(ModelMapper modelMepper,ModelRepository modelRepository){
        this.modelMapper = modelMepper;
        this.modelRepository = modelRepository;
    }
    @Override
    public ModelDTO createModel(ModelDTO modelDTO) {
        Model model = modelMapper.map(modelDTO,Model.class);
        Model saveModel = modelRepository.save(model);
        return modelMapper.map(saveModel,ModelDTO.class);
    }
}
