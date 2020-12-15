package com.neko.LibraryManagement.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neko.LibraryManagement.entities.Books;

@Repository
public interface BookRepo extends JpaRepository<Books, String>{
 
	Books findBycallNo(String callNo);
}