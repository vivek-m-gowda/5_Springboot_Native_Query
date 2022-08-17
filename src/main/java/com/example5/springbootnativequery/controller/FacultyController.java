package com.example5.springbootnativequery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example5.springbootnativequery.exception.ResourceNotFound;
import com.example5.springbootnativequery.model.Faculty;
import com.example5.springbootnativequery.repository.FacultyRepository;

@RestController
@RequestMapping("/api")
public class FacultyController {
	
	@Autowired
	private FacultyRepository facultyRepository;
	
	//get all list
	@GetMapping("/AllFaculy")
	public List <Faculty> getallFaculties(){
		return this.facultyRepository.findAll();
	}
	
	//get by id
	@GetMapping("/GetFacultyById/{id}")
	public Faculty getFacultyById (@PathVariable (value = "id") long facultyId) {
		return this.facultyRepository.findById(facultyId)
				   .orElseThrow(() -> new ResourceNotFound("Faculty not with Id:" +facultyId));
	}
	
	//create faculty
	@PostMapping("/Save")
	public Faculty createFaculty(@RequestBody Faculty faculty) {
		return this.facultyRepository.save(faculty);
	}
	
	//update faculty
	@PutMapping("/Update/{id}")
	public Faculty updateFaculty (@RequestBody Faculty faculty, @PathVariable("id") long facultyId) {
		Faculty existingFaculty = this.facultyRepository.findById(facultyId)
				.orElseThrow(()-> new ResourceNotFound("Employee not found with id :" +facultyId));
		existingFaculty.setFirstName(faculty.getFirstName());
		existingFaculty.setLastName(faculty.getLastName());
		existingFaculty.setEmail(faculty.getEmail());
		existingFaculty.setDepartment(faculty.getDepartment());
		existingFaculty.setSalary(faculty.getSalary());
		return this.facultyRepository.save(existingFaculty);
	}
	
	//delete faculty
	@DeleteMapping("/Delete/{id}")
	public ResponseEntity<String> deleteFaculty(@PathVariable("id") long facultyId){
		Faculty existingFaculty = this.facultyRepository.findById(facultyId)
				.orElseThrow(()-> new ResourceNotFound("Employee not found with id :" +facultyId));
		this.facultyRepository.delete(existingFaculty);
		return ResponseEntity.ok("1 row deleted");
	}
	
	//native query to get by name
	@GetMapping("/GetByName/{firstName}")
	public List<Faculty> getByName(@PathVariable String firstName){
		return facultyRepository.getByName(firstName);
	}
	
	//native query to get by department
	@GetMapping("/GetByDepartment/{department}")
	public List<Faculty> getByDepartment(@PathVariable String department){
		return facultyRepository.getByDepartment(department);
	}
	
	@GetMapping("/HighlyPaidFacult/{salary}")
	public List<Faculty> getHighPaidFaculty(@PathVariable Long salary){
		return facultyRepository.getHighPaidFaculty(salary);
	}
	
}
