package com.azhar.springbootintegrationtest;

import com.azhar.springbootintegrationtest.service.Calculator;
import org.junit.jupiter.api.*;

import static java.time.Duration.ofNanos;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calculator;

    //The method annotated with @BeforeEach runs before each test
    @BeforeEach                                         
    void setUp() {
        calculator = new Calculator();
    }

    //	A method annotated with @Test defines a test method
    @Test
    //	@DisplayName can be used to define the name of the test which is displayed to the user
    @DisplayName("Simple multiplication should work")   
    void testMultiply() {
        //// alternatively define assertEquals as static import instead of Assert.assertEquals
        assertEquals(20, calculator.multiply(4, 5),     
                "Regular multiplication should work");  
    }

    //@RepeatedTest defines that this test method will be executed multiple times, in this example 5 times
    @RepeatedTest(5)                                    
    @DisplayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(0, 5), "Multiple with zero should be zero");
        assertEquals(0, calculator.multiply(5, 0), "Multiple with zero should be zero");
    }

    @Test
    void exceptionTesting() {
        // set up user
        Throwable exception = assertThrows(ArithmeticException.class, () -> calculator.multiply(-1, 0));
        assertEquals("no negative multiplication.", exception.getMessage());
    }

    //JUnit will stop executing the test and additional asserts are not checked. In case you want to ensure that all asserts are checked you can assertAll.
    //
    //In this grouped assertion all assertions are executed, even after a failure. The error messages get also grouped together.
    @Test
    void groupedAssertions() {
        assertAll("negetive",
                () -> assertEquals(51, calculator.multiply(1, 5)),
                () -> assertEquals(251, calculator.multiply(5, 5),"hello")
        );
    }


    @Test
    void timeoutNotExceededWithResult() {
        // This assert fails the method if the timeout is exceeded.
        String actualResult = assertTimeout(ofNanos(1), () -> {
            return "restService.request(request)";
        });
        assertEquals("restService.request(request)", "restService.request(request)");
    }

    @Test
    void timeoutNotExceededWithResultPreemptive() {
        //f you want your tests to cancel after the timeout period is passed you can use the
        String actualResult = assertTimeoutPreemptively(ofSeconds(1), () -> {
            return "restService.request(request)";
        });
        assertEquals("restService.request(request)", "restService.request(request)");
    }

    @DisplayName("Ensure correct handling of zero")
    @Test
    void tryAssuming() {
        //to define a condition for test execution. based on this assumption the code will run
        Assumptions.assumeTrue(System.getProperty("os.name").contains("Linux"));
        assertEquals(calculator.multiply(0,5), 0, "Multiple with zero should be zero");
    }
}