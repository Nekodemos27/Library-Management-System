package com.neko.LibraryManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.neko.LibraryManagement.Repo.AdminRepo;
import com.neko.LibraryManagement.Repo.LibrarianRepo;
import com.neko.LibraryManagement.entities.Admin;
import com.neko.LibraryManagement.entities.Librarian;

@Controller
public class LibrarianController {
	@Autowired
	AdminRepo repo;
	@Autowired
	LibrarianRepo libRepo;

	@RequestMapping("/home")
	public String welcome() {
		return "welcomePage";
	}
	
	@RequestMapping("/AdminLogin")
	public String Admin_Login() {
		return "AdminLogin";
	}
	
	@RequestMapping("/validate")
	public String adminValidate(@RequestParam("adminName") String username, @RequestParam("adminPass") String password, Model model) {
		Admin admin = repo.findByUsername(username);
		if(admin !=null && admin.getPassword().equals(password))
		   return "AdminSection";
		else {
			model.addAttribute("msg", "Invalid username or password");
			return "AdminLogin";
		}	
	}
	
	@RequestMapping("/Librarian_Add")
	public String AddLibrarian () {
		return "addLibrarian";
	}
	
	@RequestMapping(value="/addLibrarian",  method=RequestMethod.POST)
	public String saveLibrarian(@ModelAttribute("librarian") Librarian librarian, Model model) {
		System.out.println(librarian.getName()+"---"+ librarian.getEmail());
		Librarian saved= libRepo.save(librarian);
		if(saved == null)
			model.addAttribute("msg", "Error occurred!");
		else 
			model.addAttribute("msg", "Librarian added successfully!");
		return "AdminSection";
	}
	
	@RequestMapping("/librarian_View")
	public String viewLibrarian(Model model) {
		List<Librarian> libList = libRepo.findAll();
		model.addAttribute("librarian", libList);
		return "viewLibrarian";
	}
	
	@RequestMapping("/updateLibrarian")
	public String updateLibrarian(@RequestParam("id") Long id ,Model model) {
		Librarian librarian = libRepo.getOne(id);
		model.addAttribute("librarian", librarian);
		return "updatePage";
	}
	
	@RequestMapping("/updatedLibrarian")
	public String updatedLibrarian(@ModelAttribute("librarian") Librarian librarian ,Model model) {
		libRepo.save(librarian);
		model.addAttribute("msg", "Librarian Updated Successfuly");
		return "AdminSection";
	}
	
	@RequestMapping("deleteLibrarian")
	public String deleteLibrarian (@ModelAttribute("librarian") Librarian librarian ,Model model) {
		libRepo.delete(librarian);
		List<Librarian> lib = libRepo.findAll();
		model.addAttribute("librarian", lib);
		return "viewLibrarian";	
	}
	
	@RequestMapping("/back")
	public String back() {
		return "AdminSection";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "welcomePage";
	}
}
