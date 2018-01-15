package com.example.shdemo.service;

import com.example.shdemo.domain.Book;
import com.example.shdemo.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BookTest {

    @Autowired
    IBook bookManager;


    private final String NAME_1 = "Dawid";
    private final String NAME_2 = "Pawel";
    private final String NAME_3 = "Janek";

    Person person1 = new Person(NAME_1);
    Person person2 = new Person(NAME_2);
    Person person3 = new Person(NAME_3);

    private final String TITLE_1 = "Tytul1";
    private final Person AUTHOR_1 = person1;
    private final double PRICE_1 = 19.90;

    private final static String TITLE_2 = "QuoVadis";
    private final Person AUTHOR_2 = person2;
    private final static double PRICE_2 = 10.99;

    private final static String TITLE_3 = "Niebieska Mila";
    private final  Person AUTHOR_3 = person3;
    private final static double PRICE_3 = 99.99;

    Book book1 = new Book(null, TITLE_1, AUTHOR_1, PRICE_1);
    Book book2 = new Book(null, TITLE_2, AUTHOR_2, PRICE_2);
    Book book3 = new Book(null, TITLE_3, AUTHOR_3, PRICE_3);

    @Test
    public void addClientCheck() {
        bookManager.addClient(person1);

        List<Person> retrievedClients = bookManager.getAllClients();

        assertEquals(1, retrievedClients.size());
        assertEquals(NAME_1, retrievedClients.get(0).getFirstName());
    }


    @Test
    public void addBook() {
        Long bookId = bookManager.addBook(book1);

        Book retrievedBook = bookManager.findBookById(bookId);


        assertFalse(retrievedBook.getSold());
        assertEquals(TITLE_1, retrievedBook.getTitle());
        assertEquals(AUTHOR_1, retrievedBook.getAuthor());
        assertEquals(PRICE_1, retrievedBook.getPrize(), 0.01);
    }

    @Test
    public void deleteBook() {
        bookManager.addBook(book1);
        bookManager.addBook(book2);

        bookManager.deleteBook(book1);

        List<Book> retrievedBooks = bookManager.getAvailavleBooks();

        assertEquals(1, retrievedBooks.size());
        assertEquals(TITLE_2, retrievedBooks.get(0).getTitle());
        assertEquals(AUTHOR_2, retrievedBooks.get(0).getAuthor());
        assertEquals(PRICE_2, retrievedBooks.get(0).getPrize(), 0.01);
    }

    @Test
    public void updateBook() {
        bookManager.addBook(book1);

        List<Book> retrievedBooks = bookManager.getAvailavleBooks();

        bookManager.updateBook(retrievedBooks.get(0), book2);

        assertEquals(1, retrievedBooks.size());
        assertEquals(TITLE_2, retrievedBooks.get(0).getTitle());
        assertEquals(AUTHOR_2, retrievedBooks.get(0).getAuthor());
        assertEquals(PRICE_2, retrievedBooks.get(0).getPrize(), 0.01);
    }

    @Test
    public void sellBookCheck(){
        Long personID = bookManager.addClient(person1);
        Person retrievedPerson = bookManager.findClientById(personID);

        Long bookID = bookManager.addBook(book1);

        bookManager.sellBook(retrievedPerson.getId(), bookID);

        List<Book> ownedBooks = bookManager.getOwnedBooks(retrievedPerson);

        assertEquals(1, ownedBooks.size());
        assertEquals(TITLE_1, ownedBooks.get(0).getTitle());
    }
}
