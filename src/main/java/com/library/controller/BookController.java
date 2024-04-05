package com.library.controller;

import com.library.model.Book;
import com.library.service.book.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {
private BookService bookService;
@GetMapping("list")
    public List<Book> getBooks()
{
    return bookService.getBooks();
}
@GetMapping("find_by_id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id)
{
    return ResponseEntity.ok(bookService.findById(id));
}
@GetMapping("find_by_name")
    public ResponseEntity<Book> getBookByName(@RequestParam String titleName)
{
    return ResponseEntity.ok(bookService.findByName(titleName));
}
@PostMapping("save")
    public ResponseEntity<Book> saveBook(@RequestBody Book book)
{
    return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.OK);
}
@PutMapping("update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book)
{
    return  new ResponseEntity<>(bookService.updateBook(book),HttpStatus.OK);
}
@DeleteMapping("delete/{id}")
    public HttpStatus   deleteBook(@PathVariable Long id)
{
    bookService.deleteBook(id);
    return HttpStatus.OK;
}
}
