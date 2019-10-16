package ch05.ex02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

//@RunWith(Enclosed.class)
public class BankAccountTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    BankAccount myAccount = new BankAccount();
    BankAccount yourAccount = new BankAccount();
    long[] values = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 20, 30};

    @Test
    public void testHistory() {
        for (int i = 0; i < values.length; i++) {
            if (i % 3 == 0) {
                myAccount.deposit(values[i]);
            } else if (i % 3 == 1) {
                myAccount.withdraw(values[i]);
            } else {
                myAccount.transfer(yourAccount, values[i]);
            }
        }
        myAccount.history().show();

        final String expected = "0: transfer 80" + ls
                + "0: deposit 70" + ls
                + "0: withdraw 60" + ls
                + "0: transfer 50" + ls
                + "0: deposit 40" + ls
                + "0: withdraw 30" + ls
                + "0: transfer 20" + ls
                + "0: deposit 10" + ls
                + "0: withdraw 20" + ls
                + "0: transfer 30" + ls;

        assertThat(outContent.toString(), is(expected));
    }

}