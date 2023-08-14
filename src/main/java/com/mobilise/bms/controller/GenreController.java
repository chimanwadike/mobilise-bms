package com.mobilise.bms.controller;

import com.mobilise.bms.dto.GenreDTO;
import com.mobilise.bms.service.GenreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<Page<GenreDTO>> getAllGenres(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize) {

        Pageable paging = PageRequest.of(page, pageSize);

        Page<GenreDTO> genres = genreService.getAllGenres(paging);

        return ResponseEntity.ok(genres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest) {

        GenreDTO genre = genreService.getGenreById(id, httpServletRequest.getServletPath());

        return ResponseEntity.ok(genre);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> createGenre(
            @Valid @RequestBody GenreDTO genreDTO,
            HttpServletRequest httpServletRequest) {

        GenreDTO createdGenre = genreService.createGenre(genreDTO, httpServletRequest.getServletPath());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> updateGenre(
            @PathVariable Long id,
            @Valid @RequestBody GenreDTO genreDTO,
            HttpServletRequest httpServletRequest) {

        GenreDTO updatedGenre = genreService.updateGenre(id, genreDTO, httpServletRequest.getServletPath());

        return ResponseEntity.ok(updatedGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest) {

        genreService.deleteGenre(id, httpServletRequest.getServletPath());

        return ResponseEntity.noContent().build();
    }
}