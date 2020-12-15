package com.neko.LibraryManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neko.LibraryManagement.Repo.BookRepo;
import com.neko.LibraryManagement.Repo.IssuedRepo;
import com.neko.LibraryManagement.Repo.StudentRepo;
import com.neko.LibraryManagement.entities.Books;
import com.neko.LibraryManagement.entities.Issued;
import com.neko.LibraryManagement.entities.Student;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepo bookRepo;
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	IssuedRepo issueRepo;
	
	@Override
	public String issueBook(String callNo, int studentId, String studentName) {
		Books book = bookRepo.findBycallNo(callNo);
		Student student = studentRepo.findById(studentId);
		if(book==null) {
			return "Check the call No and try again. The book is not found in the database.";
		}
		if(student == null) {
			return "Incorrect student id. Check and try agian!";
		}
		else if(!studentName.equals(student.getName())){
			return "The Student name with the given id is incorrect.";
		}
		else {
			Issued issue=new Issued();
			issue.setBooks(book);
			issue.setStudent(student);
			issueRepo.save(issue);
			book.setIssued(book.getIssued()+1);
			book.setQuatity(book.getQuatity()-1);
			bookRepo.save(book);
			return "Book issued Successfuly";
		}
	}

	@Override
	public String returnBook(String callNo, int studentId) {
		Books book = bookRepo.findBycallNo(callNo);
		Issued issued = issueRepo.findByBooks(book);
		if(issued==null) {
			return "Sorry, unable to return book";
		}else if(issued.getStudent().getId()==studentId) {
			issueRepo.delete(issued);
			book.setIssued(book.getIssued()-1);
			book.setQuatity(book.getQuatity()+1);
			bookRepo.save(book);
			return "Book returned successfuly";
		}
		return "Sorry, unable to return book";
	}

}
