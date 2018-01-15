package com.example.shdemo.service;


import com.example.shdemo.domain.Book;
import com.example.shdemo.domain.Person;
import org.hibernate.LazyInitializationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
public class BookTestWithoutTransactional {


    @Autowired
    IBook bookManager;


    private final String NAME_1 = "Dawid";

    Person person1 = new Person(NAME_1);
    private final String TITLE_1 = "Tytul1";
    private final Person AUTHOR_1 = person1;
    private final double PRICE_1 = 19.90;

    Book book1 = new Book(null, TITLE_1, AUTHOR_1, PRICE_1);

    @Test
    public void checkLazyInitialization() {
        bookManager.addBook(book1);

        List<Person> retrievedPersons = bookManager.getAllClients();
        Person retrievedPerson = retrievedPersons.get(0);

        boolean pass = false;

        try{
            System.out.println(retrievedPerson.getBooks().size());
        } catch (LazyInitializationException e) {
            e.printStackTrace();
            pass = true;
        }

        if (!pass)
            Assert.fail();
    }


}
