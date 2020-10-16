package ch24.ex03;

import javafx.scene.input.DataFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateConverter {
    private static final List<Integer> formatTypes = new ArrayList<>() {{
        add(DateFormat.SHORT);
        add(DateFormat.MEDIUM);
        add(DateFormat.LONG);
        add(DateFormat.FULL);
    }};

    public static void main(final String[] args) throws ParseException {
        if (args.length == 0) {
            throw new IllegalArgumentException("One or two arguments are needed!");
        }

        final String input = args[0];
        boolean isLenient = true;
        if (args.length > 1) {
            isLenient = Boolean.parseBoolean(args[1]);
        }

        final DateFormat inputFormat = new SimpleDateFormat("yyyy/MM/dd");
        inputFormat.setLenient(isLenient);
        final Date date = inputFormat.parse(input);

        for (final Integer type : formatTypes) {
            final DateFormat outputFormat = DateFormat.getDateInstance(type, Locale.US);
            System.out.println(outputFormat.format(date));
        }
    }
}
