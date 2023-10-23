package com.example.webdevelopment.repositorie;

import com.example.webdevelopment.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {
    List<Brand> findByName(String name);
}
