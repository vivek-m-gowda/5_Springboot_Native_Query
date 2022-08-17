package com.example5.springbootnativequery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example5.springbootnativequery.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long>{
	
	@Query(value = "SELECT * FROM faculty_native_query WHERE first_name=?1", nativeQuery = true)
	public List<Faculty> getByName(String firstName);
	
	@Query(value = "SELECT * FROM faculty_native_query WHERE department=?1", nativeQuery = true)
	public List<Faculty> getByDepartment(String department);
	
	@Query(value = "SELECT * FROM faculty_native_query WHERE salary > 50000", nativeQuery = true)
	public List<Faculty> getHighPaidFaculty(long salary);
	
}
