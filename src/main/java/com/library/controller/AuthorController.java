package com.library.controller;

import com.library.model.Author;
import com.library.service.author.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/author")
@AllArgsConstructor
public class    AuthorController {
    private AuthorService authorService;

    @GetMapping(value = "list")
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping(value = "find/{authorId}", produces = "application/json")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getAuthorById(authorId);
    }

    @GetMapping(value = "findByName/{authorName}",produces = "application/json")
    public Author getAuthorByName(@PathVariable String authorName){return authorService.getAuthorByName(authorName);}
    @PostMapping("save")
        public Author addAuthor(@RequestBody Author author)
        {
            return authorService.addAuthor(author);
        }
    @PutMapping("update")
    public Author updateAuthor(@RequestBody Author author)
    {
        return authorService.updateAuthor(author);
    }
    @DeleteMapping("delete/{authorId}")
    public void deleteAuthor(@PathVariable Long authorId)
    {
        authorService.deleteAuthorById(authorId);
    }
}