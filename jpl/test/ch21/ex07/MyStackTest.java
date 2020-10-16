package ch21.ex07;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyStackTest {

    final String[] elements = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz"};
    Stack<String> stack;
    MyStack<String> myStack;

    @Before
    public void initializeStacks() {
        stack = new Stack<>();
        myStack = new MyStack<>();
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void 何も入っていなければemptyはtrue() {
        assertTrue(myStack.empty());
    }

    @Test
    public void 何か入っていたらemptyはfalse() {
        myStack.push("foo");
        assertFalse(myStack.empty());
    }

    @Test
    public void peekで最後の要素を取得できる() {
        for (final String e : elements) {
            stack.push(e);
            myStack.push(e);
        }

        assertThat(myStack.peek(), is(stack.peek()));
    }

    @Test
    public void 何も入っていなければpeekで例外() {
        // 参考: https://qiita.com/komiya_atsushi/items/082d6a71e475a613338a
        expectedException.expect(EmptyStackException.class);
        myStack.peek();
    }

    @Test
    public void popで要素を取り出せる() {
        for (final String e : elements) {
            stack.push(e);
            myStack.push(e);
        }

        for (int i = 0; i < elements.length; i++) {
            final String expected = stack.pop();
            final String actual = myStack.pop();
            assertThat(actual, is(expected));
        }
    }

    @Test
    public void pushで要素を入れられる() {
        for (final String e : elements) {
            stack.push(e);
            myStack.push(e);
            assertThat(myStack.peek(), is(stack.peek()));
        }
    }

    @Test
    public void 何も入っていなければpushで例外() {
        // 参考: https://qiita.com/komiya_atsushi/items/082d6a71e475a613338a
        expectedException.expect(EmptyStackException.class);
        myStack.peek();
    }

    @Test
    public void searchでインデックスを取得できる() {
        for (final String e : elements) {
            stack.push(e);
            myStack.push(e);
        }

        for (final String e : elements) {
            final int expected = stack.search(e);
            final int actual = myStack.search(e);
            assertThat(actual, is(expected));
        }
    }

    @Test
    public void searchで要素が見つからない() {
        for (final String e : elements) {
            stack.push(e);
            myStack.push(e);
        }

        final String target = "target";
        final int expected = stack.search(target);
        final int actual = myStack.search(target);
        assertThat(actual, is(expected));
    }

}
