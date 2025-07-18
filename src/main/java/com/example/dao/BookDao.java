package com.example.dao;

import com.example.model.Book;
import java.util.List;

public interface BookDao {
    void save(Book book);
    Book findById(int id);
    List<Book> findAll();
    void update(Book book);
    void deleteById(int id);
}