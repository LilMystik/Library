package com.library.service;

import com.library.model.Genre;
import com.library.repository.GenreRepository;
import com.library.service.cache.Cache;
import com.library.service.genre.impl.GenreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

class GenreServiceTest {
  @Mock
  GenreRepository genreRepository;
  @InjectMocks
  GenreServiceImpl genreService;
  @Mock
  Cache genreCache;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getAllGenres() {
    List<Genre> genres = Arrays.asList(new Genre(), new Genre());
    when(genreRepository.findAll()).thenReturn(genres);
    List<Genre> result = genreService.getAllGenres();
    assertEquals(result, genres);
    verify(genreRepository, times(1)).findAll();
  }

  @Test
  void saveGenreTest() {
    Genre genre = new Genre();
    when(genreRepository.save(genre)).thenReturn(genre);
    Genre result = genreService.saveGenre(genre);
    assertEquals(result, genre);
    verify(genreRepository, times(1)).save(genre);
    verify(genreCache, times(1)).put(genre.getName(), genre);
  }

  @Test
  void getGenreByIdTest() {
    Genre genre = new Genre();
    when(genreRepository.findGenreById(anyLong())).thenReturn(genre);
    Genre result = genreService.findGenreById(13L);
    assertEquals(result, genre);
    verify(genreRepository, times(1)).findGenreById(anyLong());
  }

  @Test
  void updateGenreTest() {
    Genre genre = new Genre();
    genre.setId(13L);
    when(genreRepository.findGenreById(genre.getId())).thenReturn(genre);
    when(genreRepository.save(genre)).thenReturn(genre);
    Genre result = genreService.updateGenre(genre);
    assertEquals(result, genre);
    verify(genreRepository, times(1)).save(genre);
  }

  @Test
  void deleteGenreTest() {
    when(genreRepository.findGenreById(13L)).thenReturn(new Genre());
    genreService.deleteGenre(13L);
    verify(genreRepository, times(1)).deleteById(13L);
  }
}
