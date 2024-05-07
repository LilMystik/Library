package com.library.controller;

import com.library.exception.MyExceptionHandler;
import com.library.model.Genre;
import com.library.service.CounterService;
import com.library.service.genre.GenreService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@MyExceptionHandler
@RestController
@RequestMapping("/genre")
@AllArgsConstructor
public class GenreController {
  private GenreService genreService;
  private static  final  String msg = "Counter : {}";
  private CounterService counterService;
  private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

  private synchronized void logCounter() {
    LOGGER.info(msg, counterService.incrementAndGet());
  }

  @GetMapping(value = "list")
  public List<Genre> getAllGenres() {
    logCounter();
    return genreService.getAllGenres();
  }

  @GetMapping(value = "find/{genreId}")
  public Genre getGenreById(@PathVariable Long genreId) {
    logCounter();
    return genreService.findGenreById(genreId);
  }

  @PostMapping(value = "save")
  public Genre addGenre(@RequestBody Genre genre) {
    logCounter();
    if (genre == null) {
      return null;
    }
    return genreService.saveGenre(genre);
  }

  @PostMapping("saveGenres")
  public List<Genre> addGenres(@RequestBody List<Genre> genres) {
    logCounter();
    return genreService.saveGenres(genres);
  }

  @PutMapping(value = "update")
  public Genre updateGenre(@RequestBody Genre genre) {
    logCounter();
    return genreService.updateGenre(genre);
  }

  @Transactional
  @DeleteMapping(value = "delete/{genreId}")
  public void deleteGenre(@PathVariable Long genreId) {
    logCounter();
    genreService.deleteGenre(genreId);
  }
}
