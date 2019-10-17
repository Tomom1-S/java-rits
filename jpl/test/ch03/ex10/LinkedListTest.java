package ch03.ex10;

import ch03.ex08.Vehicle;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void getLengthでリストの長さを取得できる() {
        int[] values = {0, 1, 2, 3};
        LinkedList list = new LinkedList();
        for (int i = 0; i <  values.length; i++) {
            list.addLast(values[i]);
        }

        list.addNode(4, 2);
        list.addNode(5, 0);
        System.out.println(list.toString());

        assertThat(list.getLength(), is(values.length + 2));
    }

    @Test
    public void cloneでオブジェクトを複製できる() {
        Vehicle vehicle1 = new Vehicle("Tom");
        vehicle1.setDirection(20);
        vehicle1.setSpeed(100);

        Vehicle vehicle2 = new Vehicle("Bob");
        vehicle2.setDirection(200.5);
        vehicle2.setSpeed(165.5);

        Vehicle vehicle3 = new Vehicle("Molly");
        vehicle3.setDirection(60);
        vehicle3.setSpeed(180.4);

        LinkedList org = new LinkedList();
        org.addLast(vehicle1);
        org.addLast(vehicle2);
        org.addLast(vehicle3);

        LinkedList copy = org.clone();
        copy.addNode(new Vehicle("added"), 2);
        ((Vehicle) copy.getData(1)).setDirection(300);

        assertThat(copy != org, is(true));
        assertThat(copy.getClass() == org.getClass(), is(true));
        assertThat(copy.getData(1), is(org.getData(1)));
    }
}
