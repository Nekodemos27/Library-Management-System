package com.neko.LibraryManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.neko.LibraryManagement.Repo.BookRepo;
import com.neko.LibraryManagement.Repo.IssuedRepo;
import com.neko.LibraryManagement.Repo.LibrarianRepo;
import com.neko.LibraryManagement.Repo.StudentRepo;
import com.neko.LibraryManagement.entities.Books;
import com.neko.LibraryManagement.entities.Issued;
import com.neko.LibraryManagement.entities.Librarian;
import com.neko.LibraryManagement.entities.Student;
import com.neko.LibraryManagement.service.BookServiceImpl;


@Controller
public class BookController {
	
	@Autowired
	LibrarianRepo libRepo;
	@Autowired
	BookRepo bookRepo;
	@Autowired
	IssuedRepo issuedRepo;
	@Autowired
	StudentRepo studentRepo;
	@Autowired
	BookServiceImpl bookService;
	
	
	
	@RequestMapping("/LibrarianLogin")
	public String LibrarianLogin() {
		return "librarianLogin";
	}
	
	@RequestMapping("/librarianValidate")
	public String librarianValidation(@RequestParam("libName")String name, @RequestParam("libPass") String password, Model model) {
		Librarian librarian = libRepo.findByName(name);
		
		if(librarian !=null && librarian.getPassword().equals(password))
			   return "librarianSection";
		else {
				model.addAttribute("msg", "Invalid username or password");
				return "librarianLogin";
		}

	}
	
	@RequestMapping("/Book_Add")	
	public String Book_Add() {
		return "addBook";	
	}
	
	@RequestMapping("addBook")
	public String addBook(@ModelAttribute("books") Books books, Model model) {
		bookRepo.save(books);
		model.addAttribute("msg", "Book added successfuly");
		return "librarianSection";
	}
	
	@RequestMapping("/Book_View")
	public String Book_View(Model model){
		List<Books> books = bookRepo.findAll();
		model.addAttribute("books", books);
		return "viewBooks";
	}
	
	@RequestMapping("/Book_issue")
	public String Book_issue() {
		return "issueBook";
	}
	
	@RequestMapping("/issueBook")
	public String issueBook(@RequestParam("callNo") String callNo, @RequestParam("studentId") int studentId, @RequestParam("studentName") String studentName, Model model){
		System.out.println("the call no is "+callNo);
		System.out.println("the student id is "+studentId);
		System.out.println("the student name is "+studentName);
		String msg=bookService.issueBook(callNo, studentId, studentName);
		model.addAttribute("msg", msg);
		return "librarianSection";
	}
	
	@RequestMapping("/Book_ViewIssued")
	public String viewIssued(Model model) {
		List<Issued> issued = issuedRepo.findAll();
		model.addAttribute("issued", issued);
		return "viewIssued";	
	}
	
	@RequestMapping("/Book_Return")
	public String bookReturn() {
		return "returnPage";
	}
	
	@RequestMapping("/returnBook")
	public String returnBook(@RequestParam("callNo")String callNo, @RequestParam("studentId") int studentId, Model model) {
		String msg = bookService.returnBook(callNo, studentId);
		model.addAttribute("msg", msg);
		return "librarianSection";	
	}
	
	@RequestMapping("/liback")
	public String back() {
		return "librarianSection";
	}

}
