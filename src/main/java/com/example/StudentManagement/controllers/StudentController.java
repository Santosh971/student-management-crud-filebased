package com.example.StudentManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.StudentManagement.models.Student;
import com.example.StudentManagement.serviceImpl.StudentServiceImpl;

import lombok.val;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    // Create and save the student
    @PostMapping("/createStudent")
    public Student save(@RequestBody Student student) {

        return studentServiceImpl.createStudent(student);
    }

    // Get Student by id
    @GetMapping("/getStudent/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentServiceImpl.getStudentById(id);
    }

    // Get All Students
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentServiceImpl.getAllStudents();
    }

    // Update Student by id
    @PutMapping("/updateStudent/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {

        return studentServiceImpl.update(id, student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(@PathVariable Long id) {
        studentServiceImpl.deleteStudentById(id);
    }
}
