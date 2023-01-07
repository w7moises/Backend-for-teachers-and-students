package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
    Optional<Membresia> findById(Long id);

    Optional<Membresia> findByDocenteId(Long docenteId);

    Optional<Membresia> findByDocenteIdAndTarjetaId(Long docenteId, Long tarjetaId);
}
