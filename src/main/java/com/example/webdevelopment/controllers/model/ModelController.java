package com.example.webdevelopment.controllers.model;

import com.example.webdevelopment.dto.ModelDTO;
import com.example.webdevelopment.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/models")
public class ModelController {
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService){
        this.modelService = modelService;
    }
    @GetMapping("/allmodels")
    public String getAllModels(Model model) {
        List<ModelDTO> modelDTOS = modelService.getAllModels();
        model.addAttribute("models", modelDTOS);
        return "models-all";
    }
    @GetMapping("/details/{model-id}")
    public String modelDetails(@PathVariable("model-id") UUID modelId, Model model){
        ModelDTO modelDTO = modelService.getModelById(modelId);
        model.addAttribute("modelsDetails", modelDTO);
        return "models-details";
    }

    @GetMapping("/brand-startYear")
    public List<String> getModelsByBrandAndStartYear(@RequestParam String brandname,@RequestParam int yearstart){
        return modelService.getModelsByBrandAndStartYear(brandname, yearstart);
    }
    @DeleteMapping("/delete/id")
    public void deleteModelById(@PathVariable UUID id){
        modelService.deleteModelById(id);
    }

    @ModelAttribute("modelModel")
    public ModelDTO initModel(){
        return  new ModelDTO();
    }
    @GetMapping("/create")
    public String createModel(){
        return "models-add";
    }
    @PostMapping("/create")
    public String createModel(@ModelAttribute("modelDTO")ModelDTO modelDTO){
        modelService.createModel(modelDTO);
        return "redirect:/models/create";
    }
    @PostMapping("/delete/models/{id}")
    public String deleteModel(@PathVariable UUID id) {
        modelService.deleteModelById(id);
        return "redirect:/models/allmodels";
    }
}
