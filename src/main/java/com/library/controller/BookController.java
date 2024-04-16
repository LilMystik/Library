package com.library.controller;

import com.library.exception.MyExceptionHandler;
import com.library.model.Book;
import com.library.service.book.BookService;
import java.util.List;
import lombok.AllArgsConstructor;
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

  @GetMapping("list")
  public List<Book> getBooks() {
    return bookService.getBooks();
  }

  @GetMapping("find_by_id/{id}")
  public Book getBookById(@PathVariable Long id) {
    return bookService.findById(id);
  }

  @GetMapping("find_by_name")
  public Book getBookByName(@RequestParam String titleName) {
    return bookService.findByName(titleName);
  }

  @PostMapping("save")
  public Book saveBook(@RequestBody Book book) {
    return bookService.saveBook(book);
  }

  @PutMapping("update")
  public Book updateBook(@RequestBody Book book) {
    return  bookService.updateBook(book);
  }

  @DeleteMapping("delete/{title}")
  public void  deleteBook(@PathVariable String title) {
    bookService.deleteBookByTitle(title);
  }

  @GetMapping("findBookByYearAndList")
  public List<Book> findBookByTitleAndListNumber(@RequestParam int year,
                                                 @RequestParam int listNumber) {
    return bookService.findBookByYearAndListNumber(year, listNumber);
  }
}
