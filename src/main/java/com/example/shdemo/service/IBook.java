package com.example.shdemo.service;

import com.example.shdemo.domain.Book;
import com.example.shdemo.domain.Person;

import javax.persistence.PersistenceContext;
import java.util.List;

public interface IBook {


    Long addClient(Person person);
    List<Person> getAllClients();
    void deleteClient(Person person);
    Person findClientById(Long id);

    Long addBook(Book book);
    void deleteBook(Book book);
    List<Book> getAvailavleBooks();
    void disposeBook(Person person, Book book);
    Book findBookById(Long id);
    void updateBook(Book oldBook, Book newBook);

    List<Book> getOwnedBooks(Person person);
    void sellBook(Long personId, Long bookId);

}
