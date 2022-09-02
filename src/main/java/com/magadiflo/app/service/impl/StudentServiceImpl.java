package com.magadiflo.app.service.impl;

import com.magadiflo.app.domain.Student;
import com.magadiflo.app.repository.IStudentRepository;
import com.magadiflo.app.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private final IStudentRepository studentRepository;

    public StudentServiceImpl(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> listAll() {
        return this.studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student save(Student student) {
        return this.studentRepository.save(student);
    }
}
