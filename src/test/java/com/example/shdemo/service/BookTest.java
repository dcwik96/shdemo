package com.example.shdemo.service;

import com.example.shdemo.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BookTest {

    @Autowired
    IBook bookManager;

    private final String TITLE_1 = "Tytul1";
    private final String AUTHOR_1 = "Autor1";
    private final double PRICE_1 = 19.90;

    private final static String TITLE_2 = "QuoVadis";
    private final static String AUTHOR_2 = "Heniu Siena";
    private final static double PRICE_2 = 10.99;

    private final static String TITLE_3 = "Niebieska Mila";
    private final static String AUTHOR_3 = "Stephen Queen";
    private final static double PRICE_3 = 99.99;

    Book book1 = new Book(null, TITLE_1, AUTHOR_1, PRICE_1);
    Book book2 = new Book(null, TITLE_2, AUTHOR_2, PRICE_2);
    Book book3 = new Book(null, TITLE_3, AUTHOR_3, PRICE_3);

    @Test
    public void addBook() {

        bookManager.addBook(book1);

        List<Book> retrievedBooks = bookManager.getBooks();

        assertEquals(1, retrievedBooks.size());

        assertEquals(TITLE_1, retrievedBooks.get(0).getTitle());
        assertEquals(AUTHOR_1, retrievedBooks.get(0).getAuthor());
        assertEquals(PRICE_1, retrievedBooks.get(0).getPrize(), 0.01);
    }

    @Test
    public void deleteBook() {
        bookManager.addBook(book1);
        bookManager.addBook(book2);

        bookManager.deleteBook(book1);

        List<Book> retrievedBooks = bookManager.getBooks();

        assertEquals(1, retrievedBooks.size());
        assertEquals(TITLE_2, retrievedBooks.get(0).getTitle());
        assertEquals(AUTHOR_2, retrievedBooks.get(0).getAuthor());
        assertEquals(PRICE_2, retrievedBooks.get(0).getPrize(), 0.01);
    }

    @Test
    public void updateBook() {
        bookManager.addBook(book1);

        List<Book> retrievedBooks = bookManager.getBooks();

        bookManager.updateBook(retrievedBooks.get(0), book2);

        assertEquals(1, retrievedBooks.size());
        assertEquals(TITLE_2, retrievedBooks.get(0).getTitle());
        assertEquals(AUTHOR_2, retrievedBooks.get(0).getAuthor());
        assertEquals(PRICE_2, retrievedBooks.get(0).getPrize(), 0.01);
    }


}
