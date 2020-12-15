package com.neko.LibraryManagement.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neko.LibraryManagement.entities.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
	Student findById(int Id);

}
