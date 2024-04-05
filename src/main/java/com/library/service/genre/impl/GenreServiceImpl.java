package com.library.service.genre.impl;

import com.library.model.Genre;
import com.library.repository.GenreRepository;
import com.library.service.genre.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findGenreById(id);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {
genreRepository.deleteById(id);
    }
}
