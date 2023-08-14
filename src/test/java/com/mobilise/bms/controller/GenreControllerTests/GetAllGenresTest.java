package com.mobilise.bms.controller.GenreControllerTests;

import com.mobilise.bms.controller.AuthorController;
import com.mobilise.bms.controller.GenreController;
import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.dto.GenreDTO;
import com.mobilise.bms.service.AuthorService;
import com.mobilise.bms.service.GenreService;
import com.mobilise.bms.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class GetAllGenresTest {
    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }

    @Test
    public void testGetAllGenres_ReturnsSuccessStatus() throws Exception {

        Pageable pageable = PageRequest.of(0, 10);
        Page<GenreDTO> genrePage = new PageImpl<>(TestDataUtil.createSampleGenreDtos());

        when(genreService.getAllGenres(pageable)).thenReturn(genrePage);

        mockMvc.perform(get("/api/v1/genres?page=0&pageSize=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
