package com.library.controller;

import com.library.model.Author;
import com.library.service.author.AuthorService;
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

class AuthorControllerTest {
  @InjectMocks
  private AuthorController authorController;

  @Mock
  private AuthorService authorService;

 MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
  }

  @Test
  void testGetAuthors() throws  Exception {
    when(authorService.getAllAuthors()).thenReturn(Arrays.asList(new Author(), new Author()));
    mockMvc.perform(get("/author/list").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void testSaveAuthor() throws Exception {
    when(authorService.addAuthor(any(Author.class))).thenReturn(new Author());
    mockMvc.perform(post("/author/save").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
  }

  @Test
  void testGetAuthorByName() throws  Exception {
    when(authorService.getAuthorByName(anyString())).thenReturn(new Author());
    mockMvc.perform(get("/author/findByName/Markus").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void testGetAuthorById() throws Exception {
    when(authorService.getAuthorById(anyLong())).thenReturn(new Author());
    mockMvc.perform(get("/author/find/6").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void testUpdateAuthor() throws Exception {
    when(authorService.updateAuthor(any(Author.class))).thenReturn(new Author());
    mockMvc.perform(put("/author/update").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().isOk());
  }

  @Test
  void testDeleteAuthor() throws  Exception {
    mockMvc.perform(delete("/author/delete/120").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void testSaveAuthors() throws  Exception {
    doNothing().when(authorService).addAuthors(anyList());
    mockMvc.perform(post("/author/saveAuthors")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[{},{}]"))
            .andExpect(status().isOk());
  }

}
