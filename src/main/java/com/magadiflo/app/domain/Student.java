package com.magadiflo.app.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Student name must not be blank")
    @Length(min = 3, max = 40, message = "Student name must be between 3-40 characteres")
    @Column(nullable = false, length = 40)
    private String name;

    @NotNull
    @Past(message = "Student's birthday must be a date in the past")
    @Column(name = "birth_day")
    private Date birthDay;

    @NotNull
    @Future(message = "Student's graduation date must be a date in future")
    @Column(name = "graduation_date")
    private Date graduationDate;

    public Student() {
    }

    public Student(String name, Date birthDay, Date graduationDate) {
        this.name = name;
        this.birthDay = birthDay;
        this.graduationDate = graduationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }
}
