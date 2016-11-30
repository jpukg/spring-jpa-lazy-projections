package com.github.mefernandez.jpa.fetch.lazy.v3_jsonview_custom_converter;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void lazyFetchTypeSerializesEmployeeDepartment() throws Exception {
		this.mvc.perform(get("/lazy/employees")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.numberOfElements").value("20"))
				.andExpect(jsonPath("$.content[1].name").isString())
				.andExpect(jsonPath("$.content[1].department.name").value("Department"));
	}

	@Test
	public void itShouldNotSerializeBoss() throws Exception {
		this.mvc.perform(get("/lazy/employees")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.numberOfElements").value("20"))
				.andExpect(jsonPath("$.content[1].name").isString())
				.andExpect(jsonPath("$.content[1].boss").doesNotExist());
	}

}
