package models;

import myClasses.TestClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class ClassSearchTest {

    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private final String ls = System.lineSeparator();

    private final Class intCls = Class.forName("java.lang.Integer");
    private final Class testCls = Class.forName("myClasses.TestClass");
    private final ClassSearch cs = new ClassSearch();

    ClassSearchTest() throws ClassNotFoundException {
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

    @Test
    public void callConstructorForInteger() throws NoSuchMethodException, ClassNotFoundException {
        Constructor constructor = intCls.getDeclaredConstructor(java.lang.String.class);
        Integer actual = (Integer) cs.callConstructor(constructor, "42", "100");
        final Integer expected = 42;
        assertThat(actual, is(expected));

        constructor = intCls.getDeclaredConstructor(int.class);
        actual = (Integer) cs.callConstructor(constructor, 42);
        assertThat(actual, is(expected));
    }

    @Test
    public void callConstructorForTestClass() throws NoSuchMethodException, ClassNotFoundException {
        Constructor constructor = testCls.getDeclaredConstructor(java.lang.String.class);
        TestClass actual = (TestClass) cs.callConstructor(constructor, "Foo bar");
        String expected = "Foo bar";
        assertThat(actual.name, is(expected));

        constructor = testCls.getDeclaredConstructor();
        actual = (TestClass) cs.callConstructor(constructor);
        expected = "Default";
        assertThat(actual.name, is(expected));

         constructor = testCls.getDeclaredConstructor(java.lang.String.class);
         Object obj = "Foo bar";
         actual = (TestClass) cs.callConstructor(constructor, obj);
         expected = "Foo bar";
        assertThat(actual.name, is(expected));
    }

    @Test
    public void getConstructorParameterTypesForInteger() throws NoSuchMethodException {
        Constructor constructor = intCls.getDeclaredConstructor(java.lang.String.class);
        List<Type> actual = cs.getConstructorParameterTypes(constructor);
        List<Type> expected = new ArrayList<>() {{
            add(java.lang.String.class);
        }};
        assertThat(actual, is(expected));

        constructor = intCls.getDeclaredConstructor(int.class);
        actual = cs.getConstructorParameterTypes(constructor);
        expected = new ArrayList<>() {{
            add(int.class);
        }};
        assertThat(actual, is(expected));
    }

    @Test
    public void getConstructorParameterTypesForTestClass() throws NoSuchMethodException {
        Constructor constructor = testCls.getDeclaredConstructor();
        List<Type> actual = cs.getConstructorParameterTypes(constructor);
        List<Type> expected = new ArrayList<>();
        assertThat(actual, is(expected));

        constructor = testCls.getDeclaredConstructor(java.lang.String.class);
        actual = cs.getConstructorParameterTypes(constructor);
        expected = new ArrayList<>() {{
            add(java.lang.String.class);
        }};
        assertThat(actual, is(expected));
    }

    @Test
    public void getConstructorParametersForInteger() throws NoSuchMethodException {
        List<Parameter> params = cs.getConstructorParameters(
                intCls.getDeclaredConstructor(java.lang.String.class));
        List<Type> actualType = new ArrayList<>();
        List<String> actualName = new ArrayList<>();
        for (Parameter p : params) {
            actualType.add(p.getType());
            actualName.add(p.getName());
        }
        List<Type> expectedType = new ArrayList<>() {{
            add(java.lang.String.class);
        }};
        List<String> expectedName = new ArrayList<>() {{
            add("arg0");
        }};
        assertThat(actualType, is(expectedType));
        assertThat(actualName, is(expectedName));
    }

    @Test
    public void getConstructorParametersForTestClass() throws NoSuchMethodException {
        List<Parameter> params = cs.getConstructorParameters(
                testCls.getDeclaredConstructor(java.lang.String.class));
        List<Type> actualType = new ArrayList<>();
        List<String> actualName = new ArrayList<>();
        for (Parameter p : params) {
            actualType.add(p.getType());
            actualName.add(p.getName());
        }
        List<Type> expectedType = new ArrayList<>() {{
            add(java.lang.String.class);
        }};
        List<String> expectedName = new ArrayList<>() {{
            add("arg0");
        }};
        assertThat(actualType, is(expectedType));
        assertThat(actualName, is(expectedName));

        params = cs.getConstructorParameters(testCls.getDeclaredConstructor());
        actualType = new ArrayList<>();
        actualName = new ArrayList<>();
        for (Parameter p : params) {
            actualType.add(p.getType());
            actualName.add(p.getName());
        }
        expectedType = new ArrayList<>();
        expectedName = new ArrayList<>();
        assertThat(actualType, is(expectedType));
        assertThat(actualName, is(expectedName));
    }

    @Test
    void getConstructorsForInteger() {
        List<Constructor> constructors = cs.getConstructors(intCls);
        constructors.forEach(c -> {
            System.out.println(c);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected
                = "public java.lang.Integer(java.lang.String) throws java.lang.NumberFormatException" + ls
                + "public java.lang.Integer(int)" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    void getConstructorsForTestClass() {
        List<Constructor> constructors = cs.getConstructors(testCls);
        constructors.forEach(c -> {
            System.out.println(c);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected
                = "public myClasses.TestClass()" + ls
                + "public myClasses.TestClass(java.lang.String)" + ls;
        assertThat(actual, is(expected));
    }

}