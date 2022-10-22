package com.azhar.springbootintegrationtest;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
* it is all about running an application in ApplicationContext and run tests.
* Spring Framework does have a dedicated test module for integration testing.
* It is known as spring-test. If we are using spring-boot, then we need to use spring-boot-starter-test \
* which will internally use spring-test and other dependent libraries.
 * */
/*
* read more: https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.html
* @SpringBootTest annotation which provides spring-boot features over and above of the spring-test module.
* This annotation works by creating the ApplicationContext used in our tests through SpringApplication.
* It starts the embedded server, creates a web environment and then enables @Test methods to do integration testing.
* */
//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootIntegrationTestApplicationTests {

	@LocalServerPort
	private int port;

	//spring-boot also does provide other classes like TestRestTemplate to test the REST APIs
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	@Test
	public void testCreateStudent() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students"), HttpMethod.POST, entity, String.class);

		String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

		assertTrue(actual.contains("/students"));
	}

	@Test
	public void testRetrieveStudent() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/1"), HttpMethod.GET, entity, String.class);

		String expected = "{\"id\":1,\"name\":\"Rajesh Bhojwani\",\"description\":\"Class 10\"}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
