package com.example.shdemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "book.all", query = "Select b from Book b"),
        @NamedQuery(name = "book.unsold", query = "Select b from Book b where b.sold = false")
})
public class Book {

    private Long id;
    private String title;
    private Person author;
    private double prize;
    private Boolean sold = false;

    public Book() {
        super();
    }

    public Book(Long id, String title, Person author, double prize) {
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }
}
