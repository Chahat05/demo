package com.interview.demo.controller;

import com.interview.demo.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    Producer producer;

    @GetMapping ("/producerMsg")
    public void getMessageFromClient (@RequestParam("message") String message) {
        producer.sendMsgToTopic (message);
    }
    //localhost:8080/api/student/producerMsg?message="first message"


    @GetMapping("/get-all-students")
    public List<StudentEntity> getallstudents()
    {
        List<StudentEntity> allstudentlist = studentRepository.findAll(); // findAll is a part of JPA
        return allstudentlist;
    }

    @GetMapping("find-by-id/{id}")
    public StudentEntity findbyid(@PathVariable(value="id")Integer id)
    {
        StudentEntity studentEntity=studentRepository.findById(id).get();
        return studentEntity;
    }

    @PostMapping("create-student")
    public StudentEntity createstudent(@RequestBody StudentEntity student)
    {
        StudentEntity savedStudent= studentRepository.save(student);
        return savedStudent;
    }

    @PutMapping("/update-student/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable(value = "id") Integer id, @RequestBody StudentEntity studentDetails) {
        StudentEntity student = studentRepository.findById(id).get();

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());

        final StudentEntity updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }


    @DeleteMapping("delete-student/{id}")
    public Map<String, Boolean> deletestudent(@PathVariable(value="id")Integer studentId)
    {
        StudentEntity student= studentRepository.findById(studentId).get();

        studentRepository.delete(student);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

}