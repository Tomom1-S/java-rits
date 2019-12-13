package ch12.ex01;

import ch03.ex08.Vehicle;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void findで指定したデータを持つノードを取得できる() throws ObjectNotFoundException {
        int[] values = {0, 1, 2, 3, 4, 5};
        LinkedList<Integer> list = new LinkedList();
        for (int i = 0; i < values.length; i++) {
            list.addLast(values[i]);
        }

        assertThat(list.find(3), is(list.getNode(3)));
    }

    @Test
    public void findで対象が見つからない場合に例外を出す() throws ObjectNotFoundException {
        int[] values = {0, 1, 2, 3, 4, 5};
        LinkedList<Integer> list = new LinkedList();
        for (int i = 0; i < values.length; i++) {
            list.addLast(values[i]);
        }

        try {
            list.find(7);

        } catch (ObjectNotFoundException expected) {
            assertThat(expected.getMessage(), equalTo("No such data found"));
        }
    }

}