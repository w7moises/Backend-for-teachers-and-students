package com.tutofinder.app.repository;

import java.util.Optional;

import com.tutofinder.app.entity.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findById(Long id);

    Optional<Alumno> findByNombre(String nombre);
}
