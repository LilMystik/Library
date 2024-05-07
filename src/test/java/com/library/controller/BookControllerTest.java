package com.library.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.library.model.Book;
import com.library.service.book.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;

class BookControllerTest {

  @InjectMocks
  private BookController bookController;

  @Mock
  private BookService bookService;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
  }

  @Test
  void testGetBooks() throws  Exception {
    when(bookService.getBooks()).thenReturn(Arrays.asList(new Book(), new Book()));
    mockMvc.perform(get("/book/list").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void testSaveBook() throws Exception {
    when(bookService.saveBook(any(Book.class))).thenReturn(new Book());
    mockMvc.perform(post("/book/save").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
  }

  @Test
  void testGetBookByTitle() throws  Exception {
    when(bookService.findByName(anyString())).thenReturn(new Book());
    mockMvc.perform(get("/book/find_by_name?titleName=Wilds").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void testGetBookById() throws  Exception {
    when(bookService.findById(anyLong())).thenReturn(new Book());
    mockMvc.perform(get("/book/find_by_id/6").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void testUpdateAuthor() throws Exception {
    when(bookService.updateBook(any(Book.class))).thenReturn(new Book());
    mockMvc.perform(put("/book/update").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
  }

  @Test
  void testDeleteBook() throws Exception {
    mockMvc.perform(delete("/book/delete/Marcus").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

}