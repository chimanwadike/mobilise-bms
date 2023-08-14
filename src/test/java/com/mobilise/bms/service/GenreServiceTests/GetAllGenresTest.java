package com.mobilise.bms.service.GenreServiceTests;

import com.mobilise.bms.dto.GenreDTO;
import com.mobilise.bms.model.Genre;
import com.mobilise.bms.repository.GenreRepository;
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
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetAllGenresTest {
    @Mock
    private GenreRepository genreRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private GenreService genreService;

    @Test
    public void testGetAllGenres_ReturnsListOfGenres(){
        // Create a pageable object for testing
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));

        Page<Genre> genrePage = new PageImpl<>(TestDataUtil.createSampleGenres());

        // Mock the repository
        when(genreRepository.findAll(pageable)).thenReturn(genrePage);

        // Test the service method
        Page<GenreDTO> resultGenres = genreService.getAllGenres(pageable);

        // Verify that the repository method was called
        verify(genreRepository).findAll(pageable);

        // Assert the result
        assertThat(resultGenres)
                .isNotNull()
                .hasSize(TestDataUtil.createSampleGenres().size());
    }

    @Test
    public void testGetAllGenres_ReturnsEmptyList(){
        // Create a pageable object for testing
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));

        Page<Genre> genrePage = new PageImpl<>(Collections.emptyList());

        // Mock the repository
        when(genreRepository.findAll(pageable)).thenReturn(genrePage);

        // Test the service method
        Page<GenreDTO> resultGenres = genreService.getAllGenres(pageable);

        // Verify that the repository method was called
        verify(genreRepository).findAll(pageable);

        // Assert the result
        assertThat(resultGenres)
                .isEmpty();
    }
}
