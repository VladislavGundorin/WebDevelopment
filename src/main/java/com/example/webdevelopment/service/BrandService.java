package com.example.webdevelopment.service;

import com.example.webdevelopment.dto.BrandDTO;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BrandService {
    BrandDTO createBrand(BrandDTO brandDTO);

    List<BrandDTO> getAllBrands();

    BrandDTO getBrandById(UUID id);

    BrandDTO updateBrandById(UUID id,BrandDTO brandDTO);

    void deleteBrandById(UUID id);

    List<BrandDTO> getBrandByName(String name);

    List<Object[]> getBrandWithLowestMileageAndPrice();

}

