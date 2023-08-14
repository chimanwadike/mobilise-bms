package com.mobilise.bms.repository;

import com.mobilise.bms.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Queries for books by all positional combination of title and author's first and last name
     * while accepting paging object
     * @param title
     * @param authorName
     * @param pageable
     * @return
     */

    @Query("SELECT DISTINCT b FROM Book b " +
            "JOIN b.authors a " +
            "WHERE" +
            "(:title IS NULL OR LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%')) ) " +
            "AND ((:authorName IS NULL OR " +
            "LOWER(CONCAT(a.firstName, ' ', a.lastName)) LIKE LOWER(CONCAT('%', :authorName, '%')) OR " +
            "LOWER(CONCAT(a.lastName, ' ', a.firstName)) LIKE LOWER(CONCAT('%', :authorName, '%')) OR " +
            "LOWER(a.firstName) LIKE LOWER(CONCAT('%', :authorName, '%')) OR " +
            "LOWER(a.lastName) LIKE LOWER(CONCAT('%', :authorName, '%'))))")
    Page<Book> searchByTitleOrAuthor(String title, String authorName, Pageable pageable);
}
