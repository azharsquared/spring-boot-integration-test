package com.azhar.springbootintegrationtest;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedCalculatorTest {
    //use the @MethodSource annotation.
    //
    //We give it the name of the function(s) we want it to call to get it’s test data.
    // The function has to be static and must return either a Collection, an Iterator, a Stream or an Array.
    // On execution the test method gets called once for every entry in the data source.
    // In contrast to Dynamic Tests @BeforeEach and @AfterEach methods will be called for parameterized tests.
    public static int[][] data() {
        return new int[][] { { 1 , 2, 2 }, { 5, 3, 15 }, { 121, 4, 484 } };
    }

    @ParameterizedTest
    //The result of the named method is passed as argument to the test.
    @MethodSource(value =  "data")
    void testWithStringParameter(int[] data) {
        MyClass tester = new MyClass();
        int m1 = data[0];
        int m2 = data[1];
        int expected = data[2];
        assertEquals(expected, tester.multiply(m1, m2));
    }

    // class to be tested
    class MyClass {
        public int multiply(int i, int j) {
            return i * j;
        }
    }

    @ParameterizedTest
    //Lets you define an array of test values. Permissible types are String, int, long, or double.
    @ValueSource(ints = { 1, 2, 31 })
    void testWithStringParameterVAlueSource(int data) {
        MyClass tester = new MyClass();
        int m1 = data;
        int m2 = data;
        int expected = data * data;
        assertEquals(expected, tester.multiply(m1, m2));
    }

    @ParameterizedTest
    //Lets you define an array of test values. Permissible types are String, int, long, or double.
    @EnumSource(value = Months.class, names = {"JANUARY", "FEBRUARY"})
    void testWithStringParameterEnumSource(Months data) {
        MyClass tester = new MyClass();
        int m1 = data.value;
        int m2 = data.value;
        int expected = data.value * data.value;
        assertEquals(expected, tester.multiply(m1, m2));
    }

    enum Months {
        JANUARY(5),FEBRUARY(57);
        public final int value;

        private Months(Integer value) {
            this.value = value;
        }
    }

    @ParameterizedTest
    //Expects strings to be parsed as Csv. The delimiter is ','.
    @CsvSource({ "foo, 1", "'baz, qux', 3" })
    void testWithStringParameterEnumSource(String first, int second) {
        MyClass tester = new MyClass();
        int m1 = second;
        int m2 = second;
        int expected = second * second;
        //intentional failure
        assertEquals("foo", first);
        assertEquals(expected, tester.multiply(m1, m2));
    }

    @ParameterizedTest
    //Expects strings to be parsed as Csv. The delimiter is ','.
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithStringParameterArgProviderSource(int data) {
        MyClass tester = new MyClass();
        int m1 = data;
        int m2 = data;
        int expected = data * data;
        assertEquals(expected, tester.multiply(m1, m2));
    }

    class MyArgumentsProvider implements ArgumentsProvider{
        MyArgumentsProvider(){}

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(Arguments.of(1),Arguments.of(3));
        }
    }
}
//https://www.vogella.com/tutorials/JUnit/article.html