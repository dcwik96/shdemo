package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Book;

public interface IBook {

	void addBook(Book book);
	
	void deleteBook(Book book);
	
	List<Book> getBooks();
	
	
}
