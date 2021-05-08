package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Tutoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutoriaRepository extends PagingAndSortingRepository<Tutoria,Long> {
    Optional<Tutoria> findById(Long id);

    @Query(value = "SELECT t FROM Tutoria t ORDER BY CASE WHEN t.docente.membresia = true THEN 1 ELSE 2 END , t.docente.membresia")
    List<Tutoria> findAll();

}
