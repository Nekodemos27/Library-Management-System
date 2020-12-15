package com.neko.LibraryManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neko.LibraryManagement.entities.Books;
import com.neko.LibraryManagement.entities.Issued;


@Repository
public interface IssuedRepo extends JpaRepository<Issued, Long>{
	Issued findByBooks(Books book);
}
