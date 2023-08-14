package com.mobilise.bms.service;

import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.exception.ExceptionThrower;
import com.mobilise.bms.model.Author;
import com.mobilise.bms.repository.AuthorRepository;
import com.mobilise.bms.util.Verifier;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ExceptionThrower exceptionThrower;

    @Autowired
    Verifier verifier;

    public Page<AuthorDTO> getAllAuthors(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return authors.map((author) -> modelMapper.map(author, AuthorDTO.class));
    }

    public AuthorDTO getAuthorById(Long id, String path) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null){
            exceptionThrower.throwAuthorNotFoundException(path);
        }

        return modelMapper.map(author, AuthorDTO.class);
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorDTO.class);
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO, String path) {
        Author existingAuthor = authorRepository.findById(id).orElse(null);
        if (existingAuthor == null){
            exceptionThrower.throwAuthorNotFoundException(path);
        }

        modelMapper.map(authorDTO, existingAuthor);

        Author updatedAuthor = authorRepository.save(existingAuthor);
        return modelMapper.map(updatedAuthor, AuthorDTO.class);
    }

    public void deleteAuthor(Long id, String path) {
        if (!authorRepository.existsById(id)) {
            exceptionThrower.throwAuthorNotFoundException(path);
        }
        authorRepository.deleteById(id);
    }
}
