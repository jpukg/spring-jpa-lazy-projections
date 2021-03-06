package com.github.mefernandez.jpa.fetch.lazy.v1_default;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(method = RequestMethod.GET, value = "/lazy/employees")
	public Page<Employee> search(Pageable pageable, @RequestParam(required=false) String salaryFrom) {
		if (salaryFrom != null) {
			return (Page<Employee>) employeeRepository.findBySalariesSalaryGreaterThanEqual(new BigDecimal(salaryFrom), pageable);
		}
		return (Page<Employee>) employeeRepository.findAll(pageable);
	}
}
