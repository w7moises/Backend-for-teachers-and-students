package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Padre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PadreRepository extends JpaRepository<Padre, Long> {
    Optional<Padre> findById(Long id);

    Optional<Padre> findByNombre(String name);
}
