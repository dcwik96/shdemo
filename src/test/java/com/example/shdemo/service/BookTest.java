package com.example.shdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class BookTest {
	
	@Autowired
	BookManager bookManager;
	
	private final String TITLE_1 = "Tytul1";
	private final String AUTHOR_1 = "Autor1";
	private final double PRICE_1 = 19.90;


}
