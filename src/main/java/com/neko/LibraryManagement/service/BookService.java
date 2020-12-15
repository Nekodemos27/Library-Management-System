package com.neko.LibraryManagement.service;

import org.springframework.stereotype.Service;

@Service
public interface BookService {
	String issueBook(String callNo, int studentId, String studentName);
	String returnBook(String callNo, int studentId);
}
