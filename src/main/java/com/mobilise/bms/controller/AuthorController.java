package com.mobilise.bms.controller;

import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.service.AuthorService;
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
@RequestMapping("/api/v1/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<Page<AuthorDTO>> getAllAuthors(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize) {

        Pageable paging = PageRequest.of(page, pageSize);

        Page<AuthorDTO> authors = authorService.getAllAuthors(paging);

        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest) {

        AuthorDTO author = authorService.getAuthorById(id, httpServletRequest.getServletPath());

        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(
            @Valid @RequestBody AuthorDTO authorDTO) {

        AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorDTO authorDTO,
            HttpServletRequest httpServletRequest) {

        AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO, httpServletRequest.getServletPath());

        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest) {

        authorService.deleteAuthor(id, httpServletRequest.getServletPath());

        return ResponseEntity.noContent().build();
    }
}