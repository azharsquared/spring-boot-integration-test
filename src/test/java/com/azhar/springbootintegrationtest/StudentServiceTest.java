package com.azhar.springbootintegrationtest;

import com.azhar.springbootintegrationtest.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentServiceTest {
    StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    @DisplayName("saving item test")
    void testMultiply() {
        assertEquals(20, studentService.createStudent(),
                "Regular adding should work");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure multiple get call")
    void testMultiplyWithZero() {
        assertEquals(0,studentService.retrieveStudent(1L), "Ensure multiple get call");
        //assertEquals(0, calculator.multiply(5, 0), "Multiple with zero should be zero");
    }
}
