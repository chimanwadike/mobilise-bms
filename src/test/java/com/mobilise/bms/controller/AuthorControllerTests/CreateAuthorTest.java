package com.mobilise.bms.controller.AuthorControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.mobilise.bms.util.TestDataUtil.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CreateAuthorTest {
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
    public void testCreateAuthor_ReturnsSuccessStatus() throws Exception {

        AuthorDTO author = TestDataUtil.createSampleAuthorDtos().get(0);

        // Mock the service behavior
        when(authorService.createAuthor(any())).thenReturn(author);

        mockMvc.perform(post("/api/v1/authors")
                        .contentType(MediaType.APPLICATION_JSON).content(TestDataUtil.asJsonString(author)))
                        .andExpect(status().isCreated());
    }
}
