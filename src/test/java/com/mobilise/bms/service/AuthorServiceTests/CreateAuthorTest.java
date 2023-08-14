package com.mobilise.bms.service.AuthorServiceTests;

import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.exception.ExceptionThrower;
import com.mobilise.bms.model.Author;
import com.mobilise.bms.repository.AuthorRepository;
import com.mobilise.bms.service.AuthorService;
import com.mobilise.bms.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class CreateAuthorTest {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ExceptionThrower exceptionThrower;

    @InjectMocks
    private AuthorService authorService;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        authorService = new AuthorService();

        ReflectionTestUtils.setField(authorService, "authorRepository", authorRepository);
        ReflectionTestUtils.setField(authorService, "exceptionThrower", exceptionThrower);
        ReflectionTestUtils.setField(authorService, "modelMapper", modelMapper);

        author = TestDataUtil.createSampleAuthors().get(0);
        authorDTO = modelMapper.map(author, AuthorDTO.class);

        when(modelMapper.map(any(), eq(AuthorDTO.class))).thenReturn(authorDTO);
        when(modelMapper.map(any(), eq(Author.class))).thenReturn(author);
    }

    @Test
    public void testCreate_ValidAuthor_SuccessfulCreation() {
        // Mock
        when(authorRepository.save(author)).thenReturn(author);

        // Act
        AuthorDTO result = authorService.createAuthor(authorDTO);

        // Assert
        assertEquals(authorDTO, result);
        verify(authorRepository, times(1)).save(author);
    }
}
