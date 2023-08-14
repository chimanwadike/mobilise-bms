package com.mobilise.bms.controller;

import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.exception.GeneralAppException;
import com.mobilise.bms.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookDTO>> getAllBooks(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize) {

        Pageable paging = PageRequest.of(page, pageSize);

        Page<BookDTO> bookPage = bookService.getAllBooks(paging);

        return ResponseEntity.ok(bookPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(
            @PathVariable Long id, HttpServletRequest httpServletRequest) {

        BookDTO book = bookService.getBookById(id,httpServletRequest.getServletPath());

        return ResponseEntity.ok(book);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookDTO>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize
            ) throws GeneralAppException {

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<BookDTO> searchResults = bookService.searchBooksByTitleOrAuthor(title, author, pageable);

        return ResponseEntity.ok(searchResults);
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(
            @Valid @RequestBody BookDTO bookDTO, HttpServletRequest httpServletRequest) {

        BookDTO createdBook = bookService.createBook(bookDTO,httpServletRequest.getServletPath());

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookDTO bookDTO,
            HttpServletRequest httpServletRequest) {

        BookDTO updatedBook = bookService.updateBook(id, bookDTO,httpServletRequest.getServletPath());

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable Long id,
            HttpServletRequest httpServletRequest) {

        bookService.deleteBook(id,httpServletRequest.getServletPath());

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> patchBook(
            @PathVariable Long id,
            @RequestBody BookDTO bookDTO,
            HttpServletRequest httpServletRequest) {

        BookDTO patchedBook = bookService.patchBook(id, bookDTO, httpServletRequest.getServletPath());

        return ResponseEntity.ok(patchedBook);
    }
}
