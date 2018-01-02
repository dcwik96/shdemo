package com.example.shdemo.service;

import com.example.shdemo.domain.Book;
import com.example.shdemo.domain.Person;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Long addClient(Person person) {
        person.setId(null);
        return (Long) sessionFactory.getCurrentSession().save(person);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Person> getAllClients() {
        return sessionFactory.getCurrentSession().getNamedQuery("person.all").list();
    }

    @Override
    public void deleteClient(Person person) {
        person = (Person) sessionFactory.getCurrentSession().get(Person.class,
                person.getId());

        // lazy loading here
        for (Book book : person.getBooks()) {
            book.setSold(false);
            sessionFactory.getCurrentSession().update(book);
        }
        sessionFactory.getCurrentSession().delete(person);
    }

    @Override
    public Person findClientById(Long id) {
        return (Person) sessionFactory.getCurrentSession().get(Person.class, id);
    }


    @Override
    public Long addBook(Book book) {
        book.setId(null);
        return (Long) sessionFactory.getCurrentSession().save(book);
    }

    @Override
    public void deleteBook(Book book) {
        book = (Book) sessionFactory.getCurrentSession().get(Book.class, book.getId());
        sessionFactory.getCurrentSession().delete(book);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Book> getAvailavleBooks() {
        return sessionFactory.getCurrentSession().getNamedQuery("book.unsold").list();
    }

    @Override
    public void disposeBook(Person person, Book book) {
        person = (Person) sessionFactory.getCurrentSession().get(Person.class, person.getId());
        book = (Book) sessionFactory.getCurrentSession().get(Book.class, book.getId());

        Book toRemove = null;
        // lazy loading here (person.getBooks)
        for (Book aBook : person.getBooks())
            if (aBook.getId().compareTo(book.getId()) == 0) {
                toRemove = aBook;
                break;
            }

        if (toRemove != null)
            person.getBooks().remove(toRemove);

        book.setSold(false);
    }

    @Override
    public Book findBookById(Long id) {
        return (Book) sessionFactory.getCurrentSession().get(Book.class, id);
    }

    @Override
    public void updateBook(Book oldBook, Book newBook) {
        oldBook = (Book) sessionFactory.getCurrentSession().get(Book.class, oldBook.getId());

        oldBook.setTitle(newBook.getTitle());
        oldBook.setAuthor(newBook.getAuthor());
        oldBook.setPrize(newBook.getPrize());

        sessionFactory.getCurrentSession().update(oldBook);
    }

    @Override
    public List<Book> getOwnedBooks(Person person) {person = (Person) sessionFactory.getCurrentSession().get(Person.class,
            person.getId());
        // lazy loading here - try this code without (shallow) copying
        List<Book> books = new ArrayList<Book>(person.getBooks());
        return books;
    }

    @Override
    public void sellBook(Long personId, Long bookId) {
            Person person = (Person) sessionFactory.getCurrentSession().get(
                    Person.class, personId);
            Book book = (Book) sessionFactory.getCurrentSession()
                    .get(Book.class, bookId);
            book.setSold(true);
            person.getBooks().add(book);
    }


}
