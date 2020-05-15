package models;

import myClasses.TestClass;
import org.junit.jupiter.api.*;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MyObjectTest {

    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private final String ls = System.lineSeparator();

    private static final int DEFAULT_INT = 42;
    private final Class intCls = Class.forName("java.lang.Integer");
    private final Class strCls = Class.forName("java.lang.String");
    private final Class testCls = Class.forName("myClasses.TestClass");
    private final MyObject intObj = new MyObject(intCls, DEFAULT_INT);
    private final MyObject testObj = new MyObject(testCls, new TestClass());

    private static final int ORDER_CALL_METHOD_FOR_INTEGER = 0;
    private static final int ORDER_GET_FIELD_FOR_INTEGER = 1;
    private static final int ORDER_GET_FIELD_FOR_TEST = 2;
    private static final int ORDER_CHANGE_FIELD_FOR_INTEGER = 3;
    private static final int ORDER_CHANGE_FIELD_FOR_TEST = 4;

    private final String ARGS_NAME = "Foo Bar";

    MyObjectTest() throws ClassNotFoundException {
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
    @Order(ORDER_CALL_METHOD_FOR_INTEGER)
    void callMethodForInteger()
            throws NoSuchMethodException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException {

        Method method = intCls.getMethod("toString");
        final String actualStr = (String) intObj.callMethod(method);
        final String expectedStr = "42";
        assertThat(actualStr, is(expectedStr));

        // multiple args
        method = intCls.getMethod("min", int.class, int.class);
        final int actualMin = (int) intObj.callMethod(method, -100, 200);
        final int expectedMin = -100;
        assertThat(actualMin, is(expectedMin));
    }

    @Test
    void callMethodForTestClass()
            throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException,
            ClassNotFoundException, InvocationTargetException, InstantiationException {
        Method method = testCls.getMethod("setName", strCls);
        testObj.callMethod(method, ARGS_NAME);
        final String actualName = (String) testObj.getField("name");
        final String expectedName = "Foo Bar";
        assertThat(actualName, is(expectedName));

        // multiple args
        method = testCls.getMethod("addNumbers", int.class, int.class);
        final int actualSum = (int) testObj.callMethod(method, 1, 2);
        final int expectedSum = 3;
        assertThat(actualSum, is(expectedSum));
    }

    @Test
    @Order(ORDER_CHANGE_FIELD_FOR_INTEGER)
    void changeFieldForInteger()
            throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        final MyObject obj = new MyObject(Class.forName("java.lang.Integer"), DEFAULT_INT);

        obj.changeField("value", 100);
        final int actualValue = (int) obj.getField("value");
        assertThat(actualValue, is(100));
    }

    @Test
    @Order(ORDER_CHANGE_FIELD_FOR_TEST)
    void changeFieldForTestClass()
            throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException,
            NoSuchMethodException, InstantiationException, InvocationTargetException {
        final MyObject obj = new MyObject(Class.forName("myClasses.TestClass"), new TestClass());

        obj.changeField("name", "John Smith");
        final String actualName = (String) obj.getField("name");
        assertThat(actualName, is("John Smith"));

        obj.changeField("nextId", 42);
        final int actualNextId = (int) obj.getField("nextId");
        assertThat(actualNextId, is(42));

        obj.changeField("PUB_INT", 100);
        final int actualPubInt = (int) obj.getField("PUB_INT");
        assertThat(actualPubInt, is(100));

        obj.changeField("PVT_INT", 200);
        final int actualPrvInt = (int) obj.getField("PVT_INT");
        assertThat(actualPrvInt, is(200));
    }

    @Test
    @Order(ORDER_GET_FIELD_FOR_INTEGER)
    public void getFieldForInteger()
            throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        final MyObject obj = new MyObject(Class.forName("java.lang.Integer"), DEFAULT_INT);

        final int actualValue = (int) obj.getField("value");
        final int expectedValue = DEFAULT_INT;
        assertThat(actualValue, is(expectedValue));

        final int actualMax = (int) obj.getField("MAX_VALUE");
        final int expectedMax = 2147483647;
        assertThat(actualMax, is(expectedMax));
    }

    @Test
    @Order(ORDER_GET_FIELD_FOR_TEST)
    public void getFieldForTestClass()
            throws IllegalAccessException, NoSuchFieldException {
        final String actualName = (String) testObj.getField("name");
        final String expectedName = "Default";
        assertThat(actualName, is(expectedName));

        // private field
        final int actualPri = (int) testObj.getField("PVT_INT");
        final int expectedPri = DEFAULT_INT;
        assertThat(actualPri, is(expectedPri));

        // static field
        final int actualSta = (int) testObj.getField("PVT_INT");
        final int expectedSta = DEFAULT_INT;
        assertThat(actualSta, is(expectedSta));
    }

    @Test
    void getFieldsForInteger() {
        List<Field> fields = intObj.getFields();
        fields.forEach(f -> {
            System.out.println(f);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "public static final int java.lang.Integer.MIN_VALUE" + ls
                + "public static final int java.lang.Integer.MAX_VALUE" + ls
                + "public static final java.lang.Class java.lang.Integer.TYPE" + ls
                + "static final char[] java.lang.Integer.digits" + ls
                + "static final byte[] java.lang.Integer.DigitTens" + ls
                + "static final byte[] java.lang.Integer.DigitOnes" + ls
                + "static final int[] java.lang.Integer.sizeTable" + ls
                + "private final int java.lang.Integer.value" + ls
                + "public static final int java.lang.Integer.SIZE" + ls
                + "public static final int java.lang.Integer.BYTES" + ls
                + "private static final long java.lang.Integer.serialVersionUID" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    void getFieldsForTestClass() {
        List<Field> fields = testObj.getFields();
        fields.forEach(f -> {
            System.out.println(f);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "public int myClasses.TestClass.id" + ls
                + "public java.lang.String myClasses.TestClass.name" + ls
                + "private static int myClasses.TestClass.nextId" + ls
                + "public final int myClasses.TestClass.PUB_INT" + ls
                + "private final int myClasses.TestClass.PVT_INT" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    public void getMethodParametersForInteger() throws NoSuchMethodException {
        List<Parameter> params = intObj.getMethodParameters(
                intCls.getDeclaredMethod(
                        "getInteger", java.lang.String.class, java.lang.Integer.class
                ));
        List<String> actualName = new ArrayList<>();
        for (Parameter p : params) {
            actualName.add(p.getName());
        }
        List<String> expectedName = new ArrayList<>() {{
            add("arg0");
            add("arg1");
        }};
        assertThat(actualName, is(expectedName));
    }

    @Test
    public void getMethodParametersForTestClass() throws NoSuchMethodException {
        List<Parameter> params = testObj.getMethodParameters(
                testCls.getDeclaredMethod(
                        "addNumbers", int.class, int.class
                ));
        List<String> actualName = new ArrayList<>();
        for (Parameter p : params) {
            actualName.add(p.getName());
        }
        List<String> expectedName = new ArrayList<>() {{
            add("val1");
            add("val2");
        }};
        assertThat(actualName, is(expectedName));
    }

    @Test
    void getMethodsForInteger() {
        List<Method> methods = intObj.getMethods();
        methods.forEach(m -> {
            System.out.println(m);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final String expected = "public static int java.lang.Integer.numberOfLeadingZeros(int)" + ls
                + "public static int java.lang.Integer.numberOfTrailingZeros(int)" + ls
                + "public static int java.lang.Integer.bitCount(int)" + ls
                + "public boolean java.lang.Integer.equals(java.lang.Object)" + ls
                + "public static java.lang.String java.lang.Integer.toString(int)" + ls
                + "public static java.lang.String java.lang.Integer.toString(int,int)" + ls
                + "public java.lang.String java.lang.Integer.toString()" + ls
                + "public static int java.lang.Integer.hashCode(int)" + ls
                + "public int java.lang.Integer.hashCode()" + ls
                + "public static int java.lang.Integer.min(int,int)" + ls
                + "public static int java.lang.Integer.max(int,int)" + ls
                + "public static int java.lang.Integer.reverseBytes(int)" + ls
                + "static int java.lang.Integer.getChars(int,int,byte[])" + ls
                + "public int java.lang.Integer.compareTo(java.lang.Object)" + ls
                + "public int java.lang.Integer.compareTo(java.lang.Integer)" + ls
                + "public byte java.lang.Integer.byteValue()" + ls
                + "public short java.lang.Integer.shortValue()" + ls
                + "public int java.lang.Integer.intValue()" + ls
                + "public long java.lang.Integer.longValue()" + ls
                + "public float java.lang.Integer.floatValue()" + ls
                + "public double java.lang.Integer.doubleValue()" + ls
                + "public static java.lang.Integer java.lang.Integer.valueOf(int)" + ls
                + "public static java.lang.Integer java.lang.Integer.valueOf(java.lang.String,int) throws java.lang.NumberFormatException" + ls
                + "public static java.lang.Integer java.lang.Integer.valueOf(java.lang.String) throws java.lang.NumberFormatException" + ls
                + "public static java.lang.String java.lang.Integer.toHexString(int)" + ls
                + "public static java.lang.Integer java.lang.Integer.decode(java.lang.String) throws java.lang.NumberFormatException" + ls
                + "public static int java.lang.Integer.compare(int,int)" + ls
                + "public static int java.lang.Integer.reverse(int)" + ls
                + "static int java.lang.Integer.stringSize(int)" + ls
                + "public static long java.lang.Integer.toUnsignedLong(int)" + ls
                + "public static int java.lang.Integer.parseInt(java.lang.String) throws java.lang.NumberFormatException" + ls
                + "public static int java.lang.Integer.parseInt(java.lang.String,int) throws java.lang.NumberFormatException" + ls
                + "public static int java.lang.Integer.parseInt(java.lang.CharSequence,int,int,int) throws java.lang.NumberFormatException" + ls
                + "public static int java.lang.Integer.sum(int,int)" + ls
                + "public static int java.lang.Integer.compareUnsigned(int,int)" + ls
                + "private static java.lang.String java.lang.Integer.toStringUTF16(int,int)" + ls
                + "public static java.lang.String java.lang.Integer.toUnsignedString(int,int)" + ls
                + "public static java.lang.String java.lang.Integer.toUnsignedString(int)" + ls
                + "public static java.lang.String java.lang.Integer.toOctalString(int)" + ls
                + "public static java.lang.String java.lang.Integer.toBinaryString(int)" + ls
                + "private static java.lang.String java.lang.Integer.toUnsignedString0(int,int)" + ls
                + "static void java.lang.Integer.formatUnsignedInt(int,int,char[],int,int)" + ls
                + "static void java.lang.Integer.formatUnsignedInt(int,int,byte[],int,int)" + ls
                + "private static void java.lang.Integer.formatUnsignedIntUTF16(int,int,byte[],int,int)" + ls
                + "public static int java.lang.Integer.parseUnsignedInt(java.lang.String,int) throws java.lang.NumberFormatException" + ls
                + "public static int java.lang.Integer.parseUnsignedInt(java.lang.CharSequence,int,int,int) throws java.lang.NumberFormatException" + ls
                + "public static int java.lang.Integer.parseUnsignedInt(java.lang.String) throws java.lang.NumberFormatException" + ls
                + "public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String,java.lang.Integer)" + ls
                + "public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String,int)" + ls
                + "public static java.lang.Integer java.lang.Integer.getInteger(java.lang.String)" + ls
                + "public static int java.lang.Integer.divideUnsigned(int,int)" + ls
                + "public static int java.lang.Integer.remainderUnsigned(int,int)" + ls
                + "public static int java.lang.Integer.highestOneBit(int)" + ls
                + "public static int java.lang.Integer.lowestOneBit(int)" + ls
                + "public static int java.lang.Integer.rotateLeft(int,int)" + ls
                + "public static int java.lang.Integer.rotateRight(int,int)" + ls
                + "public static int java.lang.Integer.signum(int)" + ls;
        assertThat(actual, is(expected));
    }

    @Test
    void getMethodsForTestClass() {
        List<Method> methods = testObj.getMethods();
        methods.forEach(m -> {
            System.out.println(m);
        });

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        final List<String> expectedList = new ArrayList<>() {{
            add("public java.lang.String myClasses.TestClass.toString()");
            add("public java.lang.String myClasses.TestClass.getName()");
            add("public void myClasses.TestClass.setName(java.lang.String)");
            add("public int myClasses.TestClass.addNumbers(java.lang.Integer,java.lang.Integer)");
            add("public int myClasses.TestClass.addNumbers(int,int)");
            add("public int myClasses.TestClass.addNumbers(int,int,int)");
            add("public int myClasses.TestClass.addPositiveNumbers(java.lang.Integer,java.lang.Integer)");
            add("public int myClasses.TestClass.addPositiveNumbers(int,int)");
            add("private int myClasses.TestClass.multiplyNumbers(int,int)");
            add("private int myClasses.TestClass.multiplyNumbers(java.lang.Integer,java.lang.Integer)");
        }};
        for (String expected : expectedList) {
            assertThat(actual, containsString(expected));
        }
    }

}