package com.example.shdemo.service;

import com.example.shdemo.domain.Book;

import java.util.List;

public interface IBook {

    void addBook(Book book);

    void deleteBook(Book book);

    List<Book> getBooks();

    void updateBook(Book oldBook, Book newBook);


}
