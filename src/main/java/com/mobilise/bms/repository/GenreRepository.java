package com.mobilise.bms.repository;

import com.mobilise.bms.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    boolean existsByNameIgnoreCase(String name);
}
