package com.example.webdevelopment.controllers.model;

import com.example.webdevelopment.dto.ModelDTO;
import com.example.webdevelopment.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService){
        this.modelService = modelService;
    }
    @GetMapping("/allmodels")
    public List<ModelDTO> getAllModels(){
        return modelService.getAllModels();
    }
    @GetMapping("/getmodel/{id}")
    public ModelDTO getNoledById(@PathVariable UUID id){
        return modelService.getModelById(id).orElse(null);
    }
    @GetMapping("/brand-startYear")
    public List<String> getModelsByBrandAndStartYear(@RequestParam String brandname,@RequestParam int yearstart){
        return modelService.getModelsByBrandAndStartYear(brandname, yearstart);
    }
    @DeleteMapping("/id")
    public void deleteModelById(@PathVariable UUID id){
        modelService.deleteModelById(id);
    }
    @PostMapping("/create")
    public ModelDTO createModel(@RequestBody ModelDTO modelDTO){
        return modelService.createModel(modelDTO);
    }
}
