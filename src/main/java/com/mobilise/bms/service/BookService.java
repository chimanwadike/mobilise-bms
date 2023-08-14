package com.mobilise.bms.service;

import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.exception.ExceptionThrower;
import com.mobilise.bms.model.Author;
import com.mobilise.bms.model.Book;
import com.mobilise.bms.model.Genre;
import com.mobilise.bms.repository.AuthorRepository;
import com.mobilise.bms.repository.BookRepository;
import com.mobilise.bms.repository.GenreRepository;
import com.mobilise.bms.util.Verifier;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ExceptionThrower exceptionThrower;

    @Autowired
    Verifier verifier;

    public Page<BookDTO> getAllBooks(Pageable pageable) {
        // Retrieve all books from the database with paging
        Page<Book> bookPage = bookRepository.findAll(pageable);

        // Map books to bookDTO
        return bookPage
                .map(this::mapBookDTO);
    }

    public BookDTO getBookById(Long id, String path) {
        // Retrieve book by id from the database
        Book book = bookRepository.findById(id).orElse(null);

        // Throw exception if book is not found
        if (book == null){
            exceptionThrower.throwBookNotFoundException(path);
        }

        return mapBookDTO(book);
    }

    public BookDTO createBook(BookDTO bookDTO, String path) {
        verifier.setResourceUrl(path)
                .verifyObject(bookDTO);

        if (bookDTO.getPublishYear() > Year.now().getValue()){
            exceptionThrower.throwInvalidPublishYearException(path);
        }

        // Retrieve authors in batch
        List<Author> authors = authorRepository.findAllById(bookDTO.getAuthorIds());

        // Throw exception if there is a mismatch between the with author ids supplied and those in the db
        if (authors.size() != bookDTO.getAuthorIds().size()) {
            exceptionThrower.throwInvalidAuthorIdsDetectedException(path);
        }

        // Retrieve genres in batch
        List<Genre> genres = genreRepository.findAllById(bookDTO.getGenreIds());

        // Throw exception if there is a mismatch between the with genre ids supplied and those in the db
        if (genres.size() != bookDTO.getGenreIds().size()) {
            exceptionThrower.throwInvalidGenreIdsDetectedException(path);
        }

        // Map DTO to entity
        Book book = modelMapper.map(bookDTO, Book.class);
        book.setAuthors(authors); // Set the retrieved authors
        book.setGenres(genres); // Set the retrieved authors

        // Save the book
        Book savedBook = bookRepository.save(book);
        return mapBookDTO(savedBook);
    }

    public BookDTO updateBook(Long id, BookDTO bookDTO, String path) {
        // Validate parameters
        verifier.setResourceUrl(path)
                .verifyObject(bookDTO);

        // Retrieve book by id and throw exception if not found
        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            exceptionThrower.throwBookNotFoundException(path);
        }

        // Retrieve authors and genres associated with the bookDTO
        List<Author> authors = authorRepository.findAllById(bookDTO.getAuthorIds());

        // Throw exception if there is a mismatch between the with author ids supplied and those in the db
        if (authors.size() != bookDTO.getAuthorIds().size()) {
            exceptionThrower.throwInvalidAuthorIdsDetectedException(path);
        }

        // Retrieve genres associated with the bookDTO
        List<Genre> genres = genreRepository.findAllById(bookDTO.getGenreIds());

        // Throw exception if there is a mismatch between the with genre ids supplied and those in the db
        if (genres.size() != bookDTO.getGenreIds().size()) {
            exceptionThrower.throwInvalidGenreIdsDetectedException(path);
        }

        // Use ModelMapper to apply changes from DTO to existing book
        modelMapper.map(bookDTO, existingBook);
        existingBook.setAuthors(authors);
        existingBook.setGenres(genres);

        // Save updates to existing book
        Book updatedBook = bookRepository.save(existingBook);
        return mapBookDTO(updatedBook);
    }

    public void deleteBook(Long id, String path) {
        // Check if book exists and throw exception if it does not
        if (!bookRepository.existsById(id)) {
            exceptionThrower.throwBookNotFoundException(path);
        }

        // Delete book
        bookRepository.deleteById(id);
    }

    public BookDTO patchBook(Long id, BookDTO bookDTO, String path) {
        verifier.setResourceUrl(path)
                .verifyObject(bookDTO);

        Book existingBook = bookRepository.findById(id).orElse(null);
        if (existingBook == null) {
            exceptionThrower.throwBookNotFoundException(path);
        }

        // Use ModelMapper to apply changes from DTO to entity
        modelMapper.map(bookDTO, existingBook);

        // Update authors if valid
        List<Author> authors = authorRepository.findAllById(bookDTO.getAuthorIds());
        if (authors.size() != bookDTO.getAuthorIds().size()) {
            exceptionThrower.throwInvalidAuthorIdsDetectedException(path);
        }
        existingBook.setAuthors(authors);

        // Update genres if valid
        List<Genre> genres = genreRepository.findAllById(bookDTO.getGenreIds());
        if (genres.size() != bookDTO.getGenreIds().size()) {
            exceptionThrower.throwInvalidGenreIdsDetectedException(path);
        }
        existingBook.setGenres(genres);

        // Save changes to database
        Book updatedBook = bookRepository.save(existingBook);
        return mapBookDTO(updatedBook);
    }

    public Page<BookDTO> searchBooksByTitleOrAuthor(String title, String author, Pageable pageable) {
        //Search book by title or and author
        Page<Book> searchResults = bookRepository.searchByTitleOrAuthor(title, author, pageable);
        return searchResults.map(this::mapBookDTO);
    }

    BookDTO mapBookDTO(Book book){
        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        // Set author and genre IDS
        bookDTO.setAuthorIds(book.getAuthors().stream().map(Author::getId).collect(Collectors.toList()));
        bookDTO.setGenreIds(book.getGenres().stream().map(Genre::getId).collect(Collectors.toList()));
        return bookDTO;
    }

}