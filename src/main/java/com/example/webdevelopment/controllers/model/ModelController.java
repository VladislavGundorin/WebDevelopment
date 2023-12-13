package com.example.webdevelopment.controllers.model;

import com.example.webdevelopment.dto.ModelDTO;
import com.example.webdevelopment.service.ModelService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/models")
public class ModelController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService){
        this.modelService = modelService;
    }
    @GetMapping("/allmodels")
    public String getAllModels(Model model) {
        LOG.log(Level.INFO, "Entering method: getAllModels");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<ModelDTO> modelDTOS = modelService.getAllModels();
        stopWatch.stop();
        System.out.println("getAllModels execution time: " + stopWatch.getTotalTimeMillis() + " ms");
        model.addAttribute("models", modelDTOS);
        return "models-all";
    }
    @GetMapping("/details/{model-id}")
    public String modelDetails(@PathVariable("model-id") UUID modelId, Model model){
        LOG.log(Level.INFO, "Entering method: modelDetails for model " + modelId);
        ModelDTO modelDTO = modelService.getModelById(modelId);
        model.addAttribute("modelsDetails", modelDTO);
        return "models-details";
    }

    @GetMapping("/brand-startYear")
    public List<String> getModelsByBrandAndStartYear(@RequestParam String brandname,@RequestParam int yearstart){
        LOG.log(Level.INFO, "Entering method: getModelsByBrandAndStartYear");
        return modelService.getModelsByBrandAndStartYear(brandname, yearstart);
    }
    @DeleteMapping("/delete/id")
    public void deleteModelById(@PathVariable UUID id){
        LOG.log(Level.INFO, "Entering method: deleteModelById for model " + id);
        modelService.deleteModelById(id);
    }

    @ModelAttribute("modelModel")
    public ModelDTO initModel(){
        LOG.log(Level.INFO, "Entering method: initModel");
        return  new ModelDTO();
    }
    @GetMapping("/create")
    public String createModel(){
        LOG.log(Level.INFO, "Entering method: createModel");
        return "models-add";
    }
    @PostMapping("/create")
    public String createModel(@ModelAttribute("modelDTO")ModelDTO modelDTO){
        LOG.log(Level.INFO, "Entering method: createModel");
        modelService.createModel(modelDTO);
        return "redirect:/models/create";
    }
    @PostMapping("/delete/models/{id}")
    public String deleteModel(@PathVariable UUID id) {
        LOG.log(Level.INFO, "Entering method: deleteModel for model " + id);
        modelService.deleteModelById(id);
        return "redirect:/models/allmodels";
    }
}
