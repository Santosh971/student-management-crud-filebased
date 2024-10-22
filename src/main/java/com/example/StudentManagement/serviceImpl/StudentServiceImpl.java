package com.example.StudentManagement.serviceImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.StudentManagement.models.Student;
import com.example.StudentManagement.services.FileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StudentServiceImpl implements FileService {

    private final String FILE_PATH = "student.txt";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Get All Students from the file
    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<Student>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                studentList.add(objectMapper.readValue(line, Student.class));

            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return studentList;
    }

    // Save the Student List to the file

    @Override
    public void saveStudentsToFile(List<Student> students) {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {

            for (Student student : students) {
                writer.println(objectMapper.writeValueAsString(student));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Create and save student
    @Override
    public Student createStudent(Student student) {
        List<Student> studentList = getAllStudents();
        student.setId((long) (studentList.size() + 1));
        studentList.add(student);
        saveStudentsToFile(studentList);

        return student;
    }

    @Override
    public Student getStudentById(Long id) {

        List<Student> studentList = getAllStudents();
        return studentList.stream().filter(student -> student.getId().equals(id)).findFirst()
                .orElse(null);
    }

    @Override
    public Student update(Long id, Student student) {
        List<Student> studentList = getAllStudents();

        Student updateStudent = studentList.stream()
                .filter(fstudent -> fstudent.getId() != null && fstudent.getId().equals(id)).findFirst()
                .orElse(null);
        if (updateStudent != null) {
            updateStudent.setAge(student.getAge());
            updateStudent.setCourse(student.getCourse());
            updateStudent.setEmail(student.getEmail());
            updateStudent.setName(student.getName());

            saveStudentsToFile(studentList);
            return updateStudent;
        }
        return null;
    }

    // Delete Student by id
    @Override
    public void deleteStudentById(Long id) {
        List<Student> studentList = getAllStudents();

        studentList.removeIf(student -> student.getId() != null && student.getId().equals(id));
        saveStudentsToFile(studentList);
    }

}
