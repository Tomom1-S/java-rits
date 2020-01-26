package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class ObjectHandlerTest {

    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private final String ls = System.lineSeparator();

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
    public void createObjectの正常系() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final ObjectHandler oh = new ObjectHandler();
        final Object actual = oh.createObject("myClasses.testClass");

        final String expected = "id: 0, name: null, nextId: 1, PUB_INT: 42, PVT_INT: 42";
        ;

        assertThat(actual.toString(), is(expected));
    }

    @Test
    public void getFieldsの正常系() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        final ObjectHandler oh = new ObjectHandler();
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
    public void changeFieldの正常系() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        final ObjectHandler oh = new ObjectHandler();
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
    public void メソッドの〇〇系() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        final ObjectHandler oh = new ObjectHandler();
        oh.createObject("myClasses.testClass");
        oh.changeField("nextId", 5);
        String actual = oh.obj.toString();

        final String expected = "id: 0, name: null, nextId: 5, PUB_INT: 42, PVT_INT: 42";

        assertThat(actual, is(expected));
    }

}