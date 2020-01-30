package models;

import myClasses.TestClass;
import org.junit.jupiter.api.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ObjectHandlerTest {

    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private final String ls = System.lineSeparator();

    final ObjectHandler oh = new ObjectHandler();

    private final int ORDER_CREATE_OBJECT = 1;
    private final int ORDER_CHANGE_FIELD = 2;

    private final ArrayList<String> ARGS_NAME = new ArrayList<>() {{
        add("Foo Bar");
    }};

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

    @Order(ORDER_CREATE_OBJECT)
    @Test
    public void createObjectの正常系()
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        final ObjectHandler oh = new ObjectHandler();

        final String actual = oh.createObject("myClasses.TestClass").toString();
        final String expected = "id: " + (ORDER_CREATE_OBJECT - 1)
                + ", name: Default, nextId: 1, PUB_INT: 42, PVT_INT: 42";
        assertThat(actual, is(expected));
    }

    @Test
    public void getFieldsの正常系()
            throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        oh.createObject("myClasses.TestClass");
        List<Field> fields = oh.getFields();

        fields.forEach(f -> {
            System.out.println(f);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "public int myClasses.TestClass.id" + ls +
                "public java.lang.String myClasses.TestClass.name" + ls +
                "private static int myClasses.TestClass.nextId" + ls +
                "public final int myClasses.TestClass.PUB_INT" + ls +
                "private final int myClasses.TestClass.PVT_INT" + ls;
        assertThat(actual, is(expected));
    }

    @Order(ORDER_CHANGE_FIELD)
    @Test
    public void changeFieldの正常系()
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");
        oh.changeField("name", "John Smith");
        oh.changeField("nextId", 5);
//        oh.changeField("PUB_INT", 100);
//        oh.changeField("PVT_INT", 200);

        final String actual = oh.getThisObject().toString();
        final String expected = "id: " + (ORDER_CHANGE_FIELD - 1)
                + ", name: John Smith, nextId: 5, PUB_INT: 42, PVT_INT: 42";
        assertThat(actual, is(expected));
    }

    @Test
    public void getMethodsの正常系()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        oh.createObject("myClasses.TestClass");
        List<Method> methods = oh.getMethods();

        methods.forEach(m -> {
            System.out.println(m);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "public java.lang.String myClasses.TestClass.toString()" + ls
                + "public java.lang.String myClasses.TestClass.getName()" + ls
                + "public void myClasses.TestClass.setName(java.lang.String)" + ls
                + "public int myClasses.TestClass.addNumbers(java.lang.Integer,java.lang.Integer)" + ls
                + "public int myClasses.TestClass.addNumbers(int,int,int)" + ls
                + "public int myClasses.TestClass.addNumbers(int,int)" + ls
                + "public int myClasses.TestClass.addPositiveNumbers(java.lang.Integer,java.lang.Integer)" + ls
                + "public int myClasses.TestClass.addPositiveNumbers(int,int)" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    public void getMethodParameterTypesの正常系()
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        oh.createObject("myClasses.TestClass");
        Method method = oh.getThisClass().getDeclaredMethod("addNumbers", java.lang.Integer.class, java.lang.Integer.class);

        List<Type> actual = oh.getMethodParameterTypes(method);
        List<Type> expected = new ArrayList<>() {{
            add(java.lang.Integer.class);
            add(java.lang.Integer.class);
        }};

        assertThat(actual, is(expected));
    }

    @Test
    public void callMethodの正常系()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");
        oh.callMethod("setName", ARGS_NAME);

        final String actual = (String) oh.callMethod("getName");
        final String expected = "Foo Bar";
        assertThat(actual, is(expected));
    }

    @Test
    public void callMethodの正常系_オーバーロードされたメソッド()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");

        final int actual = (int) oh.callMethod("addNumbers", new ArrayList<Integer>() {{
            add(1);
            add(2);
        }});
        final int expected = 3;
        assertThat(actual, is(expected));
    }

    @Test
    public void callMethodの異常系_不適切なパラメータ()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");

        Exception e = assertThrows(RuntimeException.class,
                () -> oh.callMethod("addPositiveNumbers", new ArrayList<Integer>() {{
                    add(-1);
                    add(2);
                }}));
        final String expectMsg = "java.lang.IllegalArgumentException: Arguments should be positive.";
        assertThat(e.getMessage(), is(expectMsg));
    }

    // TODO やったほうがいい？
//    @Test
//    public void callMethodの異常系_パラメータの個数()
//            throws ClassNotFoundException, IllegalAccessException,
//            NoSuchMethodException, InstantiationException, InvocationTargetException {
//        oh.createObject("myClasses.TestClass");
//
//        Exception e = assertThrows(RuntimeException.class,
//                () -> oh.callMethod("addPositiveNumbers", new ArrayList<Integer>()));
//        final String expectMsg = "java.lang.IllegalArgumentException: Arguments should be positive.";
//        assertThat(e.getMessage(), is(expectMsg));
//    }

    @Test
    public void getConstructorsの正常系()
            throws ClassNotFoundException, IllegalAccessException, NoSuchMethodException,
            InstantiationException, InvocationTargetException {
        final ObjectHandler oh = new ObjectHandler();
        oh.createObject("myClasses.TestClass");
        List<Constructor> constructors = oh.getConstructors();

        constructors.forEach(c -> {
            System.out.println(c);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "public myClasses.TestClass()" + ls +
                "public myClasses.TestClass(java.lang.String)" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    public void getConstructorParameterTypesの正常系() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        oh.createObject("myClasses.TestClass");
        Constructor constructor = oh.getThisClass().getDeclaredConstructor(java.lang.String.class);

        List<Type> actual = oh.getConstructorParameterTypes(constructor);
        List<Type> expected = new ArrayList<>() {{
            add(java.lang.String.class);
        }};

        assertThat(actual, is(expected));
    }

    @Test
    public void callConstructorの正常系_引数なし()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");
        oh.callConstructor();

        final String actual = (String) oh.callMethod("getName");
        final String expected = "Default";
        assertThat(actual, is(expected));
    }

    @Test
    public void callConstructorの正常系_引数あり()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");
        oh.callConstructor(ARGS_NAME);

        final String actual = (String) oh.callMethod("getName");
        final String expected = "Foo Bar";
        assertThat(actual, is(expected));
    }

    @Test
    public void callConstructorの異常系()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");

        Exception e = assertThrows(NoSuchMethodException.class,
                () -> oh.callConstructor(new ArrayList<>() {{
                    add(42);
                    add("Foo Bar");
                }}));
        final String expectMsg = "myClasses.TestClass.<init>(java.lang.Integer, java.lang.String)";
        assertThat(e.getMessage(), is(expectMsg));
    }

    @Test
    public void createArrayの正常系()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");

        final TestClass[] actual = (TestClass[]) oh.createArray(TestClass.class, 5);
        final TestClass[] expected = (TestClass[]) Array.newInstance(TestClass.class, 5);
        assertThat(actual, is(expected));
    }

    @Test
    public void createArrayの異常系()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");

        Exception e = assertThrows(IllegalArgumentException.class,
                () -> oh.createArray(TestClass.class, -42));
        final String expectMsg = "size should be positive.";
        assertThat(e.getMessage(), is(expectMsg));
    }

    @Test
    public void setArrayElementの正常系() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        oh.createObject("myClasses.TestClass");
        TestClass[] arr = (TestClass[]) oh.createArray(TestClass.class, 5);
        oh.setArrayElement(2, new TestClass("John Smith"));

        final String actual = ((TestClass) oh.getArrayElement(2)).name;
        final String expected = "John Smith";
        assertThat(actual, is(expected));
    }

    @Test
    public void setArrayElementの異常系()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");

        Exception e = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> oh.setArrayElement(-42, "foo"));
        final String expectMsg = "Array index out of range: -42";
        assertThat(e.getMessage(), is(expectMsg));
    }

    @Test
    public void getArrayElementの異常系_インデックスが負()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");

        Exception e = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> oh.getArrayElement(-42));
        final String expectMsg = "Array index out of range: -42";
        assertThat(e.getMessage(), is(expectMsg));
    }

    @Test
    public void getArrayElementの異常系_インデックスがサイズより大きい()
            throws ClassNotFoundException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");

        oh.createArray(TestClass.class, 5);
        Exception e = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> oh.getArrayElement(42));
        final String expectMsg = "Array index out of range: 42";
        assertThat(e.getMessage(), is(expectMsg));
    }

    @Test
    public void メソッドの〇〇系()
            throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        oh.createObject("myClasses.TestClass");
        oh.changeField("nextId", 5);

        final String actual = oh.getThisObject().toString();
        final String expected = "id: 0, name: Default, nextId: 5, PUB_INT: 42, PVT_INT: 42";
        assertThat(actual, is(expected));
    }

}