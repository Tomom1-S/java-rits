package ch03.ex09;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SampleTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void lexicographicComparatorの正常系() {
        Comparator<B> comparator = Sample.lexicographicComparator(
                new String[] {"bPub", "cPub"}
        );

        final A a = new A();
        final B b = new A();
        assertThat(comparator.compare(a, b), is(a.cPub.compareTo(b.cPub)));
        comparator.compare(a, b);
    }

    @Test
    public void getFieldValueの正常系() {
        final A a = new A();
        final String aPub = (String) Sample.getFieldValue(a, "aPub");
        assertThat(aPub, is("this is a public field"));

        final String aPackPri = (String) Sample.getFieldValue(a, "aPackPri");
        assertThat(aPackPri, is("this is a package-private field"));

        final String aPro = (String) Sample.getFieldValue(a, "aPro");
        assertThat(aPro, is("this is a protected field"));

        final String aPri = (String) Sample.getFieldValue(a, "aPri");
        assertThat(aPri, is("this is a private field"));

        // スーパークラスのフィールドを取得
        final String bPub = (String) Sample.getFieldValue(a, "bPub");
        assertThat(bPub, is("this is a public field"));

        final String bPackPri = (String) Sample.getFieldValue(a, "bPackPri");
        assertThat(bPackPri, is("this is a package-private field"));

        final String bPro = (String) Sample.getFieldValue(a, "bPro");
        assertThat(bPro, is("this is a protected field"));

        final String bPri = (String) Sample.getFieldValue(a, "bPri");
        assertThat(bPri, is("this is a private field"));

        // スーパークラスのスーパークラスのフィールドを取得
        final String cPub = (String) Sample.getFieldValue(a, "cPub");
        assertThat(cPub, is("this is a public field"));

        final String cPackPri = (String) Sample.getFieldValue(a, "cPackPri");
        assertThat(cPackPri, is("this is a package-private field"));

        final String cPro = (String) Sample.getFieldValue(a, "cPro");
        assertThat(cPro, is("this is a protected field"));

        final String cPri = (String) Sample.getFieldValue(a, "cPri");
        assertThat(cPri, is("this is a private field"));
    }

    @Test
    public void getFieldValueの異常系() {
        expectedException.expect(RuntimeException.class);

        final A a = new A();
        Sample.getFieldValue(a, "a");
    }
}