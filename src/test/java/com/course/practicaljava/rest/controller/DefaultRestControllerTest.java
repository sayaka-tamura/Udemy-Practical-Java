package com.course.practicaljava.rest.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest // Indicate to run the test using Spring Boot
@AutoConfigureMockMvc // Annotation to auto configures mock testing
class DefaultRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test // Annotation for a testing method
	void testWelcome() throws Exception {
		var endpoint = "/api/welcome";
		var requestBuilder = MockMvcRequestBuilders.get(endpoint); // Build a HTTP GET mock Request

		// Simulate the request and compare the result using MockMvc(using andExpect)
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().string(Matchers.equalToIgnoringCase("welcome to spring boot")));
	}

	@Test
	// check if the response give value with range 40 seconds of current time
	void testTime() throws Exception {
		var endpoint = "/api/time";
		var requestBuilder = MockMvcRequestBuilders.get(endpoint); // Build a HTTP GET mock Request

		// Get MVC result
		var mockResult = mockMvc.perform(requestBuilder).andReturn();
		// Extract the content as String
		var content = mockResult.getResponse().getContentAsString();

		var contentLocalTime = LocalTime.parse(content);
		var currentLocalTime = LocalTime.now();
		var localTimeMinus40Secs = currentLocalTime.minusSeconds(40);

		// compare our own logic and the result
		assertTrue(contentLocalTime.isAfter(localTimeMinus40Secs) && contentLocalTime.isBefore(currentLocalTime));
	}

	@Test
	void testHeaderByAnnotation() throws Exception {
		var endpoint = "/api/header-one";

		// simulate header request by using HTTP headers on mock request
		var headerOne = "MockMvc";
		var headerTwo = "MyJava";

		var httpHeaders = new HttpHeaders();
		httpHeaders.add("User-agent", headerOne);
		httpHeaders.add("Practical-java", headerTwo);

		// Create the requestBuilder with headers
		var requestBuilder = MockMvcRequestBuilders.get(endpoint).headers(httpHeaders);

		// Perform mock request, and expect that the response body contains value of
		// header
		mockMvc.perform(requestBuilder).andExpect(content().string(containsString(headerOne)))
				.andExpect(content().string(containsString(headerTwo)));
	}

//	@Test
	void testHeaderByServlet() {
		fail("Not yet implemented");
	}

//	@Test
	void testHeaderByAll() {
		fail("Not yet implemented");
	}

}
