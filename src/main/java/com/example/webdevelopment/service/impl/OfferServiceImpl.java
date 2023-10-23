package com.example.webdevelopment.service.impl;

import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.repositorie.OfferRepository;
import com.example.webdevelopment.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(ModelMapper modelMapper, OfferRepository offerRepository) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDTO createOffer(OfferDTO offerDTO) {
        Offer offer = modelMapper.map(offerDTO, Offer.class);
        Offer saveOffer = offerRepository.save(offer);
        return modelMapper.map(saveOffer, OfferDTO.class);
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();
        return offers.stream().map(offer -> modelMapper.map(offer, OfferDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OfferDTO> getOfferById(UUID id) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        return optionalOffer.map(offer -> modelMapper.map(offer, OfferDTO.class));
    }

    @Override
    public OfferDTO updateOfferByID(UUID id, OfferDTO offerDTO) {
        Optional<Offer> optionalOffer = offerRepository.findById(id);
        if (optionalOffer.isPresent()) {
            Offer offer = optionalOffer.get();
//            offer.setSeller(offerDTO.getDescription());
            Offer updateSeller = offerRepository.save(offer);
            return modelMapper.map(updateSeller, OfferDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public void deleteOfferById(UUID id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<OfferDTO> findDescriptionsByModelName(String modelName) {
        List<Offer> offers = offerRepository.findDescriptionsByModelName(modelName);
        return offers.stream().map(offer -> modelMapper.map(offer,OfferDTO.class))
                .collect(Collectors.toList());
    }

}
