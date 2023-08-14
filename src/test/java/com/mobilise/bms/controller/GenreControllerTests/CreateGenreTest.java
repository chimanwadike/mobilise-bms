package com.mobilise.bms.controller.GenreControllerTests;

import com.mobilise.bms.controller.GenreController;
import com.mobilise.bms.dto.GenreDTO;
import com.mobilise.bms.service.GenreService;
import com.mobilise.bms.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CreateGenreTest {
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
    public void testCreateAuthor_ReturnsSuccessStatus() throws Exception {

        GenreDTO genre = TestDataUtil.createSampleGenreDtos().get(0);

        // Mock the service behavior
        when(genreService.createGenre(any(), any())).thenReturn(genre);

        mockMvc.perform(post("/api/v1/genres")
                        .contentType(MediaType.APPLICATION_JSON).content(TestDataUtil.asJsonString(genre)))
                        .andExpect(status().isCreated());
    }
}
