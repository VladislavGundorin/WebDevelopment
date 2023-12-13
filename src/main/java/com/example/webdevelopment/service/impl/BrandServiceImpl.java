package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.BrandDTO;
import com.example.webdevelopment.model.Brand;
import com.example.webdevelopment.repositorie.BrandRepository;
import com.example.webdevelopment.service.BrandService;
import com.example.webdevelopment.validation.ValidationUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@EnableCaching
public class BrandServiceImpl implements BrandService {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }
    @Override
    @CacheEvict(value = "brandCache", allEntries = true)
    public BrandDTO createBrand(BrandDTO brandDTO) {
        Set<ConstraintViolation<BrandDTO>> violations = validationUtil.violations(brandDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Brand brand = modelMapper.map(brandDTO, Brand.class);
        Brand savedBrand = brandRepository.save(brand);
        return modelMapper.map(savedBrand, BrandDTO.class);
    }

    @Override
    @Cacheable(value = "brandCache")
    public List<BrandDTO> getAllBrands() {
        List<Brand> barnds = brandRepository.findAll();
        return barnds.stream().map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    @Cacheable(value = "brandCache", key = "#id")
    public BrandDTO getBrandById(UUID id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            return modelMapper.map(optionalBrand.get(), (BrandDTO.class));
        }
        return null;
    }
    @Override
    @CacheEvict(value = "brandCache", key = "#id")
    public BrandDTO updateBrandById(UUID id, BrandDTO brandDTO) {
        Set<ConstraintViolation<BrandDTO>> violations = validationUtil.violations(brandDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setName(brandDTO.getName());
            Brand updateBrand = brandRepository.save(brand);
            return modelMapper.map(updateBrand, BrandDTO.class);
        } else {
            return null;
        }
    }
    @Override
    @CacheEvict(value = "brandCache", key = "#id")
    public void deleteBrandById(UUID id) {
        brandRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "brandCache", key = "#name")
    public List<BrandDTO> getBrandByName(String name) {
        List<Brand> brands = brandRepository.findByName(name);
        return brands.stream()
                .map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "brandCache", key = "'lowestMileageAndPrice'")
    public List<Object[]> getBrandWithLowestMileageAndPrice() {
        return brandRepository.findBrandWithLowestMileageAndPrice();
    }

}


