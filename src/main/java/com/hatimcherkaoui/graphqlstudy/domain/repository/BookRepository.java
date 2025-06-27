package com.hatimcherkaoui.graphqlstudy.domain.repository;

import com.hatimcherkaoui.graphqlstudy.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
}