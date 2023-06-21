package com.interview.demo.controller;

import com.interview.demo.controller.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // annotation indicates that the class or interface is completely dedicated to performing all sorts of CRUD
public interface StudentRepository extends JpaRepository<StudentEntity,Integer> //Interger = datatype of primary key in the table
{
}
