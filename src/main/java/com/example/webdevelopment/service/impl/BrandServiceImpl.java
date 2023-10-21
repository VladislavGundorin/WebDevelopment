package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.BrandDTO;
import com.example.webdevelopment.model.Brand;
import com.example.webdevelopment.repositorie.BrandRepository;
import com.example.webdevelopment.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository,ModelMapper modelMapper){
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        Brand saveBrand = brandRepository.save(brand);
        return modelMapper.map(saveBrand,BrandDTO.class);
    }
}
