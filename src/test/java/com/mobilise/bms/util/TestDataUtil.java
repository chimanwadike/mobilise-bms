package com.mobilise.bms.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.dto.GenreDTO;
import com.mobilise.bms.model.Author;
import com.mobilise.bms.model.Book;
import com.mobilise.bms.model.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

public class TestDataUtil {

    public static List<Author> createSampleAuthors() {

        Author author1 = new Author();
        author1.setId(1L);
        author1.setFirstName("author1 firstName");
        author1.setLastName("author1 secondName");
        author1.setEmailAddress("author1@example.com");

        Author author2 = new Author();
        author2.setFirstName("author2 firstName");
        author2.setLastName("author2 secondName");
        author2.setEmailAddress("author2@example.com");

        return Arrays.asList(author1, author2);
    }

    public static List<AuthorDTO> createSampleAuthorDtos() {

        AuthorDTO author1 = new AuthorDTO();
        author1.setId(1L);
        author1.setFirstName("author1 firstName");
        author1.setLastName("author1 secondName");
        author1.setEmailAddress("author1@example.com");

        AuthorDTO author2 = new AuthorDTO();
        author2.setFirstName("author2 firstName");
        author2.setLastName("author2 secondName");
        author2.setEmailAddress("author2@example.com");

        return Arrays.asList(author1, author2);
    }

    public static List<Genre> createSampleGenres() {

        Genre genre1 = new Genre();
        genre1.setId(1L);
        genre1.setName("Science and Fiction");

        Genre genre2 = new Genre();
        genre2.setId(2L);
        genre2.setName("Business and Politics");

        List<Genre> genres = Arrays.asList(genre1, genre2);
        return genres;
    }

    public static List<GenreDTO> createSampleGenreDtos() {

        GenreDTO genre1 = new GenreDTO();
        genre1.setId(1L);
        genre1.setName("Science and Fiction");

        GenreDTO genre2 = new GenreDTO();
        genre2.setId(2L);
        genre2.setName("Business and Politics");

        List<GenreDTO> genres = Arrays.asList(genre1, genre2);
        return genres;
    }

    public static List<Book> createSampleBooks() {

        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Java Head First");
        book1.setIsbn("123456789875");
        book1.setPublishYear(2000);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Design Patterns");
        book2.setIsbn("123456789877");
        book1.setPublishYear(2001);

        List<Book> books = Arrays.asList(book1, book2);
        return books;
    }

    public static List<BookDTO> createSampleBookDtos() {

        BookDTO book1 = new BookDTO();
        book1.setId(1L);
        book1.setTitle("Java Head First");
        book1.setIsbn("123456789875");
        book1.setPublishYear(2000);

        BookDTO book2 = new BookDTO();
        book2.setId(2L);
        book2.setTitle("Design Patterns");
        book2.setIsbn("123456789877");
        book1.setPublishYear(2001);

        List<BookDTO> books = Arrays.asList(book1, book2);
        return books;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
