package com.github.mefernandez.jpa.fetch.lazy.v2_force;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void lazyFetchTypeSerializesEmployeeDepartmentIfHibernate4Module_Feature_FORCE_LAZY_LOADINGIsEnabled() throws Exception {
		this.mvc.perform(get("/lazy/employees")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.numberOfElements").value("20"))
				.andExpect(jsonPath("$.content[1].name").isString())
				.andExpect(jsonPath("$.content[1].department.name").value("Department"));
	}

	@Test
	public void lazyFetchTypeSerializesEmployeeBossIfHibernate4Module_Feature_FORCE_LAZY_LOADINGIsEnabled() throws Exception {
		this.mvc.perform(get("/lazy/employees")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.numberOfElements").value("20"))
				.andExpect(jsonPath("$.content[1].name").isString())
				.andExpect(jsonPath("$.content[1].boss.name").value("The Boss"));
	}

}