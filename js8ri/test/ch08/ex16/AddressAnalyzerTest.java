package ch08.ex16;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AddressAnalyzerTest {
    @Test
    public void createAddressesでAddressのリストを取得する() {
        final List<Address> results = AddressAnalyzer.createAddresses(
                Paths.get("test/ch08/ex16/myAddresses.txt"));
        final List<Address> expecteds = Arrays.asList(new Address[]{
                new Address("New York", "NY", "10065-7258"),
                new Address("LOS ANGELES", "CA", "90002"),
                new Address("Kapolei", "HI", "96707-4434")
        });

        assertThat(results.size(), is(expecteds.size()));
        for (int i = 0; i < expecteds.size(); i++) {
            final Address actual = results.get(i);
            final Address expected = expecteds.get(i);
            assertThat(actual.city, is(expected.city));
            assertThat(actual.state, is(expected.state));
            assertThat(actual.zipCode, is(expected.zipCode));
        }
    }
}