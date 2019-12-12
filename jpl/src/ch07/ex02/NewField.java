package ch07.ex02;

public class NewField {
    static char charVal = '1';
    static byte byteVal = 10;
    static short shortVal = 10;
    static int intVal = 10;
    static long longVal = 10;
    static float floatVal = 10;
    static double doubleVal = 10;

    public static void main(String[] args) {
//        charVal = byteVal; // Incompatible type.
//        charVal = shortVal; // Incompatible type.
//        charVal = intVal; // Incompatible type.
//        charVal = longVal; // Incompatible type.
//        charVal = floatVal; // Incompatible type.
//        charVal = doubleVal; // Incompatible type.

//        byteVal = charVal; // Incompatible type.
//        byteVal = shortVal; // Incompatible type.
//        byteVal = intVal; // Incompatible type.
//        byteVal = longVal; // Incompatible type.
//        byteVal = floatVal; // Incompatible type.
//        byteVal = doubleVal; // Incompatible type.

//        shortVal = charVal; // Incompatible type.
        shortVal = byteVal; // OK
//        shortVal = intVal; // Incompatible type.
//        shortVal = longVal; // Incompatible type.
//        shortVal = floatVal; // Incompatible type.
//        shortVal = doubleVal; // Incompatible type.

        intVal = charVal; // OK
        intVal = byteVal; // OK
        intVal = shortVal; // OK
//        intVal = longVal; // Incompatible type.
//        intVal = floatVal; // Incompatible type.
//        intVal = doubleVal; // Incompatible type.

        longVal = charVal; // OK
        longVal = byteVal; // OK
        longVal = shortVal; // OK
        longVal = intVal; // OK
//        longVal = floatVal; // Incompatible type.
//        longVal = doubleVal; // Incompatible type.

        floatVal = charVal; // OK
        floatVal = byteVal; // OK
        floatVal = shortVal; // OK
        floatVal = intVal; // OK
        floatVal = longVal; // OK
//        floatVal = doubleVal; // Incompatible type.

        doubleVal = charVal; // OK
        doubleVal = byteVal; // OK
        doubleVal = shortVal; // OK
        doubleVal = intVal; // OK
        doubleVal = longVal; // OK
        doubleVal = floatVal; // OK
    }
}
