package com.mobilise.bms.service.BookServiceTests;

import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.exception.ExceptionThrower;
import com.mobilise.bms.exception.GeneralAppException;
import com.mobilise.bms.model.Author;
import com.mobilise.bms.model.Book;
import com.mobilise.bms.repository.AuthorRepository;
import com.mobilise.bms.repository.BookRepository;
import com.mobilise.bms.repository.GenreRepository;
import com.mobilise.bms.service.AuthorService;
import com.mobilise.bms.service.BookService;
import com.mobilise.bms.util.TestDataUtil;
import com.mobilise.bms.util.Verifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateBookTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ExceptionThrower exceptionThrower;

    @Mock
    private Verifier verifier;

    @InjectMocks
    private BookService bookService;

    private Book book;
    private BookDTO bookDTO;

    private final String API_LINK = "/api/users";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        bookService = new BookService();

        ReflectionTestUtils.setField(bookService, "bookRepository", bookRepository);
        ReflectionTestUtils.setField(bookService, "exceptionThrower", exceptionThrower);
        ReflectionTestUtils.setField(bookService,"authorRepository",authorRepository);
        ReflectionTestUtils.setField(bookService,"genreRepository",genreRepository);
        ReflectionTestUtils.setField(bookService, "modelMapper", modelMapper);
        ReflectionTestUtils.setField(bookService, "verifier", verifier);

        when(verifier.setResourceUrl(API_LINK)).thenReturn(verifier);

        book = new Book();
        book.setId(1L);
        book.setTitle("Java Head First");
        book.setIsbn("123456789875");
        book.setPublishYear(2000);

        bookDTO = new BookDTO();
        bookDTO.setId(1L);
        bookDTO.setTitle("Java Head First");
        bookDTO.setIsbn("123456789875");
        bookDTO.setPublishYear(2000);
    }

    @Test
    public void testCreate_ValidBook_SuccessfulCreation() {
        // Mock
        when(modelMapper.map(any(), eq(BookDTO.class))).thenReturn(bookDTO);
        when(modelMapper.map(any(), eq(Book.class))).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);

        // Act
        BookDTO result = bookService.createBook(bookDTO, API_LINK);

        // Assert
        assertEquals(bookDTO, result);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testCreate_BookPublisherYearInFuture_ThrowException() {

        // Arrange
        BookDTO invalidBook = new BookDTO();
        invalidBook.setId(3L);
        invalidBook.setPublishYear(2024);
        invalidBook.setTitle("Invalid Book");
        invalidBook.setIsbn("123456789876");

        // Mock

        //when(genreReposito0ry.existsByNameIgnoreCase(genre.getName())).thenReturn(true);
        doThrow(new GeneralAppException(HttpStatus.BAD_REQUEST, "9999", "test", API_LINK))
                .when(exceptionThrower)
                .throwInvalidPublishYearException(API_LINK);

        // Act & Assert
        assertThrows(GeneralAppException.class, () -> bookService.createBook(invalidBook, API_LINK));

    }
}
