package com.example.webdevelopment.repositorie;

import com.example.webdevelopment.dto.OfferDTO;
import com.example.webdevelopment.model.Offer;
import com.example.webdevelopment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<Offer, UUID> {
    @Query("SELECT o.description FROM Offer o " +
            "JOIN o.model m " +
            "JOIN m.brand b " +
            "WHERE b.name = :brandName AND m.name = :modelName")
    List<String> findDescriptionsByBrandAndModel(@Param("brandName") String brandName, @Param("modelName") String modelName);

    List<Offer> findAll();

    List<Offer> findBySeller(User seller);

}
