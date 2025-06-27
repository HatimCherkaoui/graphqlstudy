package com.hatimcherkaoui.graphqlstudy.controller;

import com.hatimcherkaoui.graphqlstudy.domain.entity.Book;
import com.hatimcherkaoui.graphqlstudy.domain.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    private final BookRepository repo;

    public BookController(BookRepository repo) {
        this.repo = repo;
    }

    @QueryMapping
    public List<Book> books(@Argument String author) {
        return (author == null) ? repo.findAll() : repo.findByAuthor(author);
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return repo.findById(id).orElse(null);
    }

    @MutationMapping
    public Book addBook(@Argument String title, @Argument String author) {
        return repo.save(new Book(title, author));
    }
}