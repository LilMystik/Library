package com.library.service.book;

import com.library.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book saveBook(Book book);
    Book findById(Long id);
    Book findByName(String titleName);
    Book updateBook(Book book);
    void deleteBookByTitle(String title);
    Book findBookByTitleAndListNumber(String title,int listNumber);
}
