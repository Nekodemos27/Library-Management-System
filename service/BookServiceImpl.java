package com.neko.LibraryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neko.LibraryManagement.Repo.BookRepo;
import com.neko.LibraryManagement.Repo.IssuedBooksRepo;
import com.neko.LibraryManagement.Repo.StudentRepo;
import com.neko.LibraryManagement.entities.Books;
import com.neko.LibraryManagement.entities.issuedBooks;
import com.neko.LibraryManagement.entities.Student;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepo bookRepo;
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	IssuedBooksRepo issueRepo;
	
	@Override
	public String issueBook(String callNo, int studentId, String studentName) {
		Books book = bookRepo.findBycallNo(callNo);
		Student student = studentRepo.findById(studentId);
		if(book==null) {
			return "Check the call No. The book is not found in the database.";
		}
		if(student == null) {
			return "Incorrect student id. Cannot identify a student with the given id";
		}
		else if(!studentName.equals(student.getName())){
			return "The Student name with the given id is incorrect.";
		}
		else {
			issuedBooks issued=new issuedBooks();
			issued.setBook(book);
			issued.setStudent(student);
			issueRepo.save(issued);
			return "Book issued Successfuly";
		}
	}

}
