package com.azhar.springbootintegrationtest;


import com.azhar.springbootintegrationtest.entity.Student;
import com.azhar.springbootintegrationtest.repository.StudentRepository;
import com.azhar.springbootintegrationtest.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerMockTests {
    @Autowired
    private StudentService studentService;

    //. @MockBean helps to enable the mocking of a certain layer
    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testRetrieveStudentWithMockRepository() throws Exception {

        Optional<Student> optStudent = Optional.of( new Student("Rajesh","Bhojwani"));
        when(studentRepository.findById(1L)).thenReturn(optStudent);

        //You can use assert methods, provided by JUnit or another assert framework,
        // to check an expected result versus the actual result.
        // Such statement are called asserts or assert statements.
        // Assert statements typically allow to define messages which are shown if the test fails.

        assertTrue(studentService.retrieveStudent(1L).getName().contains("Rajesh"));
    }

}
