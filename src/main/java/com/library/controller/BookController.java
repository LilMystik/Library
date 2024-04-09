package com.library.controller;

import com.library.model.Book;
import com.library.service.book.BookService;
import lombok.AllArgsConstructor;
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
    public Book getBookById(@PathVariable Long id)
{
    return bookService.findById(id);
}
@GetMapping("find_by_name")
    public Book getBookByName(@RequestParam String titleName)
{
    return bookService.findByName(titleName);
}
@PostMapping("save")
    public Book saveBook(@RequestBody Book book)
{
    return bookService.saveBook(book);
}
@PutMapping("update")
    public Book updateBook(@RequestBody Book book)
{
    return  bookService.updateBook(book);
}
@DeleteMapping("delete/{title}")
    public void  deleteBook(@PathVariable String title)
{
    bookService.deleteBookByTitle(title);
}

@GetMapping("findBookByTitleAndList")
    public Book findBookByTitleAndListNumber(@RequestParam String title,@RequestParam int listNumber)
{
    return bookService.findBookByTitleAndListNumber(title,listNumber);
}
}
