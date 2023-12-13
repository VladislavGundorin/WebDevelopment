package com.example.webdevelopment.controllers.Brand;

import com.example.webdevelopment.dto.BrandDTO;
import com.example.webdevelopment.service.BrandService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private static final Logger LOG = LogManager.getLogger(Controller.class);
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }

    @GetMapping("/allbrands")
    public String getAllBrands(Model model){
        LOG.log(Level.INFO, "Entering method: getAllBrands");
        List<BrandDTO> brandDTOs = brandService.getAllBrands();
        model.addAttribute("brands", brandDTOs);
        return "brands-all";
    }

    @GetMapping("/create")
    public String createBrand(){
        LOG.log(Level.INFO, "Entering method: createBrand");
        return "brands-add";
    }
    @ModelAttribute("brandModel")
    public BrandDTO initBrand(){
        return new BrandDTO();
    }

    @GetMapping("/getByBrandName")
    public List<BrandDTO> getBrandByName(@RequestParam String name) {
        LOG.log(Level.INFO, "Entering method: getBrandByName");
        return brandService.getBrandByName(name);
    }

    @GetMapping("/lowest-mileage-price")
    public List<Object[]> getBrandWithLowestMileageAndPrice() {
        LOG.log(Level.INFO, "Entering method: getBrandWithLowestMileageAndPrice");
        return brandService.getBrandWithLowestMileageAndPrice();
    }

    @PostMapping("/create")
    public String createBrand(@ModelAttribute("brandDTO") BrandDTO brandDTO) {
        LOG.log(Level.INFO, "Entering method: createBrand");
        brandService.createBrand(brandDTO);
        return "redirect:/brands/create";
    }

    @PostMapping("/delete/brands/{id}")
    public String deleteBrand(@PathVariable UUID id) {
        LOG.log(Level.INFO, "Entering method: deleteBrand");
        brandService.deleteBrandById(id);
        return "redirect:/brands/allbrands";
    }
}