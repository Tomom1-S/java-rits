package ch13.ex03;

import ch13.ex02.StringCounter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ImprovedDelimitedStringTest {

    @Test
    public void 二つの区切り文字が異なるときに文字列を分割して配列として返す() {
        String str = "私が「おはよう」と言ったら、彼は「やあ」と返した。";
        ImprovedDelimitedString dc = new ImprovedDelimitedString();
        String[] actual = dc.delimitedStrings(str, '「', '」');

        assertThat(actual, equalTo(new String[] {"おはよう", "やあ"}));
    }

    @Test
    public void 二つの区切り文字が同じときに文字列を分割して配列として返す() {
        String str = "I said to him, 'hello,' and he said, 'hi.'";
        ImprovedDelimitedString dc = new ImprovedDelimitedString();
        String[] actual = dc.delimitedStrings(str, '\'', '\'');

        assertThat(actual, equalTo(new String[] {"hello,", " and he said, ", "hi."}));
    }

    @Test
    public void 区切り文字が見つからないときに空配列を返す() {
        String str = "I said to him, 'hello,' and he said, 'hi.'";
        ImprovedDelimitedString dc = new ImprovedDelimitedString();
        String[] actual = dc.delimitedStrings(str, '「', '」');

        assertThat(actual, equalTo(new String[0]));
    }

}