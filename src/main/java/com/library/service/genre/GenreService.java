package com.library.service.genre;

import com.library.model.Genre;
import java.util.List;

public interface GenreService {

  List<Genre> getAllGenres();

  Genre saveGenre(Genre genre);

  Genre findGenreById(Long id);

  Genre updateGenre(Genre genre);

  void deleteGenre(Long id);
}
