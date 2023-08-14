package com.mobilise.bms.service.AuthorServiceTests;

import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.model.Author;
import com.mobilise.bms.model.Book;
import com.mobilise.bms.repository.AuthorRepository;
import com.mobilise.bms.repository.BookRepository;
import com.mobilise.bms.service.AuthorService;
import com.mobilise.bms.service.BookService;
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
public class GetAllAuthorsTest {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testGetAllAuthors_ReturnsListOfBooks(){
        // Create a pageable object for testing
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        Page<Author> authorPage = new PageImpl<>(TestDataUtil.createSampleAuthors());

        // Mock the repository
        when(authorRepository.findAll(pageable)).thenReturn(authorPage);

        // Test the service method
        Page<AuthorDTO> resultAuthors = authorService.getAllAuthors(pageable);

        // Verify that the repository method was called
        verify(authorRepository).findAll(pageable);

        // Assert the result
        assertThat(resultAuthors)
                .isNotNull()
                .hasSize(TestDataUtil.createSampleBooks().size());
    }

    @Test
    public void testGetAllAuthors_ReturnsEmptyList(){
        // Create a pageable object for testing
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));

        Page<Author> authorPage = new PageImpl<>(Collections.emptyList());

        // Mock the repository
        when(authorRepository.findAll(pageable)).thenReturn(authorPage);

        // Test the service method
        Page<AuthorDTO> resultAuthors = authorService.getAllAuthors(pageable);

        // Verify that the repository method was called
        verify(authorRepository).findAll(pageable);

        // Assert the result
        assertThat(resultAuthors)
                .isEmpty();
    }
}
