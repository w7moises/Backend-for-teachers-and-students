package com.tutofinder.app.repository;

import java.util.Optional;

import com.tutofinder.app.entity.Informe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Long> {
    Optional<Informe> findById(Long id);
}
