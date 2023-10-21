package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.repositorie.OfferRepository;
import com.example.webdevelopment.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository){
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
    }
    @Override
    public OfferDTO createOffer(OfferDTO offerDTO){
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        Offer saveOffer = offerRepository.save(offer);
        return modelMapper.map(saveOffer, OfferDTO.class);
    }
}
