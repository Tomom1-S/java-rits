package ch22.ex08;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CSVReaderTest {

    @Test
    public void 正常系() {
        final CSVReader reader = new CSVReader();
        List<String[]> actuals = new ArrayList<>();
        try {
            Readable in = new FileReader(new File("test/ch22/ex08/success1.csv"));
            actuals = reader.readCSVTable(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] expected = new String[][]{
                {"\"Name\"", "\"Age\"", "\"Sex\"", "\"Nationality\""},
                {"\"Taro Yamada\"", "42", "\"Male\"", "\"Japan\""},
                {"\"Jane Smith\"", "12", "\"Female\"", "\"US\""},
                {"\"Mario Rossi\"", "25", "\"Male\"", "\"Italy\""},
                {"\"John Doe\"", "80", "\"Male\"", "\"US\""}
        };

        int i = 0;
        for (String[] actual : actuals) {
            assertThat(actual, is(expected[i++]));
        }
    }

    @Test
    public void 空行があるときの正常系() {
        final CSVReader reader = new CSVReader();
        List<String[]> actuals = new ArrayList<>();
        try {
            Readable in = new FileReader(new File("test/ch22/ex08/success2.csv"));
            actuals = reader.readCSVTable(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] expected = new String[][]{
                {"\"Name\"", "\"Age\"", "\"Sex\"", "\"Nationality\""},
                {"\"Taro Yamada\"", "42", "\"Male\"", "\"Japan\""},
                {"\"Jane Smith\"", "12", "\"Female\"", "\"US\""},
                {"\"Mario Rossi\"", "25", "\"Male\"", "\"Italy\""},
                {"\"John Doe\"", "80", "\"Male\"", "\"US\""}
        };

        int i = 0;
        for (String[] actual : actuals) {
            assertThat(actual, is(expected[i++]));
        }
    }

    @Test
    public void カンマが4つ以上あるときの異常系() {
        final CSVReader reader = new CSVReader();
        try {
            Readable in = new FileReader(new File("test/ch22/ex08/failure.csv"));
            reader.readCSVTable(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            assertThat(e.getMessage(), is("input format error"));
        }
    }

}