package com.library.service.book.impl;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;
    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    public Book findByName(String titleName) {
        return bookRepository.findBookByName(titleName);
    }

    @Override
    public void deleteBook(Long id) {
bookRepository.deleteById(id);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }
}
