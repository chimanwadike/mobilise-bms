package com.mobilise.bms.controller.BookControllerTests;

import com.mobilise.bms.controller.BookController;
import com.mobilise.bms.dto.AuthorDTO;
import com.mobilise.bms.dto.BookDTO;
import com.mobilise.bms.service.BookService;
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
public class GetAllBooksTest {
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
    public void testGetAllBooks_ReturnsSuccessStatus() throws Exception {

        Pageable pageable = PageRequest.of(0, 10);
        Page<BookDTO> bookPage = new PageImpl<>(TestDataUtil.createSampleBookDtos());

        when(bookService.getAllBooks(pageable)).thenReturn(bookPage);

        mockMvc.perform(get("/api/v1/books?page=0&pageSize=10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
