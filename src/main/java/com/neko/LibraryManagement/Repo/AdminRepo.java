package com.neko.LibraryManagement.Repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neko.LibraryManagement.entities.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String> {
	Admin findByUsername( String username);
}
