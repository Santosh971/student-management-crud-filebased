package com.example.StudentManagement.services;

import java.util.List;

import com.example.StudentManagement.models.Student;

public interface FileService {

    // Create Student
    public Student createStudent(Student student);

    // Get all Students
    public List<Student> getAllStudents();

    // Get Student By ID

    public Student getStudentById(Long id);

    // Update Student by id

    public Student update(Long id, Student student);

    // Save Student List to the File

    public void saveStudentsToFile(List<Student> students);

    // Delete Student by id

    public void deleteStudentById(Long id);

}
