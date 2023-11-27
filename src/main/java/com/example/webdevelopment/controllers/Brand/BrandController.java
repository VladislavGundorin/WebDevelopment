package com.example.webdevelopment.controllers.Brand;

import com.example.webdevelopment.dto.BrandDTO;
import com.example.webdevelopment.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }

    @GetMapping("/allbrands")
    public String getAllBrands(Model model){
        List<BrandDTO> brandDTOs = brandService.getAllBrands();
        model.addAttribute("brands", brandDTOs);
        return "brands-all";
    }
//    @GetMapping("/brand-details/{brand-id}")
//    public String brandDetails(@PathVariable("brand-id") UUID brandId, Model model) {
//        BrandDTO brandDTO = brandService.getBrandById(brandId);
//        model.addAttribute("brandDetails", brandDTO);
//        return "brands-details";
//    }

    @GetMapping("/getByBrandName")
    public List<BrandDTO> getBrandByName(@RequestParam String name) {
        return brandService.getBrandByName(name);
    }

    @GetMapping("/lowest-mileage-price")
    public List<Object[]> getBrandWithLowestMileageAndPrice() {
        return brandService.getBrandWithLowestMileageAndPrice();
    }
//    @DeleteMapping("/delete/brands/{id}")
//    public String deleteBrandById(@PathVariable UUID id) {
//        brandService.deleteBrandById(id);
//        return "redirect:/brands/allbrands"; // Перенаправление после удаления
//    }
    @PostMapping("brands/create")
    public BrandDTO createBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.createBrand(brandDTO);
    }
    @PostMapping("/delete/brands/{id}")
    public String deleteBrand(@PathVariable UUID id) {
        brandService.deleteBrandById(id);
        return "redirect:/brands/allbrands"; // Редирект на страницу всех брендов после удаления
    }
}
