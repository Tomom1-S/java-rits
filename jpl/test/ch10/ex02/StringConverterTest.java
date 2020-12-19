//package ch10.ex02;
//
//import org.junit.Test;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//
//public class StringConverterTest {
//
//    @Test
//    public void convertSpecialCharが特殊文字を変換する() {
//        StringConverter sc = new StringConverter();
//        String actual = sc.convertSpecialChar("\n \t \b \r \f \\ \' \"");
//        String expected = "\n \t \b \r \f \\ \' \"";
//
//        assertThat(actual, is(expected));
//    }
//
//}