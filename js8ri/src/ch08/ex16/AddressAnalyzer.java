package ch08.ex16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressAnalyzer {
    public static List<Address> createAddresses(final Path path) {
        final Pattern pattern = Pattern.compile(
                "(?<city>[\\p{L} ]+),\\s*(?<state>[A-Z]{2}),\\s*(?<zipCode>[0-9]{5}(-[0-9]{4})?)");

        final List<Address> result = new ArrayList<>();
        try {
            final List<String> lines = Files.readAllLines(path);
            for (final String line : lines) {
                final Matcher matcher = pattern.matcher(line);
                if (!matcher.matches()) {
                    continue;
                }

                result.add(new Address(
                        matcher.group("city"),
                        matcher.group("state"),
                        matcher.group("zipCode")
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
