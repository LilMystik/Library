package com.library.service.genre.impl;

import com.library.model.Book;
import com.library.model.Genre;
import com.library.repository.BookRepository;
import com.library.repository.GenreRepository;
import com.library.service.cache.Cache;
import com.library.service.genre.GenreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final Cache genreCache;

    public GenreServiceImpl(Cache genreCache,BookRepository bookRepository, GenreRepository genreRepository)
    {
        this.genreRepository=genreRepository;
        this.genreCache=genreCache;
        this.bookRepository=bookRepository;
    }
    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre saveGenre(Genre genre) {
        genreCache.put(genre.getName(),genre);
        log.info("Saving genre");
        return genreRepository.save(genre);
    }

    @Override
    public Genre findGenreById(Long id) {
        return genreRepository.findGenreById(id);
    }

    @Override
    public Genre updateGenre(Genre genre) {
        Genre existingGenre=genreRepository.findGenreById(genre.getId());
        if(existingGenre!=null)
        {
            existingGenre.setId(genre.getId());
            existingGenre.setName(genre.getName());
            existingGenre.setBooks(genre.getBooks());
            genreCache.put(existingGenre.getName(),existingGenre);
        }
        assert existingGenre!=null;
        log.info("Genre have been updated");
        return genreRepository.save(genre);
    }

    @Override
    public void deleteGenre(Long id) {

        Genre genre=genreRepository.findGenreById(id);
        for(Book book:genre.getBooks())
        {
            book.getGenres().remove(genre);
        }
        bookRepository.saveAll(genre.getBooks());
        genreCache.remove(genre.getName());
        genreRepository.deleteById(id);

    }
}
