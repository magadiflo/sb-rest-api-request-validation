package com.magadiflo.app.service;

import com.magadiflo.app.domain.Student;

import java.util.List;

public interface IStudentService {

    List<Student> listAll();

    Student save(Student student);

}
