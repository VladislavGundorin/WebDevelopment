package com.example.webdevelopment.repositorie;

import com.example.webdevelopment.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    List<Brand> findByName(String name);

    @Query("SELECT b.name AS brandName, o.mileage, o.price " +
            "FROM Brand b " +
            "JOIN b.models m " +
            "JOIN m.offers o " +
            "ORDER BY o.mileage ASC, o.price ASC " +
            "LIMIT 1")
    List<Object[]> findBrandWithLowestMileageAndPrice();

}
