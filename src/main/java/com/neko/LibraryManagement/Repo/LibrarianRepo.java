package com.neko.LibraryManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.neko.LibraryManagement.entities.Librarian;

@Repository
public interface LibrarianRepo extends JpaRepository<Librarian, Long> {
	Librarian findByName(String username);
}
