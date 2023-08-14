package com.mobilise.bms.service.GenreServiceTests;

import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.dto.GenreDTO;
import com.mobilise.bms.exception.ExceptionThrower;
import com.mobilise.bms.exception.GeneralAppException;
import com.mobilise.bms.model.Author;
import com.mobilise.bms.model.Genre;
import com.mobilise.bms.repository.GenreRepository;
import com.mobilise.bms.service.AuthorService;
import com.mobilise.bms.service.GenreService;
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
public class CreateGenreTest {
    @Mock
    private GenreRepository genreRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ExceptionThrower exceptionThrower;

    @Mock
    private Verifier verifier;

    @InjectMocks
    private GenreService genreService;

    private Genre genre;
    private GenreDTO genreDTO;
    private final String API_LINK = "/api/v1/genres";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        genreService = new GenreService();

        ReflectionTestUtils.setField(genreService, "genreRepository", genreRepository);
        ReflectionTestUtils.setField(genreService, "exceptionThrower", exceptionThrower);
        ReflectionTestUtils.setField(genreService, "modelMapper", modelMapper);
        ReflectionTestUtils.setField(genreService, "verifier", verifier);

        genre = new Genre();
        genre.setId(1L);
        genre.setName("Genre 1");

        genreDTO = new GenreDTO();
        genreDTO.setId(1L);
        genreDTO.setName("Genre 1");


    }

    @Test
    public void testCreate_ValidGenre_SuccessfulCreation() {
        // Mock
        when(modelMapper.map(any(), eq(GenreDTO.class))).thenReturn(genreDTO);
        when(modelMapper.map(any(), eq(Genre.class))).thenReturn(genre);

        when(genreRepository.existsByNameIgnoreCase(genre.getName())).thenReturn(false);
        when(genreRepository.save(genre)).thenReturn(genre);

        // Act
        GenreDTO result = genreService.createGenre(genreDTO, API_LINK);

        // Assert
        assertEquals(genreDTO, result);
        verify(genreRepository, times(1)).save(genre);
        verify(genreRepository, times(1)).existsByNameIgnoreCase(genre.getName());
    }

    @Test
    public void testCreate_GenreAlreadyExists_ThrowException() {
        // Mock
        when(genreRepository.existsByNameIgnoreCase(genre.getName())).thenReturn(true);
        doThrow(new GeneralAppException(HttpStatus.CONFLICT, "9999", "test", API_LINK))
                .when(exceptionThrower)
                .throwGenreAlreadyExistsException(API_LINK);

        // Act & Assert
        assertThrows(GeneralAppException.class, () -> genreService.createGenre(genreDTO, API_LINK));

    }
}
