package com.mobilise.bms.service.BookServiceTests;

import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.dto.GenreDTO;
import com.mobilise.bms.model.Book;
import com.mobilise.bms.model.Genre;
import com.mobilise.bms.repository.BookRepository;
import com.mobilise.bms.repository.GenreRepository;
import com.mobilise.bms.service.BookService;
import com.mobilise.bms.service.GenreService;
import com.mobilise.bms.util.TestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllBooksTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetAllBooks_ReturnsListOfBooks(){
        // Create a pageable object for testing
        Pageable pageable = PageRequest.of(0, 10, Sort.by("title"));

        Page<Book> bookPage = new PageImpl<>(TestDataUtil.createSampleBooks());

        // Mock the repository
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);

        // Test the service method
        Page<BookDTO> resultBooks = bookService.getAllBooks(pageable);

        // Verify that the repository method was called
        verify(bookRepository).findAll(pageable);

        // Assert the result
        assertThat(resultBooks)
                .isNotNull()
                .hasSize(TestDataUtil.createSampleBooks().size());
    }

    @Test
    public void testGetAllBooks_ReturnsEmptyList(){
        // Create a pageable object for testing
        Pageable pageable = PageRequest.of(0, 10, Sort.by("title"));

        Page<Book> bookPage = new PageImpl<>(Collections.emptyList());

        // Mock the repository
        when(bookRepository.findAll(pageable)).thenReturn(bookPage);

        // Test the service method
        Page<BookDTO> resultBooks = bookService.getAllBooks(pageable);

        // Verify that the repository method was called
        verify(bookRepository).findAll(pageable);

        // Assert the result
        assertThat(resultBooks)
                .isEmpty();
    }
}
