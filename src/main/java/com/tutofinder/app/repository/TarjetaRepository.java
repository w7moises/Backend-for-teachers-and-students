package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    Optional<Tarjeta> findById(Long id);

    @Query("select t from Tarjeta t where upper(t.nombrePoseedor) like upper(concat('%',?1,'%'))")
    List<Tarjeta> findByNombrePoseedor(String nombre);
}
