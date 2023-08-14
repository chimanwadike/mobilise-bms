package com.mobilise.bms.controller.BookControllerTests;

import com.mobilise.bms.controller.BookController;
import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.service.BookService;
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
public class CreateBookTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testCreateBook_ReturnsSuccessStatus() throws Exception {

        BookDTO book = TestDataUtil.createSampleBookDtos().get(0);

        // Mock the service behavior
        when(bookService.createBook(any(), any())).thenReturn(book);

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON).content(TestDataUtil.asJsonString(book)))
                        .andExpect(status().isCreated());
    }
}
