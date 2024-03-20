package com.polarbookshop.catalogservice.persistence;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookRepository implements BookRepository {

    private static final Map<String,Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return null;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return false;
    }

    @Override
    public Book save(Book book) {
        return null;
    }

    @Override
    public void deleteByIsbn(String isbn) {

    }
}
