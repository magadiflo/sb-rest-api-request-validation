package com.magadiflo.app.resource;

import com.magadiflo.app.domain.Student;
import com.magadiflo.app.service.IStudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentResource {

    private final IStudentService studentService;

    public StudentResource(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> listAll() {
        return this.studentService.listAll();
    }

    @PostMapping
    public Student addOne(@RequestBody @Valid Student student) {
        return this.studentService.save(student);
    }
}
