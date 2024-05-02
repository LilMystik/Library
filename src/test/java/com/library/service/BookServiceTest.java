package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.service.book.impl.BookServiceImpl;
import com.library.service.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;

class BookServiceTest {
  @Mock
  BookRepository bookRepository;
  @InjectMocks
  BookServiceImpl bookService;
  @Mock
  Cache bookCache;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void getAllBooksTest() {
    List<Book> books = Arrays.asList(new Book(), new Book());
    when(bookRepository.findAll()).thenReturn(books);
    List<Book> result = bookService.getBooks();
    assertEquals(result, books);
    verify(bookRepository, times(1)).findAll();
  }

  @Test
  void getBookByYearAndListNumberTest() {
    int year = 204;
    int listNumber = 110;
    List<Book> books = Arrays.asList(new Book(), new Book());
    when(bookRepository.findBookByYearAndAndListNumber(year, listNumber)).thenReturn(books);
    List<Book> result = bookService.findBookByYearAndListNumber(year, listNumber);
    assertEquals(books, result);
    verify(bookRepository, times(1)).findBookByYearAndAndListNumber(year, listNumber);
  }

  @Test
  void saveBookTest() {
    Book book = new Book();
    when(bookRepository.save(book)).thenReturn(book);
    Book result = bookService.saveBook(book);
    assertEquals(result, book);
    verify(bookRepository, times(1)).save(book);
    verify(bookCache, times(1)).put(book.getTitle(), book);
  }

  @Test
  void getBookByTitleTest() {
    Book book = new Book();
    when(bookRepository.findBookByTitle(anyString())).thenReturn(book);
    Book result = bookService.findByName("Marcus");
    assertEquals(result, book);
    verify(bookRepository, times(2)).findBookByTitle(anyString());
  }

  @Test
  void getBookByIdTest() {
    Book book = new Book();
    when(bookRepository.findBookById(anyLong())).thenReturn(book);
    Book result = bookService.findById((long) 13);
    assertEquals(result, book);
    verify(bookRepository, times(1)).findBookById(anyLong());
  }

  @Test
  void updateBookTest() {
  Book book = new Book();
  book.setTitle("Title");
  when(bookRepository.findBookByTitle(book.getTitle())).thenReturn(book);
when(bookRepository.save(book)).thenReturn(book);
Book result = bookService.updateBook(book);
assertEquals(result, book);
verify(bookRepository, times(1)).save(book);
  }

  @Test
  void saveBooksTest() {
    List<Book> books = Arrays.asList(new Book(), new Book());
    bookService.saveBooks(books);
    verify(bookRepository, times(0)).save(any(Book.class));
  }

  @Test
  void deleteBookTest() {
    String name = "Title";
    when(bookRepository.findBookByTitle(name)).thenReturn(new Book());
    bookService.deleteBookByTitle(name);
    verify(bookRepository, times(1)).deleteBookByTitle(name);
  }
}