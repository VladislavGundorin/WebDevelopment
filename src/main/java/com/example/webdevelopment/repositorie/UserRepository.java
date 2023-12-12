package com.example.webdevelopment.repositorie;

import com.example.webdevelopment.enums.Role;
import com.example.webdevelopment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u.firstName, u.lastName, r.role FROM User u JOIN u.role r WHERE r.role = :roleValue")
    List<Object[]> findUsersByRole(@Param("roleValue") Role role);

    List<User> findUserById(UUID id);

    Optional<User> findUserByUsername(String username);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);


}

