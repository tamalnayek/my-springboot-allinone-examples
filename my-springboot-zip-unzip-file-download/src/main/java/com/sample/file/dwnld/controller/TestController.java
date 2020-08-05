package com.sample.file.dwnld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.file.dwnld.model.Employee;

@RestController
public class TestController {

	
	@GetMapping(value = "/employee", produces = { "application/json", "application/xml" })
	public Employee firstPage() {

		Employee emp = new Employee();
		emp.setName("Tamal Nayek");
		emp.setDesignation("manager");
		emp.setEmpId("1");
		emp.setSalary(30000);

		return emp;
	}
	
}
