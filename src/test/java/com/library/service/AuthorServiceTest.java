package com.library.service;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import com.library.service.author.impl.AuthorServiceImpl;
import com.library.service.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class AuthorServiceTest {

  @Mock
  AuthorRepository authorRepository;
  @InjectMocks
  AuthorServiceImpl authorService;
  @Mock
  Cache authorCache;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getAllAuthorsTest() {
    List<Author> authors = Arrays.asList(new Author(), new Author());
    when(authorRepository.findAll()).thenReturn(authors);
    List<Author> result = authorService.getAllAuthors();
    assertEquals(authors, result);
    verify(authorRepository, times(1)).findAll();
  }

  @Test
  void saveAuthorTest() {
    Author author = new Author();
    when(authorRepository.save(author)).thenReturn(author);
    Author result = authorService.addAuthor(author);
    assertEquals(result, author);
    verify(authorRepository, times(1)).save(author);
    verify(authorCache, times(1)).put(author.getName(), author);
  }

  @Test
  void saveAuthorsTest() {
    List<Author> authors = Arrays.asList(new Author(), new Author());
    authorService.addAuthors(authors);
    verify(authorRepository, times(0)).save(any(Author.class));
  }

  @Test
  void getAuthorByTitleTest() {
    Author author = new Author();
    when(authorRepository.findAuthorByName(anyString())).thenReturn(author);
    Author result = authorService.getAuthorByName("Marcus");
    assertEquals(result, author);
    verify(authorRepository, times(1)).findAuthorByName(anyString());
  }

  @Test
  void getAuthorByIdTest() {
    Author author = new Author();
    when(authorRepository.findAuthorById(anyLong())).thenReturn(author);
    Author result = authorService.getAuthorById(13L);
    assertEquals(result, author);
    verify(authorRepository, times(2)).findAuthorById(anyLong());
  }

  @Test
  void updateAuthorTest() {
    Author author = new Author();
    author.setName("Name");
    when(authorRepository.findAuthorByName(author.getName())).thenReturn(author);
    when(authorRepository.save(author)).thenReturn(author);
    Author result = authorService.updateAuthor(author);
    assertEquals(result, author);
    verify(authorRepository, times(0)).saveAndFlush(author);
  }

  @Test
  void deleteAuthorTest() {
    when(authorRepository.findAuthorById(13L)).thenReturn(new Author());
    authorService.deleteAuthorById(13L);
    verify(authorRepository, times(1)).deleteById(13L);
  }
}
