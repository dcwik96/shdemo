package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "book.all", query = "Select b from Book b")
})
public class Book {

    private Long id;
    private String title;
    private String author;
    private double prize;

    public Book() {
        super();
    }

    public Book(Long id, String title, String author, double prize) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.prize = prize;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }


}
