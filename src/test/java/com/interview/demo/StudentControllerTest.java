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
		// Create test data
		List<StudentEntity> studentList = new ArrayList<>();
		studentList.add(new StudentEntity(1, "John", "john@example.com"));
		studentList.add(new StudentEntity(2, "Jane", "jane@example.com"));

		// Configure the mock behavior
		when(studentRepository.findAll()).thenReturn(studentList);

		// Make the method call
		List<StudentEntity> result = studentController.getallstudents();

		// Verify the result
		assertEquals(studentList.size(), result.size());
		assertEquals(studentList.get(0), result.get(0));
		assertEquals(studentList.get(1), result.get(1));

		// Verify that the repository method was called
		verify(studentRepository).findAll();
	}

	@Test
	public void testFindById() {
		// Create test data
		Integer studentId = 1;
		StudentEntity student = new StudentEntity(studentId,"John", "john@example.com");

		// Configure the mock behavior
		when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

		// Make the method call
		StudentEntity result = studentController.findbyid(studentId);

		// Verify the result
		assertEquals(student, result);

		// Verify that the repository method was called
		verify(studentRepository).findById(studentId);
	}

	@Test
	public void testCreateStudent() {
		// Create test data
		StudentEntity student = new StudentEntity(1, "John", "john@example.com");

		// Configure the mock behavior
		when(studentRepository.save(student)).thenReturn(student);

		// Make the method call
		StudentEntity result = studentController.createstudent(student);

		// Verify the result
		assertEquals(student, result);

		// Verify that the repository method was called
		verify(studentRepository).save(student);
	}

	@Test
	public void testUpdateStudent() {
		// Create test data
		Integer studentId = 1;
		StudentEntity existingStudent = new StudentEntity(studentId, "John", "john@example.com");
		StudentEntity updatedStudent = new StudentEntity(studentId, "Jane", "jane@example.com");

		// Configure the mock behavior
		when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
		when(studentRepository.save(existingStudent)).thenReturn(updatedStudent);

		// Make the method call
		ResponseEntity<StudentEntity> response = studentController.updateStudent(studentId, updatedStudent);

		// Verify the response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(updatedStudent, response.getBody());

		// Verify that the repository methods were called with the expected arguments
		verify(studentRepository).findById(studentId);
		verify(studentRepository).save(existingStudent);
		assertEquals(updatedStudent.getName(), existingStudent.getName());
		assertEquals(updatedStudent.getEmail(), existingStudent.getEmail());
	}

	@Test
	public void testDeleteStudent() {
		// Create test data
		Integer studentId = 1;
		StudentEntity student = new StudentEntity(studentId, "John", "john@example.com");

		// Configure the mock behavior
		when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

		// Make the method call
		Map<String, Boolean> response = studentController.deletestudent(studentId);

		// Verify the response
		assertTrue(response.get("deleted"));

		// Verify that the repository methods were called with the expected arguments
		verify(studentRepository).findById(studentId);
		verify(studentRepository).delete(student);
	}
}
