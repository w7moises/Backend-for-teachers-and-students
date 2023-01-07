package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findById(Long id);
}
