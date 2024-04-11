package com.library.controller;

import com.library.model.Genre;
import com.library.service.genre.GenreService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
@AllArgsConstructor
public class GenreController {
private GenreService genreService;
@GetMapping(value="list")
    public List<Genre> getAllGenres()
{
    return genreService.getAllGenres();
}

@GetMapping(value="find/{genreId}")
    public Genre getGenreById(@PathVariable Long genreId)
{
    return genreService.findGenreById(genreId);
}
@PostMapping(value="save")
    public Genre addGenre(@RequestBody Genre genre)
{
    if(genre == null){
        return null;
    }
    return genreService.saveGenre(genre);
}
@PutMapping(value="update")
    public Genre updateGenre(@RequestBody Genre genre)
{
    return genreService.updateGenre(genre);
}
@Transactional
@DeleteMapping(value="delete/{genreId}")
    public void deleteGenre(@PathVariable Long genreId)
{
    genreService.deleteGenre(genreId);
}
}
