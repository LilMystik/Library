package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface BookRepository extends JpaRepository<Book,Long> {
    void deleteBookByTitle(String titleName);
    Book findBookById(Long id);
    Book findBookByTitle(String titleName);

    @Query(value="SELECT * FROM Books WHERE title=:titlename AND list_number=:list",nativeQuery = true)
    Book findBookByTitleAndAndListNumber(@Param("titlename") String title,@Param("list") int listnumber);
}
