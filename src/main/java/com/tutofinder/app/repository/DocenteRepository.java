package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findById(Long id);

    Optional<Docente> findByNombre(String id);
}
