package com.example.shdemo.service;

import com.example.shdemo.domain.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class BookManager implements IBook {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addBook(Book book) {
        book.setId(null);
        sessionFactory.getCurrentSession().persist(book);

    }

    @Override
    public void deleteBook(Book book) {
        book = (Book) sessionFactory.getCurrentSession().get(Book.class, book.getId());
        sessionFactory.getCurrentSession().delete(book);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getBooks() {
        return sessionFactory.getCurrentSession().getNamedQuery("book.all").list();
    }

    @Override
    public void updateBook(Book oldBook, Book newBook) {
        oldBook = (Book) sessionFactory.getCurrentSession().get(Book.class, oldBook.getId());

        oldBook.setTitle(newBook.getTitle());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPrize(newBook.getPrize());

        sessionFactory.getCurrentSession().update(oldBook);
    }


}
