package com.interview.demo;

import com.interview.demo.controller.StudentController;
import com.interview.demo.controller.StudentEntity;
import com.interview.demo.controller.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentControllerTest {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentController studentController;

	public StudentControllerTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllStudents() {

		List<StudentEntity> studentList = new ArrayList<>();
		studentList.add(new StudentEntity(1, "John", "john@example.com"));
		studentList.add(new StudentEntity(2, "Jane", "jane@example.com"));


		when(studentRepository.findAll()).thenReturn(studentList);


		List<StudentEntity> result = studentController.getallstudents();


		assertEquals(studentList.size(), result.size());
		assertEquals(studentList.get(0), result.get(0));
		assertEquals(studentList.get(1), result.get(1));


		verify(studentRepository).findAll();
	}

	@Test
	public void testFindById() {

		Integer studentId = 1;
		StudentEntity student = new StudentEntity(studentId,"John", "john@example.com");

		when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

		StudentEntity result = studentController.findbyid(studentId);

		assertEquals(student, result);

		verify(studentRepository).findById(studentId);
	}

	@Test
	public void testCreateStudent() {

		StudentEntity student = new StudentEntity(1, "John", "john@example.com");


		when(studentRepository.save(student)).thenReturn(student);

		StudentEntity result = studentController.createstudent(student);

		assertEquals(student, result);

		verify(studentRepository).save(student);
	}

	@Test
	public void testUpdateStudent() {
		Integer studentId = 1;
		StudentEntity existingStudent = new StudentEntity(studentId, "John", "john@example.com");
		StudentEntity updatedStudent = new StudentEntity(studentId, "Jane", "jan@example.com");

		when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
		when(studentRepository.save(existingStudent)).thenReturn(updatedStudent);

		ResponseEntity<StudentEntity> response = studentController.updateStudent(studentId, updatedStudent);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedStudent, response.getBody());

		verify(studentRepository).findById(studentId);
		verify(studentRepository).save(existingStudent);
		assertEquals(updatedStudent.getName(), existingStudent.getName());
		assertEquals(updatedStudent.getEmail(), existingStudent.getEmail());
	}

	@Test
	public void testDeleteStudent() {
		Integer studentId = 1;
		StudentEntity student = new StudentEntity(studentId, "John", "john@example.com");
		when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

		Map<String, Boolean> response = studentController.deletestudent(studentId);

		assertTrue(response.get("deleted"));

		verify(studentRepository).findById(studentId);
		verify(studentRepository).delete(student);
	}
}
