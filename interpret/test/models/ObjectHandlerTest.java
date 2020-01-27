package models;

import org.junit.jupiter.api.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ObjectHandlerTest {

    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private final String ls = System.lineSeparator();

    final ObjectHandler oh = new ObjectHandler();

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

    @Order(1)
    @Test
    public void createObjectの正常系()
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        final ObjectHandler oh = new ObjectHandler();

        final Object actual = oh.createObject("myClasses.testClass");
        final String expected = "id: 0, name: null, nextId: 1, PUB_INT: 42, PVT_INT: 42";
        assertThat(actual.toString(), is(expected));
    }

    @Test
    public void getFieldsの正常系()
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        oh.createObject("myClasses.testClass");
        List<Field> fields = oh.getFields();

        fields.forEach(f -> {
            System.out.println(f);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "public int myClasses.testClass.id" + ls +
                "public java.lang.String myClasses.testClass.name" + ls +
                "private static int myClasses.testClass.nextId" + ls +
                "public final int myClasses.testClass.PUB_INT" + ls +
                "private final int myClasses.testClass.PVT_INT" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    public void changeFieldの正常系()
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.testClass");
        oh.changeField("name", "John Smith");
        oh.changeField("nextId", 5);
//        oh.changeField("PUB_INT", 100);
//        oh.changeField("PVT_INT", 200);

        final String actual = oh.obj.toString();
        final String expected = "id: 0, name: John Smith, nextId: 5, PUB_INT: 42, PVT_INT: 42";
        assertThat(actual, is(expected));
    }

    @Test
    public void getMethodsの正常系()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        oh.createObject("myClasses.testClass");
        List<Method> methods = oh.getMethods();

        methods.forEach(m -> {
            System.out.println(m);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "public java.lang.String myClasses.testClass.toString()" + ls +
                "public void myClasses.testClass.setName(java.lang.String)" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    public void callMethodの正常系()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.testClass");
        oh.callMethod("setName", new ArrayList<String>() {{
            add("Foo Bar");
        }});

        final String actual = (String) oh.callMethod("getName");
        final String expected = "Foo Bar";
        assertThat(actual, is(expected));
    }

    @Test
    public void callMethodの正常系_オーバーロードされたメソッド()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.testClass");

        final int actual = (int) oh.callMethod("addNumbers", new ArrayList<Integer>() {{
            add(1);
            add(2);
        }});
        final int expected = 3;
        assertThat(actual, is(expected));
    }

    @Test
    public void callMethodの異常系()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.testClass");

        Exception e = assertThrows(RuntimeException.class,
                () -> oh.callMethod("addPositiveNumbers", new ArrayList<Integer>() {{
            add(-1);
            add(2);
        }}));
        final String expectMsg = "java.lang.IllegalArgumentException: Arguments should be positive.";
        assertThat(e.getMessage(), is(expectMsg));
    }

    @Test
    public void メソッドの〇〇系()
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        final ObjectHandler oh = new ObjectHandler();
        oh.createObject("myClasses.testClass");
        oh.changeField("nextId", 5);

        final String actual = oh.obj.toString();
        final String expected = "id: 0, name: null, nextId: 5, PUB_INT: 42, PVT_INT: 42";
        assertThat(actual, is(expected));
    }

}