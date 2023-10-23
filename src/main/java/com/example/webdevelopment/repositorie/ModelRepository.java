package com.example.webdevelopment.repositorie;

import com.example.webdevelopment.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    @Query("SELECT m.name FROM Model m JOIN m.brand b WHERE b.name = :brandName AND m.startYear >= :yearstart")
    List<String> findModelsByBrandAndStartYear(@Param("brandName") String brandName, @Param("yearstart") int yearstart);
//    @Query("SELECT m.name FROM Model m JOIN m.brand b WHERE b.name = :brandName AND m.startYear >= :startYear ORDER BY m.startYear DESC")
//    List<String> findModelsByBrandAndStartYear(String brandName, int startYear);

}
