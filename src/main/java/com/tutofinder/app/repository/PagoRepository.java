package com.tutofinder.app.repository;


import java.util.Optional;

import com.tutofinder.app.entity.Pago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    Optional<Pago> findById(Long id);
}
