package com.mobilise.bms.controller.AuthorControllerTests;

import com.mobilise.bms.controller.AuthorController;
import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.service.AuthorService;
import com.mobilise.bms.util.TestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class GetAllAuthorsTest {
    @Mock
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void testGetAllAuthors_ReturnsSuccessStatus() throws Exception {

        Pageable pageable = PageRequest.of(0, 10);
        Page<AuthorDTO> authorPage = new PageImpl<>(TestDataUtil.createSampleAuthorDtos());

        when(authorService.getAllAuthors(pageable)).thenReturn(authorPage);

        mockMvc.perform(get("/api/v1/authors?page=0&pageSize=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
