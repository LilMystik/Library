package com.library.controller;

import com.library.exception.MyExceptionHandler;
import com.library.model.Book;
import com.library.service.CounterService;
import com.library.service.book.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MyExceptionHandler
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
  private BookService bookService;
  private static  final  String msg = "Counter : {}";
  private CounterService counterService;
  private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

  private synchronized void  logCounter() {
    LOGGER.info(msg, counterService.incrementAndGet());
  }

  @GetMapping("list")
  public List<Book> getBooks() {
    logCounter();
    return bookService.getBooks();
  }

  @GetMapping("find_by_id/{id}")
  public Book getBookById(@PathVariable Long id) {
    logCounter();
    return bookService.findById(id);
  }

  @GetMapping("find_by_name")
  public Book getBookByName(@RequestParam String titleName) {
    logCounter();
    return bookService.findByName(titleName);
  }

  @PostMapping("save")
  public Book saveBook(@RequestBody Book book) {
    logCounter();
    return bookService.saveBook(book);
  }

  @PostMapping("save_books")
  public List<Book> saveBooks(@RequestBody List<Book> books) {
    logCounter();
    return bookService.saveBooks(books); }

  @PutMapping("update")
  public Book updateBook(@RequestBody Book book) {
    logCounter();
    return  bookService.updateBook(book);
  }

  @DeleteMapping("delete/{title}")
  public void  deleteBook(@PathVariable String title) {
    logCounter();
    bookService.deleteBookByTitle(title);
  }

  @GetMapping("findBookByYearAndList")
  public List<Book> findBookByTitleAndListNumber(@RequestParam int year,
                                                 @RequestParam int listNumber) {
    logCounter();
    return bookService.findBookByYearAndListNumber(year, listNumber);
  }
}
