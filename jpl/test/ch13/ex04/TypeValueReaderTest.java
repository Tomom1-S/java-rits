package ch13.ex04;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TypeValueReaderTest {

    @Test
    public void ArrayListに変換する() {
        String string = "Integer 42\n" +
                "Boolean false\n" +
                "Double 1.23";

        TypeValueReader reader = new TypeValueReader();
        List<Object> actual = reader.convertTypeValue(string);

        ArrayList<Object> expected = new ArrayList<>();
        expected.add(42);
        expected.add(false);
        expected.add(1.23);

        assertThat(actual, is(expected));
    }

    @Test
    public void 行の形式が不適なときは例外を投げる() {
        String string = "Integer 42 42\n" +
                "Boolean false\n" +
                "Double 1.23";

        TypeValueReader reader = new TypeValueReader();

        try {
            reader.convertTypeValue(string);

        } catch (Exception actual) {
            assertThat(actual.getMessage(), equalTo("Each line should be 'type value'!"));
        }
    }

    @Test
    public void typeが不適なときは例外を投げる() {
        String string = "int 42\n" +
                "Boolean false\n" +
                "Double 1.23";

        TypeValueReader reader = new TypeValueReader();

        try {
            reader.convertTypeValue(string);

        } catch (Exception actual) {
            assertThat(actual.getMessage(), equalTo("int is invalid!"));
        }
    }

}