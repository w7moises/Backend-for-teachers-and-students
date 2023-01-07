package com.tutofinder.app.repository;

import com.tutofinder.app.entity.Favorito;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface FavoritoRepository extends PagingAndSortingRepository<Favorito, Long> {
    Optional<Favorito> findById(Long id);

    List<Favorito> findFavoritoByPadreId(Long id);
}
