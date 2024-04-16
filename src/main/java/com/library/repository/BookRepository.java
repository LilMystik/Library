package com.library.repository;

import com.library.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
  /** Удаление книги.*/
  void deleteBookByTitle(String  titleName);

  Book findBookById(Long id);

  Book findBookByTitle(String titleName);

  @Query(value = "SELECT * FROM Books WHERE year_of_publish>:year"
          + " AND list_number>:list", nativeQuery = true)
  List<Book> findBookByYearAndAndListNumber(@Param("year") int year,
                                            @Param("list") int list);
}
