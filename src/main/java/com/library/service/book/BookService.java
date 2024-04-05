package com.library.service.book;

import com.library.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book saveBook(Book book);
    Book findById(Long id);
    Book findByName(String titleName);
    void deleteBook(Long id);
    Book updateBook(Book book);
}
