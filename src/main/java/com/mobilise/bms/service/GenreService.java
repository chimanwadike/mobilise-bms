package com.mobilise.bms.service;

import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.dto.GenreDTO;
import com.mobilise.bms.exception.ExceptionThrower;
import com.mobilise.bms.model.Genre;
import com.mobilise.bms.repository.GenreRepository;
import com.mobilise.bms.util.Verifier;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ExceptionThrower exceptionThrower;

    @Autowired
    Verifier verifier;

    public Page<GenreDTO> getAllGenres(Pageable pageable) {
        Page<Genre> genres = genreRepository.findAll(pageable);

        return genres.map((genre) -> modelMapper.map(genre, GenreDTO.class));
    }

    public GenreDTO getGenreById(Long id, String path) {
        Genre genre = genreRepository.findById(id).orElse(null);
        if (genre == null){
            exceptionThrower.throwGenreNotFoundException(path);
        }

        return modelMapper.map(genre, GenreDTO.class);
    }

    public GenreDTO createGenre(GenreDTO genreDTO, String path) {
        if (genreRepository.existsByNameIgnoreCase(genreDTO.getName())){
            exceptionThrower.throwGenreAlreadyExistsException(path);
        }

        Genre genre = modelMapper.map(genreDTO, Genre.class);
        Genre savedGenre = genreRepository.save(genre);
        return modelMapper.map(savedGenre, GenreDTO.class);
    }

    public GenreDTO updateGenre(Long id, GenreDTO genreDTO, String path) {
        Genre existingGenre = genreRepository.findById(id).orElse(null);
        if (existingGenre == null){
            exceptionThrower.throwGenreNotFoundException(path);
        }

        modelMapper.map(genreDTO, existingGenre);

        Genre updatedGenre = genreRepository.save(existingGenre);
        return modelMapper.map(updatedGenre, GenreDTO.class);
    }

    public void deleteGenre(Long id, String path) {
        if (!genreRepository.existsById(id)) {
            exceptionThrower.throwGenreNotFoundException(path);
        }
        genreRepository.deleteById(id);
    }
}
