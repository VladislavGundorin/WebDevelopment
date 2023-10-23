package com.example.webdevelopment.repositorie;

import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    @Query("SELECT o.description FROM Offer o JOIN o.model m WHERE m.name = :modelName")
    List<Offer> findDescriptionsByModelName(@Param("modelName") String modelName);
}
