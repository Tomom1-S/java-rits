package models;

import myClasses.TestClass;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

class MyArrayTest {
    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    private static final int DEFAULT_INT = 42;
    private static final int DEFAULT_SIZE = 10;
    private final Class intCls = Class.forName("java.lang.Integer");
    private final Class testCls = Class.forName("myClasses.TestClass");
    private final MyArray intArr = new MyArray(intCls, DEFAULT_SIZE);
    private final MyArray testArr = new MyArray(testCls, DEFAULT_SIZE);
    private final MyObject intObj = new MyObject(intCls, DEFAULT_INT);
    private final MyObject testObj = new MyObject(testCls, new TestClass());

    private static final int ORDER_SET_ELEMENT_FOR_INTEGER = 0;
    private static final int ORDER_SET_ELEMENT_FOR_TEST = 1;

    MyArrayTest() throws ClassNotFoundException {
    }

    @BeforeEach
    public void setUp() {
        defaultPrintStream = System.out;
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new BufferedOutputStream(byteArrayOutputStream)));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(defaultPrintStream);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Order(ORDER_SET_ELEMENT_FOR_INTEGER)
    void setElementForInteger() {
        int index = 0;
        Integer obj = (Integer) intObj.getObj();
        intArr.setElement(index, obj);
        Integer actual = (Integer) intArr.getElement(index);
        assertThat(actual, is(obj));

        actual = (Integer) intArr.getElement(2);
        assertThat(actual, is(nullValue()));

        index = 100;
        try {
            intArr.setElement(index, intObj.getObj());
        } catch (IndexOutOfBoundsException expected) {
            assertThat(expected.getMessage(), equalTo(String.valueOf(index)));
        }
    }

    @Test
    @Order(ORDER_SET_ELEMENT_FOR_TEST)
    void setElementForTest() {
        int index = 0;
        TestClass obj = (TestClass) testObj.getObj();
        testArr.setElement(index, obj);
        TestClass actual = (TestClass) testArr.getElement(0);
        assertThat(actual, is(obj));

        actual = (TestClass) testArr.getElement(2);
        assertThat(actual, is(nullValue()));

        index = 100;
        try {
            testArr.setElement(index, testObj.getObj());
        } catch (IndexOutOfBoundsException expected) {
            assertThat(expected.getMessage(), equalTo(String.valueOf(index)));
        }
    }
}
