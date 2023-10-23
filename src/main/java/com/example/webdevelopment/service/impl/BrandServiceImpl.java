package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.BrandDTO;
import com.example.webdevelopment.model.Brand;
import com.example.webdevelopment.repositorie.BrandRepository;
import com.example.webdevelopment.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private final ModelMapper modelMapper;
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BrandDTO createBrand(BrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        Brand saveBrand = brandRepository.save(brand);
        return modelMapper.map(saveBrand, BrandDTO.class);
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        List<Brand> barnds = brandRepository.findAll();
        return barnds.stream().map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BrandDTO> getBrandById(UUID id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        return optionalBrand.map(brand -> modelMapper.map(brand, BrandDTO.class));
    }

    @Override
    public BrandDTO updateBrandById(UUID id, BrandDTO brandDTO) {
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
    public void deleteBrandById(UUID id) {
        brandRepository.deleteById(id);
    }

    @Override
    public List<BrandDTO> getBrandByName(String name) {
        List<Brand> brands = brandRepository.findByName(name);
        return brands.stream()
                .map(brand -> modelMapper.map(brand, BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Object[]> getBrandWithLowestMileageAndPrice() {
        return brandRepository.findBrandWithLowestMileageAndPrice();
    }


}

//    @Override
//    public List<OfferDTO> findDescriptionsByModelName(String modelName) {
//        List<Offer> offers = offerRepository.findDescriptionsByModelName(modelName);
//        return offers.stream().map(offer -> modelMapper.map(offer,OfferDTO.class))
//                .collect(Collectors.toList());
//    }


