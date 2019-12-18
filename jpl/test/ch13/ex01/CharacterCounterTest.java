package ch13.ex01;

import ch12.ex01.ObjectNotFoundException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CharacterCounterTest {

    @Test
    public void 文字の出現回数を返す() throws Exception {
        String str = "Happy Birthday!";
        CharacterCounter cc = new CharacterCounter(str);
        assertThat(cc.countCharacter('y'), is(2));
    }

    @Test
    public void 見つからないときは例外を出す() throws Exception {
        String str = "Happy Birthday!";
        CharacterCounter cc = new CharacterCounter(str);

        try {
            cc.countCharacter('e');

        } catch (Exception actual) {
            assertThat(actual.getMessage(), equalTo("e is not found in Happy Birthday!"));
        }
    }

}