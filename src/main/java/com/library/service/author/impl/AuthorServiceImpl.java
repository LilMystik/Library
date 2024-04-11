package com.library.service.author.impl;

import com.library.model.Author;
import com.library.repository.AuthorRepository;
import com.library.service.author.AuthorService;
import com.library.service.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final Cache authorCache;
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, Cache cacheAuthor)
    {
        this.authorRepository=authorRepository;
        this.authorCache =cacheAuthor;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findAuthorById(id);
    }

    @Override
    public Author getAuthorByName(String name) {
        Author author=(Author) authorCache.get(name);
        if(author!=null)
        {
            log.info("(cache)Author find by name: {}",name);
            return author;
        }
        author=authorRepository.findAuthorByName(name);
        log.info("(rep)Author find by name:{}",name);
        authorCache.put(name,author);
        return author;
    }

    @Override
    public Author addAuthor(Author author) {
        authorCache.put(author.getName(),author);
        log.info("Saving author:{}",author);
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        Author existingAuthor= authorRepository.findAuthorByName(author.getName());
        if(existingAuthor!=null)
        {
            existingAuthor.setName(author.getName());
            existingAuthor.setId(author.getId());
            existingAuthor.setBooks(author.getBooks());
            authorCache.put(existingAuthor.getName(),existingAuthor);
        }
        assert existingAuthor!=null;
        log.info("Updating author");
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        Author author=getAuthorById(id);
        authorCache.remove(author.getName());
        authorRepository.deleteById(id);
        log.info("Removing author");
    }

}
