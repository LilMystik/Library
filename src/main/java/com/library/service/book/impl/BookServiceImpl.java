package com.library.service.book.impl;

import com.library.aspect.Logged;
import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.book.BookService;
import com.library.service.cache.Cache;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final Cache bookCache;

  @Autowired
  public BookServiceImpl(BookRepository bookRepository, Cache bookCache) {
    this.bookRepository = bookRepository;
    this.bookCache = bookCache;
  }

  @Override
  @Logged
  public List<Book> getBooks() {
    log.info("Getting books");
    return bookRepository.findAll();
  }

  @Override
  @Logged
  public Book saveBook(Book book) {
    bookRepository.save(book);
    bookCache.put(book.getTitle(), book);
    log.info("Saving book {}", book);
    return book;
  }

  @Override
  @Logged
  public Book findById(Long id) {
    return bookRepository.findBookById(id);
  }

  @Override
  @Logged
  public Book findByName(String titleName) {
    Book book = (Book) bookCache.get(titleName);
    if (book != null) {
      log.info("Getting book by title");
      return book;
    }
    log.info("Getting book by title: {}", titleName);
    log.info("Book found:{}", bookRepository.findBookByTitle(titleName));
    book = bookRepository.findBookByTitle(titleName);
    return book;
  }

  @Override
  @Logged
  public void deleteBookByTitle(String title) {
    bookRepository.deleteBookByTitle(title);
    bookCache.remove(title);
    log.info("Book deleted by title: {}", title);
  }

  @Override
  @Logged
  public Book updateBook(Book book) {
    Book existingBook = bookRepository.findBookByTitle(book.getTitle());
    if (existingBook != null) {
      existingBook.setId(book.getId());
      existingBook.setAuthor(book.getAuthor());
      existingBook.setTitle(book.getTitle());
      existingBook.setGenres(book.getGenres());
      existingBook.setListNumber(book.getListNumber());
      existingBook.setYearOfPublish(book.getYearOfPublish());
      bookCache.put(existingBook.getTitle(), existingBook);
    }
    assert existingBook != null;
    log.info("Book updated:{}", existingBook);
    return bookRepository.save(existingBook);

  }

  @Override
  @Logged
  public List<Book> findBookByYearAndListNumber(int year, int listNumber) {
    return bookRepository.findBookByYearAndAndListNumber(year, listNumber);
  }
}
