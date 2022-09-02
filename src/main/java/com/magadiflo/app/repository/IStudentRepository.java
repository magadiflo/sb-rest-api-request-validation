package com.magadiflo.app.repository;

import com.magadiflo.app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStudentRepository extends JpaRepository<Student, Long> {
}
