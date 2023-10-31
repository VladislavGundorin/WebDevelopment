package com.example.webdevelopment.controllers.Brand;

import com.example.webdevelopment.dto.BrandDTO;
import com.example.webdevelopment.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService){
        this.brandService = brandService;
    }
    @GetMapping("/allbrands")
    public List<BrandDTO> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/getBrand/{id}")
    public BrandDTO getBrandById(@PathVariable UUID id) {
        return brandService.getBrandById(id).orElse(null);
    }

    @GetMapping("/getByBrandName")
    public List<BrandDTO> getBrandByName(@RequestParam String name) {
        return brandService.getBrandByName(name);
    }

    @GetMapping("/lowest-mileage-price")
    public List<Object[]> getBrandWithLowestMileageAndPrice() {
        return brandService.getBrandWithLowestMileageAndPrice();
    }
    @DeleteMapping("/{id}")
    public void deleteBrandById(@PathVariable UUID id) {
        brandService.deleteBrandById(id);
    }
    @PostMapping("/create")
    public BrandDTO createBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.createBrand(brandDTO);
    }
}
